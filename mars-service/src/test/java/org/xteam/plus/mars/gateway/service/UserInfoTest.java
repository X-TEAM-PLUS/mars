package org.xteam.plus.mars.gateway.service;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.*;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.*;
import org.xteam.plus.mars.service.Service;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.OrderTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Service.class)
public class UserInfoTest {

    @Resource
    private GetUserDetailServiceImpl getUserInfoService;

    @Resource
    private GetRecommendListServiceImpl getRecommendListService;

    @Resource
    private GetSubsidyListServiceImpl getSubsidyListService;

    @Resource
    private UserLevelApplyServiceImpl userLevelApplyService;

    @Resource
    private UserActivatedHeartCardDetailServiceImpl userActivatedHeartCardInfoService;

    @Resource
    private UserHealthCheckRecordListServiceImpl userHealthCheckRecordListService;

    @Resource
    private BindBankServiceImpl bindBankService;

    @Resource
    private GetAccountInfoServiceImpl getAccountInfoService;

    @Resource
    private GetUserInsuranceListServiceImpl getUserInsuranceListService;

    @Resource
    private GetInsuranceAllListServiceImpl getInsuranceAllListService;

    @Resource
    private GetInsuranceDetailServiceImpl getInsuranceDetailService;

    @Resource
    private GetWithdrawDepositListServiceImpl getRecordListService;

    @Resource
    private WithdrawDepositApplyServiceImpl applyRecordService;

    @Resource
    private GetWithdrawDepositDetailServiceImpl getRecordInfoService;

    @Resource
    private SubmitUserDetectionServiceImpl submitUserDetectionService;

    @Resource
    private BankConfigListServiceImpl bankConfigListService;

    @Resource
    private WxPayServiceAppInfoServiceImpl wxPayServiceAppInfoService;

    @Resource
    private GetUserInsuranceDetailServiceImpl getUserInsuranceDetailService;

