package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CouncilInfoList implements Serializable {
    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 总补贴
     */
    private BigDecimal totalSubsidy;

    /**
     * 总用户数
     */
    private BigDecimal totalUser;

    /**
     * 总社工数
     */
    private BigDecimal totalSocial;

    /**
     * 总理事数
     */
    private BigDecimal totalDirector;

    /**
     * 总常任理事
     */
    private BigDecimal totalStandingDirector;

    /**
     * 开通时间
     */
    private Date openDate;

    /**
     * 开通地市名称
     */
    private String cityName;

    /**
     * 开通地市ID
     */
    private String cityNo;

    /**
     * 理事会主键
     */
    private BigDecimal councilId;

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

    public BigDecimal getTotalSubsidy() {
        return totalSubsidy;
    }

    public void setTotalSubsidy(BigDecimal totalSubsidy) {
        this.totalSubsidy = totalSubsidy;
    }

    public BigDecimal getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(BigDecimal totalUser) {
        this.totalUser = totalUser;
    }

    public BigDecimal getTotalSocial() {
        return totalSocial;
    }

    public void setTotalSocial(BigDecimal totalSocial) {
        this.totalSocial = totalSocial;
    }

    public BigDecimal getTotalDirector() {
        return totalDirector;
    }

    public void setTotalDirector(BigDecimal totalDirector) {
        this.totalDirector = totalDirector;
    }

    public BigDecimal getTotalStandingDirector() {
        return totalStandingDirector;
    }

    public void setTotalStandingDirector(BigDecimal totalStandingDirector) {
        this.totalStandingDirector = totalStandingDirector;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public BigDecimal getCouncilId() {
        return councilId;
    }

    public void setCouncilId(BigDecimal councilId) {
        this.councilId = councilId;
    }
}
