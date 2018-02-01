package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.manager.WithdrawRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 获取提现记录列表
 */
@Component
public class GetRecordListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.getRecordList";

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
            PageInfoReqVO pageInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), PageInfoReqVO.class);
            if (pageInfoReqVO == null || StringUtils.isEmpty(httpRequestBody.getUserId())
                    || pageInfoReqVO.getLimit() == null || pageInfoReqVO.getStart() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            List<WithdrawRecord> withdrawRecordList = withdrawRecordManager.query(new WithdrawRecord().setUserId(new BigDecimal(httpRequestBody.getUserId())));

            httpResponseBody.setBizContent(JsonUtils.toJSON(withdrawRecordList));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
