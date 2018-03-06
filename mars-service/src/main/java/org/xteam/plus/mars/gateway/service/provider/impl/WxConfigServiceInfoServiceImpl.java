package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;

/**
 * 获取微信通用配置，用于分享
 */
@Component
public class WxConfigServiceInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.api.gateway.wx.globle.config";

    private WxService iService = new WxService();

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        HashMap params = Maps.newHashMap();
        String timeStamp = Long.toString(new Date().getTime()).substring(0, 10);
        String ticket = iService.getJsapiTicket();
        String nonceStr = StringUtils.randomStr(32);
        String shardUrl = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=http://t.kuai-kaifa.com/index.html?userId=2000003";
        logInfo("shardUrl  [" + shardUrl + "]");
        params.put("appId", WxConfig.getInstance().getAppId());
        params.put("timeStamp", timeStamp);
        params.put("nonceStr", nonceStr);
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(shardUrl.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        params.put("signature", signature); // paySign的生成规则和Sign的生成规则一致

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

    public static void main(String[] agre) {
        String ticket = "bxLdikRXVbTPdHSM05e5u7RuJ61YBD5Op4PejkXU8jbBVV7C0lweGhDRB5TomiI2Hj1iKLkc0Z6uUfR-2NZkuw";
        String nonceStr = "cgov1tts2byg1ek06a6d3a2c8w4fy6oq";
        String timeStamp = Long.toString(new Date().getTime()).substring(0, 10);
        String url = "http://iZm5e0mpyyk2hggmfjrfibZ:8000/api/gateway";
        String shardUrl = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=" + url;
        System.out.println(shardUrl);
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(shardUrl.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            System.out.println(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
