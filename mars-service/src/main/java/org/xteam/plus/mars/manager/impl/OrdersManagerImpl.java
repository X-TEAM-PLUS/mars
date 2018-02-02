package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.OrdersDao;
import org.xteam.plus.mars.dao.ProductDao;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.domain.Orders;
import org.xteam.plus.mars.domain.Product;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ORDERS表Manager接口实现类
 */

@Service
public class OrdersManagerImpl implements OrdersManager {
    private static final Log log = LogFactory.getLog(OrdersManagerImpl.class);
    @javax.annotation.Resource
    private OrdersDao ordersDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public Orders get(Orders orders) throws Exception {
        return ordersDao.get(orders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Orders orders) throws Exception {
        return ordersDao.insert(orders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Orders> list) throws Exception {
        return ordersDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Orders orders) throws Exception {
        return ordersDao.delete(orders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Orders orders) throws Exception {
        return ordersDao.update(orders);
    }

    @Override
    public List<Orders> query(Orders orders) throws Exception {
        return ordersDao.query(orders);
    }

    @Override
    public Integer queryCount(Orders orders) throws Exception {
        return ordersDao.queryCount(orders);
    }

    @Override
    public Integer queryOrderTotalCount(Date nowDate) throws Exception {
        return ordersDao.queryOrderTotalCount(nowDate);
    }

    @Override
    public PayOrderInfo createStraightPinOrder(BigDecimal userId, BigDecimal productId, BigDecimal number, String address, String contactsMobile, BigDecimal fee) throws Exception {
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        if (userId == null) {
            throw new Exception("用户id不存在，不能生成订单!");
        }
        Product product = productDao.get(new Product().setProductId(productId));
        if (product == null) {
            throw new Exception("产品不存在，不能生成订单!");
        }
        Orders orders = new Orders();
        orders.setOrderType(0);
        orders.setBuyerUserId(userId);
        orders.setProductName(product.getProductName());
        orders.setCardType(0);
        orders.setProductPrice(fee);
        orders.setProductNum(number.intValue());
        orders.setCreated(new Date());
        orders.setProductId(productId);
        orders.setAddress(address);
        orders.setContactsMobile(contactsMobile);
        orders.setStatus(0);
        orders.setPayWay(1);
        orders.setOrderTime(new Date());
        int count = ordersDao.insert(orders);
        if (count <= 0) {
            throw new Exception("订单生成插入数据库异常!");
        }
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(String.valueOf(orders.getOrderNo()));
        payOrderInfo.setOrderName(product.getProductName());
        payOrderInfo.setTotalFee(String.valueOf(fee.intValue()));
        payOrderInfo.setTradeType("NATIVE");
        payOrderInfo.setProductId(productId.toString());
        payOrderInfo.setUserIp("124.193.184.90");
        payOrderInfo.setTimeExpire(getWaitDate());
        payOrderInfo.setTimeStart(getDate());
        return payOrderInfo;
    }

    @Override
    public boolean updateStraightPinOrder(BigDecimal orderNo, Map<Object, Object> reqMap) throws Exception {
        Orders orders = ordersDao.get(new Orders().setOrderNo(orderNo));
        if (orders == null) {
            throw new Exception("不存在的订单，请检查本次请求来源!");
        }
        try {
            orders.setPayOrderNo((String) reqMap.get("transaction_id"));
            orders.setUpdated(new Date());
            orders.setPayTime(new SimpleDateFormat("yyyyMMddHHmiss").parse(reqMap.get("time_end").toString()));
            orders.setOrderPrice(new BigDecimal(reqMap.get("total_fee").toString()));
            orders.setStatus(1);
        } catch (Exception e) {
            throw new Exception("更新订单数据失败，系统错误，导致用户无法更新订单，问题严重!");
        }
        int count = ordersDao.update(orders);
        if (count <= 0){
            throw new Exception("更新订单数据失败");
        }

        return false;
    }

    private String getDate() {
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    private String getWaitDate() {
        long date = System.currentTimeMillis();
        date += 30 * 60 * 1000;
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(date));

    }
}
