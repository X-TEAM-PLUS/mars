package org.xteam.plus.mars.service.provider;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.token.Token;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.manager.subsidy.impl.SubsidyManagerFactory;
import org.xteam.plus.mars.type.OrderTypeEnum;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.InvokePay;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.bean.WxQrcode;
import org.xteam.plus.mars.wx.bean.WxUserList;
import org.xteam.plus.mars.wx.bean.result.QrCodeResult;
import org.xteam.plus.mars.wx.bean.result.WxOAuth2AccessTokenResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/weixin")
public class WeixinNotifyWebServiceProvider extends Logging {

    @Resource
    private OrdersManager ordersManager;

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SubsidyManagerFactory subsidyManagerFactory;

    @Resource
    private CacheUtils cacheUtils;

    private WxService iService = new WxService();

    private final static String REDIS_TEMP_OATH_KEY = "org.xteam.plus.mars.service.weixin.oath.parms";

    @RequestMapping(value = "/payNotify")
    public String payNotify(HttpServletRequest request) throws Exception {
        String returnValue = "FAIL";
        try {
            //读取参数
            InputStream inputStream;
            StringBuffer sb = new StringBuffer();
            inputStream = request.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();
            //解析xml成map
            Map<Object, Object> reqMap = new HashMap<Object, Object>();
            System.out.println("收到微信支付通知接口回复:" + sb.toString());
            reqMap = com.xteam.tourismpay.web.controller.util.XMLUtil4jdom.doXMLParse(sb.toString());

            boolean checkResult = iService.verifyWeixinNotify(reqMap);
            if (!checkResult) {
                logInfo("微信支付通知接口验签失败 reqMap[" + reqMap + "]");
                return returnValue;
            }
            if (reqMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {

                ordersManager.updateStraightPinOrder(reqMap);
                Orders orders = ordersManager.get(new Orders().setOrderNo(new BigDecimal(reqMap.get("out_trade_no").toString())));
                // 发放补贴
                subsidyManagerFactory.execute(orders);
                returnValue = "SUCCESS";
            } else {
                logInfo("微信支付通知接口返回失败 reqMap[" + reqMap + "]");
                return returnValue;
            }
        } catch (Exception e) {
            logInfo("微信支付通知接口失败 ", e);
        } finally {
            return returnValue;
        }
    }

//    @RequestMapping("/getImage")
//    public void getImage(BigDecimal userId, BigDecimal productId, BigDecimal number, String address, String contactsMobile, HttpServletResponse response, HttpSession session) throws Exception {
//        PayOrderInfo payOrderInfo = ordersManager.createStraightPinOrder(userId, productId, number, address, contactsMobile);
//        InvokePay invokePay = iService.unifiedOrder(payOrderInfo, WxConfig.getInstance().getPayNotifyPath(), "");
//        String code_url = invokePay.getCodeUrl();
//        if (code_url == null || "".equals(code_url))
//            return;
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        Map hints = new HashMap();
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
//        BitMatrix bitMatrix = null;
//        try {
//            bitMatrix = multiFormatWriter.encode(code_url, BarcodeFormat.QR_CODE, 300, 300, hints);
//            BufferedImage image = toBufferedImage(bitMatrix);
//            //输出二维码图片流
//            try {
//                ImageIO.write(image, "png", response.getOutputStream());
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } catch (WriterException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//    }

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 微信公众号关注首页回调
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/publicNumberAuthorization")
    public ModelAndView publicNumberAuthorization(HttpServletRequest request, HttpServletResponse response) throws IOException, WxErrorException {
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        getMap(request);
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            String result = iService.oauth2buildAuthorizationUrl(WxConfig.getInstance().getOauth2RedirectUri(), "snsapi_userinfo", "123");
            logInfo("调用微信获取用户信息，返回结果[" + result + "]");
//            return new ModelAndView("redirect:" + result);

            iService.post(result, "");
            out.print(echostr);
        }
        return null;
    }

