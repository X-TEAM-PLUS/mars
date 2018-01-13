package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_LOCAL_COUNCIL_MEMBER]表
 */

public class LocalCouncilMember implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 地方理事会成员ID
     */
    private java.math.BigDecimal councilMemberId;

    /**
     * 地方理事会ID
     */
    private java.math.BigDecimal councilId;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 任命时间
     */
    private java.util.Date grantTime;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 职位类型
     */
    private Integer jobType;


    /**
     * 设置   councilMemberId
     *
     * @param councilMemberId (地方理事会成员ID)
     */
    public LocalCouncilMember setCouncilMemberId(java.math.BigDecimal councilMemberId) {
        this.councilMemberId = councilMemberId;
        return this;
    }

    /**
     * 获取   councilMemberId (地方理事会成员ID)
     *
     * @return
     */
    public java.math.BigDecimal getCouncilMemberId() {
        return this.councilMemberId;
    }

    /**
     * 设置   councilId
     *
     * @param councilId (地方理事会ID)
     */
    public LocalCouncilMember setCouncilId(java.math.BigDecimal councilId) {
        this.councilId = councilId;
        return this;
    }

    /**
     * 获取   councilId (地方理事会ID)
     *
     * @return
     */
    public java.math.BigDecimal getCouncilId() {
        return this.councilId;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public LocalCouncilMember setCreated(java.util.Date created) {
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
    public LocalCouncilMember setUpdated(java.util.Date updated) {
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
     * 设置   grantTime
     *
     * @param grantTime (任命时间)
     */
    public LocalCouncilMember setGrantTime(java.util.Date grantTime) {
        this.grantTime = grantTime;
        return this;
    }

    /**
     * 获取   grantTime (任命时间)
     *
     * @return
     */
    public java.util.Date getGrantTime() {
        return this.grantTime;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public LocalCouncilMember setUserId(java.math.BigDecimal userId) {
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
     * 设置   jobType
     *
     * @param jobType (职位类型)
     */
    public LocalCouncilMember setJobType(Integer jobType) {
        this.jobType = jobType;
        return this;
    }

    /**
     * 获取   jobType (职位类型)
     *
     * @return
     */
    public Integer getJobType() {
        return this.jobType;
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
    public LocalCouncilMember setStart(Integer start) {
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
    public LocalCouncilMember setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
