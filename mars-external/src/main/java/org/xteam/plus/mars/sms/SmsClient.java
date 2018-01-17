package org.xteam.plus.mars.sms;

public interface SmsClient {
    /**
     * 发送短信
     * @param mobileNo 手机号
     * @param message  短信
     * @return
     */
    public  SmsResponse  send(String mobileNo,String message) throws Exception;
}