    /**
     *  微信公众号跳转
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/goOauth")
    public ModelAndView goOauth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = request.getParameter("backUrl");
        String cipherTxt = request.getParameter("cipherTxt");
        if(cipherTxt == null || org.apache.commons.lang.StringUtils.isEmpty(cipherTxt)){
            return new ModelAndView("redirect:www.baidu.com");
        }
        Token token = cacheUtils.getToken(cipherTxt);
        if (token == null){
            return new ModelAndView("redirect:www.baidu.com");
        }
        String cardNo = request.getParameter("cardNo");
        HashMap parms = Maps.newHashMap();
        parms.put("backUrl", backUrl);
        parms.put("userId", token.getUserId());
        parms.put("cardNo", cardNo);
        Long incrementKey = stringRedisTemplate.boundValueOps(REDIS_TEMP_OATH_KEY).increment(1);
        String redisKey = REDIS_TEMP_OATH_KEY + "." + incrementKey;
        logInfo("缓存Key [" + redisKey + "] [" + JsonUtils.toJSON(parms) + "]");
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJSON(parms), 60, TimeUnit.SECONDS);
        String result = iService.oauth2buildAuthorizationUrl(WxConfig.getInstance().getOauth2RedirectUri(), "snsapi_userinfo", incrementKey.toString());
        return new ModelAndView("redirect:" + result);
    }

    /**
     * 微信公众号授权回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/oauth")
    public ModelAndView oauth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        WxOAuth2AccessTokenResult result = null;
        try {
            logInfo("redis获取key [" + REDIS_TEMP_OATH_KEY + "." + state + "]");
            String json = stringRedisTemplate.opsForValue().get(REDIS_TEMP_OATH_KEY + "." + state);
            logInfo("回调获取json [" + json + "]");
            if (org.apache.commons.lang.StringUtils.isEmpty(json)) {
                response.sendRedirect("www.baidu.com");
                return new ModelAndView("redirect:" + "www.baidu.com");
            }
            logInfo("微信授权返回code [" + code + "]");
            result = iService.oauth2ToGetAccessToken(code);
            WxUserList.WxUser user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserList.WxUser.WxUserGet(result.getOpenid(), WxConsts.LANG_CHINA));
            HashMap map = JsonUtils.fromJSON(json, HashMap.class);
            logInfo("支付授权回调请求参数 [" + map + "] 微信返回内容[" + JsonUtils.toJSON(user) + "]");
            UserInfo userInfo = null;
            user.setNickname(URLEncoder.encode(user.getNickname(), "utf-8"));
            if (map.get("userId") != null) {
                userInfo = userInfoManager.registerWxUserInfo(user, new BigDecimal(map.get("userId").toString()));
            } else {
                userInfo = userInfoManager.registerWxUserInfo(user, null);
            }
            // 卖卡地址
            if (map.get("cardNo") != null && !map.get("cardNo").equals("")) {
                return new ModelAndView("redirect:/" + map.get("backUrl").toString() + "?cardNo=" + map.get("cardNo").toString());
            }
            return new ModelAndView("redirect:/" + map.get("backUrl").toString());

        } catch (WxErrorException e) {
            logInfo("微信回调注册用户失败 失败原因 e[" + e.getMessage() + "]", e);

        } catch (Exception e) {
            logInfo("微信回调注册用户失败 失败原因 e[" + e.getMessage() + "]", e);
        }
        return new ModelAndView("redirect:/" + "www.baidu.com");
    }


    /**
     * 获取公众号关注二维码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getQrcode")
    public String createQrCode(HttpServletRequest request, HttpServletResponse response) {
        WxQrcode code = new WxQrcode();
        code.setAction_name("QR_SCENE");
        code.setExpire_seconds(99999999);
        WxQrcode.WxQrActionInfo.WxScene wxScene = new WxQrcode.WxQrActionInfo.WxScene(1);
        if (request.getParameter("refUserId") != null) {
            wxScene.setScene_id(Integer.parseInt(request.getParameter("refUserId")));
        }
//        wxScene.setScene_str("ceshi");
        code.setAction_info(new WxQrcode.WxQrActionInfo(wxScene));
        try {
            QrCodeResult result = iService.createQrCode(code);
            return result.getUrl();
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public Map getMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        try {
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                logInfo("参数 key[" + name + "] value[" + valueStr + "]");
                params.put(name, valueStr);
            }
        } catch (Exception e) {
            logInfo("获取参数异常 ", e);
        }
        return params;
    }

}
