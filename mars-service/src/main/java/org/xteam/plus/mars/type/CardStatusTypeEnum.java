package org.xteam.plus.mars.type;

public enum  CardStatusTypeEnum {
    NOT_ATIVE(0,"未激活"),
    ATIVE(1,"已激活"),
    USE(2,"使用中"),
    COMPLETE(3,"已完成"),
    SELL_OUT(4,"已卖出");

    private int code;

    private String info;


    CardStatusTypeEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
