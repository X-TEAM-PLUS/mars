package org.xteam.plus.mars.type;

public enum DeliveredInfoTypeEnum {
    NOT_CHECK(0, "未检测"),
    CHECK_ING(1, "检测中"),
    FINISH_CHECK(2, "检测完成");

    private Integer code;

    private String info;

    DeliveredInfoTypeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    private static DeliveredInfoTypeEnum valueOf(Integer code) {
        switch (code) {
            case 0:
                return NOT_CHECK;
            case 1:
                return CHECK_ING;
            case 2:
                return FINISH_CHECK;
            default:
                return null;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