    @Test
    public void getUserInsuranceDetailService(){
        HashMap parms = Maps.newHashMap();
        parms.put("insuranceOrderId",1);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(parms));
        try {
            httpRequestBody.setUserId("2000000");
            HttpResponseBody httpResponseBody = getUserInsuranceDetailService.gateWay(httpRequestBody);
            System.out.println("getUserInsuranceDetailService :" + httpResponseBody.getMsg());
            System.out.println("getUserInsuranceDetailService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wxPayServiceAppInfoService() {
        WxPayJsApiReqVO wxPayJsApiReqVO = new WxPayJsApiReqVO();
        wxPayJsApiReqVO.setUserId(new BigDecimal(2000004));
        wxPayJsApiReqVO.setProductId(new BigDecimal(1));
        wxPayJsApiReqVO.setNumber(new BigDecimal(1));
        wxPayJsApiReqVO.setAddress("北京市丰台区");
        wxPayJsApiReqVO.setContactsMobile("139912313");
        wxPayJsApiReqVO.setOrderTypeEnum(OrderTypeEnum.PLATFORM_STRAIGHT);
        System.out.println("bizContent : " + JsonUtils.toJSON(wxPayJsApiReqVO));
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(wxPayJsApiReqVO));
        try {
            HttpResponseBody httpResponseBody = wxPayServiceAppInfoService.gateWay(httpRequestBody);
            System.out.println("wxPayServiceAppInfoService :" + httpResponseBody.getMsg());
            System.out.println("wxPayServiceAppInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bankConfigListService() {
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        try {
            HttpResponseBody httpResponseBody = bankConfigListService.gateWay(httpRequestBody);
            System.out.println(httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 无法模拟提交
     */
    @Test
    public void submitUserDetectionService() {
        SubmitUserDetectionReqVO submitUserDetectionReqVO = new SubmitUserDetectionReqVO();
        submitUserDetectionReqVO.setCheckReport("测试测试测试");
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(submitUserDetectionReqVO));
        System.out.println(JsonUtils.toJSON(httpRequestBody));
//        try {
//            HttpResponseBody httpResponseBody = submitUserDetectionService.gateWay(httpRequestBody);
//            System.out.println("submitUserDetectionService :" + httpResponseBody.getMsg());
//            System.out.println("submitUserDetectionService :" + httpResponseBody.getBizContent());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 获取提现记录详情
     */
    @Test
    public void getRecordInfoService() {
        RecordReqVO recordReqVO = new RecordReqVO();
        recordReqVO.setRecordId(new BigDecimal(1));
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(recordReqVO));
        try {
            HttpResponseBody httpResponseBody = getRecordInfoService.gateWay(httpRequestBody);
            System.out.println("getRecordInfoService :" + httpResponseBody.getMsg());
            System.out.println("getRecordInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起提现申请
     */
    @Test
    public void applyRecord() {
        ApplyRecordReqVO applyRecordReqVO = new ApplyRecordReqVO();
        applyRecordReqVO.setAmount(new BigDecimal(50));
        applyRecordReqVO.setBankAccountName("宋鑫磊");
        applyRecordReqVO.setBankAccountNo("12312313");
        applyRecordReqVO.setPayWay(0);

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(applyRecordReqVO));
        httpRequestBody.setUserId("2000000");
        try {
            HttpResponseBody httpResponseBody = applyRecordService.gateWay(httpRequestBody);
            System.out.println("applyRecord :" + httpResponseBody.getMsg());
            System.out.println("applyRecord :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户提现记录
     */
    @Test
    public void getRecordListService() {
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();

        pageInfoReqVO.setStart(0);
        pageInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getRecordListService.gateWay(httpRequestBody);
            System.out.println("getRecordListService :" + httpResponseBody.getMsg());
            System.out.println("getRecordListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询保险详情
     */
    @Test
    public void getInsuranceInfoService() {
        InsuranceProductReqVO insuranceProductReqVO = new InsuranceProductReqVO();
        insuranceProductReqVO.setInsuranceProductNo(new BigDecimal(1));
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(insuranceProductReqVO));
        try {
            HttpResponseBody httpResponseBody = getInsuranceDetailService.gateWay(httpRequestBody);
            System.out.println("getInsuranceDetailService :" + httpResponseBody.getMsg());
            System.out.println("getInsuranceDetailService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取保险产品列表
     */
    @Test
    public void getInsuranceAllListService() {
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();
        pageInfoReqVO.setStart(new Integer(0));
        pageInfoReqVO.setLimit(new Integer(10));
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getInsuranceAllListService.gateWay(httpRequestBody);
            System.out.println("getInsuranceAllListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户保险信息
     */
    @Test
    public void getUserInsuranceListService() {
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();
        pageInfoReqVO.setStart(0);
        pageInfoReqVO.setLimit(10);

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getUserInsuranceListService.gateWay(httpRequestBody);
            System.out.println("getUserInsuranceListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户账户余额
     */
    @Test
    public void getAccountInfoService() {

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        try {
            HttpResponseBody httpResponseBody = getAccountInfoService.gateWay(httpRequestBody);
            System.out.println("getAccountInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户绑定银行卡
     */
    @Test
    public void bindBank() {
        long cardNo = 2333333333333333333l;
        UserCardBindReqVO userCardBindReqVO = new UserCardBindReqVO();
        userCardBindReqVO.setCardNo(cardNo);
        userCardBindReqVO.setCardUserName("严昆更新");
        userCardBindReqVO.setBankId(new BigDecimal(1004));
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(userCardBindReqVO));
        try {
            HttpResponseBody httpResponseBody = bindBankService.gateWay(httpRequestBody);
            System.out.println("getUserInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     */
    @Test
    public void getUserInfoService() {


        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000001");
        httpRequestBody.setBizContent("");
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
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();
        pageInfoReqVO.setStart(0);
        pageInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = getRecommendListService.gateWay(httpRequestBody);
            System.out.println("getRecommendListService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某用户的补贴数据
     */
    @Test
    public void subsidyList() {
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();
        pageInfoReqVO.setStart(0);
        pageInfoReqVO.setLimit(10);

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
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
    public void userLevelApply() {
        UserApplyInfoReqVO userApplyInfoReqVO = new UserApplyInfoReqVO();
        userApplyInfoReqVO.setApplayTypeEnum(ApplayTypeEnum.DIRECTOR);
        userApplyInfoReqVO.setReason("测试申请");
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000000");
        httpRequestBody.setBizContent(JsonUtils.toJSON(userApplyInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = userLevelApplyService.gateWay(httpRequestBody);
            System.out.println(JsonUtils.toJSON(httpResponseBody));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户已激活的健康卡，包含产品信息
     */
    @Test
    public void getUserDetectionInfo() {

        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000001");

        try {
            HttpResponseBody httpResponseBody = this.userActivatedHeartCardInfoService.gateWay(httpRequestBody);
            System.out.println("getUserDetectionInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询体检记录
     */
    @Test
    public void getUserDetectionList() {
        PageInfoReqVO pageInfoReqVO = new PageInfoReqVO();
        pageInfoReqVO.setStart(0);
        pageInfoReqVO.setLimit(10);
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        httpRequestBody.setUserId("2000001");
        httpRequestBody.setBizContent(JsonUtils.toJSON(pageInfoReqVO));
        try {
            HttpResponseBody httpResponseBody = this.userHealthCheckRecordListService.gateWay(httpRequestBody);
            System.out.println("getUserDetectionInfoService :" + httpResponseBody.getBizContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
