package org.xteam.plus.mars.wx;

import org.junit.Test;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.wx.api.IService;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.exception.WxErrorException;
import org.xteam.plus.mars.wx.util.PayUtil;
import org.xteam.plus.mars.wx.util.StringUtils;

import java.util.Date;
import java.util.HashMap;

public class PayServiceTest {

    private static String NOTIFY_URL = "http://t.kuai-kaifa.com/weixin/payNotify";

    IService wxService = new WxService();

    @Test
    public void getPayUrlWeb() {
        PayOrderInfo order = new PayOrderInfo();
        order.setAttach("测试数据");
        // 商品详情
        order.setDetail("测试商品");
        // 我方订单号
        order.setOrderId("100000013");
        order.setOrderName("测试body");
        order.setTotalFee("1");
        order.setTradeType("JSAPI");
        order.setProductId("123123");
        order.setUserIp("124.193.184.90");
        order.setTimeExpire(getWaitDate());
        order.setTimeStart(getDate());

        String openid = "o31nujoJe-vOD8h9X5EbmIV64BJQ";
        try {
            InvokePay invokePay = wxService.unifiedOrder(order, NOTIFY_URL, openid);
            HashMap payJsapi = new HashMap<>();
            payJsapi.put("appId", WxConfig.getInstance().getAppId());
            payJsapi.put("package", "prepay_id=" + invokePay.getPrepayId());
            payJsapi.put("signType", "MD5");
            payJsapi.put("timeStamp", Long.toString(new Date().getTime()).substring(0, 10));
            payJsapi.put("nonceStr", StringUtils.randomStr(32));
            String sign = PayUtil.createSign(payJsapi, WxConfig.getInstance().getApiKey());
            payJsapi.put("paySign", sign);
            System.out.println(payJsapi);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    private String getDate() {
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    private String getWaitDate() {
        long date = System.currentTimeMillis();
        date += 30 * 60 * 1000;
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(date));

    }
}
