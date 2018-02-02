package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;

/**
 * 手机短信验证码
 */
@Component
public class SmsCodeServiceImpl extends Logging implements GateWayService {
    private final String METHOD_NAME = "com.zhaoanyun.api.verification.code";

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        return null;
    }

//    @Resource
//    private CacheUtils cacheUtils;
//
//    @Value("${bestlease.cache.verifycode-limit}")
//    private Integer verifyCodeLimit;
//
//    @Resource
//    private InterfaceRecordManager interfaceRecordManager;
//
//    @Value("${bestlease.test.mobileNos}")
//    private String testMobileNos;
//
//
//    @Value("${bestlease.test.password}")
//    private String password;
//
//    @Override
//    public String getMethodName() {
//        return METHOD_NAME;
//    }
//
//    @Override
//    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
//        HttpResponseBody responseBody = new HttpResponseBody();
//        try {
//            //获取请求参数
//            Map<String, String> parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
//            //判断手机号是否为空
//            if (parmas != null && StringUtils.isNotEmpty(parmas.get("mobile") ) && parmas.get("mobile").length()==11) {
//                //获取手机号
//                String mobileNo = parmas.get("mobile");
//                if(checkVerifyCodeLimit(mobileNo)) {
//                    //发送短信
//                    String code = getVerifyCode(mobileNo);
//                    //保存到缓存
//                    cacheUtils.setSmsVerificationCode(mobileNo, code);
//                    String message = "您的短信验证码为:" + code + ",请于2分钟内填写。（请勿向任何人提供您收到的验证码）";
//                    //发送短信
//                    Date requestTime = new Date();
//                    SmsResponse smsResponse = SmsSender.send(mobileNo, message);
//                    logDebug("smsResponse:" + smsResponse);
//                   //记录调用记录
//                    InterfaceRecord interfaceRecord =  new InterfaceRecord();
//                    interfaceRecord.setRequestContent(mobileNo + ","+message);
//                    interfaceRecord.setResponseContent(JsonUtils.toJSON(smsResponse));
//                    interfaceRecord.setRequestTime(requestTime);
//                    interfaceRecord.setResponseTime(new Date());
//                    interfaceRecord.setInterfaceType(InterfaceTypeEnum.SMS.getCode());
//                    interfaceRecord.setCreated(new Date());
//                    if ("000".equalsIgnoreCase(smsResponse.getCode())) {
//                        responseBody.setCode(GlobalErrorMessage.SUCCESS.getCode().toString());
//                        responseBody.setMsg("发送短信验证码成功。");
//                        interfaceRecord.setResponseCode(200);
//                    } else {
//                        responseBody.setCode(GlobalErrorMessage.BUSINESS_FAILED.getCode().toString());
//                        responseBody.setMsg("发送短信验证码失败。");
//                        interfaceRecord.setResponseCode(500);
//                    }
//
//                    //保存调用记录
//                    interfaceRecordManager.insert(interfaceRecord);
//
//                }else{
//                    responseBody.setCode(GlobalErrorMessage.ILLEGAL_PARAMETERS.getCode().toString());
//                    responseBody.setMsg("当前获取短信密码次数达到上限");
//                }
//            } else {
//                responseBody.setCode(GlobalErrorMessage.ILLEGAL_PARAMETERS.getCode().toString());
//                responseBody.setMsg("手机号不正确");
//            }
//        } catch (Exception e) {
//            logError("获取短信验证码异常", e);
//            responseBody.setCode(GlobalErrorMessage.UNKNOW.getCode().toString());
//            responseBody.setMsg("发送短信验证码异常。");
//        }
//
//        return responseBody;
//    }
//
//    /**
//     * 获取短信验证码
//     * @param mobileNo
//     * @return
//     */
//    private String getVerifyCode(String mobileNo) {
//        if(testMobileNos!=null && testMobileNos.indexOf(mobileNo)!=-1){
//            return password==null?"123456": password.trim();
//        }
//        return VerifyCodeUtils.generateVerifyCode(6);
//    }
//
//    /**
//     * 验证手机短信验证码次数
//     * @param mobileNo
//     * @return
//     */
//    private boolean checkVerifyCodeLimit(String mobileNo) {
//        String date  =  new SimpleDateFormat("yyyyMMdd").format(new Date());
//        Long limit =cacheUtils.getSmsVerificationCodeLimit(mobileNo,date);
//        if(limit!=null && limit.intValue()> verifyCodeLimit){
//            return false;
//        }
//        return true;
//    }

}
