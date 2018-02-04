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
public class GetUserInfoServiceImpl  implements GateWayService {

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
        if (httpRequestBody.getUserId() == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId()))));
        if (userInfo == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        Map<String, Object> bizContentMap = JsonUtils.transform(userInfo, HashMap.class);
        //加密身份证号
        bizContentMap.put("idNumber",userInfo.getIdNumber().replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));

        //查询用户未读消息数
        bizContentMap.put("newMessageCount", messageManager.queryCount(
                new Message()
                        .setStatus(0)
                        .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId())))
                )
        );
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));

    }
}
