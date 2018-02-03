package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.MessageManager;
import org.xteam.plus.mars.manager.UserInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户信息
 */
@Component
public class GetUserInfoServiceImpl extends Logging implements GateWayService {

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private MessageManager messageManager;

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.getUserInfo";

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
            if (httpRequestBody.getUserId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId()))));
            if (userInfo == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }

            Map<String,Object> bizContentMap = JsonUtils.transform(userInfo,HashMap.class);

            //查询用户未读消息数
            bizContentMap.put("newMessageCount",messageManager.queryCount(
                    new Message()
                            .setStatus(0)
                            .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId())))
                    )
            );
            httpResponseBody.setBizContent(JsonUtils.toJSON(bizContentMap));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
