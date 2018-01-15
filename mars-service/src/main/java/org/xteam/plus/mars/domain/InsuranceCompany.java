package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_INSURANCE_COMPANY]表
 */

public class InsuranceCompany implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 保险公司ID
     */
    private java.math.BigDecimal insuranceCompanyId;

    /**
     * 保险公司名称
     */
    private String insuranceCompanyName;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 手机号
     */
    private String mobileNo;


    /**
     * 联系地址
     */
    private String linkAddress;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private java.util.Date created;


    /**
     * 设置   insuranceCompanyId
     *
     * @param insuranceCompanyId (保险公司ID)
     */
    public InsuranceCompany setInsuranceCompanyId(java.math.BigDecimal insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
        return this;
    }

    /**
     * 获取   insuranceCompanyId (保险公司ID)
     *
     * @return
     */
    public java.math.BigDecimal getInsuranceCompanyId() {
        return this.insuranceCompanyId;
    }

    /**
     * 设置   insuranceCompanyName
     *
     * @param insuranceCompanyName (保险公司名称)
     */
    public InsuranceCompany setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
        return this;
    }

    /**
     * 获取   insuranceCompanyName (保险公司名称)
     *
     * @return
     */
    public String getInsuranceCompanyName() {
        return this.insuranceCompanyName;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public InsuranceCompany setUpdated(java.util.Date updated) {
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
     * 设置   linkMan
     *
     * @param linkMan (联系人)
     */
    public InsuranceCompany setLinkMan(String linkMan) {
        this.linkMan = linkMan;
        return this;
    }

    /**
     * 获取   linkMan (联系人)
     *
     * @return
     */
    public String getLinkMan() {
        return this.linkMan;
    }

    /**
     * 设置   linkAddress
     *
     * @param linkAddress (联系地址)
     */
    public InsuranceCompany setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
        return this;
    }

    /**
     * 获取   linkAddress (联系地址)
     *
     * @return
     */
    public String getLinkAddress() {
        return this.linkAddress;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public InsuranceCompany setStatus(Integer status) {
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
    public InsuranceCompany setCreated(java.util.Date created) {
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
    public InsuranceCompany setStart(Integer start) {
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
    public InsuranceCompany setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 获取手机号
     * @return
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置手机号
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
