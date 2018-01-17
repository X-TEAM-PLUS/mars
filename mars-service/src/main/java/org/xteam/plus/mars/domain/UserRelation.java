package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_USER_RELATION]表
 */

public class UserRelation implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 关系流水号
     */
    private java.math.BigDecimal relationId;

    /**
     * 推荐人ID
     */
    private java.math.BigDecimal refereeUserId;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 设置   relationId
     *
     * @param relationId (关系流水号)
     */
    public UserRelation setRelationId(java.math.BigDecimal relationId) {
        this.relationId = relationId;
        return this;
    }

    /**
     * 获取   relationId (关系流水号)
     *
     * @return
     */
    public java.math.BigDecimal getRelationId() {
        return this.relationId;
    }

    /**
     * 设置   refereeUserId
     *
     * @param refereeUserId (推荐人ID)
     */
    public UserRelation setRefereeUserId(java.math.BigDecimal refereeUserId) {
        this.refereeUserId = refereeUserId;
        return this;
    }

    /**
     * 获取   refereeUserId (推荐人ID)
     *
     * @return
     */
    public java.math.BigDecimal getRefereeUserId() {
        return this.refereeUserId;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public UserRelation setUserId(java.math.BigDecimal userId) {
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
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public UserRelation setUpdated(java.util.Date updated) {
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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public UserRelation setCreated(java.util.Date created) {
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
    public UserRelation setStart(Integer start) {
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
    public UserRelation setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
