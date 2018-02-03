package org.xteam.plus.mars.wx;

import org.junit.Test;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.wx.api.IService;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import java.util.Date;

public class PayServiceTest {

    private static String NOTIFY_URL = "http://6a6uy6.natappfree.cc:8000/weixin/payNotify";

    IService wxService = new WxService();

    @Test
    public void getPayUrlWeb() {
        PayOrderInfo order = new PayOrderInfo();
        order.setAttach("测试数据");
        // 商品详情
        order.setDetail("测试商品");
        // 我方订单号
        order.setOrderId("100000010");
        order.setOrderName("测试body");
        order.setTotalFee("1");
        order.setTradeType("NATIVE");
        order.setProductId("123123");
        order.setUserIp("124.193.184.90");
        order.setTimeExpire(getWaitDate());
        order.setTimeStart(getDate());
        String openid = "";
        try {
            InvokePay invokePay = wxService.unifiedOrder(order, NOTIFY_URL, openid);
            System.out.println(JsonUtils.toJSON(invokePay));
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
