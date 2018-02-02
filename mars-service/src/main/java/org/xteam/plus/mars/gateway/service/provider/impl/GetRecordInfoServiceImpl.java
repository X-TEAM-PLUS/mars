package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.RecordReqVO;
import org.xteam.plus.mars.manager.WithdrawRecordManager;

import javax.annotation.Resource;

/**
 * 获取提现记录详情
 */
@Component
public class GetRecordInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.getRecordInfo";

    @Resource
    private WithdrawRecordManager withdrawRecordManager;

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
            RecordReqVO recordReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), RecordReqVO.class);
            if (recordReqVO == null || recordReqVO.getRecordId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            WithdrawRecord withdrawRecord = withdrawRecordManager.get(new WithdrawRecord().setId(recordReqVO.getRecordId()));
            if (withdrawRecord == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
                return httpResponseBody;
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(withdrawRecord));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
