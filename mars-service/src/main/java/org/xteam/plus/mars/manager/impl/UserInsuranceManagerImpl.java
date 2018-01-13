package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserInsuranceDao;
import org.xteam.plus.mars.domain.UserInsurance;
import org.xteam.plus.mars.manager.UserInsuranceManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_INSURANCE表Manager接口实现类
 */

@Service
public class UserInsuranceManagerImpl implements UserInsuranceManager {
    private static final Log log = LogFactory.getLog(UserInsuranceManagerImpl.class);
    @javax.annotation.Resource
    private UserInsuranceDao userInsuranceDao;


    @Override
    public UserInsurance get(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.get(userInsurance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.insert(userInsurance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserInsurance> list) throws Exception {
        return userInsuranceDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.delete(userInsurance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.update(userInsurance);
    }

    @Override
    public List<UserInsurance> query(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.query(userInsurance);
    }

    @Override
    public Integer queryCount(UserInsurance userInsurance) throws Exception {
        return userInsuranceDao.queryCount(userInsurance);
    }

}
