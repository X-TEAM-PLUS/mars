package org.xteam.plus.mars.gateway.service.provider.impl.body.rsp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserDetectionInfoRspVO implements Serializable {

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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getCheckReport() {
        return checkReport;
    }

    public void setCheckReport(String checkReport) {
        this.checkReport = checkReport;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public BigDecimal getCheckRecordId() {
        return checkRecordId;
    }

    public void setCheckRecordId(BigDecimal checkRecordId) {
        this.checkRecordId = checkRecordId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
