package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.MyTeamReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserRelationRspVO;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 获取用户团队总人数
 */
@Component
public class GetMeTeamCountServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.getMeTeamCount";

    @Resource
    private UserRelationManager userRelationManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        List<UserRelationRspVO> returnValue = Lists.newArrayList();
        MyTeamReqVO myTeamReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), MyTeamReqVO.class);
        if (myTeamReqVO == null || myTeamReqVO.getBeginDate() == null || myTeamReqVO.getEndDate() == null
                || StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(
                JsonUtils.toJSON(
                        userRelationManager.queryMyTeamCountAndNewUserLevelCount(
                                new BigDecimal(httpRequestBody.getUserId()),
                                myTeamReqVO.getBeginDate(),
                                myTeamReqVO.getEndDate()
                        )
                )
        );
    }
}
