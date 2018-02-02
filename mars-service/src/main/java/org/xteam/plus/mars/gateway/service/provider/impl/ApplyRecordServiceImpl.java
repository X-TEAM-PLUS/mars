package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.ApplyRecordReqVO;
import org.xteam.plus.mars.manager.AccountBalanceManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.manager.WithdrawRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 申请提现接口
 */
@Component
public class ApplyRecordServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.applyRecord";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private AccountBalanceManager accountBalanceManager;

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
            ApplyRecordReqVO applyRecordReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), ApplyRecordReqVO.class);
            if (applyRecordReqVO == null || httpRequestBody.getUserId() == null
                    || StringUtils.isEmpty(applyRecordReqVO.getBankAccountNo())
                    || StringUtils.isEmpty(applyRecordReqVO.getBankAccountName())
                    || applyRecordReqVO.getPayWay() == null
                    || applyRecordReqVO.getAmount() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            if (applyRecordReqVO.getAmount().compareTo(new BigDecimal(0)) == -1) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.CARD_ALREAD_BIND);
                return httpResponseBody;
            }
            UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(new BigDecimal(httpRequestBody.getUserId())));
            if (userInfo == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
                return httpResponseBody;
            }
            AccountBalance accountBalance = accountBalanceManager.get(new AccountBalance().setUserId(userInfo.getUserId()));
            if (accountBalance == null) {
                accountBalance = new AccountBalance();
                accountBalance.setUserId(userInfo.getUserId());
                accountBalance.setBalanceAmount(new BigDecimal(0));
            }
            if (accountBalance.getBalanceAmount().compareTo(applyRecordReqVO.getAmount()) == -1) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.AMOUNT_NOT_ENOUGH);
                return httpResponseBody;
            }
            WithdrawRecord withdrawRecord = new WithdrawRecord();
            withdrawRecord.setCreated(new Date());
            withdrawRecord.setUserId(userInfo.getUserId());
            withdrawRecord.setAmount(applyRecordReqVO.getAmount());
            withdrawRecord.setBankAccountName(applyRecordReqVO.getBankAccountName());
            withdrawRecord.setBankAccountNo(applyRecordReqVO.getBankAccountNo());
            withdrawRecord.setPayWay(applyRecordReqVO.getPayWay());
            withdrawRecord.setStatus(0);
            withdrawRecord.setApplyTime(new Date());

            int count = withdrawRecordManager.insert(withdrawRecord);
            if (count <= 0) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
                return httpResponseBody;
            }
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
