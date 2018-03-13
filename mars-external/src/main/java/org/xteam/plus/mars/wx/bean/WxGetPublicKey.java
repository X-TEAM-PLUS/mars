package org.xteam.plus.mars.wx.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class WxGetPublicKey {
    @XStreamAlias("mch_id")
    private String mchId;

    @XStreamAlias("nonce_str")
    private String nonceStr;

    @XStreamAlias("sign")
    private String sign;

    @XStreamAlias("sign_type")
    private String sign_type;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }
}
