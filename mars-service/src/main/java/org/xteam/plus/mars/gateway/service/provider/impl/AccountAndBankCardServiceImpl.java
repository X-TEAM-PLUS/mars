package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.AccountBalanceManager;
import org.xteam.plus.mars.manager.BankCardManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户账户信息
 */
@Component
public class AccountAndBankCardServiceImpl implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.user.accountandbankcard.get";

    @Resource
    private AccountBalanceManager accountBalanceManager;
    @Resource
    private BankCardManager bankCardManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        //用户ID
        BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());

        // 账户余额
        AccountBalance accountBalance = accountBalanceManager.get(
                new AccountBalance()
                        .setUserId(userId)
                        .setStatus(0)
        );
        if (accountBalance == null) {
            accountBalance = new AccountBalance();
            accountBalance.setBalanceAmount(new BigDecimal(0));
            accountBalance.setStatus(0);
            accountBalance.setUserId(new BigDecimal(httpRequestBody.getUserId()));
        }

        //银行卡信息
        List<BankCard> list = bankCardManager.query(new BankCard().setUserId(userId));
        if(list==null || list.size()==0){
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
        //组装返回信息
        Map<String,Object> result = new HashMap<>();
        result.put("accountBalance",accountBalance);
        result.put("bankCard",list.get(0));

        //返回
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(result));

    }
}
