package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.domain.GlobalConf;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserCardBindReqVO;
import org.xteam.plus.mars.manager.BankCardManager;
import org.xteam.plus.mars.manager.GlobalConfManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.GlobalConfTypeEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户绑定银行卡
 */
@Component
public class BindBankServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.bindbank";

    @Resource
    private BankCardManager bankCardManager;

    @Resource
    private GlobalConfManager globalConfManager;
    @Resource
    private UserInfoManager userInfoManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        HttpResponseBody httpResponseBody = new HttpResponseBody(GlobalErrorMessage.SUCCESS);
        UserCardBindReqVO userCardBindReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserCardBindReqVO.class);
        if (httpRequestBody.getUserId() == null
                || StringUtils.isEmpty(userCardBindReqVO.getCardUserName())
                || userCardBindReqVO.getCardNo() == null
                || userCardBindReqVO.getBankId() == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        //用户ID
        BigDecimal userId = new BigDecimal(httpRequestBody.getUserId());
        //校验实名是否与当前用户的实名一致
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(userId));
        if (userInfo == null || !userCardBindReqVO.getCardUserName().equalsIgnoreCase(userInfo.getRealName())) {
            return new HttpResponseBody(GlobalErrorMessage.DIFFERENT_REAL_NAME);
        }
        List<BankCard> list = bankCardManager.query(new BankCard().setUserId(userId));
        GlobalConf queryWhereGlobal = new GlobalConf().setParameterType(new BigDecimal(GlobalConfTypeEnum.WX_BANK_LIST.getCode()));
        queryWhereGlobal.setParameterValue(userCardBindReqVO.getBankId().toString());
        List globalList = globalConfManager.query(queryWhereGlobal);
        // 校验银行编码合法性
        if (globalList == null || globalList.isEmpty()) {
            return new HttpResponseBody(GlobalErrorMessage.BANK_ID_ERROR);
        }
        //是否已绑卡
        if (list != null && list.size() > 0) {
            return new HttpResponseBody(GlobalErrorMessage.BANK_CARD_IS_BINDED);
        } else {
            BankCard bankCard =  new BankCard().setCreated(new Date())
                    .setBankAccountNo(userCardBindReqVO.getCardNo().toString())
                    .setBankAccountName(userCardBindReqVO.getCardUserName())
                    .setBankId(userCardBindReqVO.getBankId())
                    .setUserId(userId);
            int count = bankCardManager.saveOrUpdate(bankCard);
            if (count <= 0) {
                return new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
            }
        }
        return httpResponseBody;
    }

}
