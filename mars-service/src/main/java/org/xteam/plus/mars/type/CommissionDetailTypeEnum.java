package org.xteam.plus.mars.type;

import java.math.BigDecimal;

/**
 * 平台佣金
 */
public enum CommissionDetailTypeEnum {
    SALE_HEART_CARD(1, "售卡佣金", BigDecimal.valueOf(3));

    private int code;

    private String info;

    private BigDecimal amount;


    CommissionDetailTypeEnum(int code, String info, BigDecimal amount) {
        this.code = code;
        this.info = info;
        this.amount = amount;
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


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
