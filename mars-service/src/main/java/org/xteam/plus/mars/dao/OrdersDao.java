package org.xteam.plus.mars.dao;

import org.apache.ibatis.annotations.Param;
import org.xteam.plus.mars.domain.Orders;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ORDERS表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_ORDERS]表Mapper接口
 */

public interface OrdersDao {

    /**
     * 获取
     *
     * @param orders
     * @return int
     */
    public Orders get(Orders orders) throws Exception;

    /**
     * 新增
     *
     * @param orders
     * @return int 记录数
     */
    public int insert(Orders orders) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<Orders>
     * @return int  记录数
     */
    public int batchInsert(List<Orders> list) throws Exception;

    /**
     * 删除
     *
     * @param orders
     * @return int
     */
    public int delete(Orders orders) throws Exception;

    /**
     * 更新
     *
     * @param orders
     * @return int 记录数
     */
    public int update(Orders orders) throws Exception;

    /**
     * 查询
     *
     * @param orders
     * @return List<Orders>
     */
    public List<Orders> query(Orders orders) throws Exception;

    /**
     * 查询记录数
     *
     * @param orders
     * @return List<Orders>
     */
    public Integer queryCount(Orders orders) throws Exception;

    /**
     * 订单金额统计
     * @param nowDate
     * @return
     */
    public Integer queryOrderTotalCount(@Param("nowDate") Date nowDate);
}
