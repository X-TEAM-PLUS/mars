package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.QuestionInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.QuestionInfoManager;

import javax.annotation.Resource;


/**
 * 常见问题列表
 */
@Component
public class QuestionListServiceImpl implements GateWayService {
    private final String METHOD_NAME = "com.zhaoanyun.api.global.question.list";

    @Resource
    private  QuestionInfoManager questionInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        String bizContent = JsonUtils.toJSON(questionInfoManager.query(new QuestionInfo().setLimit(Integer.MAX_VALUE)));
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(bizContent);
    }
}
