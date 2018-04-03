package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户检查记录详情
 */
@Component
public class CheckRecordDetailServiceImpl implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.user.checkRecord.detail";

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        //获取请求参数
        Map<String, String> parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), HashMap.class);
        if (StringUtils.isEmpty(httpRequestBody.getUserId())|| parmas==null || StringUtils.isEmpty(parmas.get("checkRecordId"))) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        //用户iD
        BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());
        //检查记录ID
        BigDecimal checkRecordId = new BigDecimal(httpRequestBody.getUserId());
        //查询检测记录
        HealthCheckRecord healthCheckRecord = healthCheckRecordManager.get(new HealthCheckRecord()
                .setUserId(userId)
                .setCheckRecordId(checkRecordId)
        );
        if(healthCheckRecord!=null){
            //返回
            return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(healthCheckRecord));
        }else{
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }
    }
}
