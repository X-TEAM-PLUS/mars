package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ORDERS表Manager接口
 */

public interface OrdersManager {

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
     * 查询订单金额统计
     *
     * @param nowDate
     * @return
     * @throws Exception
     */
    public Integer queryOrderTotalCount(Date nowDate) throws Exception;


    /**
     * 平台直销生成订单
     *
     * @param userId         用户Id
     * @param productId      产品Id
     * @param number         购买个数
     * @param address        收获地址
     * @param contactsMobile 联系方式
     * @return
     * @throws Exception
     */
    public PayOrderInfo createStraightPinOrder(BigDecimal userId, BigDecimal productId, BigDecimal number, String address, String contactsMobile, String certificateOf, String userRealName, String area) throws Exception;

    /**
     * 用户分销生成订单
     *
     * @param userId        用户iD
     * @param address       收获地址
     * @param cardNo        卡号
     * @param certificateOf 身份证号
     * @param userRealName  真实姓名
     * @return
     * @throws Exception
     */
    public PayOrderInfo createDistributionOrder(BigDecimal userId, String address, BigDecimal cardNo, String certificateOf, String userRealName, String area) throws Exception;

    /**
     * 支付回调后进行更新订单
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public boolean proccessOrder(Map<Object, Object> reqMap) throws Exception;

    /**
     * 卡密购买
     *
     * @param keys          卡密
     * @param userId        用户ID
     * @param address       收获地址
     * @param certificateOf 身份证号
     * @param userRealName  真实姓名
     * @param area          区域
     * @throws Exception
     */
    public void cardKeysOrder(String keys, BigDecimal userId, String address, String certificateOf, String userRealName, String area) throws Exception;
}
