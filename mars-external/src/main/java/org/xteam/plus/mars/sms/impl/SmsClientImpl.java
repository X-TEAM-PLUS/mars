package org.xteam.plus.mars.sms.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.sms.SmsClient;
import org.xteam.plus.mars.sms.SmsResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class SmsClientImpl extends Logging implements SmsClient {
    public static final String DEFAULT_CHATSET = "UTF-8";
   private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("sms");
   private static final String  SENDURL = resourceBundle.getString("mars.sms.send.url");
   private static final String  APPKEY = resourceBundle.getString("mars.sms.appkey");
    private static final String  TEMPLATES_ID = resourceBundle.getString("mars.sms.tpl_id");

    /**
     * 发送短信验证码
     * @param mobileNo 手机号
     * @param code  验证码
     * @return
     */
    @Override
    public  SmsResponse send(String mobileNo, String code) {
        SmsResponse response = null;
        try{
            Map<String, String>  request = new HashMap<>();
            request .put("key",APPKEY);
            request .put("tpl_id",TEMPLATES_ID);
            request .put("mobile",mobileNo);
            request .put("tpl_value","#code#="+code);
                    ;
            //发送请求
            response = send(request);
        }catch (Exception e){
            logError("发送短信异常",e);
            response = new SmsResponse().setError_code(99999).setReason("发送短信异常");
        }
        return  response;
    }

    /**
     * 发送短信
     * @param request
     * @return
     * @throws IOException
     */
    private  SmsResponse send(Map<String, String> request) throws IOException {
        SmsResponse smsResponse= null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(buildUrl(SENDURL, request));
        CloseableHttpResponse res = httpclient.execute(httpGet);
        try {
            if (res.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                String result = EntityUtils.toString(res.getEntity());
                logInfo("短信接口返回:" + result);
                smsResponse = JsonUtils.fromJSON(result, SmsResponse.class);
            }
        } catch (Exception e) {
            logError("调用短信接口异常", e);
        } finally {
            httpGet.releaseConnection();
        }
        return smsResponse;
    }



    /**
     * 构造请求地址
     *
     * @param url
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    private String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(url);
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isEmpty(query.getKey()) && !StringUtils.isEmpty(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isEmpty(query.getKey())) {
                    sbQuery.append(URLEncoder.encode(query.getKey(),DEFAULT_CHATSET));
                    if (!StringUtils.isEmpty(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), DEFAULT_CHATSET));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }
}
