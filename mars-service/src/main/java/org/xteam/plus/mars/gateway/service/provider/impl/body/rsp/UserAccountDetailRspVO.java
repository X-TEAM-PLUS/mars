package org.xteam.plus.mars.gateway.service.provider.impl.body.rsp;

import org.xteam.plus.mars.type.AccountDetailTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserAccountDetailRspVO implements Serializable {


    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 业务类型
     */
    private Integer businesseType;

    /**
     * 操作方向
     */
    private Integer operationDirection;

    /**
     * 金额
     */
    private java.math.BigDecimal amount;

    /**
     * 业务单号
     */
    private java.math.BigDecimal serviceNo;

    /**
     * 流水号
     */
    private java.math.BigDecimal id;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 微信头像地址
     */
    private String wxHeadPortrait;

    /**
     * 业务类型名称
     * @return
     */
    private String businesseTypeName;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getBusinesseType() {
        return businesseType;
    }

    public void setBusinesseType(Integer businesseType) {
        this.businesseType = businesseType;
        this.businesseTypeName = AccountDetailTypeEnum.valueOf(businesseType).getInfo();
    }

    public Integer getOperationDirection() {
        return operationDirection;
    }

    public void setOperationDirection(Integer operationDirection) {
        this.operationDirection = operationDirection;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(BigDecimal serviceNo) {
        this.serviceNo = serviceNo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getBusinesseTypeName() {
        return businesseTypeName;
    }

    public void setBusinesseTypeName(String businesseTypeName) {
        this.businesseTypeName = businesseTypeName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWxHeadPortrait() {
        return wxHeadPortrait;
    }

    public void setWxHeadPortrait(String wxHeadPortrait) {
        this.wxHeadPortrait = wxHeadPortrait;
    }
}
