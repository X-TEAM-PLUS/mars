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
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.SubmitUserDetectionReqVO;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.type.HealthCheckRecordTypeEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 上传检测结果
 */
@Component
public class SubmitUserDetectionServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.submitUserDetection";

    private DfsClient dfsClient = new DfsClientImpl();

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

        long beginDate = System.currentTimeMillis();
        this.logInfo(METHOD_NAME + " request  [" + httpRequestBody.toString() + "]");
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            SubmitUserDetectionReqVO submitUserDetectionReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), SubmitUserDetectionReqVO.class);
            LinkedMultiValueMap<String, MultipartFile> linkedMultiValueMap = httpRequestBody.getMultipartFileHashMap();
            if (linkedMultiValueMap.isEmpty() || StringUtils.isEmpty(httpRequestBody.getUserId())
                    || StringUtils.isEmpty(submitUserDetectionReqVO.getCheckReport())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            HealthCheckRecord healthCheckRecord = new HealthCheckRecord();
            healthCheckRecord.setUserId(new BigDecimal(httpRequestBody.getUserId()));
            Iterator<String> keys = linkedMultiValueMap.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                List<MultipartFile> multipartFiles = linkedMultiValueMap.get(key);
                if (multipartFiles.isEmpty()) {
                    httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                    return httpResponseBody;
                }
                for (MultipartFile multipartFile : multipartFiles) {
                    ImageFileInfo imageFileInfo = dfsClient.uploadImageFile(multipartFile);
                    healthCheckRecord.setCheckReport(imageFileInfo.getStoreAddress());
                }
            }
            healthCheckRecord.setCheckStatus(HealthCheckRecordTypeEnum.UNDETECTED.getCode());
            healthCheckRecord.setCreated(new Date());
            healthCheckRecord.setUploadTime(new Date());
            healthCheckRecord.setCheckResult(submitUserDetectionReqVO.getCheckReport());
            int count = healthCheckRecordManager.insert(healthCheckRecord);
            if (count <= 0) {
                logInfo("上传检测结果失败，插入数据库为0 userId[" + httpRequestBody.getUserId() + "]");
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
                return httpResponseBody;
            }
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
