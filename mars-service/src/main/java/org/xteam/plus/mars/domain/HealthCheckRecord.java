package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_HEALTH_CHECK_RECORD]表
 */

public class HealthCheckRecord implements Serializable {

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
     * 上传时间
     */
    private java.util.Date uploadTime;

    /**
     * 检测报告
     */
    private String checkReport;

    /**
     * 检测结果
     */
    private String checkResult;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 检查记录ID
     */
    private java.math.BigDecimal checkRecordId;

    /**
     * 检测状态
     */
    private Integer checkStatus;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 检测时间
     */
    private java.util.Date checkTime;

    /**
     * 自测结果 1阴性 2弱阳性 3阳性 4强阳性
     */
    private Integer selfCheckResult;

    /**
     * 健康卡号
     */
    private java.math.BigDecimal cardNo;

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public HealthCheckRecord setUserId(java.math.BigDecimal userId) {
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
     * 设置   uploadTime
     *
     * @param uploadTime (上传时间)
     */
    public HealthCheckRecord setUploadTime(java.util.Date uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    /**
     * 获取   uploadTime (上传时间)
     *
     * @return
     */
    public java.util.Date getUploadTime() {
        return this.uploadTime;
    }

    /**
     * 设置   checkReport
     *
     * @param checkReport (检测报告)
     */
    public HealthCheckRecord setCheckReport(String checkReport) {
        this.checkReport = checkReport;
        return this;
    }

    /**
     * 获取   checkReport (检测报告)
     *
     * @return
     */
    public String getCheckReport() {
        return this.checkReport;
    }

    /**
     * 设置   checkResult
     *
     * @param checkResult (检测结果)
     */
    public HealthCheckRecord setCheckResult(String checkResult) {
        this.checkResult = checkResult;
        return this;
    }

    /**
     * 获取   checkResult (检测结果)
     *
     * @return
     */
    public String getCheckResult() {
        return this.checkResult;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public HealthCheckRecord setUpdated(java.util.Date updated) {
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
     * 设置   checkRecordId
     *
     * @param checkRecordId (检查记录ID)
     */
    public HealthCheckRecord setCheckRecordId(java.math.BigDecimal checkRecordId) {
        this.checkRecordId = checkRecordId;
        return this;
    }

    /**
     * 获取   checkRecordId (检查记录ID)
     *
     * @return
     */
    public java.math.BigDecimal getCheckRecordId() {
        return this.checkRecordId;
    }

    /**
     * 设置   checkStatus
     *
     * @param checkStatus (检测状态)
     */
    public HealthCheckRecord setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }

    /**
     * 获取   checkStatus (检测状态)
     *
     * @return
     */
    public Integer getCheckStatus() {
        return this.checkStatus;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public HealthCheckRecord setCreated(java.util.Date created) {
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
     * 设置   checkTime
     *
     * @param checkTime (检测时间)
     */
    public HealthCheckRecord setCheckTime(java.util.Date checkTime) {
        this.checkTime = checkTime;
        return this;
    }

    /**
     * 获取   checkTime (检测时间)
     *
     * @return
     */
    public java.util.Date getCheckTime() {
        return this.checkTime;
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
    public HealthCheckRecord setStart(Integer start) {
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
    public HealthCheckRecord setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 获取自测结果
     * @return  自测结果 1阴性 2弱阳性 3阳性 4强阳性
     */
    public Integer getSelfCheckResult() {
        return selfCheckResult;
    }

    /**
     * 设置自测结果
     * @param selfCheckResult  自测结果 1阴性 2弱阳性 3阳性 4强阳性
     * @return
     */
    public HealthCheckRecord setSelfCheckResult(Integer selfCheckResult) {
        this.selfCheckResult = selfCheckResult;
        return this;
    }

    /**
     * 获取卡号
     * @return
     */
    public BigDecimal getCardNo() {
        return cardNo;
    }


    /**
     * 设置卡号
     * @param cardNo
     * @return
     */
    public HealthCheckRecord setCardNo(BigDecimal cardNo) {
        this.cardNo = cardNo;
        return this;
    }
}
