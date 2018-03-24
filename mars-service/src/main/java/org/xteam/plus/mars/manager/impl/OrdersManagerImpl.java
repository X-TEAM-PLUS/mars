package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.*;
import org.xteam.plus.mars.domain.*;
import org.xteam.plus.mars.gateway.service.provider.impl.WxPayServiceAppInfoServiceImpl;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.CardStatusTypeEnum;
import org.xteam.plus.mars.type.OrderTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ORDERS表Manager接口实现类
 */

@Service
public class OrdersManagerImpl extends Logging implements OrdersManager {
    private static final Log log = LogFactory.getLog(OrdersManagerImpl.class);
    @javax.annotation.Resource
    private OrdersDao ordersDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private AccountDetailDao accountDetailDao;

    @Resource
    private UserHealthCardDao userHealthCardDao;

    @Resource
    private UserRelationDao userRelationDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
    @Transactional(rollbackFor = Exception.class)
    public PayOrderInfo createStraightPinOrder(BigDecimal userId, BigDecimal productId, BigDecimal number, String address, String contactsMobile, OrderTypeEnum orderTypeEnum, BigDecimal cardNo) throws Exception {
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        Orders orders = new Orders();
        if (userId == null) {
            throw new Exception("用户id不存在，不能生成订单!");
        }
        boolean isInsert = false;
        // 如果是会员分销
        if (orderTypeEnum.getCode() == OrderTypeEnum.VIP_DISTRIBUTION.getCode()) {
            List<Orders> ordersList = ordersDao.queryEffective(new Orders().setCardNo(cardNo));
            if (ordersList != null && ordersList.size() > 0) {
                orders = ordersList.get(0);
                isInsert = true;
                if (orders.getStatus() == 1) {
                    throw new Exception("此卡已经被卖掉，无法进行购买!");
                }
            }
            UserHealthCard userHealthCard = userHealthCardDao.get(new UserHealthCard().setCardNo(cardNo));
            if (userHealthCard == null) {
                throw new Exception("用户健康卡为空，不能进行支付!");
            }
            Product product = productDao.get(new Product().setProductId(userHealthCard.getProductId()));
            if (product == null && orderTypeEnum.getCode() == OrderTypeEnum.PLATFORM_STRAIGHT.getCode()) {
                throw new Exception("产品不存在，不能生成订单!");
            }
            orders.setProductNum(1);
            orders.setProductPrice(product.getAmount());
            orders.setSellerUserId(userHealthCard.getBuyerUserId());
            orders.setProductName(product.getProductName());
            orders.setProductId(product.getProductId());
        } else {
            Product product = productDao.get(new Product().setProductId(productId));
            if (product == null && orderTypeEnum.getCode() == OrderTypeEnum.PLATFORM_STRAIGHT.getCode()) {
                throw new Exception("产品不存在，不能生成订单!");
            }
            orders.setProductNum(number.intValue());
            orders.setProductPrice(product.getAmount().multiply(number));
            orders.setProductName(product.getProductName());
            orders.setProductId(product.getProductId());
        }
        orders.setOrderType(orderTypeEnum.getCode());
        orders.setBuyerUserId(userId);

        orders.setCardType(0);
        orders.setCardNo(cardNo);
        orders.setAddress(address);
        orders.setContactsMobile(contactsMobile);
        orders.setStatus(0);
        orders.setPayWay(1);
        orders.setOrderTime(new Date());
        if (!isInsert) {
            orders.setCreated(new Date());
            int count = ordersDao.insert(orders);
            if (count <= 0) {
                throw new Exception("订单生成插入数据库异常!");
            }
        } else {
            orders.setUpdated(new Date());
            ordersDao.update(orders);
        }
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(String.valueOf(orders.getOrderNo()));
        payOrderInfo.setOrderName(orders.getProductName());
        payOrderInfo.setTotalFee(String.valueOf(orders.getProductPrice()));
        payOrderInfo.setTradeType("NATIVE");
        payOrderInfo.setProductId(orders.getProductId().toString());
        payOrderInfo.setUserIp("124.193.184.90");
        payOrderInfo.setTimeExpire(getWaitDate());
        payOrderInfo.setTimeStart(getDate());
        return payOrderInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStraightPinOrder(Map<Object, Object> reqMap) throws Exception {
        BigDecimal orderNo = new BigDecimal((String) reqMap.get("out_trade_no"));
        Orders orders = ordersDao.get(new Orders().setOrderNo(orderNo));
        if (orders == null) {
            throw new Exception("不存在的订单，请检查本次请求来源!");
        }
        Product product = productDao.get(new Product().setProductId(orders.getProductId()));
        if (product == null) {
            throw new Exception("产品不存在，不能生成订单!");
        }
        try {
            orders.setPayOrderNo((String) reqMap.get("transaction_id"));
            orders.setUpdated(new Date());
            orders.setPayTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(reqMap.get("time_end").toString()));
            orders.setOrderPrice(new BigDecimal(reqMap.get("total_fee").toString()));
            orders.setStatus(1);
        } catch (Exception e) {
            throw new Exception("更新订单数据失败，系统错误，导致用户无法更新订单，问题严重!");
        }
        // 如果是会员分销订单，清理缓存
        if (OrderTypeEnum.valueOf(orders.getOrderType()) == OrderTypeEnum.VIP_DISTRIBUTION) {
            stringRedisTemplate.delete(WxPayServiceAppInfoServiceImpl.REDIS_TEMP_OATH_KEY + orders.getCardNo());
        }
        int count = ordersDao.update(orders);
        if (count <= 0) {
            throw new Exception("更新订单数据失败");
        }
        if (orders.getOrderType() == OrderTypeEnum.PLATFORM_STRAIGHT.getCode()) {
            BigDecimal buyUserId = orders.getBuyerUserId();
            UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(buyUserId));
            // 如果用户是游客，直接提升为会员
            if (userInfo.getUserLevel() == UserLevelEnum.TOURIST.getCode()) {
                userInfo.setUserLevel(UserLevelEnum.MEMBER.getCode());
                logInfo("用户ID [" + userInfo.getUserId() + "] 开始升级为会员 ");
                count = userInfoDao.update(userInfo);
                if (count <= 0) {
                    throw new Exception("升级用户等级失败,更新数据库为" + count + "!");
                }
            }
            List<UserHealthCard> userHealthCards = userHealthCardDao.queryForActiveUser(new UserHealthCard().setActivateUserId(userInfo.getUserId()));
            // 插入卡
            for (int i = 0; i < orders.getProductNum(); i++) {

                UserHealthCard userHealthCard = new UserHealthCard();
                userHealthCard.setCreated(new Date());
                userHealthCard.setProductId(orders.getProductId());
                userHealthCard.setProductType(product.getCardType());
                userHealthCard.setSurvivalPeriodMode(product.getSurvivalPeriodMode());
                userHealthCard.setSurvivalPeriodNum(product.getSurvivalPeriodNum());
                userHealthCard.setSendPeriodMode(product.getSendPeriodMode());
                userHealthCard.setSendPeriod(product.getSendPeriod());
                userHealthCard.setSendTotalCount(product.getSendTotalCount());
                userHealthCard.setBuyerUserId(orders.getBuyerUserId());
                userHealthCard.setSendCount(0);
                userHealthCard.setOrderNo(orders.getOrderNo());
                if (userHealthCards != null && userHealthCards.size() > 0) {
                    userHealthCard.setStatus(CardStatusTypeEnum.NOT_ATIVE.getCode());
                } else {
                    logInfo("用户购买卡自己激活 [" + JsonUtils.toJSON(userHealthCard) + "]");
                    userHealthCard.setStatus(CardStatusTypeEnum.ATIVE.getCode());
                    userHealthCard.setActivateUserId(orders.getBuyerUserId());
                }

                userHealthCardDao.insert(userHealthCard);
                // 插入账户明细表
                AccountDetail accountDetail = new AccountDetail();
                accountDetail.setAmount(product.getAmount());
                accountDetail.setServiceNo(orders.getOrderNo());
                accountDetail.setCreated(new Date());
                accountDetail.setUserId(orders.getBuyerUserId());
                accountDetail.setBusinesseType(AccountDetailTypeEnum.USER_BUY.getCode());
                accountDetail.setOperationDirection(1);
                count = accountDetailDao.insert(accountDetail);
                if (count <= 0) {
                    throw new Exception("更新订单数据失败");
                }
            }
        }
        if (orders.getOrderType() == OrderTypeEnum.VIP_DISTRIBUTION.getCode()) {
            UserHealthCard userHealthCard = userHealthCardDao.get(new UserHealthCard().setCardNo(orders.getCardNo()));
            if (userHealthCard == null) {
                throw new Exception("支付时，用户健康卡不存在，错误的地方，严重!");
            }
            userHealthCard.setUpdated(new Date());
            userHealthCard.setStatus(CardStatusTypeEnum.ATIVE.getCode());
            userHealthCard.setActivateUserId(orders.getBuyerUserId());
            userHealthCard.setCardActivateTime(new Date());
            userHealthCard.setOrderNo(orders.getOrderNo());
            userHealthCard.setCardDeadline(computationalValidity(product));
            userHealthCard.setSendCount(0);
            userHealthCardDao.update(userHealthCard);
            // 查询关系是否存在，如果不存在则创建关系
            if (userRelationDao.queryCount(new UserRelation().setUserId(orders.getBuyerUserId())) <= 0) {
                UserRelation userRelation = new UserRelation();
                userRelation.setUserId(orders.getBuyerUserId());
                userRelation.setRefereeUserId(orders.getSellerUserId());
                userRelation.setCreated(new Date());
                userRelationDao.insert(userRelation);
            }
        }
        return true;
    }

    private String getDate() {
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    private String getWaitDate() {
        long date = System.currentTimeMillis();
        date += 30 * 60 * 1000;
        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date(date));

    }

    /**
     * 获取健康卡有效期
     *
     * @param product
     * @return
     */
    private Date computationalValidity(Product product) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.YEAR, product.getSurvivalPeriodNum());//把日期往后增加一年.整数往后推,负数往前移动
        return calendar.getTime();
    }
}
