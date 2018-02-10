package org.xteam.plus.mars.manager.subsidy.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;

//@Component
public class SubsidyManagerFactory extends Logging {

//    @Resource
//    private SocialWorkerManagerImpl socialWorkerManager;
//
//    @Resource
//    private DirectorManagerImpl directorManager;
//
//    @Resource
//    private StandingDirectorManagerImpl standingDirectorManager;
//
//    @Resource
//    private UserInfoManager userInfoManager;
//
//    public boolean execute(Orders orders) throws Exception {
//        logInfo("开始发放补贴 SubsidyManagerFactory orders [" + JsonUtils.toJSON(orders) + "]");
//        if (orders.getSellerUserId() == null) {
//            logInfo("orders [" + JsonUtils.toJSON(orders) + "] 不存在销售人员id，不进行补贴");
//            return true;
//        }
//        UserInfo sellerUserInfo = userInfoManager.get(new UserInfo().setUserId(orders.getSellerUserId()));
//        // 如果订单用户为社工
//        if (UserLevelEnum.valueOf(sellerUserInfo.getUserLevel()).getCode() == UserLevelEnum.SOCIAL.getCode()) {
//            return socialWorkerManager.doExecute(orders);
//        }
//        // 如果订单用户为理事
//        if (UserLevelEnum.valueOf(sellerUserInfo.getUserLevel()).getCode() == UserLevelEnum.DIRECTOR.getCode()) {
//            return directorManager.doExecute(orders);
//        }
//        if (UserLevelEnum.valueOf(sellerUserInfo.getUserLevel()).getCode() == UserLevelEnum.STANDING_DIRECTOR.getCode()) {
//            return standingDirectorManager.doExecute(orders);
//        }
//        return true;
//    }

}
