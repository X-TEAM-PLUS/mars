package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.ApplyRecordReqVO;
import org.xteam.plus.mars.manager.AccountBalanceManager;
import org.xteam.plus.mars.manager.BankCardManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.manager.WithdrawRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 申请提现接口
 */
@Component
public class WithdrawDepositApplyServiceImpl extends Logging implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.withdraw.apply";

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private BankCardManager bankCardManager;

    @Resource
    private AccountBalanceManager accountBalanceManager;

    @Resource
    private WithdrawRecordManager withdrawRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);

        ApplyRecordReqVO applyRecordReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), ApplyRecordReqVO.class);
        if (httpRequestBody.getUserId() == null   || applyRecordReqVO.getAmount() == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }

        if (applyRecordReqVO.getAmount().compareTo(new BigDecimal(100)) == -1) {
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(new BigDecimal(httpRequestBody.getUserId())));
        if (userInfo == null) {
            return new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
        }
        AccountBalance accountBalance = accountBalanceManager.get(new AccountBalance().setUserId(userInfo.getUserId()));
        if (accountBalance == null) {
            accountBalance = new AccountBalance();
            accountBalance.setUserId(userInfo.getUserId());
            accountBalance.setBalanceAmount(new BigDecimal(0));
        }
        if (accountBalance.getBalanceAmount().compareTo(applyRecordReqVO.getAmount()) == -1) {
            return  new HttpResponseBody(GlobalErrorMessage.AMOUNT_NOT_ENOUGH);
        }

        /**
         * 查询绑定的银行卡
         */
        List<BankCard> bankCardList = bankCardManager.query(new BankCard().setUserId(userInfo.getUserId()));
        if(bankCardList==null || bankCardList.size()==0){
            return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }
        BankCard  bankCard = bankCardList.get(0);

        WithdrawRecord withdrawRecord = new WithdrawRecord();
        withdrawRecord.setCreated(new Date());
        withdrawRecord.setUserId(userInfo.getUserId());
        withdrawRecord.setAmount(applyRecordReqVO.getAmount());
        withdrawRecord.setBankAccountName(bankCard.getBankAccountName());
        withdrawRecord.setBankAccountNo(bankCard.getBankAccountNo());
        withdrawRecord.setPayWay(1);
        withdrawRecord.setStatus(0);
        withdrawRecord.setApplyTime(new Date());

        int count = withdrawRecordManager.insert(withdrawRecord);
        if (count <= 0) {
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.USER_ID_NOT_HIVE);
            return httpResponseBody;
        }

        return httpResponseBody;
    }
}
