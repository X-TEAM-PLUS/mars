package org.xteam.plus.mars.gateway.service.provider.impl;


import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户申请升级接口(社工)
 */
@Component
public class UserLevelApplyServiceSocialImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.userLevelApply.social";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private ApplyInfoManager applyInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        UserApplyInfoReqVO userApplyInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserApplyInfoReqVO.class);
        if (userApplyInfoReqVO == null) {
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }
        if (userApplyInfoReqVO.getWayType() == null) {
            return new HttpResponseBody(GlobalErrorMessage.USER_LEVEL_WAY_IS_NOT_NULL);
        }
        if (StringUtils.isEmpty(userApplyInfoReqVO.getInterests())
                || StringUtils.isEmpty(userApplyInfoReqVO.getIdNumber())
                || StringUtils.isEmpty(userApplyInfoReqVO.getRealName())
                || StringUtils.isEmpty(userApplyInfoReqVO.getReason())
                || StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(new BigDecimal(httpRequestBody.getUserId())));
        if (userInfo == null) {
            throw new Exception(GlobalErrorMessage.OBJECT_ISNULL.getMessage());
        }
        // 用户身份校验
        UserLevelEnum userLevelEnum = UserLevelEnum.valueOf(userInfo.getUserLevel());
        // 用户申请为理事
        if (userLevelEnum.getCode() != UserLevelEnum.MEMBER.getCode()) {
            throw new Exception("用户当前不是社工，不能申请为理事!");
        }
        ApplyInfo applyInfo = new ApplyInfo();
        applyInfo.setStatus(1);
        applyInfo.setApplyReason(userApplyInfoReqVO.getReason());
        applyInfo.setApplyType(ApplayTypeEnum.SOCIAL.getCode());
        applyInfo.setApplyWay(userApplyInfoReqVO.getWayType());
        applyInfo.setCreated(new Date());
        applyInfo.setUserId(new BigDecimal(httpRequestBody.getUserId()));
        applyInfo.setIdNumber(userApplyInfoReqVO.getIdNumber());
        applyInfo.setRealName(userApplyInfoReqVO.getRealName());
        int count = applyInfoManager.insert(applyInfo);
        if (count > 0) {
            return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        } else {
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
    }
}
