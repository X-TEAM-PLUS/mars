package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_PRODUCT]表
 */

public class Product implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 有效期类型
     */
    private Integer survivalPeriodMode;

    /**
     * 有效期期数
     */
    private Integer survivalPeriodNum;

    /**
     * 发货周期类型
     */
    private Integer sendPeriodMode;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 产品ID
     */
    private java.math.BigDecimal productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 发货周期
     */
    private Integer sendPeriod;

    /**
     * 发货总次数
     */
    private Integer sendTotalCount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private java.util.Date created;


    /**
     * 设置   cardType
     *
     * @param cardType (卡类型)
     */
    public Product setCardType(Integer cardType) {
        this.cardType = cardType;
        return this;
    }

    /**
     * 获取   cardType (卡类型)
     *
     * @return
     */
    public Integer getCardType() {
        return this.cardType;
    }

    /**
     * 设置   survivalPeriodMode
     *
     * @param survivalPeriodMode (有效期类型)
     */
    public Product setSurvivalPeriodMode(Integer survivalPeriodMode) {
        this.survivalPeriodMode = survivalPeriodMode;
        return this;
    }

    /**
     * 获取   survivalPeriodMode (有效期类型)
     *
     * @return
     */
    public Integer getSurvivalPeriodMode() {
        return this.survivalPeriodMode;
    }

    /**
     * 设置   survivalPeriodNum
     *
     * @param survivalPeriodNum (有效期期数)
     */
    public Product setSurvivalPeriodNum(Integer survivalPeriodNum) {
        this.survivalPeriodNum = survivalPeriodNum;
        return this;
    }

    /**
     * 获取   survivalPeriodNum (有效期期数)
     *
     * @return
     */
    public Integer getSurvivalPeriodNum() {
        return this.survivalPeriodNum;
    }

    /**
     * 设置   sendPeriodMode
     *
     * @param sendPeriodMode (发货周期类型)
     */
    public Product setSendPeriodMode(Integer sendPeriodMode) {
        this.sendPeriodMode = sendPeriodMode;
        return this;
    }

    /**
     * 获取   sendPeriodMode (发货周期类型)
     *
     * @return
     */
    public Integer getSendPeriodMode() {
        return this.sendPeriodMode;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public Product setUpdated(java.util.Date updated) {
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
     * 设置   productId
     *
     * @param productId (产品ID)
     */
    public Product setProductId(java.math.BigDecimal productId) {
        this.productId = productId;
        return this;
    }

    /**
     * 获取   productId (产品ID)
     *
     * @return
     */
    public java.math.BigDecimal getProductId() {
        return this.productId;
    }

    /**
     * 设置   productName
     *
     * @param productName (产品名称)
     */
    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * 获取   productName (产品名称)
     *
     * @return
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 设置   sendPeriod
     *
     * @param sendPeriod (发货周期)
     */
    public Product setSendPeriod(Integer sendPeriod) {
        this.sendPeriod = sendPeriod;
        return this;
    }

    /**
     * 获取   sendPeriod (发货周期)
     *
     * @return
     */
    public Integer getSendPeriod() {
        return this.sendPeriod;
    }

    /**
     * 设置   sendTotalCount
     *
     * @param sendTotalCount (发货总次数)
     */
    public Product setSendTotalCount(Integer sendTotalCount) {
        this.sendTotalCount = sendTotalCount;
        return this;
    }

    /**
     * 获取   sendTotalCount (发货总次数)
     *
     * @return
     */
    public Integer getSendTotalCount() {
        return this.sendTotalCount;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public Product setStatus(Integer status) {
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
    public Product setCreated(java.util.Date created) {
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
    public Product setStart(Integer start) {
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
    public Product setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
