package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class InsuranceProductReqVO implements Serializable{

    public BigDecimal insuranceProductNo;

    public BigDecimal getInsuranceProductNo() {
        return insuranceProductNo;
    }

    public void setInsuranceProductNo(BigDecimal insuranceProductNo) {
        this.insuranceProductNo = insuranceProductNo;
    }
}
