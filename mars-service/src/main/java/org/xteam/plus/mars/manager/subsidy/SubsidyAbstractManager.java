package org.xteam.plus.mars.manager.subsidy;

import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.AccountBalanceDao;
import org.xteam.plus.mars.dao.AccountDetailDao;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

public abstract class SubsidyAbstractManager extends Logging implements SubsidyManager {
    @Resource
    protected AccountDetailDao accountDetailDao;

    @Resource
    protected AccountBalanceDao accountBalanceDao;


    /**
     * 发放补贴
     *
     * @param accountDetailTypeEnum 补贴类型与金额
     * @param userInfo               补贴用户
     * @param orderNo               订单ID
     * @return
     */
    protected void grantSubsidy(AccountDetailTypeEnum accountDetailTypeEnum, UserInfo userInfo, BigDecimal orderNo) throws Exception {
        logInfo("发放补贴::账户id[" + userInfo + "]::用户级别["+ UserLevelEnum.valueOf(userInfo.getUserLevel()).getInfo()+"]::补贴类型[" + accountDetailTypeEnum.getInfo() + "]::补贴金额[" + accountDetailTypeEnum.getAmount() + "]::订单号[" + orderNo + "]");
        AccountBalance accountBalance = accountBalanceDao.get(new AccountBalance().setUserId(userInfo.getUserId()));
        // 初始化账户数据
        if (accountBalance == null) {
            accountBalance = new AccountBalance()
                    .setUserId(userInfo.getUserId())
                    .setBalanceAmount(new BigDecimal(accountDetailTypeEnum.getAmount()))
                    .setCreated(new Date())
                    .setUpdated(new Date())
            ;
            accountBalanceDao.insert(accountBalance);
        } else {
            //更新余额
            accountBalance.setBalanceAmount(accountBalance.getBalanceAmount()
                    .add(new BigDecimal(accountDetailTypeEnum.getAmount())))
                    .setUpdated(new Date())
            ;
            accountBalanceDao.update(accountBalance);
        }
        //写账户明细
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setUserId(userInfo.getUserId());
        accountDetail.setOperationDirection(1);
        accountDetail.setBusinesseType(accountDetailTypeEnum.getCode());
        accountDetail.setAmount(new BigDecimal(accountDetailTypeEnum.getAmount()));
        accountDetail.setServiceNo(orderNo);
        accountDetail.setCreated(new Date());
        accountDetailDao.insert(accountDetail);
    }

}
