package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.fdfs.DfsClient;
import org.xteam.plus.mars.fdfs.ImageFileInfo;
import org.xteam.plus.mars.fdfs.impl.DfsClientImpl;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.type.HealthCheckRecordTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 上传检测结果
 */
@Component
public class SubmitUserDetectionServiceImpl extends Logging implements GateWayService {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("heartcheck");

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.submitUserDetection";

    private DfsClient dfsClient = new DfsClientImpl();

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Resource
    private UserHealthCardManager userHealthCardManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        //获取业务参数
        Map<String, String> parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
        //获取上传的文件
        LinkedMultiValueMap<String, MultipartFile> linkedMultiValueMap = httpRequestBody.getMultipartFileHashMap();
        if(!parmas.containsKey("selfCheckResult")
                || linkedMultiValueMap.isEmpty()){
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        HealthCheckRecord healthCheckRecord = new HealthCheckRecord();
        healthCheckRecord.setUserId(new BigDecimal(httpRequestBody.getUserId()));
        Iterator<String> keys = linkedMultiValueMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            List<MultipartFile> multipartFiles = linkedMultiValueMap.get(key);
            if (multipartFiles.isEmpty()) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            for (MultipartFile multipartFile : multipartFiles) {
                if(multipartFile.isEmpty()){
                    return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                }
                ImageFileInfo imageFileInfo = dfsClient.uploadImageFile(multipartFile);
                if(imageFileInfo!=null &&  imageFileInfo.getStoreAddress()!=null) {
                    healthCheckRecord.setCheckReport(imageFileInfo.getStoreAddress());
                }else{
                    return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                }
            }
        }

        //查询当前用户卡号的次数
        BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());
        UserHealthCard userHealthCard = userHealthCardManager.queryForProductByActive(
                new UserHealthCard().setActivateUserId(userId)
        );
        //无有效卡
        if(userHealthCard==null){
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }
        //检测次数已满
        if(userHealthCard.getSendTotalCount()<=userHealthCard.getSendCount()){
            return new HttpResponseBody(GlobalErrorMessage.CHECK_TIMES_DEPLETE);
        }

        //保存检测记录
        healthCheckRecord.setCheckTime(new Date() );
        healthCheckRecord.setCreated(new Date());
        healthCheckRecord.setCheckStatus(HealthCheckRecordTypeEnum.UNDETECTED.getCode());
        healthCheckRecord.setUploadTime(new Date());
        healthCheckRecord.setCardNo(userHealthCard.getCardNo());
        healthCheckRecord.setSelfCheckResult(Integer.valueOf(parmas.get("selfCheckResult").toString()));
        //根据自测结果，匹配对应的检查结果
        if(StringUtils.isNotEmpty(resourceBundle.getString(parmas.get("selfCheckResult")))){
            healthCheckRecord.setCheckResult(resourceBundle.getString(parmas.get("selfCheckResult")));
            healthCheckRecord.setCheckStatus(HealthCheckRecordTypeEnum.DETECTED.getCode());
        }
        int count = healthCheckRecordManager.insert(healthCheckRecord);
        if (count <= 0) {
            logInfo("上传检测结果失败，插入数据库为0 userId[" + httpRequestBody.getUserId() + "]");
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
    }
}
