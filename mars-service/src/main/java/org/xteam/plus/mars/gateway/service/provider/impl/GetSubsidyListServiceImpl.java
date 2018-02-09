package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.Map;

/**
 * 获取某用户的补贴列表
 */
@Component
public class GetSubsidyListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.subsidy.list";

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
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.SOCIAL_WORK_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_SUBSIDY);

        accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_SERVICE_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.DIRECTOR_SERVICE_SUBSIDY);

        accountDetailTypeEnumList.add(AccountDetailTypeEnum.SOCIAL_EXTENSION_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.DIRECTOR_EXTENSION_SUBSIDY);
        accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_EXTENSION_SUBSIDY);
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

        if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        Map<String, Object> bizContentMap = Maps.newHashMap();
        List<UserAccountDetailRspVO> userAccountDetailRspVOS = Lists.newArrayList();
        List<AccountDetail> accountDetailList = accountDetailManager.queryBusinessTypes(accountDetailTypeEnumList, new BigDecimal(httpRequestBody.getUserId()));
        for (AccountDetail accountDetail : accountDetailList) {
            userAccountDetailRspVOS.add(userAccountDetailConvertService.toVO(accountDetail));
        }
        bizContentMap.put("list", userAccountDetailRspVOS);

        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }
}
