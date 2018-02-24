package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.BankCardManager;
import org.xteam.plus.mars.manager.MessageManager;
import org.xteam.plus.mars.manager.UserInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户信息
 */
@Component
public class GetUserDetailServiceImpl implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.detail";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private MessageManager messageManager;

    @Resource
    private BankCardManager bankCardManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (httpRequestBody.getUserId() == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId()))));
        if (userInfo == null) {
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }
        userInfo.setRealName(URLDecoder.decode(userInfo.getRealName(), "utf-8"));

        Map<String, Object> bizContentMap = JsonUtils.transform(userInfo, HashMap.class);
        //加密身份证号
        if (userInfo != null && userInfo.getIdNumber() != null) {
            bizContentMap.put("idNumber", userInfo.getIdNumber().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2"));
        }

        //查询用户未读消息数
        bizContentMap.put("newMessageCount", messageManager.queryCount(
                new Message()
                        .setStatus(0)
                        .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId())))
                )
        );
        //查询用户银行卡信息
        List<BankCard> bankCardList = bankCardManager.query(
                new BankCard()
                        .setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId())))
        );
        if (bankCardList != null && bankCardList.size() > 0) {
            bizContentMap.put("card", bankCardList.get(0));
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));

    }
}
