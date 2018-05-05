package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import org.xteam.plus.mars.type.OrderTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class KeysUserBuyReqVO implements Serializable {
    // 收获地址
    private String address;
    // 区域
    private String area;
    // 联系方式
    private String contactsMobile;
    // 卡密
    private String cardKeys;
    // 身份证号
    private String certificateOf;
    // 用户真实姓名
    private String userRealName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getCardKeys() {
        return cardKeys;
    }

    public void setCardKeys(String cardKeys) {
        this.cardKeys = cardKeys;
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
