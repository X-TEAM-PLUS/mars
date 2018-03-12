package org.xteam.plus.mars.sms.impl;

import org.xteam.plus.mars.sms.SmsClient;
import org.xteam.plus.mars.sms.SmsResponse;

import static org.junit.jupiter.api.Assertions.*;

class SmsClientImplTest {

    @org.junit.jupiter.api.Test
    void send() throws Exception {
        SmsClient smsClient =  new SmsClientImpl();
        SmsResponse response = smsClient.send("15926078444","您好，iphoneX 6折订购，数量有限，欢迎抢购，200红包已入账。www.isuixin.com");
        assertEquals(response.getError_code().intValue(),0);
    }
}