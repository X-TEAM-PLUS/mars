package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.*;
import org.xteam.plus.mars.domain.*;
import org.xteam.plus.mars.manager.WithdrawRecordManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;
import org.xteam.plus.mars.type.WithDrawStatusName;
import org.xteam.plus.mars.wx.api.IService;
import org.xteam.plus.mars.wx.api.WxService;
import org.xteam.plus.mars.wx.bean.result.PayPocketMoneyResult;
import org.xteam.plus.mars.wx.exception.WxErrorException;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_WITHDRAW_RECORD表Manager接口实现类
 */

@Service
public class WithdrawRecordManagerImpl extends Logging implements WithdrawRecordManager {
    private static final Log log = LogFactory.getLog(WithdrawRecordManagerImpl.class);

    IService wxService = new WxService();

    @Resource
    private AccountDetailDao accountDetailDao;

    @Resource
    private AccountBalanceDao accountBalanceDao;

    @Resource
    public UserInfoDao userInfoDao;

    @javax.annotation.Resource
    private WithdrawRecordDao withdrawRecordDao;

    @Resource
    private MessageDao messageDao;

    @Override
    public WithdrawRecord get(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.get(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.insert(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<WithdrawRecord> list) throws Exception {
        return withdrawRecordDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.delete(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.update(withdrawRecord);
    }

    @Override
    public List<WithdrawRecord> query(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.query(withdrawRecord);
    }

    @Override
    public Integer queryCount(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.queryCount(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int agreeApply(WithdrawRecord withdrawrecord) throws Exception {
        withdrawrecord = withdrawRecordDao.get(withdrawrecord);
        if (withdrawrecord == null) {
            throw new Exception("系统异常,找不到该笔申请!");
        }
        withdrawrecord.setUpdated(new Date());
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(withdrawrecord.getUserId()));
        if (userInfo == null || StringUtils.isEmpty(userInfo.getWxOpenid())) {
            withdrawrecord.setStatus(WithDrawStatusName.PayError.getCode());
            logInfo("后台进行微信支付零钱打款失败 不存在的用户ID [" + JsonUtils.toJSON(userInfo) + "]");
            throw new Exception("不存在的用户进行申请!");
        } else {
            try {
                //查询账户余额
                AccountBalance accountBalance = accountBalanceDao.get(new AccountBalance().setUserId(withdrawrecord.getUserId()));
                //查余额
                if (accountBalance != null &&
                        withdrawrecord.getAmount().compareTo(accountBalance.getBalanceAmount()) != 1) {

                    PayPocketMoneyResult payPocketMoneyResult = wxService.payForAnotherPocketMoney(userInfo.getWxOpenid(), "FORCE_CHECK", withdrawrecord.getBankAccountName(), String.valueOf(withdrawrecord.getAmount().intValue()), "早安工程发放补贴");
                    logInfo("代付返回结果:" + JsonUtils.toJSON(payPocketMoneyResult));
                    if (payPocketMoneyResult.getResultCode().equals("SUCCESS")) {
                        withdrawrecord.setTransactionNo(payPocketMoneyResult.getPaymentNo());
                        withdrawrecord.setPayTime(new Date());
                        withdrawrecord.setUpdated(new Date());
                        withdrawrecord.setStatus(WithDrawStatusName.Pay.getCode());

                        //更新余额
                        accountBalance.setBalanceAmount(accountBalance.getBalanceAmount().subtract(withdrawrecord.getAmount()));
                        accountBalance.setUpdated(new Date());
                        accountBalanceDao.update(accountBalance);

                        // 插入账户明细表
                        AccountDetail accountDetail = new AccountDetail();
                        accountDetail.setAmount(withdrawrecord.getAmount());
                        accountDetail.setServiceNo(withdrawrecord.getId());
                        accountDetail.setCreated(new Date());
                        accountDetail.setUserId(withdrawrecord.getUserId());
                        accountDetail.setBusinesseType(AccountDetailTypeEnum.WITHDRAWALS.getCode());
                        accountDetail.setOperationDirection(2);
                        accountDetailDao.insert(accountDetail);

                        //发送消息
                        Message message =  new Message()
                                .setUserId(withdrawrecord.getUserId())
                                .setCreated(new Date())
                                .setStatus(0)
                                .setMessageTitle("提现成功。")
                                .setMessageContent("已提现到您的微信钱包,提现金额是:"+withdrawrecord.getAmount())
                                .setUpdated(new Date())
                        ;
                        messageDao.insert(message);

                        return withdrawRecordDao.update(withdrawrecord);
                    } else {
                        withdrawrecord.setErrorInfo(payPocketMoneyResult.getErrCodeDes());
                        withdrawrecord.setStatus(WithDrawStatusName.PayError.getCode());
                        return withdrawRecordDao.update(withdrawrecord);
                    }
                } else {
                    throw new Exception("账户余额不足，打款失败!");
                }
            } catch (WxErrorException wxError) {
                withdrawrecord.setStatus(WithDrawStatusName.PayError.getCode());
                logError("代付调用微信企业到零钱接口失败 失败原因 [" + wxError.getMessage() + "]", wxError);
                return withdrawRecordDao.update(withdrawrecord);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int refuseApply(WithdrawRecord withdrawrecord) throws Exception {
        withdrawrecord.setUpdated(new Date());
        return withdrawRecordDao.refuseApply(withdrawrecord);
    }

}
