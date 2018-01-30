package org.xteam.plus.mars.webapp.controller;

import org.apache.catalina.util.URLEncoder;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.WxUserList;
import org.xteam.plus.mars.wx.bean.result.WxOAuth2AccessTokenResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@RestController
@RequestMapping("/weixin")
public class WeixinNotifyWebService extends Logging {

    private WxService iService = new WxService();

    /**
     * 微信公众号用户授权回调
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
            return new ModelAndView("redirect:"+result);

//            iService.post(result,"");
//            out.print(echostr);
        }
        return null;
    }

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

}
