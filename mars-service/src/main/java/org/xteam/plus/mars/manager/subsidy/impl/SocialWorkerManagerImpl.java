package org.xteam.plus.mars.manager.subsidy.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
 * 社工补贴与社工管理补贴发放
 */
@Component
public class SocialWorkerManagerImpl extends SubsidyAbstractManager {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRelationDao userRelationDao;

    @Override
    public UserLevelEnum getUserLevelEnum() {
        return UserLevelEnum.SOCIAL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(Orders orders, UserInfo upUserInfo) throws Exception {
        //社工推广补贴
        grantSubsidy(AccountDetailTypeEnum.SOCIAL_EXTEND, upUserInfo, orders.getOrderNo());
        //查找关系发放补贴
        UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(upUserInfo.getUserId()));
        if (userRelation != null) {
            //查找上级
            UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userRelation.getRefereeUserId()));
            //上级用户级别
            UserLevelEnum userLevel = UserLevelEnum.valueOf(userInfo.getUserLevel());
            switch (userLevel) {
                //理事
                case DIRECTOR:
                    //理事社工管理补贴
                    grantSubsidy(AccountDetailTypeEnum.DIRECTOR_SOCIAL_MANAGER, userInfo, orders.getOrderNo());
//                    //查找更上级
//                    UserRelation superUserRelation = userRelationDao.getByUserId(new UserRelation().setUserId(userInfo.getUserId()));
//                    if(superUserRelation!=null){
//                        UserInfo superUserInfo = userInfoDao.get(new UserInfo().setUserId(superUserRelation.getRefereeUserId()));
//                        switch (UserLevelEnum.valueOf(superUserInfo.getUserLevel())){
//                            case DIRECTOR:
//                                //理事服务补贴
//                                grantSubsidy(AccountDetailTypeEnum.DIRECTOR_SERVICE, superUserInfo, orders.getOrderNo());
//                                break;
//                            case STANDING_DIRECTOR:
//                                //常务理事服务补贴
//                                grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_SERVICE, superUserInfo, orders.getOrderNo());
//                                break;
//                        }
//                    }
                    break;
                //常任理事
                case STANDING_DIRECTOR:
                    //常务理事社工管理补贴
                    grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_SOCIAL_MANAGER, userInfo, orders.getOrderNo());
                    break;
            }
        }

    }
}
