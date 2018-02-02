package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_BANK_CARD]表
 */

public class BankCard implements Serializable {

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
     * 银行卡账户名称
     */
    private String bankAccountName;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 用户银行卡ID
     */
    private java.math.BigDecimal bankCardId;

    /**
     * 银行卡号
     */
    private String bankAccountNo;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 银行编码
     */
    private BigDecimal bankId;


    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public BankCard setCreated(java.util.Date created) {
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
     * @param bankAccountName (银行卡账户名称)
     */
    public BankCard setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    /**
     * 获取   bankAccountName (银行卡账户名称)
     *
     * @return
     */
    public String getBankAccountName() {
        return this.bankAccountName;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public BankCard setUpdated(java.util.Date updated) {
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
     * 设置   bankCardId
     *
     * @param bankCardId (用户银行卡ID)
     */
    public BankCard setBankCardId(java.math.BigDecimal bankCardId) {
        this.bankCardId = bankCardId;
        return this;
    }

    /**
     * 获取   bankCardId (用户银行卡ID)
     *
     * @return
     */
    public java.math.BigDecimal getBankCardId() {
        return this.bankCardId;
    }

    /**
     * 设置   bankAccountNo
     *
     * @param bankAccountNo (银行卡号)
     */
    public BankCard setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    /**
     * 获取   bankAccountNo (银行卡号)
     *
     * @return
     */
    public String getBankAccountNo() {
        return this.bankAccountNo;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public BankCard setUserId(java.math.BigDecimal userId) {
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
    public BankCard setStart(Integer start) {
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
    public BankCard setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }


    public BigDecimal getBankId() {
        return bankId;
    }

    public void setBankId(BigDecimal bankId) {
        this.bankId = bankId;
    }
}
