package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Service;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.AreaManager;

import javax.annotation.Resource;

@Service
public class GetAreaDetailServiceImpl implements GateWayService {

    @Resource
    private AreaManager areaManager;

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.area.detail";

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(areaManager.getAreaAllJson()));
    }
}
