package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_INSURANCE_PRODUCT]表
 */

public class InsuranceProduct implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 详情
     */
    private String remark;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 保险产品编号
     */
    private java.math.BigDecimal insuranceProductNo;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 保险名称
     */
    private String insuranceName;

    /**
     * 保险公司ID
     */
    private java.math.BigDecimal insuranceCompanyId;

    /**
     * 价格
     */
    private java.math.BigDecimal price;

    /**
     * 展示图
     */
    private String imgInfo;

    /**
     * 有效期期数
     */
    private Integer periodsNum;

    /**
     * 有效期类型
     */
    private Integer periodsMode;

    /**
     * 保险公司
     */
    private InsuranceCompany insuranceCompany;


    /**
     * 设置   remark
     *
     * @param remark (详情)
     */
    public InsuranceProduct setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 获取   remark (详情)
     *
     * @return
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public InsuranceProduct setCreated(java.util.Date created) {
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
     * 设置   status
     *
     * @param status (状态)
     */
    public InsuranceProduct setStatus(Integer status) {
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
     * 设置   insuranceProductNo
     *
     * @param insuranceProductNo (保险产品编号)
     */
    public InsuranceProduct setInsuranceProductNo(java.math.BigDecimal insuranceProductNo) {
        this.insuranceProductNo = insuranceProductNo;
        return this;
    }

    /**
     * 获取   insuranceProductNo (保险产品编号)
     *
     * @return
     */
    public java.math.BigDecimal getInsuranceProductNo() {
        return this.insuranceProductNo;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public InsuranceProduct setUpdated(java.util.Date updated) {
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
     * 设置   insuranceName
     *
     * @param insuranceName (保险名称)
     */
    public InsuranceProduct setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
        return this;
    }

    /**
     * 获取   insuranceName (保险名称)
     *
     * @return
     */
    public String getInsuranceName() {
        return this.insuranceName;
    }

    /**
     * 设置   insuranceCompanyId
     *
     * @param insuranceCompanyId (保险公司ID)
     */
    public InsuranceProduct setInsuranceCompanyId(java.math.BigDecimal insuranceCompanyId) {
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
     * 设置   price
     *
     * @param price (价格)
     */
    public InsuranceProduct setPrice(java.math.BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * 获取   price (价格)
     *
     * @return
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 设置   imgInfo
     *
     * @param imgInfo (展示图)
     */
    public InsuranceProduct setImgInfo(String imgInfo) {
        this.imgInfo = imgInfo;
        return this;
    }

    /**
     * 获取   imgInfo (展示图)
     *
     * @return
     */
    public String getImgInfo() {
        return this.imgInfo;
    }

    /**
     * 设置   periodsNum
     *
     * @param periodsNum (有效期期数)
     */
    public InsuranceProduct setPeriodsNum(Integer periodsNum) {
        this.periodsNum = periodsNum;
        return this;
    }

    /**
     * 获取   periodsNum (有效期期数)
     *
     * @return
     */
    public Integer getPeriodsNum() {
        return this.periodsNum;
    }

    /**
     * 设置   periodsMode
     *
     * @param periodsMode (有效期类型)
     */
    public InsuranceProduct setPeriodsMode(Integer periodsMode) {
        this.periodsMode = periodsMode;
        return this;
    }

    /**
     * 获取   periodsMode (有效期类型)
     *
     * @return
     */
    public Integer getPeriodsMode() {
        return this.periodsMode;
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
    public InsuranceProduct setStart(Integer start) {
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
    public InsuranceProduct setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
}
