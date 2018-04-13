package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-04-13
 * Time: 16:56
 * TableName: [T_MARS_COMMISSION_DETAIL]表
 */

public class CommissionDetail implements Serializable {

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
     * 订单号
     */
    private java.math.BigDecimal ordreNo;

    /**
     * 健康卡号
     */
    private java.math.BigDecimal cardNo;

    /**
     * 佣金金额
     */
    private java.math.BigDecimal commissionAmount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 流水号
     */
    private java.math.BigDecimal id;

    /**
     * 佣金类型
     */
    private Integer commissionType;


    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public CommissionDetail setCreated(java.util.Date created) {
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
    public CommissionDetail setUpdated(java.util.Date updated) {
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
     * 设置   ordreNo
     *
     * @param ordreNo (订单号)
     */
    public CommissionDetail setOrdreNo(java.math.BigDecimal ordreNo) {
        this.ordreNo = ordreNo;
        return this;
    }

    /**
     * 获取   ordreNo (订单号)
     *
     * @return
     */
    public java.math.BigDecimal getOrdreNo() {
        return this.ordreNo;
    }

    /**
     * 设置   cardNo
     *
     * @param cardNo (健康卡号)
     */
    public CommissionDetail setCardNo(java.math.BigDecimal cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    /**
     * 获取   cardNo (健康卡号)
     *
     * @return
     */
    public java.math.BigDecimal getCardNo() {
        return this.cardNo;
    }

    /**
     * 设置   commissionAmount
     *
     * @param commissionAmount (佣金金额)
     */
    public CommissionDetail setCommissionAmount(java.math.BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
        return this;
    }

    /**
     * 获取   commissionAmount (佣金金额)
     *
     * @return
     */
    public java.math.BigDecimal getCommissionAmount() {
        return this.commissionAmount;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public CommissionDetail setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取   status (状态)
     *
     * @return
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置   id
     *
     * @param id (流水号)
     */
    public CommissionDetail setId(java.math.BigDecimal id) {
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
     * 设置   commissionType
     *
     * @param commissionType (佣金类型)
     */
    public CommissionDetail setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
        return this;
    }

    /**
     * 获取   commissionType (佣金类型)
     *
     * @return
     */
    public Integer getCommissionType() {
        return this.commissionType;
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
    public CommissionDetail setStart(Integer start) {
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
    public CommissionDetail setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
