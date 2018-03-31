package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class GetLastCheckRecordImpl  implements GateWayService {
    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.UserDetection.getlast";

    @Resource
    private UserHealthCardManager userHealthCardManager;

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
        //返回
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(userHealthCard));
    }
}
