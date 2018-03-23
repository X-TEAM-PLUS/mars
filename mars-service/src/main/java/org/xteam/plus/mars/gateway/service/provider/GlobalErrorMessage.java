package org.xteam.plus.mars.gateway.service.provider;

public enum GlobalErrorMessage {
    SUCCESS(10000,"接口调用成功")
    ,UNKNOW(20000,"服务不可用")
    ,UNAUTHORIZED(20001,"权限不足")
    ,MISSING_PARAMETERS(40001,"缺少必选参数")
    ,ILLEGAL_PARAMETERS(40002,"非法的参数")
    ,BUSINESS_FAILED(40004,"业务处理失败")
    ,SELL_USER_NOT_FIND(40015,"卖卡方用户不存在")
    ,OBJECT_ISNULL(40005,"对象为空!")
    ,CARD_ALREAD_BIND(40006,"银行卡已经被绑定")
    ,APPLY_AMOUNT_ZER(40007,"申请金额不能小与等于0")
    ,USER_ID_NOT_HIVE(40008,"用户ID不存在")
    ,AMOUNT_NOT_ENOUGH(40009,"金额不足，不能进行转账")
    ,BANK_ID_ERROR(40010,"银行编码错误，不能进行绑定")
    ,PRODUCT_ID_NOT_HIVE(40011,"产品ID不存在")
    ,DIFFERENT_REAL_NAME(40012,"实名不一致")
    ,BANK_CARD_IS_BINDED(40013,"重复的绑卡操作")
    ,CARD_NO_NOT_FIND(40014,"卡号不存在，无法进行获取")
    ,TOKEN_IS_EFFICACY(40015,"token已经失效")
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
