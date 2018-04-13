package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.*;
import org.xteam.plus.mars.domain.*;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.manager.MessageManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.CardStatusTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_APPLY_INFO表Manager接口实现类
 */

@Service
public class ApplyInfoManagerImpl extends Logging implements ApplyInfoManager {
    private static final Log log = LogFactory.getLog(ApplyInfoManagerImpl.class);

    @Resource
    private UserInfoDao userInfoDao;

    @javax.annotation.Resource
    private ApplyInfoDao applyInfoDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private UserHealthCardDao userHealthCardDao;

    @Resource
    private AccountDetailDao accountDetailDao;

    @Resource
    private MessageManager messageManager;

    @Override
    public ApplyInfo get(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.get(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ApplyInfo applyInfo) throws Exception {
        if (applyInfo.getStatus() == 1) {
            logInfo("用户ID[" + applyInfo.getUserId() + "] 正在进行" + ApplayTypeEnum.valueOf(applyInfo.getApplyType()) + "升级申请，系统将自动审核通过，进行升级");
            UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(applyInfo.getUserId()));
            if (applyInfo.getApplyType() == ApplayTypeEnum.SOCIAL.getCode()) {
                userInfo.setUserLevel(UserLevelEnum.SOCIAL.getCode());
                userInfo.setRealName(applyInfo.getRealName());
                userInfo.setIdNumber(applyInfo.getIdNumber());
            }
            if (applyInfo.getApplyType() == ApplayTypeEnum.STANDING_DIRECTOR.getCode()) {
                userInfo.setUserLevel(UserLevelEnum.STANDING_DIRECTOR.getCode());
            }
            if (applyInfo.getApplyType() == ApplayTypeEnum.DIRECTOR.getCode()) {
                userInfo.setUserLevel(UserLevelEnum.DIRECTOR.getCode());
            }
            userInfoDao.update(userInfo);
        }
        return applyInfoDao.insert(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<ApplyInfo> list) throws Exception {
        return applyInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.delete(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.update(applyInfo);
    }

    @Override
    public List<ApplyInfo> query(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.query(applyInfo);
    }

    @Override
    public Integer queryCount(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.queryCount(applyInfo);
    }

    @Override
    public List<ApplyInfo> queryForUserInfo(ApplyInfo applyInfo) throws Exception {
        List<ApplyInfo> applyInfos = applyInfoDao.queryForUserInfo(applyInfo);
        // 转换用户姓名
        for (ApplyInfo applyInfoTemp : applyInfos) {
            if (applyInfoTemp.getUserInfo() != null && !StringUtils.isEmpty(applyInfoTemp.getUserInfo().getRealName())) {
                applyInfoTemp.getUserInfo().setRealName(URLDecoder.decode(applyInfoTemp.getUserInfo().getRealName(), "utf-8"));
            }
        }
        return applyInfos;
    }

    @Override
    public Integer queryForUserInfoCount(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.queryForUserInfoCount(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditpass(BigDecimal applyId) throws Exception {
        ApplyInfo applyInfo = applyInfoDao.get(new ApplyInfo().setApplyId(applyId));
        if (applyInfo == null) {
            throw new Exception("不存在的对象");
        }
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(applyInfo.getUserId()));
        if (userInfo == null) {
            throw new Exception("不存在的用户");
        }
        ApplayTypeEnum applayTypeEnum = ApplayTypeEnum.valueOf(applyInfo.getApplyType());
        if (applayTypeEnum.getCode() == ApplayTypeEnum.DIRECTOR.getCode()) {
            userInfo.setUserLevel(UserLevelEnum.DIRECTOR.getCode());
            userInfoDao.update(userInfo);
        }
        if (applayTypeEnum.getCode() == ApplayTypeEnum.STANDING_DIRECTOR.getCode()) {
            userInfo.setUserLevel(UserLevelEnum.STANDING_DIRECTOR.getCode());
            userInfoDao.update(userInfo);
        }
        //发送消息
        messageManager.sendMessage(applyInfo.getUserId(),
                ApplayTypeEnum.valueOf(applyInfo.getApplyType()).getInfo() + "申请审批通过",
                "恭喜您，你已成功升级为早安工程的" + ApplayTypeEnum.valueOf(applyInfo.getApplyType()).getInfo() + "。");

        applyInfo.setStatus(1);
        applyInfo.setUpdated(new Date());
        int count = applyInfoDao.update(applyInfo);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean dismissal(BigDecimal applyId) throws Exception {
        ApplyInfo applyInfo = applyInfoDao.get(new ApplyInfo().setApplyId(applyId));
        if (applyInfo == null) {
            throw new Exception("不存在的对象");
        }
        applyInfo.setStatus(-1);
        applyInfo.setUpdated(new Date());
        int count = applyInfoDao.update(applyInfo);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean greenChannel(BigDecimal applyId, int cardNum) throws Exception {
        ApplyInfo applyInfo = applyInfoDao.get(new ApplyInfo().setApplyId(applyId));

        if (applyInfo == null) {
            throw new Exception("不存在的对象");
        }
        if (applyInfo.getStatus() != 0) {
            throw new Exception("申请状态不是未审核状态不能进行绿色通道升级");
        }
        List<Product> products = productDao.query(new Product());
        Product product = products.get(0);
        for (int i = 0; i < cardNum; i++) {
            createUserHealthCard(applyInfo, product);
        }
        //发送消息
        messageManager.sendMessage(applyInfo.getUserId(),
                ApplayTypeEnum.valueOf(applyInfo.getApplyType()).getInfo() + "申请审批通过",
                "恭喜您，你已成功升级为早安工程的" + ApplayTypeEnum.valueOf(applyInfo.getApplyType()).getInfo() + "。");
        applyInfo.setStatus(1);
        int count = applyInfoDao.update(applyInfo);
        if (count > 0) {
            return true;
        }
        return false;
    }

    private void createUserHealthCard(ApplyInfo applyInfo, Product product) throws Exception {
        UserHealthCard userHealthCard = new UserHealthCard();
        userHealthCard.setCreated(new Date());
        userHealthCard.setProductId(product.getProductId());
        userHealthCard.setProductType(product.getCardType());
        userHealthCard.setSurvivalPeriodMode(product.getSurvivalPeriodMode());
        userHealthCard.setSurvivalPeriodNum(product.getSurvivalPeriodNum());
        userHealthCard.setSendPeriodMode(product.getSendPeriodMode());
        userHealthCard.setSendPeriod(product.getSendPeriod());
        userHealthCard.setSendTotalCount(product.getSendTotalCount());
        userHealthCard.setBuyerUserId(applyInfo.getUserId());
        userHealthCard.setSendCount(0);
        userHealthCard.setStatus(CardStatusTypeEnum.NOT_ATIVE.getCode());
        userHealthCard.setOrderNo(applyInfo.getApplyId());
        userHealthCardDao.insert(userHealthCard);
        // 插入账户明细表
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAmount(product.getAmount());
        accountDetail.setServiceNo(applyInfo.getApplyId());
        accountDetail.setCreated(new Date());
        accountDetail.setUserId(applyInfo.getUserId());
        accountDetail.setBusinesseType(AccountDetailTypeEnum.USER_BUY_GREEN.getCode());
        accountDetail.setOperationDirection(1);
        int count = accountDetailDao.insert(accountDetail);
        if (count <= 0) {
            throw new Exception("更新订单数据失败");
        }
    }

}
