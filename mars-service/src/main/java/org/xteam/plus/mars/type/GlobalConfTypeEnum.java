package org.xteam.plus.mars.type;

/**
 * 全局配置信息Type
 */
public enum GlobalConfTypeEnum {
    WX_BANK_LIST(1, "微信银行列表");

    private int code;

    private String info;

    GlobalConfTypeEnum(int code, String info) {
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

    public static GlobalConfTypeEnum valueOf(int code) {
        switch (code) {
            case 1:
                return WX_BANK_LIST;
            default:
                return null;
        }
    }
}
