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
import org.xteam.plus.mars.manager.subsidy.SubsidyManagerFactory;
import org.xteam.plus.mars.type.*;
import org.xteam.plus.mars.wx.bean.PayOrderInfo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private AccountBalanceDao accountBalanceDao;

    @Resource
    private UserHealthCardDao userHealthCardDao;

    @Resource
    private UserRelationDao userRelationDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SubsidyManagerFactory subsidyManagerFactory;

    @Resource
    private CommissionDetailDao commissionDetailDao;


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
    public PayOrderInfo createStraightPinOrder(BigDecimal userId, BigDecimal productId, BigDecimal number, String address, String contactsMobile, String certificateOf, String userRealName) throws Exception {
        Orders orders = new Orders();
        if (userId == null) {
            throw new Exception("用户id不存在，不能生成订单!");
        }
        Product product = productDao.get(new Product().setProductId(productId));
        if (product == null) {
            throw new Exception("产品不存在，不能生成订单!");
        }

        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        if (userInfo == null) {
            throw new Exception("用户不存在!");
        }
        if (userInfo.getUserLevel() == 0) {
            userInfo.setRealName(userRealName);
            userInfo.setIdNumber(certificateOf);
            userInfo.setUpdated(new Date());
            // 回写用户数据
            userInfoDao.update(userInfo);
        }

        orders.setProductNum(1);
        orders.setProductPrice(product.getAmount());
        orders.setOrderPrice(product.getAmount().multiply(BigDecimal.valueOf(orders.getProductNum())));
        orders.setProductName(product.getProductName());
        orders.setProductId(product.getProductId());
        orders.setOrderType(OrderTypeEnum.PLATFORM_STRAIGHT.getCode());
        orders.setBuyerUserId(userId);
        orders.setCardType(0);
        orders.setAddress(address);
        orders.setContactsMobile(contactsMobile);
        orders.setStatus(0);
        orders.setPayWay(1);
        orders.setOrderTime(new Date());
        orders.setCreated(new Date());
        int count = ordersDao.insert(orders);
        if (count <= 0) {
            throw new Exception("订单生成插入数据库异常!");
        }


        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(String.valueOf(orders.getOrderNo()));
        payOrderInfo.setOrderName(orders.getProductName());
        payOrderInfo.setTotalFee(String.valueOf(orders.getOrderPrice()));
        payOrderInfo.setTradeType("NATIVE");
        payOrderInfo.setProductId(orders.getProductId().toString());
        payOrderInfo.setUserIp("124.193.184.90");
        payOrderInfo.setTimeExpire(getWaitDate());
        payOrderInfo.setTimeStart(getDate());
        return payOrderInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayOrderInfo createDistributionOrder(BigDecimal userId, String address, BigDecimal cardNo, String certificateOf, String userRealName) throws Exception {
        String buyCardNoLockKey = WxPayServiceAppInfoServiceImpl.REDIS_TEMP_OATH_KEY + cardNo.toString();
        if (stringRedisTemplate.opsForValue().get(buyCardNoLockKey) != null) {
            throw new Exception("该卡已被锁定，可能是已被购买或其它用户正在购买中.!");
        } else {
            //加锁 10分钟
            stringRedisTemplate.opsForValue().set(buyCardNoLockKey, "buyNow", 10, TimeUnit.MINUTES);
        }
        //查询该卡是否存在和状态
        UserHealthCard userHealthCard = userHealthCardDao.get(new UserHealthCard().setCardNo(cardNo));
        if (userHealthCard == null) {
            throw new Exception("不存的健康卡号.!");
        } else if (userHealthCard.getStatus() != 0) {
            throw new Exception("该卡已被激活过.!");
        }

        //查询当前用户是否已有激活的健康卡
        int userHealthCardCount = userHealthCardDao.queryCount(new UserHealthCard().setActivateUserId(userId));
        if (userHealthCardCount > 0) {
            throw new Exception("您已经有一张正在激活的健康卡，无法进行再次购买");
        }
        if (userHealthCard.getBuyerUserId().compareTo(userId) == 0) {
            throw new Exception("自己购买的健康卡无法分享给自己");
        }

        Product product = productDao.get(new Product().setProductId(userHealthCard.getProductId()));
        if (product == null) {
            throw new Exception("产品不存在，不能生成订单!");
        }
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        if (userInfo == null) {
            throw new Exception("用户不存在!");
        }
        if (userInfo.getUserLevel() == 0) {
            userInfo.setRealName(userRealName);
            userInfo.setIdNumber(certificateOf);
            userInfo.setUpdated(new Date());
            // 回写用户数据
            userInfoDao.update(userInfo);
        }


        Orders orders = new Orders();
        orders.setProductNum(1);
        orders.setProductPrice(product.getAmount());
        orders.setOrderPrice(product.getAmount().multiply(BigDecimal.valueOf(orders.getProductNum())));
        orders.setSellerUserId(userHealthCard.getBuyerUserId());
        orders.setProductName(product.getProductName());
        orders.setProductId(product.getProductId());
        orders.setOrderType(OrderTypeEnum.VIP_DISTRIBUTION.getCode());
        orders.setBuyerUserId(userId);
        orders.setCardType(0);
        orders.setCardNo(cardNo);
        orders.setAddress(address);
        orders.setContactsMobile(userInfo.getMobileNo());
        orders.setStatus(0);
        orders.setPayWay(1);
        orders.setOrderTime(new Date());
        orders.setCreated(new Date());
        ordersDao.insert(orders);


        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(String.valueOf(orders.getOrderNo()));
        payOrderInfo.setOrderName(orders.getProductName());
        payOrderInfo.setTotalFee(String.valueOf(orders.getOrderPrice()));
        payOrderInfo.setTradeType("NATIVE");
        payOrderInfo.setProductId(orders.getProductId().toString());
        payOrderInfo.setUserIp("124.193.184.90");
        payOrderInfo.setTimeExpire(getWaitDate());
        payOrderInfo.setTimeStart(getDate());
        return payOrderInfo;
    }

    /**
     * 更新成功订单
     *
     * @throws Exception
     */
    private void updateSuccessOrderInfo(Orders orders, Map<Object, Object> resultMap) throws Exception {
        try {
            orders.setPayOrderNo((String) resultMap.get("transaction_id"));
            orders.setUpdated(new Date());
            orders.setPayTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(resultMap.get("time_end").toString()));
            orders.setOrderPrice(new BigDecimal(resultMap.get("total_fee").toString()));
            orders.setStatus(1);
        } catch (Exception e) {
            throw new Exception("更新订单数据失败，系统错误，导致用户无法更新订单，问题严重!");
        }
        // 如果是会员分销订单，清理缓存
        if (OrderTypeEnum.valueOf(orders.getOrderType()) == OrderTypeEnum.VIP_DISTRIBUTION) {
            stringRedisTemplate.delete(WxPayServiceAppInfoServiceImpl.REDIS_TEMP_OATH_KEY + orders.getCardNo());
        }
        //更新订单状态
        int count = ordersDao.update(orders);
        if (count <= 0) {
            throw new Exception("更新订单数据失败");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean proccessOrder(Map<Object, Object> reqMap) throws Exception {
        //查询订单
        Orders orders = ordersDao.get(new Orders().setOrderNo(new BigDecimal((String) reqMap.get("out_trade_no"))));
        if (orders == null) {
            throw new Exception("不存在的订单，请检查本次请求来源!");
        }
        //查询产品信息
        Product product = productDao.get(new Product().setProductId(orders.getProductId()));
        if (product == null) {
            throw new Exception("产品不存在，不能生成订单!");
        }
        //更新订单状态
        updateSuccessOrderInfo(orders, reqMap);

        //查询购买用户信息
        UserInfo buyerUser = userInfoDao.get(new UserInfo().setUserId(orders.getBuyerUserId()));
        //按订单类型分别处理
        if (orders.getOrderType() == OrderTypeEnum.PLATFORM_STRAIGHT.getCode()) {
            //平台直销
            processPlatformStraightOrder(orders, buyerUser, product);
        } else if (orders.getOrderType() == OrderTypeEnum.VIP_DISTRIBUTION.getCode()) {
            //会员分销
            processVipDistributionOrder(orders, buyerUser, product);
        }

        //返回
        return true;
    }

    /**
     * 处理会员分销订单
     *
     * @param orders
     * @param buyerUser
     * @param product
     * @throws Exception
     */
    private void processVipDistributionOrder(Orders orders, UserInfo buyerUser, Product product) throws Exception {
        logInfo("分销订单:" + JsonUtils.toJSON(orders) + "::buyerUser::" + JsonUtils.toJSON(buyerUser));
        //查询购买的健康卡
        UserHealthCard userHealthCard = userHealthCardDao.get(new UserHealthCard().setCardNo(orders.getCardNo()));
        if (userHealthCard == null) {
            throw new Exception("支付时，用户健康卡不存在，错误的地方，严重!");
        } else {
            userHealthCard.setUpdated(new Date());
            userHealthCard.setStatus(CardStatusTypeEnum.ATIVE.getCode());
            userHealthCard.setActivateUserId(orders.getBuyerUserId());
            userHealthCard.setCardActivateTime(new Date());
            userHealthCard.setOrderNo(orders.getOrderNo());
            userHealthCard.setCardDeadline(computationalValidity(product));
            userHealthCard.setSendCount(0);
            userHealthCardDao.update(userHealthCard);

            //更新激活用户表信息
            buyerUser.setCardActivateMode(2);
            buyerUser.setCardNo(userHealthCard.getCardNo());
            buyerUser.setCardActivateTime(userHealthCard.getCardActivateTime());
            buyerUser.setCardLifeTime(userHealthCard.getCardDeadline());
            buyerUser.setUpdated(new Date());
            buyerUser.setStatus(1);

            if (buyerUser.getUserLevel().intValue() == UserLevelEnum.TOURIST.getCode()) {
                buyerUser.setUserLevel(UserLevelEnum.MEMBER.getCode());
                logInfo("用户ID [" + buyerUser.getUserId() + "] 开始升级为会员 ");
            }
            userInfoDao.update(buyerUser);
            logInfo("用户购买卡自己激活 [" + JsonUtils.toJSON(userHealthCard) + "]");
        }
        // 查询关系是否存在，如果不存在则创建关系
        if (userRelationDao.queryCount(new UserRelation().setUserId(orders.getBuyerUserId())) <= 0) {
            UserRelation userRelation = new UserRelation();
            userRelation.setUserId(orders.getBuyerUserId());
            userRelation.setRefereeUserId(orders.getSellerUserId());
            userRelation.setCreated(new Date());
            userRelationDao.insert(userRelation);
        }

        // 插入账户明细表
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAmount(orders.getOrderPrice());
        accountDetail.setServiceNo(orders.getOrderNo());
        accountDetail.setCreated(new Date());
        accountDetail.setUserId(orders.getBuyerUserId());
        accountDetail.setBusinesseType(AccountDetailTypeEnum.USER_BUY.getCode());
        accountDetail.setOperationDirection(1);
        int count = accountDetailDao.insert(accountDetail);
        if (count <= 0) {
            throw new Exception("写入账户明细数据失败");
        }

        //写账户余额
        AccountBalance accountBalance = accountBalanceDao.get(new AccountBalance().setUserId(orders.getSellerUserId()));
        if (accountBalance == null) {
            accountBalance = new AccountBalance();
            accountBalance.setBalanceAmount(orders.getOrderPrice());
            accountBalance.setUserId(orders.getSellerUserId());
            accountBalance.setCreated(new Date());
            accountBalance.setUpdated(new Date());
            accountBalance.setStatus(0);
            //插入
            accountBalanceDao.insert(accountBalance);
        } else {
            //更新
            accountBalance.setBalanceAmount(accountBalance.getBalanceAmount().add(orders.getOrderPrice()));
            accountBalance.setUpdated(new Date());
            accountBalanceDao.update(accountBalance);
        }
    }

    /**
     * 处理平台直销订单
     *
     * @param orders
     * @param buyerUser
     * @param product
     * @throws Exception
     */
    private void processPlatformStraightOrder(Orders orders, UserInfo buyerUser, Product product) throws Exception {
        boolean isActiveHeartCard = false;
        List<UserHealthCard> userHealthCards = userHealthCardDao.queryForActiveUser(new UserHealthCard().setActivateUserId(buyerUser.getUserId()));
        // 插入用户卡
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
        //如果用户是游客或或是未激活健康卡的用户 则直接激活
        if (userHealthCards == null || userHealthCards.isEmpty() || buyerUser.getUserLevel() == UserLevelEnum.TOURIST.getCode()) {
            //设置激活用户ID
            userHealthCard.setActivateUserId(buyerUser.getUserId());
            //设置激活时间
            userHealthCard.setCardActivateTime(new Date());
            //设置有效期限
            userHealthCard.setCardDeadline(computationalValidity(product));
            //已激活
            userHealthCard.setStatus(CardStatusTypeEnum.ATIVE.getCode());
            //是否激活卡
            isActiveHeartCard = true;
        } else {
            //未激活状态
            userHealthCard.setStatus(CardStatusTypeEnum.NOT_ATIVE.getCode());
        }
        userHealthCardDao.insert(userHealthCard);

        //更新用户信息
        if (isActiveHeartCard) {
            buyerUser.setCardActivateMode(2);
            buyerUser.setCardNo(userHealthCard.getCardNo());
            buyerUser.setCardActivateTime(userHealthCard.getCardActivateTime());
            buyerUser.setCardLifeTime(userHealthCard.getCardDeadline());
            buyerUser.setUpdated(new Date());
            buyerUser.setStatus(1);

            if (buyerUser.getUserLevel().intValue() == UserLevelEnum.TOURIST.getCode()) {
                buyerUser.setUserLevel(UserLevelEnum.MEMBER.getCode());
                logInfo("用户ID [" + buyerUser.getUserId() + "] 开始升级为会员 ");
            }
            userInfoDao.update(buyerUser);
            logInfo("用户购买卡自己激活 [" + JsonUtils.toJSON(userHealthCard) + "]");
        }

        // 发放补贴
        subsidyManagerFactory.execute(orders);

        //记录平台佣金明细
        commissionDetailDao.insert(new CommissionDetail()
        .setCommissionAmount(CommissionDetailTypeEnum.SALE_HEART_CARD.getAmount())
                .setCardNo(userHealthCard.getCardNo())
                .setOrdreNo(userHealthCard.getCardNo())
                .setCommissionType(CommissionDetailTypeEnum.SALE_HEART_CARD.getCode())
                .setCreated(new Date())
                .setStatus(0)
        )
        ;
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
