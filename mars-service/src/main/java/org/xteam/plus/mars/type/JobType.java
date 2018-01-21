package org.xteam.plus.mars.type;

/**
 * 职位类型 0:未任命 1:副理事长 2:行政
 */
public enum JobType {
    NOT_JOB(0, "未任命"), DEPUTY_DIRECTOR_GENERAL(1, "副理事长"), ADMINISTARATION(2, "行政");

    private Integer code;

    private String info;

    JobType(Integer code, String info) {
        this.code = code;
        this.info = info;
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

    public JobType valueOf(Integer code) {
        switch (code) {
            case 0:
                return NOT_JOB;
            case 1:
                return DEPUTY_DIRECTOR_GENERAL;
            case 2:
                return ADMINISTARATION;
            default:
                return null;
        }
    }
}
