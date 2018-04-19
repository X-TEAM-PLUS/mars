package org.xteam.plus.mars.manager.subsidy.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.manager.subsidy.SubsidyAbstractManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;


/**
 * 发放常任理事推广补贴与常任理事管理补贴
 */
@Component
public class StandingDirectorManagerImpl extends SubsidyAbstractManager {
    @Resource
    private UserRelationDao userRelationDao;
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserLevelEnum getUserLevelEnum() {
        return UserLevelEnum.STANDING_DIRECTOR;
    }

    @Override
    public void doExecute(Orders orders, UserInfo upUserInfo) throws Exception {
        //常务理事推广补贴
        grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_EXTEND, upUserInfo, orders.getOrderNo());
        //查找上级
        UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(upUserInfo.getUserId()));
        if (userRelation != null) {
            UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userRelation.getRefereeUserId()));
            UserLevelEnum userLevel = UserLevelEnum.valueOf(userInfo.getUserLevel());
            switch (userLevel) {
                case STANDING_DIRECTOR:
                    //常务理事服务补贴
                    grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_SERVICE, userInfo, orders.getOrderNo());
                    break;
            }
        }
    }
}
