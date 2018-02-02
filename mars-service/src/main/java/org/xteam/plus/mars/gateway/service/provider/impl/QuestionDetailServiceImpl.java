package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.QuestionInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.QuestionInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 问题详情
 */
@Component
public class QuestionDetailServiceImpl implements GateWayService {
    private final String METHOD_NAME = "com.zhaoanyun.api.gateway.global.question.detail";

    @Resource
    private  QuestionInfoManager questionInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (StringUtils.isNotEmpty(httpRequestBody.getBizContent())){
                Map<String,Object> params = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
                if(params !=null && params.containsKey("questionId")){
                    String bizContent = JsonUtils.toJSON(questionInfoManager.get(new QuestionInfo().setQuestionId(BigDecimal.valueOf(Long.valueOf(params.get("questionId").toString())))));
                    return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(bizContent);
                }else{
                    return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                }
        }else{
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
    }
}
