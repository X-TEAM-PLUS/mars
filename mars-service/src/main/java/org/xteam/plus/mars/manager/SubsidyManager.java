package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.Orders;

public interface SubsidyManager {

    /**
     * 执行发放补贴方法
     *
     * @param orders        订单详情
     * @return
     */
    public boolean doExecute(Orders orders) throws Exception;
}
