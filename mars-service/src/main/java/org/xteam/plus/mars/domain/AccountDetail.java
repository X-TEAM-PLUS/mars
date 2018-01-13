package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_ACCOUNT_DETAIL]表
 */

public class AccountDetail implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public AccountDetail setCreated(java.util.Date created) {
        this.created = created;
        return this;
    }

    /**
     * 获取   created (创建时间)
     *
     * @return
     */
    public java.util.Date getCreated() {
        return this.created;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public AccountDetail setUpdated(java.util.Date updated) {
        this.updated = updated;
        return this;
    }

    /**
     * 获取   updated (更新时间)
     *
     * @return
     */
    public java.util.Date getUpdated() {
        return this.updated;
    }

    /**
     * 设置   businesseType
     *
     * @param businesseType (业务类型)
     */
    public AccountDetail setBusinesseType(Integer businesseType) {
        this.businesseType = businesseType;
        return this;
    }

    /**
     * 获取   businesseType (业务类型)
     *
     * @return
     */
    public Integer getBusinesseType() {
        return this.businesseType;
    }

    /**
     * 设置   operationDirection
     *
     * @param operationDirection (操作方向)
     */
    public AccountDetail setOperationDirection(Integer operationDirection) {
        this.operationDirection = operationDirection;
        return this;
    }

    /**
     * 获取   operationDirection (操作方向)
     *
     * @return
     */
    public Integer getOperationDirection() {
        return this.operationDirection;
    }

    /**
     * 设置   amount
     *
     * @param amount (金额)
     */
    public AccountDetail setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * 获取   amount (金额)
     *
     * @return
     */
    public java.math.BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置   serviceNo
     *
     * @param serviceNo (业务单号)
     */
    public AccountDetail setServiceNo(java.math.BigDecimal serviceNo) {
        this.serviceNo = serviceNo;
        return this;
    }

    /**
     * 获取   serviceNo (业务单号)
     *
     * @return
     */
    public java.math.BigDecimal getServiceNo() {
        return this.serviceNo;
    }

    /**
     * 设置   id
     *
     * @param id (流水号)
     */
    public AccountDetail setId(java.math.BigDecimal id) {
        this.id = id;
        return this;
    }

    /**
     * 获取   id (流水号)
     *
     * @return
     */
    public java.math.BigDecimal getId() {
        return this.id;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public AccountDetail setUserId(java.math.BigDecimal userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取   userId (用户ID)
     *
     * @return
     */
    public java.math.BigDecimal getUserId() {
        return this.userId;
    }


    /**
     * 获取开始下标
     *
     * @return
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置开始下标
     *
     * @param start
     */
    public AccountDetail setStart(Integer start) {
        this.start = start;
        return this;
    }

    /**
     * 获取界限值
     *
     * @return
     */
    public Integer getLimit() {
        return limit;
    }


    /**
     * 设置界限值
     *
     * @param limit
     */
    public AccountDetail setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
