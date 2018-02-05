package org.xteam.plus.mars.gateway.service.provider.impl;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.gateway.service.provider.impl.body.req.PageInfoReqVO;
import org.xteam.plus.mars.manager.WithdrawRecordManager;
import org.xteam.plus.mars.wx.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取提现记录列表
 */
@Component
public class GetWithdrawDepositListServiceImpl extends Logging implements GateWayService {

    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.withdraw.record.list";

    @Resource
    private WithdrawRecordManager withdrawRecordManager;

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {

        PageInfoReqVO pageInfoReqVO = JsonUtils.fromJSON(httpRequestBody.getBizContent(), PageInfoReqVO.class);
        if (StringUtils.isEmpty(httpRequestBody.getUserId())) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        Map<String,Object> bizContentMap = Maps.newHashMap();
        bizContentMap.put("list",withdrawRecordManager.query(
                new WithdrawRecord().
                        setUserId(new BigDecimal(httpRequestBody.getUserId()))
                        .setStart(pageInfoReqVO!=null && pageInfoReqVO.getStart()!=null ? pageInfoReqVO.getStart():0)
                        .setLimit(pageInfoReqVO!=null && pageInfoReqVO.getLimit()!=null?pageInfoReqVO.getLimit():Integer.MAX_VALUE)
                )
        );
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }
}
