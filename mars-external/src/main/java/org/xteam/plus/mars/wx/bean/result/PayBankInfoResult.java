package org.xteam.plus.mars.wx.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamCDataConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class PayBankInfoResult {

    @XStreamAlias("return_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnCode;

    @XStreamAlias("return_msg")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnMsg;

        @XStreamAlias("result_code")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String resultCode;

    @XStreamAlias("mch_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchId;

    @XStreamAlias("error_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCode;

    @XStreamAlias("err_code_des")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCodeDes;

    @XStreamAlias("partner_trade_no")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String partnerTradeNo;

    @XStreamAlias("amount")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String amount;

    @XStreamAlias("nonce_str")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonce_str;

    @XStreamAlias("sign")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String sign;

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

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

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

    public static PayBankInfoResult fromXml(String xml) {
        return XStreamTransformer.fromXml(PayBankInfoResult.class, xml);
    }
}
