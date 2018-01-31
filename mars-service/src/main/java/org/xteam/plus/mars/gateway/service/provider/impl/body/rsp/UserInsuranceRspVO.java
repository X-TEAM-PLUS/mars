package org.xteam.plus.mars.gateway.service.provider.impl.body.rsp;

import org.xteam.plus.mars.domain.InsuranceCompany;
import org.xteam.plus.mars.domain.InsuranceProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserInsuranceRspVO implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 截止日期
     */
    private java.util.Date expirationDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 保险合同号
     */
    private String contractNo;

    /**
     * 投保人身份证号
     */
    private java.math.BigDecimal holderIdNumber;

    /**
     * 投保人
     */
    private String policyHolder;

    /**
     * 承保日期
     */
    private java.util.Date acceptInsuranceDate;

    /**
     * 用户保单流水号
     */
    private java.math.BigDecimal insuranceOrderId;

    /**
     * 保险产品编号
     */
    private java.math.BigDecimal insuranceProductNo;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 保险信息
     */
    private InsuranceProduct insuranceProduct;

    /**
     * 保险公司信息
     */
    private InsuranceCompany insuranceCompany;

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getHolderIdNumber() {
        return holderIdNumber;
    }

    public void setHolderIdNumber(BigDecimal holderIdNumber) {
        this.holderIdNumber = holderIdNumber;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }

    public Date getAcceptInsuranceDate() {
        return acceptInsuranceDate;
    }

    public void setAcceptInsuranceDate(Date acceptInsuranceDate) {
        this.acceptInsuranceDate = acceptInsuranceDate;
    }

    public BigDecimal getInsuranceOrderId() {
        return insuranceOrderId;
    }

    public void setInsuranceOrderId(BigDecimal insuranceOrderId) {
        this.insuranceOrderId = insuranceOrderId;
    }

    public BigDecimal getInsuranceProductNo() {
        return insuranceProductNo;
    }

    public void setInsuranceProductNo(BigDecimal insuranceProductNo) {
        this.insuranceProductNo = insuranceProductNo;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public InsuranceProduct getInsuranceProduct() {
        return insuranceProduct;
    }

    public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
        this.insuranceProduct = insuranceProduct;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
}
