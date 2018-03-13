package org.xteam.plus.mars.wx.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class WxPayAnotherBank {

    // 商户号
    @XStreamAlias("mch_id")
    private String mch_id;

    // 商户企业付款单号
    @XStreamAlias("partner_trade_no")
    private String partner_trade_no;

    // 随机字符串
    @XStreamAlias("nonce_str")
    private String nonce_str;

    // 签名
    @XStreamAlias("sign")
    private String sign;

    // 收款方银行卡号
    @XStreamAlias("enc_bank_no")
    private String enc_bank_no;

    // 收款方用户名
    @XStreamAlias("enc_true_name")
    private String enc_true_name;

    // 收款方开户行
    @XStreamAlias("bank_code")
    private String bank_code;

    // 付款金额
    @XStreamAlias("amount")
    private String amount;

    // 付款说明
    @XStreamAlias("desc")
    private String desc;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
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

    public String getEnc_bank_no() {
        return enc_bank_no;
    }

    public void setEnc_bank_no(String enc_bank_no) {
        this.enc_bank_no = enc_bank_no;
    }

    public String getEnc_true_name() {
        return enc_true_name;
    }

    public void setEnc_true_name(String enc_true_name) {
        this.enc_true_name = enc_true_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
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

    public String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }
}
