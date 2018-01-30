package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_USER_HEALTH_CARD]表
 */

public class UserHealthCard implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 发货周期类型
     */
    private Integer sendPeriodMode;

    /**
     * 发货周期
     */
    private Integer sendPeriod;

    /**
     * 发货总次数
     */
    private Integer sendTotalCount;

    /**
     * 已发货次数
     */
    private Integer sendCount;

    /**
     * 产品ID
     */
    private java.math.BigDecimal productId;

    /**
     * 会员卡有效截止日期
     */
    private java.util.Date cardDeadline;

    /**
     * 卡类型
     */
    private Integer productType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 有效期类型
     */
    private Integer survivalPeriodMode;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 有效期期数
     */
    private Integer survivalPeriodNum;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 购卡用户ID
     */
    private java.math.BigDecimal buyerUserId;

    /**
     * 激活用户ID
     */
    private java.math.BigDecimal activateUserId;

    /**
     * 卡号
     */
    private java.math.BigDecimal cardNo;

    /**
     * 会员卡激活日期
     */
    private java.util.Date cardActivateTime;

    /**
     * 激活用户信息
     */
    private UserInfo activateUserInfo;

    /**
     * 产品信息
     */
    private Product product;
    /**
     * 设置   sendPeriodMode
     *
     * @param sendPeriodMode (发货周期类型)
     */
    public UserHealthCard setSendPeriodMode(Integer sendPeriodMode) {
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
     * 设置   sendPeriod
     *
     * @param sendPeriod (发货周期)
     */
    public UserHealthCard setSendPeriod(Integer sendPeriod) {
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
    public UserHealthCard setSendTotalCount(Integer sendTotalCount) {
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
     * 设置   sendCount
     *
     * @param sendCount (已发货次数)
     */
    public UserHealthCard setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
        return this;
    }

    /**
     * 获取   sendCount (已发货次数)
     *
     * @return
     */
    public Integer getSendCount() {
        return this.sendCount;
    }

    /**
     * 设置   productId
     *
     * @param productId (产品ID)
     */
    public UserHealthCard setProductId(java.math.BigDecimal productId) {
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
     * 设置   cardDeadline
     *
     * @param cardDeadline (会员卡有效截止日期)
     */
    public UserHealthCard setCardDeadline(java.util.Date cardDeadline) {
        this.cardDeadline = cardDeadline;
        return this;
    }

    /**
     * 获取   cardDeadline (会员卡有效截止日期)
     *
     * @return
     */
    public java.util.Date getCardDeadline() {
        return this.cardDeadline;
    }

    /**
     * 设置   productType
     *
     * @param productType (卡类型)
     */
    public UserHealthCard setProductType(Integer productType) {
        this.productType = productType;
        return this;
    }

    /**
     * 获取   productType (卡类型)
     *
     * @return
     */
    public Integer getProductType() {
        return this.productType;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public UserHealthCard setStatus(Integer status) {
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
     * 设置   survivalPeriodMode
     *
     * @param survivalPeriodMode (有效期类型)
     */
    public UserHealthCard setSurvivalPeriodMode(Integer survivalPeriodMode) {
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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public UserHealthCard setCreated(java.util.Date created) {
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
     * 设置   survivalPeriodNum
     *
     * @param survivalPeriodNum (有效期期数)
     */
    public UserHealthCard setSurvivalPeriodNum(Integer survivalPeriodNum) {
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
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public UserHealthCard setUpdated(java.util.Date updated) {
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
     * 设置   buyerUserId
     *
     * @param buyerUserId (购卡用户ID)
     */
    public UserHealthCard setBuyerUserId(java.math.BigDecimal buyerUserId) {
        this.buyerUserId = buyerUserId;
        return this;
    }

    /**
     * 获取   buyerUserId (购卡用户ID)
     *
     * @return
     */
    public java.math.BigDecimal getBuyerUserId() {
        return this.buyerUserId;
    }

    /**
     * 设置   activateUserId
     *
     * @param activateUserId (激活用户ID)
     */
    public UserHealthCard setActivateUserId(java.math.BigDecimal activateUserId) {
        this.activateUserId = activateUserId;
        return this;
    }

    /**
     * 获取   activateUserId (激活用户ID)
     *
     * @return
     */
    public java.math.BigDecimal getActivateUserId() {
        return this.activateUserId;
    }

    /**
     * 设置   cardNo
     *
     * @param cardNo (卡号)
     */
    public UserHealthCard setCardNo(java.math.BigDecimal cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    /**
     * 获取   cardNo (卡号)
     *
     * @return
     */
    public java.math.BigDecimal getCardNo() {
        return this.cardNo;
    }

    /**
     * 设置   cardActivateTime
     *
     * @param cardActivateTime (会员卡激活日期)
     */
    public UserHealthCard setCardActivateTime(java.util.Date cardActivateTime) {
        this.cardActivateTime = cardActivateTime;
        return this;
    }

    /**
     * 获取   cardActivateTime (会员卡激活日期)
     *
     * @return
     */
    public java.util.Date getCardActivateTime() {
        return this.cardActivateTime;
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
    public UserHealthCard setStart(Integer start) {
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
    public UserHealthCard setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public UserInfo getActivateUserInfo() {
        return activateUserInfo;
    }

    public void setActivateUserInfo(UserInfo activateUserInfo) {
        this.activateUserInfo = activateUserInfo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
