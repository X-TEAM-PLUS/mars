package org.xteam.plus.mars.manager.subsidy;

import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.type.UserLevelEnum;

public interface SubsidyManager {
    public UserLevelEnum getUserLevelEnum();

    /**
     * 执行发放补贴方法
     *
     * @param orders        订单详情
     *  @param upUserInfo 上级用户
     * @return
     */
    public void  doExecute(Orders orders,UserInfo upUserInfo) throws Exception;
}
