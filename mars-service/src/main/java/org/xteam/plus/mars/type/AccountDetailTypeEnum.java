package org.xteam.plus.mars.type;

/**
 * 账户信息类型
 */
public enum AccountDetailTypeEnum {
    SOCIAL_EXTENSION_SUBSIDY(1, "社工推广补贴", 100,UserLevelEnum.SOCIAL),
    DIRECTOR_EXTENSION_SUBSIDY(2, "理事推广补贴", 120, UserLevelEnum.DIRECTOR),
    STANDING_DIRECTOR_EXTENSION_SUBSIDY(3, "常任理事推广补贴", 135, UserLevelEnum.STANDING_DIRECTOR),
    SOCIAL_WORK_SUBSIDY(4, "社工管理补贴", 20, UserLevelEnum.SOCIAL),
    DIRECTOR_WORK_SUBSIDY(5, "理事管理补贴", 15, UserLevelEnum.DIRECTOR),
    STANDING_DIRECTOR_SUBSIDY(6, "常务理事管理补贴", 35, UserLevelEnum.STANDING_DIRECTOR),
    DIRECTOR_SERVICE_SUBSIDY(7, "理事服务补贴", 10, UserLevelEnum.SOCIAL),
    STANDING_DIRECTOR_SERVICE_SUBSIDY(8, "常任理事服务补贴", 10, UserLevelEnum.SOCIAL),
    VIP_SUBSIDY(9, "会员提成", 0, UserLevelEnum.SOCIAL),
    USER_BUY(10, "用户买卡", 0, UserLevelEnum.SOCIAL),
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
                return SOCIAL_EXTENSION_SUBSIDY;
            case 2:
                return DIRECTOR_EXTENSION_SUBSIDY;
            case 3:
                return STANDING_DIRECTOR_EXTENSION_SUBSIDY;
            case 4:
                return SOCIAL_WORK_SUBSIDY;
            case 5:
                return DIRECTOR_WORK_SUBSIDY;
            case 6:
                return STANDING_DIRECTOR_SUBSIDY;
            case 7:
                return DIRECTOR_SERVICE_SUBSIDY;
            case 8:
                return STANDING_DIRECTOR_SERVICE_SUBSIDY;
            case 9:
                return VIP_SUBSIDY;
            case 10:
                return USER_BUY;
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
