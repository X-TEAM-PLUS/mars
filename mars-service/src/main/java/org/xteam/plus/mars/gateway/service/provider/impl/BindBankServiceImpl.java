package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserCardBindReqVO;
import org.xteam.plus.mars.manager.BankCardManager;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户绑定银行卡
 */
@Component
public class BindBankServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.bindBank";

    @Resource
    private BankCardManager bankCardManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

        long beginDate = System.currentTimeMillis();
        this.logInfo(METHOD_NAME + " request  [" + httpRequestBody.toString() + "]");
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        try {
            UserCardBindReqVO userCardBindReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserCardBindReqVO.class);
            if (userCardBindReqVO.getUserId() == null
                    || StringUtils.isEmpty(userCardBindReqVO.getCardUserName())
                    || userCardBindReqVO.getCardNo() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            List<BankCard> list = bankCardManager.query(new BankCard().setBankCardId(userCardBindReqVO.getCardNo()));
            if (!list.isEmpty()) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.CARD_ALREAD_BIND);
                return httpResponseBody;
            }
            BankCard bankCard = new BankCard();
            bankCard.setBankAccountNo(userCardBindReqVO.getCardNo().toString());
            bankCard.setBankAccountName(userCardBindReqVO.getCardUserName());
            bankCard.setCreated(new Date());
            bankCard.setUserId(userCardBindReqVO.getUserId());
            int count = bankCardManager.insert(bankCard);
            if (count <= 0) {
                logInfo("用户绑定银行卡失败,插入数据为0");
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
                return httpResponseBody;
            }
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
