package org.xteam.plus.mars.type;

public enum OrderTypeEnum {

    PLATFORM_STRAIGHT(0, "平台直销"),

    VIP_DISTRIBUTION(1, "会员分销");

    private int code;

    private String info;

    OrderTypeEnum(int code, String info) {
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

    public static OrderTypeEnum valueOf(int code) {
        switch (code) {
            case 0:
                return PLATFORM_STRAIGHT;
            case 1:
                return VIP_DISTRIBUTION;
            default:
                return null;
        }
    }
}
