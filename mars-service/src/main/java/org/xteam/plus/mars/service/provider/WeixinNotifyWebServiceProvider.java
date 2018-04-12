package org.xteam.plus.mars.service.provider;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.gateway.token.Token;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.WxUserList;
import org.xteam.plus.mars.wx.bean.result.WxOAuth2AccessTokenResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
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
            logInfo("收到微信支付通知接口回复:" + sb.toString());
            reqMap = com.xteam.tourismpay.web.controller.util.XMLUtil4jdom.doXMLParse(sb.toString());

            boolean checkResult = iService.verifyWeixinNotify(reqMap);
            if (!checkResult) {
                logInfo("微信支付通知接口验签失败 reqMap[" + reqMap + "]");
                return returnValue;
            }
            if (reqMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                ordersManager.proccessOrder(reqMap);
                returnValue = "SUCCESS";
            } else {
                logInfo("微信支付通知接口返回失败 reqMap[" + reqMap + "]");
                return returnValue;
            }
        } catch (Exception e) {
            logError("微信支付通知接口失败 ", e);
        }
        return returnValue;
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
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            String result = iService.oauth2buildAuthorizationUrl(WxConfig.getInstance().getOauth2RedirectUri(), "snsapi_userinfo", "123");
            logInfo("调用微信获取用户信息，返回结果[" + result + "]");
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
    public ModelAndView goOauth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cipherTxt = request.getParameter("cipherTxt");
        if(cipherTxt == null || org.apache.commons.lang.StringUtils.isEmpty(cipherTxt)){
            throw new Exception("cipherTxt 为空");
        }
        Token token = cacheUtils.getToken(cipherTxt);
        if (token == null){
            throw new Exception("获到缓存token信息为空");
        }
        Map<String,String > parms = getRequestMap(request);
        parms.put("userId", token.getUserId());
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
    public ModelAndView oauth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        WxOAuth2AccessTokenResult result = null;
        try {
            logInfo("redis获取key [" + REDIS_TEMP_OATH_KEY + "." + state + "]");
            String json = stringRedisTemplate.opsForValue().get(REDIS_TEMP_OATH_KEY + "." + state);
            logInfo("回调获取json [" + json + "]");
            if (org.apache.commons.lang.StringUtils.isEmpty(json)) {
                throw new Exception("获到缓存回调获取json信息为空");
            }
            logInfo("微信授权返回code [" + code + "]");
            result = iService.oauth2ToGetAccessToken(code);
            WxUserList.WxUser user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserList.WxUser.WxUserGet(result.getOpenid(), WxConsts.LANG_CHINA));
            HashMap<String,String>  map = JsonUtils.fromJSON(json, HashMap.class);
            logInfo("微信公众号授权请求参数 [" + map + "] 微信返回内容[" + JsonUtils.toJSON(user) + "]");
            if (map.get("userId") != null) {
                 userInfoManager.registerWxUserInfo(user, new BigDecimal(map.get("userId")));
            } else {
                 userInfoManager.registerWxUserInfo(user, null);
            }
            //跳转地址
            return new ModelAndView(getUrl(map));
        } catch (Exception e) {
            logError("微信回调注册用户失败", e);
            throw new Exception("微信回调注册用户失败");
        }
    }

    /**
     * 跳转地址
     * @param map
     * @return
     */
    private String getUrl(HashMap<String,String> map) {
        String urlKey = "backUrl";
        StringBuilder url = new StringBuilder("redirect:/");
        url.append(map.get(urlKey) ).append("?");
        for (String key : map.keySet()) {
            if(!urlKey.equalsIgnoreCase(key)){
                url.append(key).append("=").append(map.get(key)).append("&");
            }
        }
        url.append("dt").append("=").append(System.currentTimeMillis());
        return url.toString();

    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    private  Map<String,String > getRequestMap(HttpServletRequest request){
        Map<String, String> target = new HashMap<>();
        if (request.getParameterMap() != null && !request.getParameterMap().isEmpty()) {
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                target.put(entry.getKey(), org.apache.commons.lang.StringUtils.join(entry.getValue(), ","));
            }
        }
        return target;
    }
}
