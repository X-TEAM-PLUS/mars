package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.AccountBalanceDao;
import org.xteam.plus.mars.dao.AccountDetailDao;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class SubsidyAbstractManager extends Logging implements SubsidyManager{

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRelationDao userRelationDao;

    @Resource
    private AccountDetailDao accountDetailDao;

    @Resource
    private AccountBalanceDao accountBalanceDao;

    /**
     * 获取当前用户等级
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    public UserLevelEnum getUserLevel(BigDecimal userId) throws Exception {
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        if (userInfo == null) {
            throw new Exception("用户不存在,[" + userId + "]!");
        }
        return UserLevelEnum.valueOf(userInfo.getUserLevel());
    }

    /**
     * 获取用户上一级用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public UserInfo getUpperLevel(BigDecimal userId) throws Exception {
        List<UserRelation> userRelationList = userRelationDao.queryForUser(new UserRelation().setUserId(userId));
        if (userRelationList != null) {
            if (userRelationList.size() == 0) {
                return null;
            }
            if (userRelationList.size() > 1) {
                throw new Exception("错误的关系，用户存在两个上级[" + userId + "]");
            }
            return userInfoDao.get(new UserInfo().setUserId(userRelationList.get(0).getRefereeUserId()));
        }
        return null;
    }

    /**
     * 发放补贴
     *
     * @param accountDetailTypeEnum 补贴类型与金额
     * @param userId                补贴用户ID
     * @param orderId               订单ID
     * @return
     */
    public boolean grantSubsidy(AccountDetailTypeEnum accountDetailTypeEnum, BigDecimal userId, BigDecimal orderId) throws Exception {
        List<AccountBalance> list = accountBalanceDao.query(new AccountBalance().setUserId(userId));
        AccountBalance accountBalance;
        boolean insert = false;
        // 初始化账户数据
        if (list == null || list.size() == 0) {
            accountBalance = new AccountBalance().setBalanceAmount(new BigDecimal(0));
            insert = true;
        }
        if (list.size() > 1) {
            throw new Exception("用户账户错误，用户不能存在多个账户!");
        }
        accountBalance = list.get(0);
        accountBalance.getBalanceAmount().add(new BigDecimal(accountDetailTypeEnum.getAmount()));
        if (insert) {
            accountBalance.setStatus(0);
            accountBalance.setCreated(new Date());
            accountBalanceDao.insert(accountBalance);
        } else {
            accountBalance.setUpdated(new Date());
            accountBalanceDao.update(accountBalance);
        }
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setOperationDirection(1);
        accountDetail.setBusinesseType(accountDetailTypeEnum.getCode());
        accountDetail.setAmount(new BigDecimal(accountDetailTypeEnum.getAmount()));
        accountDetail.setServiceNo(orderId);
        accountDetail.setCreated(new Date());
        int count = accountDetailDao.insert(accountDetail);
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean grantSubsidyList(List<AccountDetailTypeEnum> list, BigDecimal userId, BigDecimal orderId) throws Exception {
        boolean success = false;
        UserInfo upSuperInfo = getUpperLevel(userId);
        if (upSuperInfo == null) {
            logInfo("没有上级领导不需要发放管理补贴 用户ID[" + userId + "]");
            return true;
        }

        for (AccountDetailTypeEnum accountDetailTypeEnum : list) {
            logInfo("用户id[" + upSuperInfo.getUserId() + "] 当前级别[" + UserLevelEnum.valueOf(upSuperInfo.getUserLevel()).getInfo() + "] 发放["+accountDetailTypeEnum.getInfo()+"] 发放金额["+accountDetailTypeEnum.getAmount()+"] 开始发放");
            if (upSuperInfo.getUserLevel() != accountDetailTypeEnum.getUserLevelEnum().getCode()) {
                logInfo("用户id[" + upSuperInfo.getUserId() + "] 当前级别[" + UserLevelEnum.valueOf(upSuperInfo.getUserLevel()).getInfo() + "] 发放["+accountDetailTypeEnum.getInfo()+"] 权限不足，不能进行发放，跳过本次方法");
                continue;
            }
            // 获取上级领导用户
            if (upSuperInfo != null) {
                logInfo("开始发放补贴 补贴类型[" + accountDetailTypeEnum.getInfo() + "] 用户等级[" + UserLevelEnum.valueOf(upSuperInfo.getUserLevel()).getInfo() + "] 用户ID[" + upSuperInfo.getUserId() + "]");
                success = grantSubsidy(accountDetailTypeEnum, upSuperInfo.getUserId(), orderId);
                if (!success) {
                    throw new Exception("发放补贴 用户等级[" + UserLevelEnum.valueOf(upSuperInfo.getUserLevel()).getInfo() + "] 补贴失败,失败原因返回false");
                }
                upSuperInfo = getUpperLevel(upSuperInfo.getUserId());
            }
        }
        return success;
    }

}
