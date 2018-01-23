package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;


/**
* Created by IntelliJ IDEA.
* User: yankun
* Date: 2018-01-23
* Time: 17:05
* TableName: [T_MARS_INVOICE_INFO]表
*/

public class InvoiceInfo implements Serializable {

    /**
    * 开始下标
    */
    private  Integer start = 0;

    /**
    * 界限值
    */
    private Integer limit = 20;

    /**
    * 区县
    */
    private String countyName;

    /**
    * 联系地址
    */
    private String linkAddress;

    /**
    * 会员卡激活日期
    */
    private Date cardActivateTime;

    /**
    * 会员卡有效期
    */
    private Date cardLifeTime;

    /**
    * 发件日期
    */
    private Date deliveredDate;

    /**
    * 省
    */
    private String provinceName;

    /**
    * 备注
    */
    private String remark;

    /**
    * 地市
    */
    private String cityName;

    /**
    * 手机号
    */
    private String mobileNo;

    /**
    * 快递费
    */
    private BigDecimal freight;

    /**
    * 上一支试剂发货日期
    */
    private Date previousDate;

    /**
    * 会员卡号
    */
    private BigDecimal cardNo;

    /**
    * 下一支试剂发货日期
    */
    private Date nextDate;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 运单号
    */
    private Date waybillNo;


    /**
    *  设置   countyName
    * @param countyName  (区县)
    */
    public  InvoiceInfo   setCountyName(String countyName){
        this.countyName = countyName;
        return this;
    }

    /**
    *  获取   countyName (区县)
    * @return
    */
    public String getCountyName(){
            return this.countyName;
    }

    /**
    *  设置   linkAddress
    * @param linkAddress  (联系地址)
    */
    public  InvoiceInfo   setLinkAddress(String linkAddress){
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
    public  InvoiceInfo   setCardActivateTime(Date cardActivateTime){
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
    *  设置   cardLifeTime
    * @param cardLifeTime  (会员卡有效期)
    */
    public  InvoiceInfo   setCardLifeTime(Date cardLifeTime){
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
    *  设置   deliveredDate
    * @param deliveredDate  (发件日期)
    */
    public  InvoiceInfo   setDeliveredDate(Date deliveredDate){
        this.deliveredDate = deliveredDate;
        return this;
    }

    /**
    *  获取   deliveredDate (发件日期)
    * @return
    */
    public Date getDeliveredDate(){
            return this.deliveredDate;
    }

    /**
    *  设置   provinceName
    * @param provinceName  (省)
    */
    public  InvoiceInfo   setProvinceName(String provinceName){
        this.provinceName = provinceName;
        return this;
    }

    /**
    *  获取   provinceName (省)
    * @return
    */
    public String getProvinceName(){
            return this.provinceName;
    }

    /**
    *  设置   remark
    * @param remark  (备注)
    */
    public  InvoiceInfo   setRemark(String remark){
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
    *  设置   cityName
    * @param cityName  (地市)
    */
    public  InvoiceInfo   setCityName(String cityName){
        this.cityName = cityName;
        return this;
    }

    /**
    *  获取   cityName (地市)
    * @return
    */
    public String getCityName(){
            return this.cityName;
    }

    /**
    *  设置   mobileNo
    * @param mobileNo  (手机号)
    */
    public  InvoiceInfo   setMobileNo(String mobileNo){
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
    *  设置   freight
    * @param freight  (快递费)
    */
    public  InvoiceInfo   setFreight(BigDecimal freight){
        this.freight = freight;
        return this;
    }

    /**
    *  获取   freight (快递费)
    * @return
    */
    public BigDecimal getFreight(){
            return this.freight;
    }

    /**
    *  设置   previousDate
    * @param previousDate  (上一支试剂发货日期)
    */
    public  InvoiceInfo   setPreviousDate(Date previousDate){
        this.previousDate = previousDate;
        return this;
    }

    /**
    *  获取   previousDate (上一支试剂发货日期)
    * @return
    */
    public Date getPreviousDate(){
            return this.previousDate;
    }

    /**
    *  设置   cardNo
    * @param cardNo  (会员卡号)
    */
    public  InvoiceInfo   setCardNo(BigDecimal cardNo){
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
    *  设置   nextDate
    * @param nextDate  (下一支试剂发货日期)
    */
    public  InvoiceInfo   setNextDate(Date nextDate){
        this.nextDate = nextDate;
        return this;
    }

    /**
    *  获取   nextDate (下一支试剂发货日期)
    * @return
    */
    public Date getNextDate(){
            return this.nextDate;
    }

    /**
    *  设置   realName
    * @param realName  (真实姓名)
    */
    public  InvoiceInfo   setRealName(String realName){
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
    *  设置   waybillNo
    * @param waybillNo  (运单号)
    */
    public  InvoiceInfo   setWaybillNo(Date waybillNo){
        this.waybillNo = waybillNo;
        return this;
    }

    /**
    *  获取   waybillNo (运单号)
    * @return
    */
    public Date getWaybillNo(){
            return this.waybillNo;
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
    public InvoiceInfo  setStart(Integer start) {
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
    public InvoiceInfo  setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
