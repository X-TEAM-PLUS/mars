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
    public void  doExecute(Orders orders,UserInfo upUserInfo) throws Exception {
            //常务理事推广补贴
            grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_EXTEND,upUserInfo,orders.getOrderNo());
            //查找关系发放补贴
            UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(upUserInfo.getUserId()));
            //常务理事服务补贴
            if(userRelation!=null && UserLevelEnum.STANDING_DIRECTOR.getCode()==upUserInfo.getUserLevel()) {
                grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_SERVICE,userInfoDao.get(new UserInfo().setUserId(userRelation.getRefereeUserId())),orders.getOrderNo());
            }
    }
}
