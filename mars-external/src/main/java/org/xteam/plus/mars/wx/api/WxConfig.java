package org.xteam.plus.mars.wx.api;


import org.xteam.plus.mars.wx.bean.WxAccessToken;
import org.xteam.plus.mars.wx.exception.WxErrorException;
import org.xteam.plus.mars.wx.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 微信全局配置对象-从配置文件读取
 *
 * @author antgan
 * @datetime 2016/12/14
 */
public class WxConfig {
    private static final String configFile = "/wx.properties";
    private static WxConfig config = null;

    //配置文件读取项
    private volatile String appId;
    private volatile String appSecret;
    private volatile String token;
    private volatile String aesKey;
    private volatile String mchId;
    private volatile String apiKey;

    // 用户授权回调地址
    private volatile String oauth2RedirectUri;

    //内存更新
    private volatile String accessToken;
    private volatile long expiresTime;
    private volatile String jsapiTicket;
    private volatile long jsapiTicketExpiresTime;

    // 支付回调地址
    private volatile String payNotifyPath;

    // 微信支付证书文件路径
    private volatile String certPath;
    private volatile String certPass;
    // 企业代付公钥pkcs8 转换后的
    private volatile String pkcs8;

    private volatile String payIp;
    // 微信分享回调
    private volatile String shardUrl;

    public WxConfig() {
        //写读配置文件代码
        Properties p = new Properties();
        InputStream inStream = this.getClass().getResourceAsStream(configFile);
        if (inStream == null) {
            try {
                throw new WxErrorException("根目录找不到文件");
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        try {
            p.load(inStream);
            this.appId = p.getProperty("wx.appId");
            if (StringUtils.isNotBlank(this.appId)) this.appId = this.appId.trim();
            this.appSecret = p.getProperty("wx.appSecret");
            if (StringUtils.isNotBlank(this.appSecret)) this.appSecret = this.appSecret.trim();
            this.token = p.getProperty("wx.token");
            if (StringUtils.isNotBlank(this.token)) this.token = this.token.trim();
            this.aesKey = p.getProperty("wx.aesKey");
            if (StringUtils.isNotBlank(this.aesKey)) this.aesKey = this.aesKey.trim();
            this.mchId = p.getProperty("wx.mchId");
            if (StringUtils.isNotBlank(this.mchId)) this.mchId = this.mchId.trim();
            this.apiKey = p.getProperty("wx.apiKey");
            if (StringUtils.isNotBlank(this.apiKey)) this.apiKey = this.apiKey.trim();
            this.oauth2RedirectUri = p.getProperty("wx.oauth2RedirectUrl");
            if (StringUtils.isNotBlank(this.oauth2RedirectUri)) this.oauth2RedirectUri = this.oauth2RedirectUri.trim();
            this.payNotifyPath = p.getProperty("wx.payNotifyPath");
            if (StringUtils.isNotBlank(this.payNotifyPath)) this.payNotifyPath = this.payNotifyPath.trim();
            this.certPath = p.getProperty("wx.cert.path");
            if (StringUtils.isNotBlank(this.certPath)) this.certPath = this.certPath.trim();
            this.certPass = p.getProperty("wx.cert.pass");
            if (StringUtils.isNotBlank(this.certPass)) this.certPass = this.certPass.trim();
            this.pkcs8 = p.getProperty("wx.pkcs8.pass");
            if (StringUtils.isNotBlank(this.pkcs8)) this.pkcs8 = this.pkcs8.trim();
            this.payIp = p.getProperty("wx.pay.ip");
            if (StringUtils.isNotBlank(this.payIp)) this.payIp = this.payIp.trim();
            this.shardUrl = p.getProperty("wx.shard.url");
            if (StringUtils.isNotBlank(this.shardUrl)) this.shardUrl = this.shardUrl.trim();

            inStream.close();
        } catch (IOException e) {
            try {
                throw new WxErrorException("load wx.properties error,class根目录下找不到wx.properties文件");
            } catch (WxErrorException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("load wx.properties success");
    }

    /**
     * 同步获取/加载单例
     *
     * @return
     */
    public static synchronized WxConfig getInstance() {
        if (config == null) {
            config = new WxConfig();
        }
        return config;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public void expireAccessToken() {
        this.expiresTime = 0;
    }

    public synchronized void updateAccessToken(WxAccessToken accessToken) {
        updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public long getJsapiTicketExpiresTime() {
        return jsapiTicketExpiresTime;
    }

    public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
        this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
    }

    public boolean isJsapiTicketExpired() {
        return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
    }

    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        this.jsapiTicket = jsapiTicket;
        // 预留200秒的时间
        this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
    }

    public void expireJsapiTicket() {
        this.jsapiTicketExpiresTime = 0;
    }


    //getter


    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getToken() {
        return token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public String getMchId() {
        return mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getPayNotifyPath() {
        return payNotifyPath;
    }

    public String getCertPass() {
        return certPass;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCertPath() {
        return certPath;
    }

    public String getPkcs8() {
        return pkcs8;
    }

    public String getPayIp() {
        return payIp;
    }

    @Override
    public String toString() {
        return "WxConfig [appId=" + appId + ", appSecret=" + appSecret + ", token=" + token + ", aesKey=" + aesKey
                + ", mchId=" + mchId + ", apiKey=" + apiKey + ", accessToken=" + accessToken + ", expiresTime="
                + expiresTime + ", jsapiTicket=" + jsapiTicket + ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime
                + "]";
    }

    public String getShardUrl() {
        return shardUrl;
    }

    public String getOauth2RedirectUri() {
        return oauth2RedirectUri;
    }

    public void setOauth2RedirectUri(String oauth2RedirectUri) {
        this.oauth2RedirectUri = oauth2RedirectUri;
    }
}
