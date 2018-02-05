package org.xteam.plus.mars.gateway.service.provider.impl;


import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.check.UserLevelApplyCheckService;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.ApplyInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户申请升级接口
 */
@Component
public class UserLevelApplyServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.userLevelApply";

    @Resource
    private ApplyInfoManager applyInfoManager;

    @Resource
    private UserLevelApplyCheckService userLevelApplyCheckService;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
            UserApplyInfoReqVO userApplyInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserApplyInfoReqVO.class);
            userLevelApplyCheckService.checkCalibration(userApplyInfoReqVO,new BigDecimal(httpRequestBody.getUserId()));
            ApplyInfo applyInfo = new ApplyInfo();
            applyInfo.setApplyReason(userApplyInfoReqVO.getReason());
            applyInfo.setApplyType(userApplyInfoReqVO.getApplayTypeEnum().getCode());
            applyInfo.setApplyWay(0);
            applyInfo.setStatus(0);
            applyInfo.setCreated(new Date());
            applyInfo.setUserId(new BigDecimal(httpRequestBody.getUserId()));
            int count = applyInfoManager.insert(applyInfo);
            if (count > 0) {
                return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
            } else {
                return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
            }
    }
}
