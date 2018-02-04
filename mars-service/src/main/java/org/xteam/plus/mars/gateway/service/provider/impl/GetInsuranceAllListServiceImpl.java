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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取所有保险产品列表
 */
@Component
public class GetInsuranceAllListServiceImpl implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.insurance.product.list";

    @Resource
    private InsuranceProductManager insuranceProductManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        List<InsuranceProduct> insuranceProductList = insuranceProductManager.queryForCompany(
                new InsuranceProduct()
                        .setStatus(1)
                        .setStart(0)
                        .setLimit(Integer.MAX_VALUE)
        );
        Map<String,Object> bizContentMap = new HashMap<>();
        bizContentMap.put("list",insuranceProductList);
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));

    }
}
