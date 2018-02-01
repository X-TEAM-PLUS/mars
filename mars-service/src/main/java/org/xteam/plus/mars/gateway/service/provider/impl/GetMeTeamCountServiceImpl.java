package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserRelationRspVO;

import java.util.List;

/**
 * 获取用户团队总人数
 */
@Component
public class GetMeTeamCountServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.getMeTeamCount";

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

            List<UserRelationRspVO> returnValue = Lists.newArrayList();
            UserInfoReqVO userInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserInfoReqVO.class);
            if (userInfoReqVO == null || userInfoReqVO.getUserId() == null || userInfoReqVO.getLimit() == null || userInfoReqVO.getStart() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
//            List<UserRelation> list = userRelationManager.queryThisAndNextLevelUser(userInfoReqVO.getUserId(), userInfoReqVO.getStart(), userInfoReqVO.getLimit());
//            for (UserRelation userRelation : list) {
//                returnValue.add(userRelationConvertService.toVO(userRelation));
//            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(returnValue));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
