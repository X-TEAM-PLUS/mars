package org.xteam.plus.mars.cache;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.token.Token;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class CacheUtils extends Logging {

    @Value("${mars.cache.verifycode-life}")
    private Integer verifyCodeLife;

    @Resource
    private RedisTemplate redisTemplate;



    /**
     * 设置用户短信验证码
     * @param mobileNo
     * @param verifyCode
     */
    public  void setSmsVerificationCode(String mobileNo,String verifyCode ){
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            //设置
            valueOperations.set(CacheNameSpace.SMS_VERIFY_CODE+':'+mobileNo, verifyCode, verifyCodeLife, TimeUnit.MINUTES);
        } catch (Exception e) {
           logError("设置验证码到缓存异常", e);
        }
    }

    /**
     * 获取用户短信验证码
     * @param mobileNo
     */
    public  String  getSmsVerificationCode(String mobileNo){
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            //设置
            return valueOperations.get(CacheNameSpace.SMS_VERIFY_CODE+':'+mobileNo);
        } catch (Exception e) {
            logError("设置验证码到缓存异常", e);
        }
        return null;
    }


    /**
     * 获取用户短信验证码次数
     * @param mobileNo
     * @param date  yyyyMMdd
     */
    public  Long  getSmsVerificationCodeLimit(String mobileNo,String date ){
        try {
            ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
            String key = CacheNameSpace.SMS_VERIFY_CODE_LIMIT+":"+date+':'+mobileNo;
            Long lockValue = valueOperations.increment(key, 1);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
            return lockValue;
        } catch (Exception e) {
            logError("设置验证码到缓存异常", e);
        }
        return null;
    }

    /**
     * 清除用户短信验证码
     * @param mobileNo
     */
    public  void   removeSmsVerificationCode(String mobileNo){
        try {
             redisTemplate.delete(CacheNameSpace.SMS_VERIFY_CODE+':'+mobileNo);
        } catch (Exception e) {
            logError("清除验证码异常", e);
        }
    }

    /**
     * 设置Token
     * @param token
     */
    public  void setToken(Token token){
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String userTokenKey = CacheNameSpace.USER_TOKEN+":"+token.getMobileNo();
            if(valueOperations.get(userTokenKey)!=null){
                redisTemplate.delete(valueOperations.get(userTokenKey));
            }
            String tokenKey = CacheNameSpace.TOKEN+':'+token.getCipherText();
            //设置新Token
            valueOperations.set(tokenKey, JsonUtils.toJSON(token));
            valueOperations.set(userTokenKey,tokenKey);
        } catch (Exception e) {
            logError("设置token到缓存异常", e);
        }
    }

    /**
     * 获取Token
     * @param cipherText
     */
    public Token getToken(String cipherText){
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String tokenKey = CacheNameSpace.TOKEN+':'+cipherText;
            String tokenJson = valueOperations.get(tokenKey);
            return  JsonUtils.fromJSON(tokenJson,Token.class) ;
        } catch (Exception e) {
            logError("获取Token异常", e);
        }
        return null;
    }
}
