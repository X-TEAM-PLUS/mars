package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.convert.UserDetectionInfoConvertService;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserDetectionInfoRspVO;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class GetUserDetectionListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.getUserDetectionList";

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Resource
    private UserDetectionInfoConvertService userDetectionInfoConvertService;

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
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }

            HealthCheckRecord queryWhere  = new HealthCheckRecord().setUserId(new BigDecimal(httpRequestBody.getUserId()));
            queryWhere.setStart(pageInfoReqVO.getStart());
            queryWhere.setLimit(pageInfoReqVO.getLimit());

            List<HealthCheckRecord> healthCheckRecordList = healthCheckRecordManager.query(queryWhere);
            if (healthCheckRecordList == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
                return httpResponseBody;
            }
            List<UserDetectionInfoRspVO> bizContent = Lists.newArrayList();
            for (HealthCheckRecord healthCheckRecord : healthCheckRecordList) {
                bizContent.add(userDetectionInfoConvertService.toVO(healthCheckRecord));
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(bizContent));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
