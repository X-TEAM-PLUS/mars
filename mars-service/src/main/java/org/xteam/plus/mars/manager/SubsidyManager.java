package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.type.UserLevelEnum;

public interface SubsidyManager {
    public UserLevelEnum getUserLevelEnum();

    /**
     * 执行发放补贴方法
     *
     * @param orders        订单详情
     * @return
     */
    public boolean doExecute(Orders orders) throws Exception;
}
