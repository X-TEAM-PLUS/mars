package org.xteam.plus.mars.wx.util;

import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;
import org.xteam.plus.mars.wx.bean.WxUnifiedOrder;
import org.xteam.plus.mars.wx.util.crypto.MD5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 支付工具类
 * @author antgan
 *
 */
public class PayUtil {

	/**
	 * 生成统一支付订单实体
	 * @param bizOrder
	 * @param ip
	 * @param openId
	 * @return
	 */
	public static WxUnifiedOrder createPayInfo(PayOrderInfo order, String notifyUrl , String openid) {
		//map只是为了ASCII码从小到大排序（字典序）
		Map<String, String> payinfo = new HashMap<>();	
		payinfo.put("appid", WxConfig.getInstance().getAppId());
		payinfo.put("mch_id", WxConfig.getInstance().getMchId());
		payinfo.put("nonce_str", StringUtils.randomStr(32));
		payinfo.put("body", order.getOrderName());
		payinfo.put("out_trade_no", order.getOrderId());//商品订单号
		payinfo.put("total_fee", ""+order.getTotalFee());
		payinfo.put("spbill_create_ip",order.getUserIp());
		payinfo.put("notify_url", notifyUrl);
		payinfo.put("trade_type", order.getTradeType());
		payinfo.put("time_start",order.getTimeStart());
		payinfo.put("time_expire",order.getTimeExpire());
		payinfo.put("sign", createSign(payinfo, WxConfig.getInstance().getApiKey()));
		//赋值对象
		WxUnifiedOrder pay = new WxUnifiedOrder();
		pay.setAppid(payinfo.get("appid"));
		pay.setMchId(payinfo.get("mch_id"));
		pay.setNonceStr(payinfo.get("nonce_str"));
		pay.setBody(payinfo.get("body"));
		pay.setOutTradeNo(payinfo.get("out_trade_no"));
		pay.setTotalFee(payinfo.get("total_fee"));
		pay.setSpbillCreateIp(payinfo.get("spbill_create_ip"));
		pay.setNotifyUrl(payinfo.get("notify_url"));
		pay.setTradeType(payinfo.get("trade_type"));
		pay.setTimeStart(payinfo.get("time_start"));
		pay.setTimeExpire(payinfo.get("time_expire"));
		pay.setSign(payinfo.get("sign"));
		return pay;
	}
	
	/**
	 * 生成sign签名
	 * @param payinfo
	 * @param key
	 * @return
	 */
	public static String createSign(Map<String, String> payinfo, String keyStr){
		Set<String> keysSet = payinfo.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = payinfo.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            temp.append(valueString);
        }
        String tempStr = temp.toString()+"&key="+keyStr;
        return MD5.getMD5(tempStr, "UTF-8").toUpperCase();
	}
}
