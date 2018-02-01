package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.domain.UserInsurance;
import org.xteam.plus.mars.gateway.service.provider.impl.body.rsp.UserInsuranceRspVO;

@Component
public class UserInsuranceConvertService implements ConvertService<UserInsurance,UserInsuranceRspVO>{
    @Override
    public UserInsuranceRspVO toVO(UserInsurance userInsurance) {
        UserInsuranceRspVO userInsuranceRspVO = new UserInsuranceRspVO();
        BeanUtils.copyProperties(userInsurance, userInsuranceRspVO);
        userInsuranceRspVO.setInsuranceProduct(userInsurance.getInsuranceProduct());
        userInsuranceRspVO.setInsuranceCompany(userInsurance.getInsuranceCompany());
        return userInsuranceRspVO;
    }

    @Override
    public UserInsurance toPO(UserInsuranceRspVO userInsuranceRspVO) {
        return null;
    }
}
