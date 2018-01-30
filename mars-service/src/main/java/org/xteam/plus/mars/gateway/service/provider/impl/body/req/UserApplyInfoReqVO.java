package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import org.xteam.plus.mars.type.ApplayTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户申请记录请求
 */
public class UserApplyInfoReqVO implements Serializable {

    private BigDecimal userId;

    private ApplayTypeEnum applayTypeEnum;

    private String reason;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public ApplayTypeEnum getApplayTypeEnum() {
        return applayTypeEnum;
    }

    public void setApplayTypeEnum(ApplayTypeEnum applayTypeEnum) {
        this.applayTypeEnum = applayTypeEnum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}