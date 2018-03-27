package org.xteam.plus.mars.gateway.service.provider.impl;


import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 用户申请升级接口(社工)
 */
@Component
public class UserLevelApplyServiceDirectorImpl extends AbstractUserLevelApplyService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.userLevelApply.director";

    @Resource
    private ApplyInfoManager applyInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        UserApplyInfoReqVO userApplyInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserApplyInfoReqVO.class);
        return submitApplyUp(userApplyInfoReqVO, new BigDecimal(httpRequestBody.getUserId()), ApplayTypeEnum.DIRECTOR);
    }
}
