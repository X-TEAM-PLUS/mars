package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_DELIVERED_INFO]表
 */

public class DeliveredInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 下一支试剂发货日期
     */
    private java.util.Date nextDate;

    /**
     * 运单号
     */
    private String waybillNo;

    /**
     * 发货流水号
     */
    private java.math.BigDecimal deliveredId;

    /**
     * 发件日期
     */
    private java.util.Date deliveredDate;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 邮寄地址
     */
    private String address;

    /**
     * 发货次数
     */
    private Integer deliveredCount;

    /**
     * 快递费
     */
    private java.math.BigDecimal deliveredFee;

    /**
     * 上一支试剂发货日期
     */
    private java.util.Date previousDate;

    /**
     * 省
     */
    private String province;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 状态
     * 0 未发货 1已发货
     */
    private BigDecimal status;

    /**
     * 用户信息
     */
    private UserInfo userInfo;


    /**
     * 用户健康卡信息
     */
    private UserHealthCard userHealthCard;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 截止日期
     */
    private Date endDate;


    /**
     * 设置   nextDate
     *
     * @param nextDate (下一支试剂发货日期)
     */
    public DeliveredInfo setNextDate(java.util.Date nextDate) {
        this.nextDate = nextDate;
        return this;
    }

    /**
     * 获取   nextDate (下一支试剂发货日期)
     *
     * @return
     */
    public java.util.Date getNextDate() {
        return this.nextDate;
    }

    /**
     * 设置   waybillNo
     *
     * @param waybillNo (运单号)
     */
    public DeliveredInfo setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
        return this;
    }

    /**
     * 获取   waybillNo (运单号)
     *
     * @return
     */
    public String getWaybillNo() {
        return this.waybillNo;
    }

    /**
     * 设置   deliveredId
     *
     * @param deliveredId (发货流水号)
     */
    public DeliveredInfo setDeliveredId(java.math.BigDecimal deliveredId) {
        this.deliveredId = deliveredId;
        return this;
    }

    /**
     * 获取   deliveredId (发货流水号)
     *
     * @return
     */
    public java.math.BigDecimal getDeliveredId() {
        return this.deliveredId;
    }

    /**
     * 设置   deliveredDate
     *
     * @param deliveredDate (发件日期)
     */
    public DeliveredInfo setDeliveredDate(java.util.Date deliveredDate) {
        this.deliveredDate = deliveredDate;
        return this;
    }

    /**
     * 获取   deliveredDate (发件日期)
     *
     * @return
     */
    public java.util.Date getDeliveredDate() {
        return this.deliveredDate;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public DeliveredInfo setUserId(java.math.BigDecimal userId) {
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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public DeliveredInfo setCreated(java.util.Date created) {
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
     * 设置   address
     *
     * @param address (邮寄地址)
     */
    public DeliveredInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * 获取   address (邮寄地址)
     *
     * @return
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置   deliveredCount
     *
     * @param deliveredCount (发货次数)
     */
    public DeliveredInfo setDeliveredCount(Integer deliveredCount) {
        this.deliveredCount = deliveredCount;
        return this;
    }

    /**
     * 获取   deliveredCount (发货次数)
     *
     * @return
     */
    public Integer getDeliveredCount() {
        return this.deliveredCount;
    }

    /**
     * 设置   deliveredFee
     *
     * @param deliveredFee (快递费)
     */
    public DeliveredInfo setDeliveredFee(java.math.BigDecimal deliveredFee) {
        this.deliveredFee = deliveredFee;
        return this;
    }

    /**
     * 获取   deliveredFee (快递费)
     *
     * @return
     */
    public java.math.BigDecimal getDeliveredFee() {
        return this.deliveredFee;
    }

    /**
     * 设置   previousDate
     *
     * @param previousDate (上一支试剂发货日期)
     */
    public DeliveredInfo setPreviousDate(java.util.Date previousDate) {
        this.previousDate = previousDate;
        return this;
    }

    /**
     * 获取   previousDate (上一支试剂发货日期)
     *
     * @return
     */
    public java.util.Date getPreviousDate() {
        return this.previousDate;
    }

    /**
     * 设置   province
     *
     * @param province (省)
     */
    public DeliveredInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    /**
     * 获取   province (省)
     *
     * @return
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public DeliveredInfo setUpdated(java.util.Date updated) {
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
     * 设置   city
     *
     * @param city (市)
     */
    public DeliveredInfo setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * 获取   city (市)
     *
     * @return
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置   area
     *
     * @param area (区)
     */
    public DeliveredInfo setArea(String area) {
        this.area = area;
        return this;
    }

    /**
     * 获取   area (区)
     *
     * @return
     */
    public String getArea() {
        return this.area;
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
    public DeliveredInfo setStart(Integer start) {
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
    public DeliveredInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 获取开始日期
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始日期
     *
     * @param startDate
     * @return
     */
    public DeliveredInfo setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 获取截止日期
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置截止日期
     *
     * @param endDate
     * @return
     */
    public DeliveredInfo setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public UserHealthCard getUserHealthCard() {
        return userHealthCard;
    }

    public DeliveredInfo setUserHealthCard(UserHealthCard userHealthCard) {
        this.userHealthCard = userHealthCard;
        return this;
    }
}
