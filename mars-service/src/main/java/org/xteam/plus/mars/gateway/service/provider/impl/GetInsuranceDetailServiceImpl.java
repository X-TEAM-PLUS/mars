package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.InsuranceProduct;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.InsuranceProductReqVO;
import org.xteam.plus.mars.manager.InsuranceProductManager;

import javax.annotation.Resource;

/**
 * 获取保险产品详情
 */
@Component
public class GetInsuranceDetailServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.insurance.getDetail";

    @Resource
    private InsuranceProductManager insuranceProductManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);

        InsuranceProductReqVO insuranceProductReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), InsuranceProductReqVO.class);
        if (insuranceProductReqVO.getInsuranceProductNo() == null) {
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            return httpResponseBody;
        }

        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(
                JsonUtils.toJSON(
                        insuranceProductManager.getForCompany(
                                new InsuranceProduct()
                                        .setInsuranceProductNo(insuranceProductReqVO.getInsuranceProductNo())
                        )));
    }
}
