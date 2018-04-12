package org.xteam.plus.mars.manager.subsidy.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.dao.OrdersDao;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.SubsidyAbstractManager;
import org.xteam.plus.mars.manager.SubsidyManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * 社工补贴与社工管理补贴发放
 */
@Component
public class SocialWorkerManagerImpl extends SubsidyAbstractManager {

    @Resource
    private OrdersDao ordersDao;

    @Override
    public UserLevelEnum getUserLevelEnum() {
        return UserLevelEnum.SOCIAL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean doExecute( Orders orders) throws Exception {
        try {
            logInfo("开始社工补贴与社工管理补贴 [" + JsonUtils.toJSON(orders) + "]");
            if (orders == null) {
                logInfo("获取的订单错误，不存在的订单ID，无法进行补贴[" + JsonUtils.toJSON(orders) + "]");
                return false;
            }
            if (orders.getSellerUserId() == null) {
                logInfo("订单中不存在售卖人信息，不进行补贴[" + JsonUtils.toJSON(orders) + "]");
                return true;
            }
            // 推广补贴
            logInfo("开始社工推广补贴[" + JsonUtils.toJSON(orders) + "] 推广人[" + orders.getSellerUserId() + "]");
            boolean success = grantSubsidy(AccountDetailTypeEnum.SOCIAL_EXTENSION_SUBSIDY, orders.getSellerUserId(), orders.getOrderNo());
            if (!success) {
                throw new Exception("发放userId[" + orders.getSellerUserId() + "] 推广补贴失败,失败原因返回false");
            }
            // 服务补贴
            List<AccountDetailTypeEnum> accountDetailTypeEnumList = Lists.newArrayList();
            // 增加社工管理补贴
            accountDetailTypeEnumList.add(AccountDetailTypeEnum.SOCIAL_WORK_SUBSIDY);
            // 增加理事管理补贴
            accountDetailTypeEnumList.add(AccountDetailTypeEnum.DIRECTOR_WORK_SUBSIDY);
            // 增加常务理事管理补贴
            accountDetailTypeEnumList.add(AccountDetailTypeEnum.STANDING_DIRECTOR_SUBSIDY);

            return grantSubsidyList(accountDetailTypeEnumList, orders.getSellerUserId(),  orders.getOrderNo());
        } catch (Exception e) {
            logError("发放社工补贴与社工管理补贴失败，失败原因", e);
        } finally {
            logInfo("发放社工补贴与社工管理补贴 [" + JsonUtils.toJSON(orders) + "] 结束");
        }
        return true;
    }
}
