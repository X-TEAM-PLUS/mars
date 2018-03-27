package org.xteam.plus.mars.gateway.service.provider.impl;


import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 用户申请升级接口，通用方法
 */
public abstract class AbstractUserLevelApplyService extends Logging implements GateWayService {


    @Resource
    private UserRelationManager userRelationManager;

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private ApplyInfoManager applyInfoManager;

    /**
     * 校验升级的角色，是否够资格升级
     *
     * @param userId
     * @return
     */
    protected boolean checkUserRelationCardTotal(BigDecimal userId, ApplayTypeEnum applayTypeEnum) throws Exception {
        try {
            // 升级社工不需要进行判断
            if (applayTypeEnum.getCode() == ApplayTypeEnum.SOCIAL.getCode()) {
                return true;
            }
            // 获取用户直接推荐人
            int userRelationCount = userRelationManager.queryForUserCount(new UserRelation().setRefereeUserId(userId));
            if (userRelationCount >= applayTypeEnum.getLimit()) {
                logInfo("用户ID [" + userId + "] 直接推荐人满足条件，可以直接升级 [" + userRelationCount + "]");
                return true;
            }
            HashMap<String, Object> userRelationMap = userRelationManager.queryMyTeamCountAndNextLevelCount(userId);
            List<HashMap> userRelationList = (List) userRelationMap.get("userTeamList");
            // 不存在用户推荐人
            if (userRelationList == null || userRelationList.size() == 0) {
                logInfo("用户ID [" + userId + "] 申请为[" + applayTypeEnum.getInfo() + "] 时，不存在用户关系");
                return false;
            }
            int count = 0;
            for (HashMap relationUser : userRelationList) {
                logInfo("用户ID [" + userId + "] 的用户下线 [" + JsonUtils.toJSON(relationUser) + "]");
                if (relationUser.get("userCount") != null) {
                    if (Integer.parseInt(relationUser.get("userCount").toString()) >= applayTypeEnum.getLimit()) {
                        count++;
                    }
                }
            }
            if (count >= 2) {
                logInfo("用户ID [" + userId + "] 够资格进行升级为 [" + applayTypeEnum.getInfo() + "]");
                return true;
            }
        } catch (Exception e) {
            logInfo("获取用户所有推荐人的总购卡数 异常 userId[" + userId + "]", e);
            throw e;
        }
        return false;
    }


    public boolean checkCalibration(UserApplyInfoReqVO userApplyInfoReqVO, BigDecimal userId, ApplayTypeEnum applayTypeEnum) throws Exception {

        if (userApplyInfoReqVO == null || userId == null || applayTypeEnum == null) {
            throw new Exception(GlobalErrorMessage.MISSING_PARAMETERS.getMessage());
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(userId));
        if (userInfo == null) {
            throw new Exception(GlobalErrorMessage.OBJECT_ISNULL.getMessage());
        }
        UserLevelEnum userLevelEnum = UserLevelEnum.valueOf(userInfo.getUserLevel());
        // 用户申请为社工
        if (applayTypeEnum.getCode() == ApplayTypeEnum.SOCIAL.getCode()) {
            if (userLevelEnum.getCode() != UserLevelEnum.MEMBER.getCode()) {
                throw new Exception("用户当前不是会员，不能申请为社工!");
            }
        }
        // 用户申请为理事
        if (applayTypeEnum.getCode() == ApplayTypeEnum.DIRECTOR.getCode()) {
            if (userLevelEnum.getCode() != UserLevelEnum.SOCIAL.getCode()) {
                throw new Exception("用户当前不是社工，不能申请为理事!");
            }
        }
        // 用户申请为常任理事
        if (applayTypeEnum.getCode() == ApplayTypeEnum.STANDING_DIRECTOR.getCode()) {
            if (userLevelEnum.getCode() != UserLevelEnum.DIRECTOR.getCode()) {
                throw new Exception("用户当前不是理事，不能申请为常任理事!");
            }
        }
        return checkUserRelationCardTotal(userId, applayTypeEnum);
    }


    protected HttpResponseBody submitApplyUp(UserApplyInfoReqVO userApplyInfoReqVO, BigDecimal userId, ApplayTypeEnum applayTypeEnum) throws Exception {
        try {
            ApplyInfo applyInfo = new ApplyInfo();
            if (checkCalibration(userApplyInfoReqVO, userId, applayTypeEnum)) {
                applyInfo.setStatus(1);
            } else {
                applyInfo.setStatus(0);
            }
            applyInfo.setApplyReason(userApplyInfoReqVO.getReason());
            applyInfo.setApplyType(applayTypeEnum.getCode());
            applyInfo.setApplyWay(0);
            applyInfo.setCreated(new Date());
            applyInfo.setUserId(userId);
            int count = applyInfoManager.insert(applyInfo);
            if (count > 0) {
                return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
            } else {
                return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
            }
        } catch (Exception e) {
            logInfo("申请升级异常 req[" + JsonUtils.toJSON(userApplyInfoReqVO) + "]", e);
            throw e;
        }

    }
}
