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
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserAccountDetailRspVO;
import org.xteam.plus.mars.manager.AccountDetailManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 获取某用户的补贴列表
 */
@Component
public class GetSubsidyListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.getSubsidyList";

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
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            List<UserAccountDetailRspVO> userAccountDetailRspVOS = Lists.newArrayList();
            List<AccountDetail> accountDetailList = accountDetailManager.queryBusinessTypes(accountDetailTypeEnumList, new BigDecimal(httpRequestBody.getUserId()));
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
