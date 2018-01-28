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

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
