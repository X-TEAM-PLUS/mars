package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.common.VerifyCodeUtils;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.sms.SmsClient;
import org.xteam.plus.mars.sms.SmsResponse;
import org.xteam.plus.mars.sms.impl.SmsClientImpl;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机短信验证码
 */
@Component
public class SmsCodeServiceImpl extends Logging implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.verification.code";

    /**
     * 短信接口
     */
    private SmsClient  smsClient = new SmsClientImpl();


    @Resource
    private CacheUtils cacheUtils;

    /**
     * 短信验证码有效期
     */
    @Value("${mars.cache.verifycode-limit}")
    private Integer verifyCodeLimit;

    /**
     * 测试的手机号
     */
    @Value("${mars.test.mobileNos}")
    private String testMobileNos;

    /**
     * 默认的密码
     */
    @Value("${mars.test.password}")
    private String password;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }


    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody responseBody = new HttpResponseBody();
        try {
            //获取请求参数
            Map<String, String> parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
            //判断手机号是否为空
            if (parmas != null && StringUtils.isNotEmpty(parmas.get("mobileNo") ) && parmas.get("mobileNo").length()==11) {
                //获取手机号
                String mobileNo = parmas.get("mobileNo");
                if(checkVerifyCodeLimit(mobileNo)) {
                    //发送短信
                    String code = getVerifyCode(mobileNo);
                    //保存到缓存
                    cacheUtils.setSmsVerificationCode(mobileNo, code);
                    //如果不是测试号，则发送短信
                    if(testMobileNos.indexOf(mobileNo)==-1){
                        //发送短信
                        SmsResponse smsResponse = smsClient.send(mobileNo, code);
                        logDebug("smsResponse:" + smsResponse);
                        if (0==smsResponse.getError_code()) {
                            responseBody.setCode(GlobalErrorMessage.SUCCESS.getCode().toString());
                            responseBody.setMsg("发送短信验证码成功。");
                        } else {
                            responseBody.setCode(GlobalErrorMessage.BUSINESS_FAILED.getCode().toString());
                            responseBody.setMsg("发送短信验证码失败。");
                        }
                    }else{
                        responseBody.setCode(GlobalErrorMessage.SUCCESS.getCode().toString());
                        responseBody.setMsg("发送短信验证码成功。");
                    }
                }else{
                    responseBody.setCode(GlobalErrorMessage.ILLEGAL_PARAMETERS.getCode().toString());
                    responseBody.setMsg("当前获取短信密码次数达到上限");
                }
            } else {
                responseBody.setCode(GlobalErrorMessage.ILLEGAL_PARAMETERS.getCode().toString());
                responseBody.setMsg("手机号不正确");
            }
        } catch (Exception e) {
            logError("获取短信验证码异常", e);
            responseBody.setCode(GlobalErrorMessage.UNKNOW.getCode().toString());
            responseBody.setMsg("发送短信验证码异常。");
        }

        return responseBody;
    }

    /**
     * 获取短信验证码
     * @param mobileNo
     * @return
     */
    private String getVerifyCode(String mobileNo) {
        if(testMobileNos!=null && testMobileNos.indexOf(mobileNo)!=-1){
            return password==null?"123456": password.trim();
        }
        return VerifyCodeUtils.generateVerifyCode(6);
    }

    /**
     * 验证手机短信验证码次数
     * @param mobileNo
     * @return
     */
    private boolean checkVerifyCodeLimit(String mobileNo) {
        if(testMobileNos.indexOf(mobileNo)==-1){
            return true;
        }
        String date  =  new SimpleDateFormat("yyyyMMdd").format(new Date());
        Long limit =cacheUtils.getSmsVerificationCodeLimit(mobileNo,date);
        if(limit!=null && limit.intValue()> verifyCodeLimit){
            return false;
        }
        return true;
    }

}
