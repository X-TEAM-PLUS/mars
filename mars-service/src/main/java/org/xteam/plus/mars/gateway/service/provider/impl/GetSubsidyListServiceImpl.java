package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.convert.UserAccountDetailConvertService;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserInfoReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserAccountDetailRspVO;
import org.xteam.plus.mars.manager.AccountDetailManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户账户余额查询接口
 */
@Component
public class GetSubsidyListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.getSubsidyList";

    @Resource
    private AccountDetailManager accountDetailManager;

    @Resource
    private UserAccountDetailConvertService userAccountDetailConvertService;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    public static List<AccountDetailTypeEnum> accountDetailTypeEnumList = Lists.newArrayList();

    static {
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.DIRECTOR_WORK_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.EXTENSION_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.SERVICE_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.SOCIAL_WORK_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_SUBSIDY);
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        long beginDate = System.currentTimeMillis();
        this.logInfo(METHOD_NAME + " request  [" + httpRequestBody.toString() + "]");
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            UserInfoReqVO userInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserInfoReqVO.class);
            if (userInfoReqVO.getUserId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            List<UserAccountDetailRspVO> userAccountDetailRspVOS = Lists.newArrayList();
            List<AccountDetail> accountDetailList = accountDetailManager.queryBusinessTypes(accountDetailTypeEnumList, userInfoReqVO.getUserId());
            for (AccountDetail accountDetail : accountDetailList) {
                userAccountDetailRspVOS.add(userAccountDetailConvertService.toVO(accountDetail));
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(userAccountDetailRspVOS));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
