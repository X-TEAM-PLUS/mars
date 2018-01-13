package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_USER_SALES_INFO]表
 */

public class UserSalesInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 团队人数
     */
    private Integer teamPeopleNumber;

    /**
     * 团队补贴收益
     */
    private java.math.BigDecimal teamProfitAmount;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 推广人数
     */
    private Integer promotionNumber;


    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public UserSalesInfo setUserId(java.math.BigDecimal userId) {
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
     * 设置   teamPeopleNumber
     *
     * @param teamPeopleNumber (团队人数)
     */
    public UserSalesInfo setTeamPeopleNumber(Integer teamPeopleNumber) {
        this.teamPeopleNumber = teamPeopleNumber;
        return this;
    }

    /**
     * 获取   teamPeopleNumber (团队人数)
     *
     * @return
     */
    public Integer getTeamPeopleNumber() {
        return this.teamPeopleNumber;
    }

    /**
     * 设置   teamProfitAmount
     *
     * @param teamProfitAmount (团队补贴收益)
     */
    public UserSalesInfo setTeamProfitAmount(java.math.BigDecimal teamProfitAmount) {
        this.teamProfitAmount = teamProfitAmount;
        return this;
    }

    /**
     * 获取   teamProfitAmount (团队补贴收益)
     *
     * @return
     */
    public java.math.BigDecimal getTeamProfitAmount() {
        return this.teamProfitAmount;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public UserSalesInfo setCreated(java.util.Date created) {
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
    public UserSalesInfo setUpdated(java.util.Date updated) {
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
     * 设置   promotionNumber
     *
     * @param promotionNumber (推广人数)
     */
    public UserSalesInfo setPromotionNumber(Integer promotionNumber) {
        this.promotionNumber = promotionNumber;
        return this;
    }

    /**
     * 获取   promotionNumber (推广人数)
     *
     * @return
     */
    public Integer getPromotionNumber() {
        return this.promotionNumber;
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
    public UserSalesInfo setStart(Integer start) {
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
    public UserSalesInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
