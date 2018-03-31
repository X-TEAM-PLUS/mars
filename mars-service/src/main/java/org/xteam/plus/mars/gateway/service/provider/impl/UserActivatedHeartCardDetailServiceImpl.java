package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户已激活的健康卡
 */
@Component
public class UserActivatedHeartCardDetailServiceImpl implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.security.user.heartcard.activated.get";

    @Resource
    private UserHealthCardManager userHealthCardManager;

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
            if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
                 return  new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
            }
            //用户iD
            BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());
            UserHealthCard userHealthCard = userHealthCardManager.queryForProductByActive(
                            new UserHealthCard().setActivateUserId(userId)
            );
            if (userHealthCard == null) {
                return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
            }
            Map<String,Object> resultMap = JsonUtils.transform(userHealthCard, HashMap.class);
            //剩余次数
            resultMap.put("surplusCount",userHealthCard.getSendTotalCount()-userHealthCard.getSendCount());
            //查询检测记录
            List<HealthCheckRecord>  healthCheckRecords = healthCheckRecordManager.query(new HealthCheckRecord()
                    .setUserId(userId)
                    .setStart(0)
                    .setLimit(Integer.MAX_VALUE)
            );
            //检测记录
            resultMap.put("list",healthCheckRecords);

            //返回
           return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(resultMap));

    }
}
