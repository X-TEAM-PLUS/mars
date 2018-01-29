package org.xteam.plus.mars.gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.GetRecommendListServiceImpl;
import org.xteam.plus.mars.gateway.service.provider.impl.GetUserInfoServiceImpl;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserInfoReqVO;
import org.xteam.plus.mars.service.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Service.class)
public class UserInfoTest {

    @Resource
    private GetUserInfoServiceImpl getUserInfoService;

    @Resource
    private GetRecommendListServiceImpl getRecommendListService;

    @Test
    public void getUserInfoService() {
        UserInfoReqVO userInfoReqVO = new UserInfoReqVO();
        userInfoReqVO.setUserId(new BigDecimal(2000000));

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getUserInfoService.gateWay(httpRequestBody);
            System.out.println("getUserInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRecommendListService() {
        UserInfoReqVO userInfoReqVO = new UserInfoReqVO();
        userInfoReqVO.setUserId(new BigDecimal(2000000));
        userInfoReqVO.setStart(0);
        userInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getRecommendListService.gateWay(httpRequestBody);
            System.out.println("getRecommendListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
