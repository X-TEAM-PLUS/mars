package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserCardBindReqVO implements Serializable {


    private Long cardNo;

    private String cardUserName;

    private BigDecimal bankId;

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }

    public BigDecimal getBankId() {
        return bankId;
    }

    public void setBankId(BigDecimal bankId) {
        this.bankId = bankId;
    }
}
