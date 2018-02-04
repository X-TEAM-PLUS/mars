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
public class GetAccountInfoServiceImpl implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.user.getAccountInfo";

    @Resource
    private AccountBalanceManager accountBalanceManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            AccountBalance accountBalance = accountBalanceManager.get(
                    new AccountBalance()
                    .setUserId(new BigDecimal(httpRequestBody.getUserId()))
                    .setStatus(0)
            );
            if (accountBalance == null) {
                accountBalance = new AccountBalance();
                accountBalance.setBalanceAmount(new BigDecimal(0));
                accountBalance.setStatus(0);
                accountBalance.setUserId(new BigDecimal(httpRequestBody.getUserId()));
            }
            return   new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(accountBalance));

    }
}
