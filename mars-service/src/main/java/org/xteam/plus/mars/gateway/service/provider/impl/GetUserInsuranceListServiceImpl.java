package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.UserInsurance;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.convert.UserInsuranceConvertService;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.UserInfoReqVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserInsuranceRspVO;
import org.xteam.plus.mars.manager.UserInsuranceManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取用户健康卡接口
 */
@Component
public class GetUserInsuranceListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "com.mars.gateway.user.insurance.getUserInsuranceList";

    @Resource
    private UserInsuranceManager userInsuranceManager;

    @Resource
    private UserInsuranceConvertService userInsuranceConvertService;

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
            UserInfoReqVO userInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), UserInfoReqVO.class);
            if (userInfoReqVO.getUserId() == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
                return httpResponseBody;
            }
            List<UserInsuranceRspVO> userInsuranceRspVOList = Lists.newArrayList();
            UserInsurance queryWhere = new UserInsurance().setUserId(userInfoReqVO.getUserId());
            queryWhere.setStart(userInfoReqVO.getStart());
            queryWhere.setLimit(userInfoReqVO.getLimit());
            List<UserInsurance> userInsurances = userInsuranceManager.queryForProduct(queryWhere);
            for (UserInsurance userInsurance : userInsurances) {
                userInsuranceRspVOList.add(userInsuranceConvertService.toVO(userInsurance));
            }
            httpResponseBody.setBizContent(JsonUtils.toJSON(userInsuranceRspVOList));
        } catch (Exception e) {
            logInfo(METHOD_NAME + " error system_error ", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        } finally {
            logInfo(METHOD_NAME + " finish result[" + JsonUtils.toJSON(httpResponseBody.getBizContent()) + "] longtime[" + (beginDate - System.currentTimeMillis()) + "]");
        }
        return httpResponseBody;
    }
}
