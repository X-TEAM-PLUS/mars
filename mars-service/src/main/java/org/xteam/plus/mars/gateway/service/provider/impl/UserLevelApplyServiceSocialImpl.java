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
import org.xteam.plus.mars.manager.MessageManager;
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

    @Resource
    private MessageManager  messageManager;

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

        if ( StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        //真实姓名
        if ( StringUtils.isEmpty(userApplyInfoReqVO.getRealName())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_REAL_NAME);
        }
        //身份证件号码
        if ( StringUtils.isEmpty(userApplyInfoReqVO.getIdNumber()) ) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_ID_NUMBER);
        }


        //申请原因
        if (StringUtils.isEmpty(userApplyInfoReqVO.getReason())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_APPLY_REASON);
        }

        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(new BigDecimal(httpRequestBody.getUserId())));
        if (userInfo == null) {
            throw new Exception(GlobalErrorMessage.OBJECT_ISNULL.getMessage());
        }
        // 用户身份校验
        UserLevelEnum userLevelEnum = UserLevelEnum.valueOf(userInfo.getUserLevel());
        // 用户申请
        if (userLevelEnum.getCode() != UserLevelEnum.MEMBER.getCode()) {
            throw new Exception("用户当前不是会员，不能申请为社工!");
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
            //发送消息
            messageManager.sendMessage(applyInfo.getUserId(),"社工申请审批通过","恭喜您，你的已成功升级为早安工程的社工。");
            return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        } else {
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
    }
}
