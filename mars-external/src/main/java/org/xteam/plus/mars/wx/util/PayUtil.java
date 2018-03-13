package org.xteam.plus.mars.wx.util;

import org.xteam.plus.mars.wx.api.WxConfig;
import org.xteam.plus.mars.wx.bean.*;
import org.xteam.plus.mars.wx.util.crypto.MD5;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 支付工具类
 *
 * @author antgan
 */
public class PayUtil {

    /**
     * 生成统一支付订单实体
     *
     * @param bizOrder
     * @param ip
     * @param openId
     * @return
     */
    public static WxUnifiedOrder createPayInfo(PayOrderInfo order, String notifyUrl, String openid) {
        //map只是为了ASCII码从小到大排序（字典序）
        Map<String, String> payinfo = new HashMap<>();
        payinfo.put("appid", WxConfig.getInstance().getAppId());
        payinfo.put("mch_id", WxConfig.getInstance().getMchId());
        payinfo.put("nonce_str", StringUtils.randomStr(32));
        payinfo.put("body", order.getOrderName());
        payinfo.put("out_trade_no", order.getOrderId());//商品订单号
        payinfo.put("total_fee", "" + order.getTotalFee());
        payinfo.put("spbill_create_ip", order.getUserIp());
        payinfo.put("notify_url", notifyUrl);
        payinfo.put("trade_type", order.getTradeType());
        if (order.getTradeType().equalsIgnoreCase("JSAPI")) {
            payinfo.put("openid", openid);
        }
        payinfo.put("time_start", order.getTimeStart());
        payinfo.put("time_expire", order.getTimeExpire());
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
        if (order.getTradeType().equalsIgnoreCase("JSAPI")) {
            pay.setOpenid(payinfo.get("openid"));
        }
        pay.setTimeStart(payinfo.get("time_start"));
        pay.setTimeExpire(payinfo.get("time_expire"));
        pay.setSign(payinfo.get("sign"));
        return pay;
    }

    public static WxGetPublicKey createPublicKey() {
        Map<String, String> publicKey = new HashMap<>();
        publicKey.put("mch_id", WxConfig.getInstance().getMchId());
        publicKey.put("nonce_str", StringUtils.randomStr(32));
        publicKey.put("sign_type", "MD5");

        WxGetPublicKey wxGetPublicKey = new WxGetPublicKey();
        wxGetPublicKey.setMchId(publicKey.get("mch_id"));
        wxGetPublicKey.setNonceStr(publicKey.get("nonce_str"));
        wxGetPublicKey.setSign_type(publicKey.get("sign_type"));
        wxGetPublicKey.setSign(createSign(publicKey, WxConfig.getInstance().getApiKey()));
        return wxGetPublicKey;
    }

    public static WxPayPocketMoney createPayPocketMoney(WxPayPocketMoney wxPayPocketMoney) {
        Map<String, String> wxPayPocketMoneyMap = new HashMap<>();
        wxPayPocketMoneyMap.put("mch_appid", WxConfig.getInstance().getAppId());
        wxPayPocketMoneyMap.put("mchid", WxConfig.getInstance().getMchId());
        wxPayPocketMoneyMap.put("device_info", "zaoan");
        wxPayPocketMoneyMap.put("nonce_str", StringUtils.randomStr(32));
        wxPayPocketMoneyMap.put("partner_trade_no", getPartnerTradeNo());
        wxPayPocketMoneyMap.put("spbill_create_ip", WxConfig.getInstance().getPayIp());
        wxPayPocketMoneyMap.put("openid", wxPayPocketMoney.getOpenid());
        wxPayPocketMoneyMap.put("check_name", wxPayPocketMoney.getCheckName());
        wxPayPocketMoneyMap.put("re_user_name", wxPayPocketMoney.getReUserName());
        wxPayPocketMoneyMap.put("amount", wxPayPocketMoney.getAmount());
        wxPayPocketMoneyMap.put("desc", wxPayPocketMoney.getDesc());

        wxPayPocketMoney.setMchAppid(wxPayPocketMoneyMap.get("mch_appid"));
        wxPayPocketMoney.setMchId(wxPayPocketMoneyMap.get("mchid"));
        wxPayPocketMoney.setDeviceInfo(wxPayPocketMoneyMap.get("device_info"));
        wxPayPocketMoney.setNonce_str(wxPayPocketMoneyMap.get("nonce_str"));
        wxPayPocketMoney.setPartnerTradeNo(wxPayPocketMoneyMap.get("partner_trade_no"));
        wxPayPocketMoney.setSpbillCreateIp(wxPayPocketMoneyMap.get("spbill_create_ip"));
        wxPayPocketMoney.setSign(createSign(wxPayPocketMoneyMap, WxConfig.getInstance().getApiKey()));
        return wxPayPocketMoney;
    }

    public static WxPayAnotherBank createPayAnotherBank(WxPayAnotherBank wxPayAnotherBank) {
        Map<String, String> wxPayAnotherBankMap = new HashMap<>();
        wxPayAnotherBankMap.put("mch_id", WxConfig.getInstance().getMchId());
        wxPayAnotherBankMap.put("partner_trade_no", getPartnerTradeNo());
        wxPayAnotherBankMap.put("nonce_str", StringUtils.randomStr(32));
        wxPayAnotherBankMap.put("enc_bank_no", wxPayAnotherBank.getEnc_bank_no());
        wxPayAnotherBankMap.put("enc_true_name", wxPayAnotherBank.getEnc_true_name());
        wxPayAnotherBankMap.put("bank_code", wxPayAnotherBank.getBank_code());
        wxPayAnotherBankMap.put("amount", wxPayAnotherBank.getAmount());
        wxPayAnotherBankMap.put("desc", wxPayAnotherBank.getDesc());

        wxPayAnotherBank.setMch_id(wxPayAnotherBankMap.get("mch_id"));
        wxPayAnotherBank.setPartner_trade_no(wxPayAnotherBankMap.get("partner_trade_no"));
        wxPayAnotherBank.setNonce_str(wxPayAnotherBankMap.get("nonce_str"));
        wxPayAnotherBank.setSign(createSign(wxPayAnotherBankMap, WxConfig.getInstance().getApiKey()));
        return wxPayAnotherBank;
    }

    public static int index = 0;

    public static String getPartnerTradeNo() {
        StringBuffer returnValue = new StringBuffer("110");
        try {
            String dateIndex = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            returnValue.append(dateIndex).append(getSeq());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue.toString();
    }

    public static String getSeq() {
        if (index >= 99999) {
            index = 0;
        }
        index++;
        String indexStr = String.valueOf(index);
        while (indexStr.length() < 5) {
            indexStr = "0" + indexStr;
        }
        return indexStr;
    }

    /**
     * 生成sign签名
     *
     * @param payinfo
     * @param key
     * @return
     */
    public static String createSign(Map<String, String> payinfo, String keyStr) {
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
        String tempStr = temp.toString() + "&key=" + keyStr;
        System.out.println(tempStr);
        return MD5.getMD5(tempStr, "UTF-8").toUpperCase();
    }
}
