package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.InsuranceCompanyDao;
import org.xteam.plus.mars.domain.InsuranceCompany;
import org.xteam.plus.mars.manager.InsuranceCompanyManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_INSURANCE_COMPANY表Manager接口实现类
 */

@Service
public class InsuranceCompanyManagerImpl implements InsuranceCompanyManager {
    private static final Log log = LogFactory.getLog(InsuranceCompanyManagerImpl.class);
    @javax.annotation.Resource
    private InsuranceCompanyDao insuranceCompanyDao;


    @Override
    public InsuranceCompany get(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.get(insuranceCompany);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.insert(insuranceCompany);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<InsuranceCompany> list) throws Exception {
        return insuranceCompanyDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.delete(insuranceCompany);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.update(insuranceCompany);
    }

    @Override
    public List<InsuranceCompany> query(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.query(insuranceCompany);
    }

    @Override
    public Integer queryCount(InsuranceCompany insuranceCompany) throws Exception {
        return insuranceCompanyDao.queryCount(insuranceCompany);
    }

}
