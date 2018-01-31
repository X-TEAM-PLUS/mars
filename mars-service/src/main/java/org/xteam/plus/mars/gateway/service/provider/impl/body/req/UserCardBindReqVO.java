package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserCardBindReqVO implements Serializable {

    private BigDecimal userId;

    private BigDecimal cardNo;

    private String cardUserName;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getCardNo() {
        return cardNo;
    }

    public void setCardNo(BigDecimal cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }
}
