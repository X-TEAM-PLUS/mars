package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import org.xteam.plus.mars.type.OrderTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class WxPayJsApiReqVO implements Serializable {
    // 产品ID
    private BigDecimal productId;
    // 购买个数
    private BigDecimal number;
    // 收获地址
    private String address;
    // 联系方式
    private String contactsMobile;
    // 订单类型
    private OrderTypeEnum orderTypeEnum;
    // 卡号
    private BigDecimal cardNo;
    // 身份证号
    String certificateOf;
    // 用户真实姓名
    String userRealName;

    public BigDecimal getProductId() {
        return productId;
    }

    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public OrderTypeEnum getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
        this.orderTypeEnum = orderTypeEnum;
    }

    public BigDecimal getCardNo() {
        return cardNo;
    }

    public void setCardNo(BigDecimal cardNo) {
        this.cardNo = cardNo;
    }


    public String getCertificateOf() {
        return certificateOf;
    }

    public void setCertificateOf(String certificateOf) {
        this.certificateOf = certificateOf;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
}
