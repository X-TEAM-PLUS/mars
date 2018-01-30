package org.xteam.plus.mars.webapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.WxQrcode;
import org.xteam.plus.mars.wx.bean.WxUserList;
import org.xteam.plus.mars.wx.bean.result.QrCodeResult;
import org.xteam.plus.mars.wx.bean.result.WxOAuth2AccessTokenResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/weixin")
public class WeixinNotifyWebService extends Logging {

    private WxService iService = new WxService();

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
            return new ModelAndView("redirect:" + result);

//            iService.post(result,"");
//            out.print(echostr);
        }
        return null;
    }

    /**
     * 微信公众号授权回调
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/oauth")
    public void oauth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        PrintWriter out = response.getWriter();
        WxOAuth2AccessTokenResult result = null;
        try {
            result = iService.oauth2ToGetAccessToken(code);
            WxUserList.WxUser user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserList.WxUser.WxUserGet(result.getOpenid(), WxConsts.LANG_CHINA));
            System.out.println(user.toString());
            out.print(user.toString());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取公众号关注二维码
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
