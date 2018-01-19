package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_WITHDRAW_RECORD]表
 */

public class WithdrawRecord implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 打款账号
     */
    private String bankAccountNo;

    /**
     * 流水号
     */
    private java.math.BigDecimal id;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 提取金额
     */
    private java.math.BigDecimal amount;

    /**
     * 申请时间
     */
    private java.util.Date applyTime;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 第三方支付流水号
     */
    private String transactionNo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 提款人姓名
     */
    private String bankAccountName;

    /**
     * 打款时间
     */
    private java.util.Date payTime;


    /**
     * 余额
     */
    private java.math.BigDecimal balanceAmount;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 设置   bankAccountNo
     *
     * @param bankAccountNo (打款账号)
     */
    public WithdrawRecord setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    /**
     * 获取   bankAccountNo (打款账号)
     *
     * @return
     */
    public String getBankAccountNo() {
        return this.bankAccountNo;
    }

    /**
     * 设置   id
     *
     * @param id (流水号)
     */
    public WithdrawRecord setId(java.math.BigDecimal id) {
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
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public WithdrawRecord setUpdated(java.util.Date updated) {
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
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public WithdrawRecord setUserId(java.math.BigDecimal userId) {
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
     * 设置   amount
     *
     * @param amount (提取金额)
     */
    public WithdrawRecord setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * 获取   amount (提取金额)
     *
     * @return
     */
    public java.math.BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置   applyTime
     *
     * @param applyTime (申请时间)
     */
    public WithdrawRecord setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
        return this;
    }

    /**
     * 获取   applyTime (申请时间)
     *
     * @return
     */
    public java.util.Date getApplyTime() {
        return this.applyTime;
    }

    /**
     * 设置   payWay
     *
     * @param payWay (支付方式)
     */
    public WithdrawRecord setPayWay(Integer payWay) {
        this.payWay = payWay;
        return this;
    }

    /**
     * 获取   payWay (支付方式)
     *
     * @return
     */
    public Integer getPayWay() {
        return this.payWay;
    }

    /**
     * 设置   transactionNo
     *
     * @param transactionNo (第三方支付流水号)
     */
    public WithdrawRecord setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
        return this;
    }

    /**
     * 获取   transactionNo (第三方支付流水号)
     *
     * @return
     */
    public String getTransactionNo() {
        return this.transactionNo;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public WithdrawRecord setStatus(Integer status) {
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
    public WithdrawRecord setCreated(java.util.Date created) {
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
     * 设置   bankAccountName
     *
     * @param bankAccountName (提款人姓名)
     */
    public WithdrawRecord setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    /**
     * 获取   bankAccountName (提款人姓名)
     *
     * @return
     */
    public String getBankAccountName() {
        return this.bankAccountName;
    }

    /**
     * 设置   payTime
     *
     * @param payTime (打款时间)
     */
    public WithdrawRecord setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
        return this;
    }

    /**
     * 获取   payTime (打款时间)
     *
     * @return
     */
    public java.util.Date getPayTime() {
        return this.payTime;
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
    public WithdrawRecord setStart(Integer start) {
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
    public WithdrawRecord setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 获取账户余额
     * @return
     */
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * 设置账户余额
     * @param balanceAmount
     */
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    /**
     * 获取手机号
     * @return
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置手机号
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
