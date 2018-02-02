package org.xteam.plus.mars.wx.bean;

/**
 * 支付信息
 *
 * @author antgan
 */
public class PayOrderInfo {
    //订单号，对应out_trade_no；商户系统内部订单号，要求32个字符内、且在同一个商户号下唯一。
    private String orderId;
    //商品描述，对应body
    private String orderName;
    //商品详情
    private String detail;
    //价格，单位分
    private String totalFee;
    //附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
    private String attach;
    //trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
    private String productId;

    //取值如下：JSAPI，NATIVE，APP等
    private String tradeType = "JSAPI";
    // 必须传正确的用户端IP,详见获取用户ip指引
    private String userIp;

    // 交易结束时间	订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
    //注意：最短失效时间间隔必须大于5分钟
    private String timeExpire;
    // 交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见
    private String timeStart;

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
}
