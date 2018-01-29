package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserInfoRspVO;

@Component
public class UserInfoConvertService implements ConvertService<UserInfo,UserInfoRspVO>{

    public UserInfoConvertService() {
    }

    @Override
    public UserInfoRspVO toVO(UserInfo userInfo) {
        UserInfoRspVO userInfoRspVO = new UserInfoRspVO();
        BeanUtils.copyProperties(userInfo, userInfoRspVO);
        return userInfoRspVO;
    }

    @Override
    public UserInfo toPO(UserInfoRspVO userInfoRspVO) {
        return null;
    }
}
