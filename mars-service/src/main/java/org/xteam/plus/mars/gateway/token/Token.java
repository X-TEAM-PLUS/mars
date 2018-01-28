package org.xteam.plus.mars.gateway.token;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2017-12-16
 * Time: 14:28
 * 功能: token实体类
 */
public class Token {
    /**
     *  渠道号
     */
    private String channelCode;

    /**
     * 用户ID
     */
    private   String userId;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 密文
     */
    private String cipherText;


    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 时间戳
     */
    private Date timestamp;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
