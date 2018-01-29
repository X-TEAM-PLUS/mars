package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserInfoRspVO;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserRelationRspVO;

@Component
public class UserRelationConvertService implements ConvertService<UserRelation, UserRelationRspVO> {
    @Override
    public UserRelationRspVO toVO(UserRelation userRelation) {
        UserRelationRspVO userRelationRspVO = new UserRelationRspVO();
        BeanUtils.copyProperties(userRelation, userRelationRspVO);

        UserInfoRspVO userInfoRspVO = new UserInfoRspVO();
        BeanUtils.copyProperties(userRelation.getUserInfo(), userInfoRspVO);
        userRelationRspVO.setUserInfoRspVO(userInfoRspVO);
        return userRelationRspVO;
    }

    @Override
    public UserRelation toPO(UserRelationRspVO userRelationRspVO) {
        return null;
    }
}
