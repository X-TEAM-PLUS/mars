package org.xteam.plus.mars.type;

/**
 * 账户信息类型
 */
public enum AccountDetailTypeEnum {
    SOCIAL_EXTEND(1, "社工推广补贴", 100,UserLevelEnum.SOCIAL),

    DIRECTOR_EXTEND(2, "理事推广补贴", 120, UserLevelEnum.DIRECTOR),
    DIRECTOR_SOCIAL_MANAGER(3,"理事社工管理补贴",20,UserLevelEnum.DIRECTOR),
    DIRECTOR_SERVICE(4,"理事服务补贴",10,UserLevelEnum.DIRECTOR),

    STANDING_DIRECTOR_EXTEND(5, "常务理事推广补贴", 135, UserLevelEnum.STANDING_DIRECTOR),
    STANDING_DIRECTOR_SOCIAL_MANAGER(6,"常务理事社工管理补贴",35,UserLevelEnum.STANDING_DIRECTOR),
    STANDING_DIRECTOR_DIRECTOR_MANAGER(7,"常务理事理事管理补贴",15,UserLevelEnum.STANDING_DIRECTOR),
    STANDING_DIRECTOR_SERVICE(8,"常务理事服务补贴",10,UserLevelEnum.STANDING_DIRECTOR),

    VIP_SUBSIDY(9, "会员提成", 0, UserLevelEnum.SOCIAL),
    USER_BUY(10, "用户买卡", 0, UserLevelEnum.SOCIAL),
    USER_BUY_GREEN(11, "用户买卡绿色通道", 0, UserLevelEnum.SOCIAL),
    WITHDRAWALS(99, "提现", 0, UserLevelEnum.SOCIAL);

    private int code;

    private String info;

    private int amount;

    private UserLevelEnum userLevelEnum;

    AccountDetailTypeEnum(int code, String info, int amount, UserLevelEnum userLevelEnum) {
        this.code = code;
        this.info = info;
        this.amount = amount;
        this.userLevelEnum = userLevelEnum;
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

    public static AccountDetailTypeEnum valueOf(int code) {
        switch (code) {
            case 1:
                return SOCIAL_EXTEND;
            case 2:
                return DIRECTOR_EXTEND;
            case 3:
                return DIRECTOR_SOCIAL_MANAGER;
            case 4:
                return DIRECTOR_SERVICE;
            case 5:
                return STANDING_DIRECTOR_EXTEND;
            case 6:
                return STANDING_DIRECTOR_SOCIAL_MANAGER;
            case 7:
                return STANDING_DIRECTOR_DIRECTOR_MANAGER;
            case 8:
                return STANDING_DIRECTOR_SERVICE;
            case 9:
                return VIP_SUBSIDY;
            case 10:
                return USER_BUY;
            case 99:
                return WITHDRAWALS;
            default:
                return null;
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserLevelEnum getUserLevelEnum() {
        return userLevelEnum;
    }

    public void setUserLevelEnum(UserLevelEnum userLevelEnum) {
        this.userLevelEnum = userLevelEnum;
    }
}
