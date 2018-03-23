package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.token.Token;
import org.xteam.plus.mars.gateway.token.TokenGenerator;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机短信验证码登录
 */
@Component
public class SmsLoginServiceImpl extends Logging implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.smscode.login";

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }



    @Resource
    private CacheUtils cacheUtils;

    @Resource
    private UserInfoManager userInfoManager;

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody responseBody = new HttpResponseBody();
        try {
            //获取请求参数
            Map<String, String> parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
            //判断手机号是否为空
            if (parmas != null && StringUtils.isNotEmpty(parmas.get("mobileNo") ) && parmas.get("mobileNo").length()==11
                    && StringUtils.isNotEmpty(parmas.get("smsCode") ) && parmas.get("smsCode").length()==6
                    ) {
                //获取手机号
                String mobileNo = parmas.get("mobileNo");
                //获取短信验证码
                String smsCode = parmas.get("smsCode");
                //获取缓存短信验证码
                String cacheSmsCode = cacheUtils.getSmsVerificationCode(mobileNo);
                //验证
                if(smsCode.equalsIgnoreCase(cacheSmsCode)){
                    //检测用户表里是否存在
                    UserInfo userInfo = userInfoManager.getByMobileNo(mobileNo);
                    //如果未注册
                    if(userInfo==null){
                        //如果不存在
                        userInfo = new UserInfo();
                        userInfo.setMobileNo(mobileNo);
                        userInfo.setStatus(0);
                        userInfo.setCreated(new Date());
                        userInfo.setUpdated(new Date());
                        userInfo.setUserLevel(UserLevelEnum.TOURIST.getCode());
                        userInfo.setRegisterTime(new Date());
                        //注册用户
                        userInfoManager.insert(userInfo);
                    }
                    //删除短信验证码
                    cacheUtils.removeSmsVerificationCode(mobileNo);
                    //生成token
                    Token token = TokenGenerator.generateToken(httpRequestBody.getChannelCode(),userInfo.getUserId().toString(),mobileNo,httpRequestBody.getDeviceInfo());
                    //缓存token
                    cacheUtils.setToken(token);

                    responseBody.setCode(GlobalErrorMessage.SUCCESS.getCode().toString());
                    responseBody.setMsg("登录认证成功。");
                    Map<String,String> tokenMap = new HashMap<String,String>();
                    tokenMap.put("token",token.getCipherText());
                    responseBody.setBizContent(JsonUtils.toJSON(tokenMap));
                }else {
                    responseBody.setCode(GlobalErrorMessage.BUSINESS_FAILED.getCode().toString());
                    responseBody.setMsg("认证失败，短信验证码不正确。");
                }
            } else {
                responseBody.setErrorMessage(GlobalErrorMessage.ILLEGAL_PARAMETERS);
            }
        } catch (Exception e) {
            logError("短信验证码登录认证异常", e);
            responseBody.setCode(GlobalErrorMessage.UNKNOW.getCode().toString());
            responseBody.setMsg("短信验证码登录认证异常。");
        }

        return responseBody;
    }
}
