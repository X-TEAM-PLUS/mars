package org.xteam.plus.mars.wx;

import org.junit.Test;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.wx.api.IService;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.bean.result.PayPocketMoneyResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;
import org.xteam.plus.mars.wx.util.PayUtil;
import org.xteam.plus.mars.wx.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    @Test
    public void getPublicKey() {
        try {
            System.out.println(JsonUtils.toJSON(wxService.getPublicKey()));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信代付到银行卡
     */
    @Test
    public void payForAnotherBank() {
        try {
            wxService.payForAnotherBank("6214830163371957", "宋鑫磊", "1001", "100", "test");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信代付到零钱
     */
    @Test
    public void payForAnotherPocket() {
        try {
            PayPocketMoneyResult payPocketMoneyResult = wxService.payForAnotherPocketMoney("o31nujoJe-vOD8h9X5EbmIV64BJQ", "FORCE_CHECK", "宋鑫磊1", "100", "发放补贴(早安工程)");
            System.out.println(JsonUtils.toJSON(payPocketMoneyResult));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getName() {
        String userName = "%E4%B8%A5%E7%90%A8";
        try {
            System.out.println(URLDecoder.decode(userName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
