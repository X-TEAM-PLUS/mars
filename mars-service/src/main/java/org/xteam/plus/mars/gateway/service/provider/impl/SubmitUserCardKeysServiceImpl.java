package org.xteam.plus.mars.gateway.service.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.fdfs.DfsClient;
import org.xteam.plus.mars.fdfs.impl.DfsClientImpl;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.KeysUserBuyReqVO;
import org.xteam.plus.mars.manager.OrdersManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 * 提交卡密升级
 */
@Component
public class SubmitUserCardKeysServiceImpl extends Logging implements GateWayService {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("heartcheck");

    private final String METHOD_NAME = "com.zaoangongcheng.gateway.user.submitUserCardKeys";

    private DfsClient dfsClient = new DfsClientImpl();

    @Resource
    private OrdersManager ordersManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        //获取业务参数
        KeysUserBuyReqVO parmas = JsonUtils.fromJSON(httpRequestBody.getBizContent(), KeysUserBuyReqVO.class);
        //校验请求参数
        if (!checkRequest(parmas)) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        try {
            //业务处理
            ordersManager.cardKeysOrder(
                    parmas.getCardKeys(),
                    new BigDecimal(httpRequestBody.getUserId()),
                    parmas.getAddress(),
                    parmas.getCertificateOf(),
                    parmas.getUserRealName(),
                    parmas.getArea());
        } catch (Exception e) {
            return new HttpResponseBody("999999", e.getMessage());
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS);
    }

    private boolean checkRequest(KeysUserBuyReqVO keysUserBuyReqVO) throws Exception {
        if (keysUserBuyReqVO == null) {
            return false;
        }
        if (StringUtils.isEmpty(keysUserBuyReqVO.getAddress())) {
            return false;
        }
        if (StringUtils.isEmpty(keysUserBuyReqVO.getArea())) {
            return false;
        }
        if (StringUtils.isEmpty(keysUserBuyReqVO.getUserRealName())) {
            return false;
        }
        if (StringUtils.isEmpty(keysUserBuyReqVO.getCertificateOf())) {
            return false;
        }
        return true;
    }
}
