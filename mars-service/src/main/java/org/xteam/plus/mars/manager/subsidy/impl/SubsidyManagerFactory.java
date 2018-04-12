package org.xteam.plus.mars.manager.subsidy.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.manager.SubsidyManager;
import org.xteam.plus.mars.type.OrderTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class SubsidyManagerFactory extends Logging implements ApplicationContextAware {

    private static Map<Integer, SubsidyManager> subsidyManagerMap = new HashMap<Integer, SubsidyManager>();

    private ApplicationContext applicationContext;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRelationDao userRelationDao;

    protected void init() {
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(SubsidyManager.class);
            for (String beanName : beanNames) {
                SubsidyManager subsidyManager = (SubsidyManager) applicationContext.getBean(beanName);
                // 如果订单用户为社工
                if (subsidyManager instanceof SocialWorkerManagerImpl) {
                    subsidyManagerMap.put(UserLevelEnum.SOCIAL.getCode(), subsidyManager);
                }
                // 如果订单用户为理事
                if (subsidyManager instanceof DirectorManagerImpl) {
                    subsidyManagerMap.put(UserLevelEnum.DIRECTOR.getCode(), subsidyManager);
                }
                if (subsidyManager instanceof StandingDirectorManagerImpl) {
                    subsidyManagerMap.put(UserLevelEnum.STANDING_DIRECTOR.getCode(), subsidyManager);
                }
            }
        } catch (Exception e) {
            logError("加载补贴实例异常", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }

    public boolean execute(Orders orders) throws Exception {
        logInfo("开始发放补贴 SubsidyManagerFactory orders [" + JsonUtils.toJSON(orders) + "]");

        if (OrderTypeEnum.valueOf(orders.getOrderType()) != OrderTypeEnum.PLATFORM_STRAIGHT) {
            logInfo("订单ID [" + orders.getOrderNo() + "] 不是平台直销的订单，不能进行发放补贴");
            return true;
        }
        if (orders.getStatus() != 1) {
            logInfo("订单ID [" + orders.getOrderNo() + "] 支付状态 [" + orders.getStatus() + "] 支付状态错误，不能进行补贴!");
            return true;
        }

        //查找关系发放补贴
        UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(orders.getBuyerUserId()));
        //如果上级不为空
        if(userRelation!=null){
            UserInfo sellerUserInfo = userInfoDao.get(new UserInfo().setUserId(userRelation.getRefereeUserId()));
            SubsidyManager subsidyManager = subsidyManagerMap.get(sellerUserInfo.getUserLevel());
            if (subsidyManager != null) {
                return subsidyManager.doExecute(orders);
            }
            logInfo("用户等级为 UserLevel[" + UserLevelEnum.valueOf(sellerUserInfo.getUserLevel()).getInfo() + "] 不能进行补贴");

        }
        return true;
    }

}
