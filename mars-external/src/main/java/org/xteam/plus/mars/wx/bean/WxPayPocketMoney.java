package org.xteam.plus.mars.wx.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class WxPayPocketMoney {

    @XStreamAlias("mch_appid")
    private String mchAppid;

    // 商户号
    @XStreamAlias("mchid")
    private String mchId;

    @XStreamAlias("device_info")
    private String deviceInfo;

    // 随机字符串
    @XStreamAlias("nonce_str")
    private String nonce_str;

    // 商户企业付款单号
    @XStreamAlias("partner_trade_no")
    private String partnerTradeNo;

    @XStreamAlias("openid")
    private String openid;

    @XStreamAlias("check_name")
    private String checkName;

    @XStreamAlias("re_user_name")
    private String reUserName;

    @XStreamAlias("amount")
    private String amount;

    @XStreamAlias("desc")
    private String desc;

    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    @XStreamAlias("sign")
    private String sign;

    public String getMchAppid() {
        return mchAppid;
    }

    public void setMchAppid(String mchAppid) {
        this.mchAppid = mchAppid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }
}
