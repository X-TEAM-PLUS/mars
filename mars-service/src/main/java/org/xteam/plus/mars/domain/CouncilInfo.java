package org.xteam.plus.mars.domain;

import org.xteam.plus.mars.type.JobType;

import java.io.Serializable;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_COUNCIL_INFO]表
 */

public class CouncilInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 理事会ID
     */
    private java.math.BigDecimal councilId;

    /**
     * 城市编号
     */
    private java.math.BigDecimal cityNo;

    /**
     * 开通日期
     */
    private java.util.Date openDate;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 用户
     */
    private String userInfo;

    /**
     * 职务
     */
    private JobType jobType;

    /**
     * 地方常务理事所有成员
     */
    private List<LocalCouncilMember> localCouncilMemberList;

    /**
     * 设置   councilId
     *
     * @param councilId (理事会ID)
     */
    public CouncilInfo setCouncilId(java.math.BigDecimal councilId) {
        this.councilId = councilId;
        return this;
    }

    /**
     * 获取   councilId (理事会ID)
     *
     * @return
     */
    public java.math.BigDecimal getCouncilId() {
        return this.councilId;
    }

    /**
     * 设置   cityNo
     *
     * @param cityNo (城市编号)
     */
    public CouncilInfo setCityNo(java.math.BigDecimal cityNo) {
        this.cityNo = cityNo;
        return this;
    }

    /**
     * 获取   cityNo (城市编号)
     *
     * @return
     */
    public java.math.BigDecimal getCityNo() {
        return this.cityNo;
    }

    /**
     * 设置   openDate
     *
     * @param openDate (开通日期)
     */
    public CouncilInfo setOpenDate(java.util.Date openDate) {
        this.openDate = openDate;
        return this;
    }

    /**
     * 获取   openDate (开通日期)
     *
     * @return
     */
    public java.util.Date getOpenDate() {
        return this.openDate;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public CouncilInfo setCreated(java.util.Date created) {
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
    public CouncilInfo setUpdated(java.util.Date updated) {
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
    public CouncilInfo setStart(Integer start) {
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
    public CouncilInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public List<LocalCouncilMember> getLocalCouncilMemberList() {
        return localCouncilMemberList;
    }

    public void setLocalCouncilMemberList(List<LocalCouncilMember> localCouncilMemberList) {
        this.localCouncilMemberList = localCouncilMemberList;
    }
}
