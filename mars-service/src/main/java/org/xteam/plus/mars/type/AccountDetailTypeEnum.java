package org.xteam.plus.mars.type;

/**
 * 账户信息类型
 */
public enum AccountDetailTypeEnum {
    EXTENSION_SUBSIDY(1, "推广补贴"),
    SOCIAL_WORK_SUBSIDY(2, "社工管理补贴"),
    SERVICE_SUBSIDY(3, "服务补贴"),
    VIP_SUBSIDY(4, "会员提成"),
    DIRECTOR_WORK_SUBSIDY(5, "理事管理补贴"),
    STANDING_DIRECTOR_SUBSIDY(6, "常务理事管理补贴"),
    WITHDRAWALS(7, "提现");

    private int code;

    private String info;

    AccountDetailTypeEnum(int code, String info) {
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

    public static AccountDetailTypeEnum valueOf(int code) {
        switch (code) {
            case 1:
                return EXTENSION_SUBSIDY;
            case 2:
                return SOCIAL_WORK_SUBSIDY;
            case 3:
                return SERVICE_SUBSIDY;
            case 4:
                return VIP_SUBSIDY;
            case 5:
                return DIRECTOR_WORK_SUBSIDY;
            case 6:
                return STANDING_DIRECTOR_SUBSIDY;
            case 7:
                return WITHDRAWALS;
            default:
                return null;
        }
    }
}
