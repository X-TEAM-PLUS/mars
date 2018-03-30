package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.fdfs.DfsClient;
import org.xteam.plus.mars.fdfs.ImageFileInfo;
import org.xteam.plus.mars.fdfs.impl.DfsClientImpl;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.type.HealthCheckRecordTypeEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 上传检测结果
 */
@Component
public class SubmitUserDetectionServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.submitUserDetection";

    private DfsClient dfsClient = new DfsClientImpl();

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

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
                //healthCheckRecord.setCheckReport("images/pic.png");

            }
        }
        healthCheckRecord.setCheckTime(new Date() );
        healthCheckRecord.setCheckStatus(HealthCheckRecordTypeEnum.UNDETECTED.getCode());
        healthCheckRecord.setCreated(new Date());
        healthCheckRecord.setUploadTime(new Date());
        healthCheckRecord.setSelfCheckResult(Integer.valueOf(parmas.get("selfCheckResult").toString()));
        //TODO 根据自测结果，匹配对应的检查结果
//        healthCheckRecord.setCheckResult("");
        int count = healthCheckRecordManager.insert(healthCheckRecord);
        if (count <= 0) {
            logInfo("上传检测结果失败，插入数据库为0 userId[" + httpRequestBody.getUserId() + "]");
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
    }
}
