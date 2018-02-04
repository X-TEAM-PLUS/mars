package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserHealthCheckRecordListServiceImpl  implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.heartcheck.record.list";

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
            PageInfoReqVO pageInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), PageInfoReqVO.class);
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            List<HealthCheckRecord> healthCheckRecordList = healthCheckRecordManager.query(new HealthCheckRecord().setUserId(new BigDecimal(httpRequestBody.getUserId()))
                    .setStart(pageInfoReqVO.getStart())
                    .setLimit(pageInfoReqVO.getLimit())
            );
            if (healthCheckRecordList == null) {
                return  new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
            }
        Map<String,Object> bizContentMap = new HashMap<>();
        bizContentMap.put("list",healthCheckRecordList);
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }
}
