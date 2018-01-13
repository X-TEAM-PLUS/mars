package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.InsuranceProductDao;
import org.xteam.plus.mars.domain.InsuranceProduct;
import org.xteam.plus.mars.manager.InsuranceProductManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_INSURANCE_PRODUCT表Manager接口实现类
 */

@Service
public class InsuranceProductManagerImpl implements InsuranceProductManager {
    private static final Log log = LogFactory.getLog(InsuranceProductManagerImpl.class);
    @javax.annotation.Resource
    private InsuranceProductDao insuranceProductDao;


    @Override
    public InsuranceProduct get(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.get(insuranceProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.insert(insuranceProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<InsuranceProduct> list) throws Exception {
        return insuranceProductDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.delete(insuranceProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.update(insuranceProduct);
    }

    @Override
    public List<InsuranceProduct> query(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.query(insuranceProduct);
    }

    @Override
    public Integer queryCount(InsuranceProduct insuranceProduct) throws Exception {
        return insuranceProductDao.queryCount(insuranceProduct);
    }

}
