package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserDetectionInfoRspVO;

@Component
public class UserDetectionInfoConvertService implements ConvertService<HealthCheckRecord, UserDetectionInfoRspVO> {

    @Override
    public UserDetectionInfoRspVO toVO(HealthCheckRecord healthCheckRecord) {
        UserDetectionInfoRspVO userDetectionInfoRspVO = new UserDetectionInfoRspVO();
        BeanUtils.copyProperties(healthCheckRecord, userDetectionInfoRspVO);
        return userDetectionInfoRspVO;
    }

    @Override
    public HealthCheckRecord toPO(UserDetectionInfoRspVO userDetectionInfoRspVO) {
        return null;
    }
}
