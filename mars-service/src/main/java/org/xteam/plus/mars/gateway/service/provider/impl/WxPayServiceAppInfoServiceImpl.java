package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.Product;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.WxPayJsApiReqVO;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.manager.ProductManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.bean.WxJsapiConfig;
import org.xteam.plus.mars.wx.util.PayUtil;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class WxPayServiceAppInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.api.gateway.wx.pay.unifiedOrder";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private ProductManager productManager;

    @Resource
    private OrdersManager ordersManager;

    private WxService iService = new WxService();

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

        long beginDate = System.currentTimeMillis();
        this.logInfo(METHOD_NAME + " request  [" + httpRequestBody.toString() + "]");
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            WxPayJsApiReqVO wxPayJsApiReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), WxPayJsApiReqVO.class);
            if (wxPayJsApiReqVO == null || wxPayJsApiReqVO.getProductId() == null || wxPayJsApiReqVO.getUserId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(wxPayJsApiReqVO.getUserId()));
            if (userInfo == null || StringUtils.isEmpty(userInfo.getWxOpenid())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
                return httpResponseBody;
            }
            Product product = productManager.get(new Product().setProductId(wxPayJsApiReqVO.getProductId()));
            if (product == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.PRODUCT_ID_NOT_HIVE);
                return httpResponseBody;
            }
            PayOrderInfo payOrderInfo = ordersManager.createStraightPinOrder(wxPayJsApiReqVO.getUserId(), wxPayJsApiReqVO.getProductId(), wxPayJsApiReqVO.getNumber(), wxPayJsApiReqVO.getAddress(), wxPayJsApiReqVO.getContactsMobile());
            payOrderInfo.setTradeType("JSAPI");
            InvokePay invokePay = iService.unifiedOrder(payOrderInfo, WxConfig.getInstance().getPayNotifyPath(), userInfo.getWxOpenid());
            List<String> jsApiList = Lists.newArrayList();
            jsApiList.add("chooseWXPay");
            HashMap params = new HashMap<>();
            String packageValue = ("prepay_id=" + invokePay.getPrepayId());
            params.put("appId", WxConfig.getInstance().getAppId());
            params.put("timeStamp", Long.toString(new Date().getTime()));
            params.put("nonceStr", StringUtils.randomStr(32));
            params.put("signType", "MD5");
            params.put("package",("prepay_id=" + invokePay.getPrepayId()));

            params.put("paySign", PayUtil.createSign(params,WxConfig.getInstance().getApiKey())); // paySign的生成规则和Sign的生成规则一致

            params.put("packageValue", invokePay.getPrepayId()); // 这里用packageValue是预防package是关键字在js获取值出错
            params.put("sendUrl", WxConfig.getInstance().getPayNotifyPath()); // 付款成功后跳转的页面
            params.put("out_trade_no", payOrderInfo.getOrderId()); // 付款成功后跳转的页面

            httpResponseBody.setBizContent(JsonUtils.toJSON(params));

        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }

    public static void main(String[] agre) {
        UUID.randomUUID().toString().replace("-", "");
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        System.out.println(time1.getTime());
    }
}
