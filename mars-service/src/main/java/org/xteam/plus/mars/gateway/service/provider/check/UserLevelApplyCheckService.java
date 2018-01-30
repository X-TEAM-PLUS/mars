package org.xteam.plus.mars.gateway.service.provider.check;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserApplyInfoReqVO;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;

@Component
public class UserLevelApplyCheckService {

    @Resource
    private UserInfoManager userInfoManager;

    public void checkCalibration(UserApplyInfoReqVO userApplyInfoReqVO) throws Exception {

        if (userApplyInfoReqVO == null || userApplyInfoReqVO.getUserId() == null || userApplyInfoReqVO.getApplayTypeEnum() == null) {
            throw new Exception(GlobalErrorMessage.MISSING_PARAMETERS.getMessage());
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(userApplyInfoReqVO.getUserId()));
        if (userInfo == null) {
            throw new Exception(GlobalErrorMessage.OBJECT_ISNULL.getMessage());
        }
        UserLevelEnum userLevelEnum = UserLevelEnum.valueOf(userInfo.getUserLevel());
        ApplayTypeEnum applayTypeEnum = userApplyInfoReqVO.getApplayTypeEnum();
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
    }
}
