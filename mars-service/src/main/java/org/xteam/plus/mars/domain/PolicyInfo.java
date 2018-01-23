package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;


/**
* Created by IntelliJ IDEA.
* User: yankun
* Date: 2018-01-23
* Time: 17:05
* TableName: [T_MARS_POLICY_INFO]表
*/

public class PolicyInfo implements Serializable {

    /**
    * 开始下标
    */
    private  Integer start = 0;

    /**
    * 界限值
    */
    private Integer limit = 20;

    /**
    * 会员卡有效期
    */
    private Date cardLifeTime;

    /**
    * 续期缴费次数
    */
    private Integer payTimes;

    /**
    * 会员卡号
    */
    private BigDecimal cardNo;

    /**
    * 备注
    */
    private String remark;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 保单号
    */
    private String policyNo;

    /**
    * 保费
    */
    private BigDecimal premium;

    /**
    * 联系地址
    */
    private String linkAddress;

    /**
    * 会员卡激活日期
    */
    private Date cardActivateTime;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 生效日期
    */
    private Date effectiveDate;

    /**
    * 出生日期
    */
    private Date birthDate;

    /**
    * 身份证号
    */
    private String idNumber;

    /**
    * 手机号
    */
    private String mobileNo;


    /**
    *  设置   cardLifeTime
    * @param cardLifeTime  (会员卡有效期)
    */
    public  PolicyInfo   setCardLifeTime(Date cardLifeTime){
        this.cardLifeTime = cardLifeTime;
        return this;
    }

    /**
    *  获取   cardLifeTime (会员卡有效期)
    * @return
    */
    public Date getCardLifeTime(){
            return this.cardLifeTime;
    }

    /**
    *  设置   payTimes
    * @param payTimes  (续期缴费次数)
    */
    public  PolicyInfo   setPayTimes(Integer payTimes){
        this.payTimes = payTimes;
        return this;
    }

    /**
    *  获取   payTimes (续期缴费次数)
    * @return
    */
    public Integer getPayTimes(){
            return this.payTimes;
    }

    /**
    *  设置   cardNo
    * @param cardNo  (会员卡号)
    */
    public  PolicyInfo   setCardNo(BigDecimal cardNo){
        this.cardNo = cardNo;
        return this;
    }

    /**
    *  获取   cardNo (会员卡号)
    * @return
    */
    public BigDecimal getCardNo(){
            return this.cardNo;
    }

    /**
    *  设置   remark
    * @param remark  (备注)
    */
    public  PolicyInfo   setRemark(String remark){
        this.remark = remark;
        return this;
    }

    /**
    *  获取   remark (备注)
    * @return
    */
    public String getRemark(){
            return this.remark;
    }

    /**
    *  设置   realName
    * @param realName  (真实姓名)
    */
    public  PolicyInfo   setRealName(String realName){
        this.realName = realName;
        return this;
    }

    /**
    *  获取   realName (真实姓名)
    * @return
    */
    public String getRealName(){
            return this.realName;
    }

    /**
    *  设置   policyNo
    * @param policyNo  (保单号)
    */
    public  PolicyInfo   setPolicyNo(String policyNo){
        this.policyNo = policyNo;
        return this;
    }

    /**
    *  获取   policyNo (保单号)
    * @return
    */
    public String getPolicyNo(){
            return this.policyNo;
    }

    /**
    *  设置   premium
    * @param premium  (保费)
    */
    public  PolicyInfo   setPremium(BigDecimal premium){
        this.premium = premium;
        return this;
    }

    /**
    *  获取   premium (保费)
    * @return
    */
    public BigDecimal getPremium(){
            return this.premium;
    }

    /**
    *  设置   linkAddress
    * @param linkAddress  (联系地址)
    */
    public  PolicyInfo   setLinkAddress(String linkAddress){
        this.linkAddress = linkAddress;
        return this;
    }

    /**
    *  获取   linkAddress (联系地址)
    * @return
    */
    public String getLinkAddress(){
            return this.linkAddress;
    }

    /**
    *  设置   cardActivateTime
    * @param cardActivateTime  (会员卡激活日期)
    */
    public  PolicyInfo   setCardActivateTime(Date cardActivateTime){
        this.cardActivateTime = cardActivateTime;
        return this;
    }

    /**
    *  获取   cardActivateTime (会员卡激活日期)
    * @return
    */
    public Date getCardActivateTime(){
            return this.cardActivateTime;
    }

    /**
    *  设置   age
    * @param age  (年龄)
    */
    public  PolicyInfo   setAge(Integer age){
        this.age = age;
        return this;
    }

    /**
    *  获取   age (年龄)
    * @return
    */
    public Integer getAge(){
            return this.age;
    }

    /**
    *  设置   effectiveDate
    * @param effectiveDate  (生效日期)
    */
    public  PolicyInfo   setEffectiveDate(Date effectiveDate){
        this.effectiveDate = effectiveDate;
        return this;
    }

    /**
    *  获取   effectiveDate (生效日期)
    * @return
    */
    public Date getEffectiveDate(){
            return this.effectiveDate;
    }

    /**
    *  设置   birthDate
    * @param birthDate  (出生日期)
    */
    public  PolicyInfo   setBirthDate(Date birthDate){
        this.birthDate = birthDate;
        return this;
    }

    /**
    *  获取   birthDate (出生日期)
    * @return
    */
    public Date getBirthDate(){
            return this.birthDate;
    }

    /**
    *  设置   idNumber
    * @param idNumber  (身份证号)
    */
    public  PolicyInfo   setIdNumber(String idNumber){
        this.idNumber = idNumber;
        return this;
    }

    /**
    *  获取   idNumber (身份证号)
    * @return
    */
    public String getIdNumber(){
            return this.idNumber;
    }

    /**
    *  设置   mobileNo
    * @param mobileNo  (手机号)
    */
    public  PolicyInfo   setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
        return this;
    }

    /**
    *  获取   mobileNo (手机号)
    * @return
    */
    public String getMobileNo(){
            return this.mobileNo;
    }


    /**
    *  获取开始下标
    * @return
    */
    public Integer getStart() {
        return start;
    }

    /**
    *  设置开始下标
    * @param start
    */
    public PolicyInfo  setStart(Integer start) {
        this.start = start;
        return this;
    }

    /**
    *  获取界限值
    * @return
    */
    public Integer getLimit() {
        return limit;
    }


    /**
    *  设置界限值
    * @param limit
    */
    public PolicyInfo  setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
