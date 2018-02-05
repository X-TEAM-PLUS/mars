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
 * 获取用户保险详情接口
 */
@Component
public class GetUserInsuranceDetailServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.insurance.detail";

    @Resource
    private UserInsuranceManager userInsuranceManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (StringUtils.isEmpty(httpRequestBody.getUserId())
                || StringUtils.isEmpty(httpRequestBody.getBizContent())
                ) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        Map<String,Object> params = JsonUtils.fromJSON(httpRequestBody.getBizContent(),HashMap.class);

        UserInsurance userInsurance = userInsuranceManager.getForProduct(new BigDecimal(params.get("insuranceOrderId").toString()));
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(userInsurance));

    }
}
