package org.xteam.plus.mars.type;

public enum HealthCheckRecordTypeEnum {

    UNDETECTED(0, "未检测"),
    DETECTED(1, "检测完成");
    private int code;

    private String info;

    HealthCheckRecordTypeEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public static HealthCheckRecordTypeEnum valueOf(int code) {
        switch (code) {
            case 0:
                return UNDETECTED;
            default:
                return null;
        }
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
