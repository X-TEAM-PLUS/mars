package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.AccountBalanceManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 获取用户账户信息
 */
@Component
public class GetAccountInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.getAccountInfo";

    @Resource
    private AccountBalanceManager accountBalanceManager;

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
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());
            AccountBalance queryWhere = new AccountBalance();
            queryWhere.setUserId(userId);
            queryWhere.setStatus(0);
            AccountBalance accountBalance = accountBalanceManager.get(queryWhere);
            if (accountBalance == null) {
                accountBalance = new AccountBalance();
                accountBalance.setBalanceAmount(new BigDecimal(0));
                accountBalance.setStatus(0);
                accountBalance.setUserId(userId);
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(accountBalance));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
