package org.xteam.plus.mars.manager.subsidy.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.dao.OrdersDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.manager.SubsidyAbstractManager;
import org.xteam.plus.mars.manager.SubsidyManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;

import java.util.List;

/**
 * 发放常任理事推广补贴与常任理事管理补贴
 */
@Component
public class StandingDirectorManagerImpl extends SubsidyAbstractManager {
    @Resource
    private UserRelationDao userRelationDao;

    @Override
    public UserLevelEnum getUserLevelEnum() {
        return UserLevelEnum.STANDING_DIRECTOR;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean doExecute(Orders orders) throws Exception {
        try {
            logInfo("开始发放常任理事推广补贴与常任理事管理补贴 [" + JsonUtils.toJSON(orders) + "]");

            if (orders == null) {
                logInfo("获取的订单错误，不存在的订单ID，无法进行补贴[" + JsonUtils.toJSON(orders)  + "]");
                return false;
            }

            //查找关系发放补贴
            UserRelation userRelation = userRelationDao.getByUserId(new UserRelation().setUserId(orders.getBuyerUserId()));
            if(userRelation!=null) {
                // 推广补贴
                logInfo("开始发放推广补贴[" + JsonUtils.toJSON(orders) + "] 推广人[" +userRelation.getRefereeUserId() + "]");
                boolean success = grantSubsidy(AccountDetailTypeEnum.STANDING_DIRECTOR_EXTENSION_SUBSIDY, userRelation.getRefereeUserId(), orders.getOrderNo());
                if (!success) {
                    throw new Exception("发放userId[" + userRelation.getRefereeUserId()+ "] 推广补贴失败,失败原因返回false");
                }
                // 服务补贴
                List<AccountDetailTypeEnum> accountDetailTypeEnumList = Lists.newArrayList();
                // 增加常务理事管理补贴
                accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_SUBSIDY);
                return grantSubsidyList(accountDetailTypeEnumList,userRelation.getRefereeUserId(), orders.getOrderNo());
            }else{
                logInfo("未找到用户["+orders.getBuyerUserId()+"]的上级关系");
            }
        } catch (Exception e) {
            logInfo("发放常任理事推广补贴与常任理事管理补贴失败，失败原因", e);
        } finally {
            logInfo("发放常任理事推广补贴与常任理事管理补贴 [" + JsonUtils.toJSON(orders) + "] 结束");
        }
        return true;
    }
}
