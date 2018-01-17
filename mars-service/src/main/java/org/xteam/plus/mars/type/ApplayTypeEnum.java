package org.xteam.plus.mars.type;

/**
 * Created by xinleisong on 2018/1/17.
 * 申请记录表 申请类型 	申请类型 1:社工 2理事 3常任理事
 */
public enum ApplayTypeEnum {

    SOCIAL(1, "社工"),

    DIRECTOR(2, "理事"),

    STANDING_DIRECTOR(3, "常务理事");

    private String info;

    private Integer code;

    ApplayTypeEnum(Integer code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static ApplayTypeEnum valueOf(Integer code) {
        if (code == null) {
            return null;
        }

        switch (code) {
            case 1:
                return ApplayTypeEnum.SOCIAL;
            case 2:
                return ApplayTypeEnum.DIRECTOR;
            case 3:
                return ApplayTypeEnum.STANDING_DIRECTOR;
            default:
                return null;
        }
    }
}
