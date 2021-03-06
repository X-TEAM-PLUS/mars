package org.xteam.plus.mars.sms.impl;

import org.xteam.plus.mars.sms.SmsClient;
import org.xteam.plus.mars.sms.SmsResponse;

import static org.junit.jupiter.api.Assertions.*;

class SmsClientImplTest {

    @org.junit.jupiter.api.Test
    void send() throws Exception {
        SmsClient smsClient =  new SmsClientImpl();
        SmsResponse response = smsClient.send("15926078444","111111");
        assertEquals(response.getError_code().intValue(),0);
    }
}