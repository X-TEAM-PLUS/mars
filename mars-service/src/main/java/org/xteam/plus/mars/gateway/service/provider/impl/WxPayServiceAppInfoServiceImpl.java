package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.WxPayJsApiReqVO;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.OrderTypeEnum;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.util.PayUtil;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class WxPayServiceAppInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zaoangongcheng.api.gateway.wx.pay.unifiedOrder";

    public final static String REDIS_TEMP_OATH_KEY = "org.xteam.plus.mars.service.weixin.pay.parms";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private OrdersManager ordersManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private WxService iService = new WxService();

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            WxPayJsApiReqVO wxPayJsApiReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), WxPayJsApiReqVO.class);

            if (wxPayJsApiReqVO == null || httpRequestBody.getUserId() == null || wxPayJsApiReqVO.getOrderTypeEnum() == null) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            if (wxPayJsApiReqVO.getOrderTypeEnum() == OrderTypeEnum.PLATFORM_STRAIGHT) {
                if (wxPayJsApiReqVO.getProductId() == null) {
                    return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                }
            }
            if (wxPayJsApiReqVO.getOrderTypeEnum() == OrderTypeEnum.VIP_DISTRIBUTION) {
                if (wxPayJsApiReqVO.getCardNo() == null) {
                    return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                }
            }
            UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(new BigDecimal(httpRequestBody.getUserId())));
            if (userInfo == null || StringUtils.isEmpty(userInfo.getWxOpenid())) {
                return new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
            }
            PayOrderInfo payOrderInfo;
            // 平台直销
            if (wxPayJsApiReqVO.getOrderTypeEnum().getCode() == OrderTypeEnum.PLATFORM_STRAIGHT.getCode()) {
                payOrderInfo = ordersManager.createStraightPinOrder(
                        userInfo.getUserId(), wxPayJsApiReqVO.getProductId(), BigDecimal.ONE, wxPayJsApiReqVO.getAddress(),
                        wxPayJsApiReqVO.getContactsMobile(), wxPayJsApiReqVO.getCertificateOf(), wxPayJsApiReqVO.getUserRealName(), wxPayJsApiReqVO.getArea());
            } else {
                payOrderInfo = ordersManager.createDistributionOrder(
                        userInfo.getUserId(), wxPayJsApiReqVO.getAddress(),
                        wxPayJsApiReqVO.getCardNo(), wxPayJsApiReqVO.getCertificateOf(), wxPayJsApiReqVO.getUserRealName(), wxPayJsApiReqVO.getArea());
            }

            payOrderInfo.setTradeType("JSAPI");
            logInfo("预下单接口生成订单 [" + JsonUtils.toJSON(payOrderInfo) + "]");
            InvokePay invokePay = iService.unifiedOrder(payOrderInfo, WxConfig.getInstance().getPayNotifyPath(), userInfo.getWxOpenid());
            logInfo("统一下单接口返回结果 : " + JsonUtils.toJSON(invokePay));
            List<String> jsApiList = Lists.newArrayList();
            jsApiList.add("chooseWXPay");
            HashMap params = new HashMap<>();
            String packageValue = ("prepay_id=" + invokePay.getPrepayId());
            params.put("appId", WxConfig.getInstance().getAppId());
            params.put("timeStamp", Long.toString(new Date().getTime()));
            params.put("nonceStr", StringUtils.randomStr(32));
            params.put("signType", "MD5");
            params.put("package", ("prepay_id=" + invokePay.getPrepayId()));

            params.put("paySign", PayUtil.createSign(params, WxConfig.getInstance().getApiKey())); // paySign的生成规则和Sign的生成规则一致

            params.put("packageValue", invokePay.getPrepayId()); // 这里用packageValue是预防package是关键字在js获取值出错
            params.put("sendUrl", WxConfig.getInstance().getPayNotifyPath()); // 付款成功后跳转的页面
            params.put("out_trade_no", payOrderInfo.getOrderId()); // 付款成功后跳转的页面

            httpResponseBody.setBizContent(JsonUtils.toJSON(params));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody("999912", e.getMessage());
        }
        return httpResponseBody;
    }

    public static void main(String[] agre) {
        UUID.randomUUID().toString().replace("-", "");
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        System.out.println(time1.getTime());
    }
}
