package org.xteam.plus.mars.wx.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamCDataConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class PayPocketMoneyResult {

    @XStreamAlias("return_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnCode;

    @XStreamAlias("return_msg")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnMsg;

    @XStreamAlias("result_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String resultCode;

    @XStreamAlias("mchid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchId;

    @XStreamAlias("nonce_str")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonce_str;

    @XStreamAlias("partner_trade_no")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String partnerTradeNo;

    @XStreamAlias("payment_no")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String paymentNo;

    @XStreamAlias("payment_time")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String paymentTime;

    @XStreamAlias("device_info")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String deviceInfo;

    @XStreamAlias("mch_appid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchAppid;

    @XStreamAlias("err_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCode;

    @XStreamAlias("err_code_des")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCodeDes;


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
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

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getMchAppid() {
        return mchAppid;
    }

    public void setMchAppid(String mchAppid) {
        this.mchAppid = mchAppid;
    }

    public static PayPocketMoneyResult fromXml(String xml) {
        return XStreamTransformer.fromXml(PayPocketMoneyResult.class, xml);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
