package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_USER_INSURANCE]表
 */

public class UserInsurance implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 截止日期
     */
    private java.util.Date expirationDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 保险合同号
     */
    private String contractNo;

    /**
     * 投保人身份证号
     */
    private java.math.BigDecimal holderIdNumber;

    /**
     * 投保人
     */
    private String policyHolder;

    /**
     * 承保日期
     */
    private java.util.Date acceptInsuranceDate;

    /**
     * 用户保单流水号
     */
    private java.math.BigDecimal insuranceOrderId;

    /**
     * 保险产品编号
     */
    private java.math.BigDecimal insuranceProductNo;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;


    /**
     * 设置   expirationDate
     *
     * @param expirationDate (截止日期)
     */
    public UserInsurance setExpirationDate(java.util.Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    /**
     * 获取   expirationDate (截止日期)
     *
     * @return
     */
    public java.util.Date getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public UserInsurance setStatus(Integer status) {
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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public UserInsurance setCreated(java.util.Date created) {
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
    public UserInsurance setUpdated(java.util.Date updated) {
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
     * 设置   contractNo
     *
     * @param contractNo (保险合同号)
     */
    public UserInsurance setContractNo(String contractNo) {
        this.contractNo = contractNo;
        return this;
    }

    /**
     * 获取   contractNo (保险合同号)
     *
     * @return
     */
    public String getContractNo() {
        return this.contractNo;
    }

    /**
     * 设置   holderIdNumber
     *
     * @param holderIdNumber (投保人身份证号)
     */
    public UserInsurance setHolderIdNumber(java.math.BigDecimal holderIdNumber) {
        this.holderIdNumber = holderIdNumber;
        return this;
    }

    /**
     * 获取   holderIdNumber (投保人身份证号)
     *
     * @return
     */
    public java.math.BigDecimal getHolderIdNumber() {
        return this.holderIdNumber;
    }

    /**
     * 设置   policyHolder
     *
     * @param policyHolder (投保人)
     */
    public UserInsurance setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
        return this;
    }

    /**
     * 获取   policyHolder (投保人)
     *
     * @return
     */
    public String getPolicyHolder() {
        return this.policyHolder;
    }

    /**
     * 设置   acceptInsuranceDate
     *
     * @param acceptInsuranceDate (承保日期)
     */
    public UserInsurance setAcceptInsuranceDate(java.util.Date acceptInsuranceDate) {
        this.acceptInsuranceDate = acceptInsuranceDate;
        return this;
    }

    /**
     * 获取   acceptInsuranceDate (承保日期)
     *
     * @return
     */
    public java.util.Date getAcceptInsuranceDate() {
        return this.acceptInsuranceDate;
    }

    /**
     * 设置   insuranceOrderId
     *
     * @param insuranceOrderId (用户保单流水号)
     */
    public UserInsurance setInsuranceOrderId(java.math.BigDecimal insuranceOrderId) {
        this.insuranceOrderId = insuranceOrderId;
        return this;
    }

    /**
     * 获取   insuranceOrderId (用户保单流水号)
     *
     * @return
     */
    public java.math.BigDecimal getInsuranceOrderId() {
        return this.insuranceOrderId;
    }

    /**
     * 设置   insuranceProductNo
     *
     * @param insuranceProductNo (保险产品编号)
     */
    public UserInsurance setInsuranceProductNo(java.math.BigDecimal insuranceProductNo) {
        this.insuranceProductNo = insuranceProductNo;
        return this;
    }

    /**
     * 获取   insuranceProductNo (保险产品编号)
     *
     * @return
     */
    public java.math.BigDecimal getInsuranceProductNo() {
        return this.insuranceProductNo;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public UserInsurance setUserId(java.math.BigDecimal userId) {
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
    public UserInsurance setStart(Integer start) {
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
    public UserInsurance setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
