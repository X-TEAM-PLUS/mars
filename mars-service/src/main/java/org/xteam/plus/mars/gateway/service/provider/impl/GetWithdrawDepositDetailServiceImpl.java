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
public class GetWithdrawDepositDetailServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.withdrawDeposit.get";

    @Resource
    private WithdrawRecordManager withdrawRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

            RecordReqVO recordReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), RecordReqVO.class);
            if (recordReqVO == null || recordReqVO.getRecordId() == null) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            WithdrawRecord withdrawRecord = withdrawRecordManager.get(new WithdrawRecord().setId(recordReqVO.getRecordId()));
            if (withdrawRecord == null) {
                return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
            }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(withdrawRecord));
    }
}
