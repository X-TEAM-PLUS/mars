package org.xteam.plus.mars.wx.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamCDataConverter;
import org.xteam.plus.mars.wx.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public class GetPublicKeyResult {

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

    @XStreamAlias("pub_key")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String pubKey;

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

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public static GetPublicKeyResult fromXml(String xml) {
        return XStreamTransformer.fromXml(GetPublicKeyResult.class, xml);
    }
}
