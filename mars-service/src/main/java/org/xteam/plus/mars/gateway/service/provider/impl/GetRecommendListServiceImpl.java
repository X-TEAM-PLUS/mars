package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.convert.UserRelationConvertService;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserRelationRspVO;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 获取用户下所有的关系用户
 */
@Component
public class GetRecommendListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.getRecommendList";

    @Resource
    private UserRelationManager userRelationManager;

    @Resource
    private UserRelationConvertService userRelationConvertService;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

            List<UserRelationRspVO> returnValue = Lists.newArrayList();
            PageInfoReqVO pageInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), PageInfoReqVO.class);
            if (pageInfoReqVO == null  || pageInfoReqVO.getLimit() == null || pageInfoReqVO.getStart() == null
                    || StringUtils.isEmpty(httpRequestBody.getUserId())) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);

            }
            HashMap bizContentMap = Maps.newHashMap();
            List<UserRelation> list = userRelationManager.queryThisAndNextLevelUser(new BigDecimal(httpRequestBody.getUserId()), pageInfoReqVO.getStart(), pageInfoReqVO.getLimit());
            for (UserRelation userRelation : list) {
                returnValue.add(userRelationConvertService.toVO(userRelation));
            }
            bizContentMap.put("list",returnValue);

        return null;
    }
}
