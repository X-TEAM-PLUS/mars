package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.GlobalConf;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.GlobalConfManager;
import org.xteam.plus.mars.type.GlobalConfTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class BankConfigListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.bank.list";

    @Resource
    private GlobalConfManager globalConfManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HashMap<String, Object> bizContentMap = Maps.newHashMap();

        bizContentMap.put("list", globalConfManager.query(
                new GlobalConf().
                        setParameterType(new BigDecimal(GlobalConfTypeEnum.WX_BANK_LIST.getCode()))
        ));

        return new HttpResponseBody().setBizContent(JsonUtils.toJSON(bizContentMap));

    }
}
