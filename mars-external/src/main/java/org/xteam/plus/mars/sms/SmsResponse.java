package org.xteam.plus.mars.sms;

public class SmsResponse {
    /**
     * 返回码  0为成功
     */
   private Integer error_code;

    /**
     * 	返回说明
     */
   private String reason;

    public Integer getError_code() {
        return error_code;
    }

    public SmsResponse setError_code(Integer error_code) {
        this.error_code = error_code;
        return this;

    }

    public String getReason() {
        return reason;
    }

    public SmsResponse setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
