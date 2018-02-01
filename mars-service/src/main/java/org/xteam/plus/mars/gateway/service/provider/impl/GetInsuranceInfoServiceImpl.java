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

@Component
public class GetInsuranceInfoServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.insurance.getInfo";

    @Resource
    private InsuranceProductManager insuranceProductManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        long beginDate = System.currentTimeMillis();
        this.logInfo(METHOD_NAME + " request  [" + httpRequestBody.toString() + "]");
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            InsuranceProductReqVO insuranceProductReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), InsuranceProductReqVO.class);
            if (insuranceProductReqVO.getInsuranceProductNo() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            InsuranceProduct insuranceProduct = insuranceProductManager.getForCompany(new InsuranceProduct().setInsuranceProductNo(insuranceProductReqVO.getInsuranceProductNo()));
            if (insuranceProduct == null){
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
                return httpResponseBody;
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(insuranceProduct));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
