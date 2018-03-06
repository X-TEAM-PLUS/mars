package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户健康卡接口
 */
@Component
public class GetUserHeartCardListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.heartcard.list";

    @Resource
    private UserHealthCardManager userHealthCardManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
            Map<String,Object>params = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            List<UserHealthCard> userHealthCards = userHealthCardManager.queryForActiveUser(
                    new UserHealthCard().setBuyerUserId(new BigDecimal(httpRequestBody.getUserId()))
                    .setStart(params!=null && params.containsKey("start")?Integer.valueOf(params.get("start").toString()):0)
                            .setLimit(params!=null && params.containsKey("limit")?Integer.valueOf(params.get("start").toString()):Integer.MAX_VALUE)
            );
        Map<String,Object> bizContentMap = new HashMap<>();
        bizContentMap.put("list",userHealthCards);
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }
}
