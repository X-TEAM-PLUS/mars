package org.xteam.plus.mars.gateway.service.provider;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public class HttpRequestBody {

    private LinkedMultiValueMap<String, MultipartFile> multipartFileHashMap = new LinkedMultiValueMap<String, MultipartFile>();

    /**
     * 系统分配的渠道代码
     */
    private String channelCode;

    /**
     * 接口名称
     */
    private String method;

    /**
     * 格式 ： 仅支持JSON
     */
    private String format;

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset;

    /**
     * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    private String timestamp;

    /**
     * 调用的接口版本，固定为：1.0
     */
    private String version;

    /**
     * 授权token
     */
    private String token;

    /**
     * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，
     */
    private String bizContent;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 签名
     */
    private String sign;

    /**
     * 请求地址
     */
    private String requestUrl;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public LinkedMultiValueMap<String, MultipartFile> getMultipartFileHashMap() {
        return multipartFileHashMap;
    }

    public void setMultipartFileHashMap(LinkedMultiValueMap<String, MultipartFile> multipartFileHashMap) {
        this.multipartFileHashMap = multipartFileHashMap;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
