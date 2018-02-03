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
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class WxPayServiceAppInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.api.gateway.wx.getAppInfo";

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
            WxJsapiConfig wxJsapiConfig = iService.createJsapiConfig(WxConfig.getInstance().getPayNotifyPath(), jsApiList);
            HashMap result = new HashMap<>();
            result.put("prepay_id", invokePay.getPrepayId());
            result.put("wxJsapiConfig", wxJsapiConfig);
            httpRequestBody.setBizContent(JsonUtils.toJSON(result));

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
