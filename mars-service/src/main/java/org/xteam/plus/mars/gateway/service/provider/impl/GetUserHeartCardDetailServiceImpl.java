package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户健康卡详情
 */
@Component
public class GetUserHeartCardDetailServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.heartcard.get";

    @Resource
    private UserHealthCardManager userHealthCardManager;

    @Resource
    private UserInfoManager userInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        Map<String, Object> params = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
        if (StringUtils.isEmpty((String) params.get("cardNo"))) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        // 获取用户卡详情
        UserHealthCard queryWhere = new UserHealthCard();
        queryWhere.setCardNo(new BigDecimal(params.get("cardNo").toString()));
        UserHealthCard userHealthCards = userHealthCardManager.get(queryWhere);
        if (userHealthCards == null) {
            return new HttpResponseBody(GlobalErrorMessage.CARD_NO_NOT_FIND);
        }
        // 获取购卡用户详情
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(userHealthCards.getBuyerUserId()));
        Map<String, Object> bizContentMap = new HashMap<>();
        bizContentMap.put("userHealthCard", userHealthCards);
        bizContentMap.put("buyerUser", userInfo);

        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }
}
