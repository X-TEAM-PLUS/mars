package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.InsuranceProduct;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.manager.InsuranceProductManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取所有保险产品列表
 */
@Component
public class GetInsuranceAllListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.insurance.getAllList";

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
            PageInfoReqVO pageInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), PageInfoReqVO.class);
            if (pageInfoReqVO.getStart() == null || pageInfoReqVO.getLimit() == null
                    || StringUtils.isEmpty(httpRequestBody.getUserId())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            InsuranceProduct insuranceProduct = new InsuranceProduct();
            insuranceProduct.setStart(pageInfoReqVO.getStart());
            insuranceProduct.setLimit(pageInfoReqVO.getLimit());

            List<InsuranceProduct> insuranceProductList = insuranceProductManager.queryForCompany(insuranceProduct);
            if (insuranceProductList == null || insuranceProductList.isEmpty()) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
                return httpResponseBody;
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(insuranceProductList));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
