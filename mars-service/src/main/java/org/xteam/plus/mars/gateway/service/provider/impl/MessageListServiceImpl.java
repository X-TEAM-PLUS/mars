package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.domain.QuestionInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.MessageManager;
import org.xteam.plus.mars.manager.QuestionInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 消息列表
 */
@Component
public class MessageListServiceImpl implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.message.list";

    @Resource
    private MessageManager messageManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (httpRequestBody.getUserId() == null) {
            new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        Map<String,Object>  bizContentMap = new HashMap<>();
        bizContentMap.put("list",messageManager.query(
                new Message()
                        .setLimit(Integer.MAX_VALUE)
                        .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId())))
                )
        );
        String bizContent = JsonUtils.toJSON(bizContentMap);
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(bizContent);
    }
}
