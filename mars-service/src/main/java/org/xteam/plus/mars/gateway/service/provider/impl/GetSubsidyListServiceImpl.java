package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserInfoReqVO;
import org.xteam.plus.mars.manager.AccountBalanceManager;

import javax.annotation.Resource;

/**
 * 用户账户余额查询接口
 */
@Component
public class GetSubsidyListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.getSubsidyList";

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
            UserInfoReqVO userInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserInfoReqVO.class);
            if (userInfoReqVO.getUserId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            AccountBalance accountBalance = accountBalanceManager.get(new AccountBalance().setUserId(userInfoReqVO.getUserId()));
            if (accountBalance == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
                return httpResponseBody;
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
