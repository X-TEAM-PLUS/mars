package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_ORDERS]表
 */

public class Orders implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 数量
     */
    private Integer productNum;

    /**
     * 产品单价
     */
    private java.math.BigDecimal productPrice;

    /**
     * 订单总价
     */
    private java.math.BigDecimal orderPrice;

    /**
     * 订单号
     */
    private java.math.BigDecimal orderNo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 支付流水号
     */
    private String payOrderNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 卡号
     */
    private java.math.BigDecimal cardNo;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 下单时间
     */
    private java.util.Date orderTime;

    /**
     * 购卡用户ID
     */
    private java.math.BigDecimal buyerUserId;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 售卡用户ID
     */
    private java.math.BigDecimal sellerUserId;

    /**
     * 支付时间
     */
    private java.util.Date payTime;

    /**
     * 产品ID
     */
    private java.math.BigDecimal productId;


    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 设置   productNum
     *
     * @param productNum (数量)
     */
    public Orders setProductNum(Integer productNum) {
        this.productNum = productNum;
        return this;
    }

    /**
     * 获取   productNum (数量)
     *
     * @return
     */
    public Integer getProductNum() {
        return this.productNum;
    }

    /**
     * 设置   productPrice
     *
     * @param productPrice (产品单价)
     */
    public Orders setProductPrice(java.math.BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    /**
     * 获取   productPrice (产品单价)
     *
     * @return
     */
    public java.math.BigDecimal getProductPrice() {
        return this.productPrice;
    }

    /**
     * 设置   orderPrice
     *
     * @param orderPrice (订单总价)
     */
    public Orders setOrderPrice(java.math.BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    /**
     * 获取   orderPrice (订单总价)
     *
     * @return
     */
    public java.math.BigDecimal getOrderPrice() {
        return this.orderPrice;
    }

    /**
     * 设置   orderNo
     *
     * @param orderNo (订单号)
     */
    public Orders setOrderNo(java.math.BigDecimal orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    /**
     * 获取   orderNo (订单号)
     *
     * @return
     */
    public java.math.BigDecimal getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public Orders setStatus(Integer status) {
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
     * 设置   payOrderNo
     *
     * @param payOrderNo (支付流水号)
     */
    public Orders setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
        return this;
    }

    /**
     * 获取   payOrderNo (支付流水号)
     *
     * @return
     */
    public String getPayOrderNo() {
        return this.payOrderNo;
    }

    /**
     * 设置   productName
     *
     * @param productName (产品名称)
     */
    public Orders setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * 获取   productName (产品名称)
     *
     * @return
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public Orders setCreated(java.util.Date created) {
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
     * 设置   cardType
     *
     * @param cardType (卡类型)
     */
    public Orders setCardType(Integer cardType) {
        this.cardType = cardType;
        return this;
    }

    /**
     * 获取   cardType (卡类型)
     *
     * @return
     */
    public Integer getCardType() {
        return this.cardType;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public Orders setUpdated(java.util.Date updated) {
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
     * 设置   cardNo
     *
     * @param cardNo (卡号)
     */
    public Orders setCardNo(java.math.BigDecimal cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    /**
     * 获取   cardNo (卡号)
     *
     * @return
     */
    public java.math.BigDecimal getCardNo() {
        return this.cardNo;
    }

    /**
     * 设置   orderType
     *
     * @param orderType (订单类型)
     */
    public Orders setOrderType(Integer orderType) {
        this.orderType = orderType;
        return this;
    }

    /**
     * 获取   orderType (订单类型)
     *
     * @return
     */
    public Integer getOrderType() {
        return this.orderType;
    }

    /**
     * 设置   orderTime
     *
     * @param orderTime (下单时间)
     */
    public Orders setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    /**
     * 获取   orderTime (下单时间)
     *
     * @return
     */
    public java.util.Date getOrderTime() {
        return this.orderTime;
    }

    /**
     * 设置   buyerUserId
     *
     * @param buyerUserId (购卡用户ID)
     */
    public Orders setBuyerUserId(java.math.BigDecimal buyerUserId) {
        this.buyerUserId = buyerUserId;
        return this;
    }

    /**
     * 获取   buyerUserId (购卡用户ID)
     *
     * @return
     */
    public java.math.BigDecimal getBuyerUserId() {
        return this.buyerUserId;
    }

    /**
     * 设置   payWay
     *
     * @param payWay (支付方式)
     */
    public Orders setPayWay(Integer payWay) {
        this.payWay = payWay;
        return this;
    }

    /**
     * 获取   payWay (支付方式)
     *
     * @return
     */
    public Integer getPayWay() {
        return this.payWay;
    }

    /**
     * 设置   sellerUserId
     *
     * @param sellerUserId (售卡用户ID)
     */
    public Orders setSellerUserId(java.math.BigDecimal sellerUserId) {
        this.sellerUserId = sellerUserId;
        return this;
    }

    /**
     * 获取   sellerUserId (售卡用户ID)
     *
     * @return
     */
    public java.math.BigDecimal getSellerUserId() {
        return this.sellerUserId;
    }

    /**
     * 设置   payTime
     *
     * @param payTime (支付时间)
     */
    public Orders setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
        return this;
    }

    /**
     * 获取   payTime (支付时间)
     *
     * @return
     */
    public java.util.Date getPayTime() {
        return this.payTime;
    }

    /**
     * 设置   productId
     *
     * @param productId (产品ID)
     */
    public Orders setProductId(java.math.BigDecimal productId) {
        this.productId = productId;
        return this;
    }

    /**
     * 获取   productId (产品ID)
     *
     * @return
     */
    public java.math.BigDecimal getProductId() {
        return this.productId;
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
    public Orders setStart(Integer start) {
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
    public Orders setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 获取用户手机号
     * @return
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *  设置用户手机号
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * 获取用户姓名
     * @return
     */
    public String getRealName() {
        return realName;
    }


    /**
     * 设置用户姓名
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }
}
