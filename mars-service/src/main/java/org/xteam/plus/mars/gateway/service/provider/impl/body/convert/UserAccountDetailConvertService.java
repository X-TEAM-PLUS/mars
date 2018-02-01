package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserAccountDetailRspVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserInfoRspVO;

@Component
public class UserAccountDetailConvertService implements ConvertService<AccountDetail,UserAccountDetailRspVO>{

    @Override
    public UserAccountDetailRspVO toVO(AccountDetail accountDetail) {
        UserAccountDetailRspVO userAccountDetailRspVO = new UserAccountDetailRspVO();
        BeanUtils.copyProperties(accountDetail, userAccountDetailRspVO);
        return userAccountDetailRspVO;
    }

    @Override
    public AccountDetail toPO(UserAccountDetailRspVO userAccountDetailRspVO) {
        return null;
    }
}
