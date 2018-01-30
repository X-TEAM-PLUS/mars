package org.xteam.plus.mars.gateway.service.provider;

public enum GlobalErrorMessage {
    SUCCESS(10000,"接口调用成功")
    ,UNKNOW(20000,"服务不可用")
    ,UNAUTHORIZED(20001,"权限不足")
    ,MISSING_PARAMETERS(40001,"缺少必选参数")
    ,ILLEGAL_PARAMETERS(40002,"非法的参数")
    ,BUSINESS_FAILED(40004,"业务处理失败")
    ,OBJECT_ISNULL(40005,"对象为空!")
    ;
    private Integer code;
    private String message;

    GlobalErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
