package org.xteam.plus.mars.gateway.token;

import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.MD5;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2017-12-16
 * Time: 14:28
 * 功能:  token生成器
 */
public class TokenGenerator {
    /**
     * 生成token
     * @param channelCode 渠道号
     * @param userId  用户ID
     * @param deviceInfo  设置信息
     * @return
     */
    public static Token  generateToken(String channelCode,String userId,String mobileNo,String deviceInfo ) throws Exception {
        Token token =null;
        try{
            token  =  new Token();
            token.setChannelCode(channelCode);
            token.setUserId(userId);
            token.setMobileNo(mobileNo);
            token.setDeviceInfo(deviceInfo);
            token.setTimestamp(new Date());
            token.setCipherText(MD5.getMD5(JsonUtils.toJSON(token)));
        }catch (Exception e){
              throw new Exception("生成token异常");
        }
        return token;
    }

}
