package org.xteam.plus.mars.type;

/**
 * Created by xinleisong on 2018/1/17.
 * 用户级别:0:游客 1:会员 2:社工 3:理事 4:常任理事
 */
public enum UserLevelEnum {
    TOURIST(0, "游客"),
    MEMBER(1, "会员"),
    SOCIAL(2, "社工"),
    DIRECTOR(3, "理事"),
    STANDING_DIRECTOR(4, "常务理事");

    private String info;
    private Integer code;

    UserLevelEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public static UserLevelEnum valueOf(Integer code) {
        switch (code) {
            case 0:
                return UserLevelEnum.TOURIST;
            case 1:
                return UserLevelEnum.MEMBER;
            case 2:
                return UserLevelEnum.SOCIAL;
            case 3:
                return UserLevelEnum.DIRECTOR;
            case 4:
                return UserLevelEnum.STANDING_DIRECTOR;
            default:
                return null;
        }
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
}
