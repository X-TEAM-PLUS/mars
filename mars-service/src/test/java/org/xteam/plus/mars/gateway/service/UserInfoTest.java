package org.xteam.plus.mars.gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.*;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserInfoReqVO;
import org.xteam.plus.mars.service.Service;
import org.xteam.plus.mars.type.ApplayTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Service.class)
public class UserInfoTest {

    @Resource
    private GetUserInfoServiceImpl getUserInfoService;

    @Resource
    private GetRecommendListServiceImpl getRecommendListService;

    @Resource
    private GetSubsidyListServiceImpl getSubsidyListService;

    @Resource
    private UserLevelApplyServiceImpl userLevelApplyService;

    @Resource
    private GetUserDetectionInfoServiceImpl getUserDetectionInfoService;

    @Resource
    private GetUserDetectionListServiceImpl getUserDetectionListService;

    /**
     * 获取用户信息
     */
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

    /**
     * 获取某用户下所有社工的推荐人数，与自己直接推荐人数
     */
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

    /**
     * 获取账户余额
     */
    @Test
    public void subsidyList(){
        UserInfoReqVO userInfoReqVO = new UserInfoReqVO();
        userInfoReqVO.setUserId(new BigDecimal(2000000));
        userInfoReqVO.setStart(0);
        userInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getSubsidyListService.gateWay(httpRequestBody);
            System.out.println("getSubsidyListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请升级，
     * userId
     * applayType 申请级别
     * reason 申请理由
     */
    @Test
    public void userLevelApply(){
        UserApplyInfoReqVO userApplyInfoReqVO = new UserApplyInfoReqVO();
        userApplyInfoReqVO.setUserId(new BigDecimal(2000000));
        userApplyInfoReqVO.setApplayTypeEnum(ApplayTypeEnum.DIRECTOR);
        userApplyInfoReqVO.setReason("测试申请");
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userApplyInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = userLevelApplyService.gateWay(httpRequestBody);
            System.out.println(JsonUtils.toJSON(httpResponseBody));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取用户已激活的健康卡，包含产品信息
     */
    @Test
    public void getUserDetectionInfo(){
        UserInfoReqVO userInfoReqVO = new UserInfoReqVO();
        userInfoReqVO.setUserId(new BigDecimal(2000001));

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = this.getUserDetectionInfoService.gateWay(httpRequestBody);
            System.out.println("getUserDetectionInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询体检记录
     */
    @Test
    public void getUserDetectionList(){
        UserInfoReqVO userInfoReqVO = new UserInfoReqVO();
        userInfoReqVO.setUserId(new BigDecimal(2000001));
        userInfoReqVO.setStart(0);
        userInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(userInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = this.getUserDetectionListService.gateWay(httpRequestBody);
            System.out.println("getUserDetectionInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
