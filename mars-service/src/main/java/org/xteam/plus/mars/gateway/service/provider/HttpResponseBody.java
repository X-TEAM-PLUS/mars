package org.xteam.plus.mars.gateway.service.provider;


public class HttpResponseBody {

    /**
     * 网关返回码
     */
    private String code;

    /**
     * 网关返回码描述
     */
    private String  msg;

    /**
     * 响应参数的集合，最大长度不限
     */
    private String bizContent;

    /**
     * 签名
     */
    private String sign;


    public String getCode() {
        return code;
    }

    public HttpResponseBody setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpResponseBody setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getBizContent() {
        return bizContent;
    }

    public HttpResponseBody setBizContent(String bizContent) {
        this.bizContent = bizContent;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public HttpResponseBody setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public HttpResponseBody() {
    }

    public HttpResponseBody(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public HttpResponseBody(GlobalErrorMessage globalErrorMessage) {
        setErrorMessage(globalErrorMessage);
    }
    /**
     * 设置异常信息
     * @param globalErrorMessage
     */
    public void setErrorMessage(GlobalErrorMessage globalErrorMessage){
        setCode(globalErrorMessage.getCode().toString());
        setMsg(globalErrorMessage.getMessage());
    }
}
