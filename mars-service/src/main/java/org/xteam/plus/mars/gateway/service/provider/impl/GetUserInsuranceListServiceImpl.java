package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserInsurance;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.UserInsuranceManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户保险列表接口
 */
@Component
public class GetUserInsuranceListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.insurance.list";

    @Resource
    private UserInsuranceManager userInsuranceManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        List<UserInsurance> userInsurances = userInsuranceManager.queryForProduct(
                new UserInsurance().setUserId(new BigDecimal(httpRequestBody.getUserId()))
                                                    .setStart(0)
                                                    .setLimit(Integer.MAX_VALUE)
        );

        Map<String, Object> bizContentMap = new HashMap<>();
        bizContentMap.put("list", userInsurances);
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));

    }
}
