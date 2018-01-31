package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class RecordReqVO implements Serializable{

    public BigDecimal recordId;

    public BigDecimal getRecordId() {
        return recordId;
    }

    public void setRecordId(BigDecimal recordId) {
        this.recordId = recordId;
    }
}
