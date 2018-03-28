package org.xteam.plus.mars.gateway.service.provider.impl;


import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 用户申请升级接口(社工)
 */
@Component
public class UserLevelApplyServiceDirectorImpl extends AbstractUserLevelApplyService {

    private final String METHOD_NAME = "com.zhaoanyun.gateway.user.userLevelApply.director";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private ApplyInfoManager applyInfoManager;

    @Resource
    private UserRelationManager userRelationManager;

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
        if (userLevelEnum.getCode() != UserLevelEnum.SOCIAL.getCode()) {
            throw new Exception("用户当前不是社工，不能申请为理事!");
        }

        ApplyInfo applyInfo = new ApplyInfo();
        applyInfo.setApplyReason(userApplyInfoReqVO.getReason());
        applyInfo.setApplyType(ApplayTypeEnum.DIRECTOR.getCode());
        applyInfo.setApplyWay(userApplyInfoReqVO.getWayType());
        applyInfo.setCreated(new Date());
        applyInfo.setUserId(userInfo.getUserId());

        // 获取用户直接推荐人
        int userRelationCount = userRelationManager.queryForUserCount(new UserRelation().setRefereeUserId(userInfo.getUserId()));
        if (userRelationCount >= ApplayTypeEnum.DIRECTOR.getLimit()) {
            logInfo("用户ID [" + userInfo.getUserId() + "] 直接推荐人满足条件，可以直接升级 [" + userRelationCount + "]");
            applyInfo.setStatus(1);
        }
        if (applyInfo.getStatus() != 1) {
            HashMap<String, Object> userRelationMap = userRelationManager.queryMyTeamCountAndNextLevelCount(userInfo.getUserId());
            List<HashMap> userRelationList = (List) userRelationMap.get("userTeamList");
            // 不存在用户推荐人
            if (userRelationList == null || userRelationList.size() == 0) {
                logInfo("用户ID [" + userInfo.getUserId() + "] 申请为[" + ApplayTypeEnum.DIRECTOR.getInfo() + "] 时，不存在用户关系");
                applyInfo.setStatus(0);
            }
            int userLimitCount = 0;
            for (HashMap relationUser : userRelationList) {
                logInfo("用户ID [" + userInfo.getUserId() + "] 的用户下线 [" + JsonUtils.toJSON(relationUser) + "]");
                if (relationUser.get("userCount") != null) {
                    if (Integer.parseInt(relationUser.get("userCount").toString()) >= ApplayTypeEnum.DIRECTOR.getLimit()) {
                        userLimitCount++;
                    }
                }
            }
            // 如果满足条件的下线用户等于或超过2个，则自动申请设计
            if (userLimitCount >= 2) {
                applyInfo.setStatus(1);
            }
        }
        int count = applyInfoManager.insert(applyInfo);
        if (count > 0) {
            return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        } else {
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
    }
}
