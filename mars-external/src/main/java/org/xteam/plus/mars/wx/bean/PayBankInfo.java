package org.xteam.plus.mars.wx.bean;

public class PayBankInfo {
    /**
     *
     INVALID_REQUEST	无效的请求，商户系统异常导致，商户权限异常、证书错误、频率限制等	使用原单号以及原请求参数重试
     SYSTEMERROR	    业务错误导致交易失败	使用原单号以及原请求参数重试
     PARAM_ERROR	    参数错误，商户系统异常导致	商户检查请求参数是否合法，证书，签名
     SIGNERROR	        签名错误	按照文档签名算法进行签名值计算
     AMOUNT_LIMIT	    超额；已达到今日付款金额上限或已达到今日银行卡收款金额上限	今天暂停该商户发起代付请求或今日暂停向该银行卡转账
     ORDERPAID	        受理失败，订单已存在	查询，确认订单；或换单重试
     FATAL_ERROR	    已存在该单，并且订单信息不一致；或订单太老	核定订单信息；
     NOTENOUGH	        账号余额不足	请用户充值或更换支付卡后再支付
     FREQUENCY_LIMITED	超过每分钟600次的频率限制	稍后用原单号重试
     SUCCESS	        Wx侧受理成功
     */
    // 返回状态码
    private String returnCode;
    // 返回信息
    private String returnMsg;
    // 业务结果
    private String resultCode;
    // 错误代码
    private String errCode;
    // 错误代码描述
    private String errCodeDes;
    // 随机字符串
    private String nonceStr;
    // 商户号
    private String mchId;
    // 商户企业付款单号
    private String partnerTradeNo;
    // 代付金额
    private String amount;
    // 微信企业付款单号
    private String paymentNo;
    // 手续费金额
    private String cmmsAmt;

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

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
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

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getCmmsAmt() {
        return cmmsAmt;
    }

    public void setCmmsAmt(String cmmsAmt) {
        this.cmmsAmt = cmmsAmt;
    }
}
