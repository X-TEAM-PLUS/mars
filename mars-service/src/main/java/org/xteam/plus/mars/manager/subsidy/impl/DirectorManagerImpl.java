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
 * 发放理事推广补贴与理事管理补贴
 */
@Component
public class DirectorManagerImpl extends SubsidyAbstractManager {
    @Resource
    private UserRelationDao userRelationDao;
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserLevelEnum getUserLevelEnum() {
        return UserLevelEnum.DIRECTOR;
    }

    @Override
    public void  doExecute(Orders orders,UserInfo upUserInfo) throws Exception {
            //理事推广补贴
            grantSubsidy(AccountDetailTypeEnum.DIRECTOR_EXTEND, upUserInfo, orders.getOrderNo());
            //查找关系发放补贴
            UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(upUserInfo.getUserId()));
            if(userRelation!=null) {
                //查找上级
                UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userRelation.getRefereeUserId()));
                    //上级用户级别
                    UserLevelEnum userLevel = UserLevelEnum.valueOf(userInfo.getUserLevel());
                    switch (userLevel){
                        //理事
                        case DIRECTOR:
                            //理事服务补贴
                            grantSubsidy(AccountDetailTypeEnum.DIRECTOR_SERVICE,userInfo, orders.getOrderNo());
                            break;
                        //常任理事
                        case STANDING_DIRECTOR:
                            grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_DIRECTOR_MANAGER,userInfo, orders.getOrderNo());
                            break;
                    }
                }
    }
}
