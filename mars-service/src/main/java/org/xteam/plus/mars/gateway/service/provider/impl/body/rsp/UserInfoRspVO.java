package org.xteam.plus.mars.gateway.service.provider.impl.body.rsp;

import org.xteam.plus.mars.type.UserLevelEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserInfoRspVO implements Serializable{

    /**
     * 会员卡号
     */
    private BigDecimal cardNo;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 会员开通方式
     */
    private Integer cardActivateMode;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 会员卡类型
     */
    private BigDecimal cardType;

    /**
     * 联系地址
     */
    private String linkAddress;

    /**
     * 会员卡有效期
     */
    private java.util.Date cardLifeTime;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 会员卡激活日期
     */
    private java.util.Date cardActivateTime;

    /**
     * 注册时间
     */
    private java.util.Date registerTime;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 微信账户
     */
    private String weixinAccount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 登录次数
     */
    private BigDecimal loginCount;

    /**
     * 注册来源
     */
    private String registerSource;

    /**
     * 更新时间
     */
    private java.util.Date updated;


    /**
     * 用户级别
     */
    private Integer userLevel;

    /**
     * 用户ID
     */
    private BigDecimal userId;

    /**
     * 最后登录时间
     */
    private java.util.Date lastLoginTime;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 累计提现金额
     */
    private BigDecimal accumulativeWithdrawals;

    /**
     * 累计补贴金额
     */
    private BigDecimal accumulativeSubsidy;

    /**
     * 省(编号)
     */
    private BigDecimal province;

    /**
     * 城市(编号)
     */
    private BigDecimal city;

    /**
     * 区县(编号)
     */
    private BigDecimal county;

    /**
     * 省(编号)
     */
    private String provinceName;

    /**
     * 城市(编号)
     */
    private String cityName;

    /**
     * 区县(编号)
     */
    private String countyName;

    /**
     * 续费次数
     */
    private Integer payTimes;


    public BigDecimal getCardNo() {
        return cardNo;
    }

    public void setCardNo(BigDecimal cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getCardActivateMode() {
        return cardActivateMode;
    }

    public void setCardActivateMode(Integer cardActivateMode) {
        this.cardActivateMode = cardActivateMode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BigDecimal getCardType() {
        return cardType;
    }

    public void setCardType(BigDecimal cardType) {
        this.cardType = cardType;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public Date getCardLifeTime() {
        return cardLifeTime;
    }

    public void setCardLifeTime(Date cardLifeTime) {
        this.cardLifeTime = cardLifeTime;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getCardActivateTime() {
        return cardActivateTime;
    }

    public void setCardActivateTime(Date cardActivateTime) {
        this.cardActivateTime = cardActivateTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getWeixinAccount() {
        return weixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(BigDecimal loginCount) {
        this.loginCount = loginCount;
    }

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public BigDecimal getAccumulativeWithdrawals() {
        return accumulativeWithdrawals;
    }

    public void setAccumulativeWithdrawals(BigDecimal accumulativeWithdrawals) {
        this.accumulativeWithdrawals = accumulativeWithdrawals;
    }

    public BigDecimal getAccumulativeSubsidy() {
        return accumulativeSubsidy;
    }

    public void setAccumulativeSubsidy(BigDecimal accumulativeSubsidy) {
        this.accumulativeSubsidy = accumulativeSubsidy;
    }

    public BigDecimal getProvince() {
        return province;
    }

    public void setProvince(BigDecimal province) {
        this.province = province;
    }

    public BigDecimal getCity() {
        return city;
    }

    public void setCity(BigDecimal city) {
        this.city = city;
    }

    public BigDecimal getCounty() {
        return county;
    }

    public void setCounty(BigDecimal county) {
        this.county = county;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(Integer payTimes) {
        this.payTimes = payTimes;
    }
}
