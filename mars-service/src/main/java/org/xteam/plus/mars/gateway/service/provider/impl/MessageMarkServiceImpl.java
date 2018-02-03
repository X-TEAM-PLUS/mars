package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.MessageManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 消息移除
 */
@Component
public class MessageMarkServiceImpl implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.message.mark";

    @Resource
    private MessageManager messageManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (StringUtils.isNotEmpty(httpRequestBody.getUserId()) && StringUtils.isNotEmpty(httpRequestBody.getBizContent())) {
            Map<String, Object> params = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
            if (params != null && params.containsKey("messageId")) {
                int rowCount = messageManager.update(
                        new Message()
                                .setStatus(1)
                                .setUpdated(new Date())
                                .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId().toString())))
                                .setMessageId(BigDecimal.valueOf(Long.valueOf(params.get("messageId").toString())))
                );
                if (rowCount > 0) {
                    return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
                } else {
                    return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
                }
            } else {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
        } else {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
    }

}
