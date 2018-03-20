package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_APPLY_INFO]表
 */

public class ApplyInfo implements Serializable {

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
     * 申请原因
     */
    private String applyReason;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 申请流水号
     */
    private java.math.BigDecimal applyId;

    /**
     * 申请方式
     */
    private Integer applyWay;

    /**
     * 申请类型
     */
    private Integer applyType;

    private String realName;

    private String mobile;

    private String idNumber;

    private String interests;


    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public ApplyInfo setCreated(java.util.Date created) {
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
    public ApplyInfo setUpdated(java.util.Date updated) {
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
     * 设置   applyReason
     *
     * @param applyReason (申请原因)
     */
    public ApplyInfo setApplyReason(String applyReason) {
        this.applyReason = applyReason;
        return this;
    }

    /**
     * 获取   applyReason (申请原因)
     *
     * @return
     */
    public String getApplyReason() {
        return this.applyReason;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public ApplyInfo setStatus(Integer status) {
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
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public ApplyInfo setUserId(java.math.BigDecimal userId) {
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
     * 设置   applyId
     *
     * @param applyId (申请流水号)
     */
    public ApplyInfo setApplyId(java.math.BigDecimal applyId) {
        this.applyId = applyId;
        return this;
    }

    /**
     * 获取   applyId (申请流水号)
     *
     * @return
     */
    public java.math.BigDecimal getApplyId() {
        return this.applyId;
    }

    /**
     * 设置   applyWay
     *
     * @param applyWay (申请方式)
     */
    public ApplyInfo setApplyWay(Integer applyWay) {
        this.applyWay = applyWay;
        return this;
    }

    /**
     * 获取   applyWay (申请方式)
     *
     * @return
     */
    public Integer getApplyWay() {
        return this.applyWay;
    }

    /**
     * 设置   applyType
     *
     * @param applyType (申请类型)
     */
    public ApplyInfo setApplyType(Integer applyType) {
        this.applyType = applyType;
        return this;
    }

    /**
     * 获取   applyType (申请类型)
     *
     * @return
     */
    public Integer getApplyType() {
        return this.applyType;
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
    public ApplyInfo setStart(Integer start) {
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
    public ApplyInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
