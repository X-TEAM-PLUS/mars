package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_USER_INFO]表
 */

public class UserInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 会员卡号
     */
    private java.math.BigDecimal cardNo;

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
    private java.math.BigDecimal cardType;

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
    private java.math.BigDecimal loginCount;

    /**
     * 注册来源
     */
    private String registerSource;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 密码
     */
    private String passwork;

    /**
     * 用户级别
     */
    private Integer userLevel;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 最后登录时间
     */
    private java.util.Date lastLoginTime;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 设置   cardNo
     *
     * @param cardNo (会员卡号)
     */
    public UserInfo setCardNo(java.math.BigDecimal cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    /**
     * 获取   cardNo (会员卡号)
     *
     * @return
     */
    public java.math.BigDecimal getCardNo() {
        return this.cardNo;
    }

    /**
     * 设置   idNumber
     *
     * @param idNumber (身份证号)
     */
    public UserInfo setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    /**
     * 获取   idNumber (身份证号)
     *
     * @return
     */
    public String getIdNumber() {
        return this.idNumber;
    }

    /**
     * 设置   cardActivateMode
     *
     * @param cardActivateMode (会员开通方式)
     */
    public UserInfo setCardActivateMode(Integer cardActivateMode) {
        this.cardActivateMode = cardActivateMode;
        return this;
    }

    /**
     * 获取   cardActivateMode (会员开通方式)
     *
     * @return
     */
    public Integer getCardActivateMode() {
        return this.cardActivateMode;
    }

    /**
     * 设置   realName
     *
     * @param realName (真实姓名)
     */
    public UserInfo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    /**
     * 获取   realName (真实姓名)
     *
     * @return
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * 设置   cardType
     *
     * @param cardType (会员卡类型)
     */
    public UserInfo setCardType(java.math.BigDecimal cardType) {
        this.cardType = cardType;
        return this;
    }

    /**
     * 获取   cardType (会员卡类型)
     *
     * @return
     */
    public java.math.BigDecimal getCardType() {
        return this.cardType;
    }

    /**
     * 设置   linkAddress
     *
     * @param linkAddress (联系地址)
     */
    public UserInfo setLinkAddress(String linkAddress) {
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
     * 设置   cardLifeTime
     *
     * @param cardLifeTime (会员卡有效期)
     */
    public UserInfo setCardLifeTime(java.util.Date cardLifeTime) {
        this.cardLifeTime = cardLifeTime;
        return this;
    }

    /**
     * 获取   cardLifeTime (会员卡有效期)
     *
     * @return
     */
    public java.util.Date getCardLifeTime() {
        return this.cardLifeTime;
    }

    /**
     * 设置   mobileNo
     *
     * @param mobileNo (手机号)
     */
    public UserInfo setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    /**
     * 获取   mobileNo (手机号)
     *
     * @return
     */
    public String getMobileNo() {
        return this.mobileNo;
    }

    /**
     * 设置   cardActivateTime
     *
     * @param cardActivateTime (会员卡激活日期)
     */
    public UserInfo setCardActivateTime(java.util.Date cardActivateTime) {
        this.cardActivateTime = cardActivateTime;
        return this;
    }

    /**
     * 获取   cardActivateTime (会员卡激活日期)
     *
     * @return
     */
    public java.util.Date getCardActivateTime() {
        return this.cardActivateTime;
    }

    /**
     * 设置   registerTime
     *
     * @param registerTime (注册时间)
     */
    public UserInfo setRegisterTime(java.util.Date registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    /**
     * 获取   registerTime (注册时间)
     *
     * @return
     */
    public java.util.Date getRegisterTime() {
        return this.registerTime;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public UserInfo setCreated(java.util.Date created) {
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
     * 设置   weixinAccount
     *
     * @param weixinAccount (微信账户)
     */
    public UserInfo setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
        return this;
    }

    /**
     * 获取   weixinAccount (微信账户)
     *
     * @return
     */
    public String getWeixinAccount() {
        return this.weixinAccount;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public UserInfo setStatus(Integer status) {
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
     * 设置   loginCount
     *
     * @param loginCount (登录次数)
     */
    public UserInfo setLoginCount(java.math.BigDecimal loginCount) {
        this.loginCount = loginCount;
        return this;
    }

    /**
     * 获取   loginCount (登录次数)
     *
     * @return
     */
    public java.math.BigDecimal getLoginCount() {
        return this.loginCount;
    }

    /**
     * 设置   registerSource
     *
     * @param registerSource (注册来源)
     */
    public UserInfo setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
        return this;
    }

    /**
     * 获取   registerSource (注册来源)
     *
     * @return
     */
    public String getRegisterSource() {
        return this.registerSource;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public UserInfo setUpdated(java.util.Date updated) {
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
     * 设置   passwork
     *
     * @param passwork (密码)
     */
    public UserInfo setPasswork(String passwork) {
        this.passwork = passwork;
        return this;
    }

    /**
     * 获取   passwork (密码)
     *
     * @return
     */
    public String getPasswork() {
        return this.passwork;
    }

    /**
     * 设置   userLevel
     *
     * @param userLevel (用户级别)
     */
    public UserInfo setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    /**
     * 获取   userLevel (用户级别)
     *
     * @return
     */
    public Integer getUserLevel() {
        return this.userLevel;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public UserInfo setUserId(java.math.BigDecimal userId) {
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
     * 设置   lastLoginTime
     *
     * @param lastLoginTime (最后登录时间)
     */
    public UserInfo setLastLoginTime(java.util.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    /**
     * 获取   lastLoginTime (最后登录时间)
     *
     * @return
     */
    public java.util.Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    /**
     * 设置   nickName
     *
     * @param nickName (昵称)
     */
    public UserInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    /**
     * 获取   nickName (昵称)
     *
     * @return
     */
    public String getNickName() {
        return this.nickName;
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
    public UserInfo setStart(Integer start) {
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
    public UserInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
