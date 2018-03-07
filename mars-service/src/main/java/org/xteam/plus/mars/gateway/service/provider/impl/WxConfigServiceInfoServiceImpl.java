package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Maps;
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
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;

/**
 * 获取微信通用配置，用于分享
 */
@Component
public class WxConfigServiceInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.api.gateway.wx.globle.config";

    @Resource
    private UserHealthCardManager userHealthCardManager;

    @Resource
    private UserInfoManager userInfoManager;

    private WxService iService = new WxService();

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        HashMap params = Maps.newHashMap();
        HashMap hashMap = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
        // 取消必填参数
        if (hashMap == null || hashMap.isEmpty() || hashMap.get("cardNo") == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        String timeStamp = Long.toString(new Date().getTime()).substring(0, 10);
        String ticket = iService.getJsapiTicket();
        String nonceStr = StringUtils.randomStr(32);
        String shardUrl = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=http://t.kuai-kaifa.com/index.html?userId=2000003";
        logInfo("shardUrl  [" + shardUrl + "]");
        params.put("appId", WxConfig.getInstance().getAppId());
        params.put("timeStamp", timeStamp);
        params.put("nonceStr", nonceStr);
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(shardUrl.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());
        params.put("signature", signature); // paySign的生成规则和Sign的生成规则一致
        UserHealthCard userHealthCard = userHealthCardManager.get(new UserHealthCard().setCardNo(new BigDecimal(hashMap.get("cardNo").toString())));
        if (userHealthCard == null) {
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(userHealthCard.getBuyerUserId()));
        if (userInfo == null) {
            return new HttpResponseBody(GlobalErrorMessage.SELL_USER_NOT_FIND);
        }
        params.put("sellUser", userInfo);
        httpResponseBody.setBizContent(JsonUtils.toJSON(params));
        return httpResponseBody;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
