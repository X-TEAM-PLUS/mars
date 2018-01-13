package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.CityInfoDao;
import org.xteam.plus.mars.domain.CityInfo;
import org.xteam.plus.mars.manager.CityInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_CITY_INFO表Manager接口实现类
 */

@Service
public class CityInfoManagerImpl implements CityInfoManager {
    private static final Log log = LogFactory.getLog(CityInfoManagerImpl.class);
    @javax.annotation.Resource
    private CityInfoDao cityInfoDao;


    @Override
    public CityInfo get(CityInfo cityInfo) throws Exception {
        return cityInfoDao.get(cityInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CityInfo cityInfo) throws Exception {
        return cityInfoDao.insert(cityInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<CityInfo> list) throws Exception {
        return cityInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(CityInfo cityInfo) throws Exception {
        return cityInfoDao.delete(cityInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CityInfo cityInfo) throws Exception {
        return cityInfoDao.update(cityInfo);
    }

    @Override
    public List<CityInfo> query(CityInfo cityInfo) throws Exception {
        return cityInfoDao.query(cityInfo);
    }

    @Override
    public Integer queryCount(CityInfo cityInfo) throws Exception {
        return cityInfoDao.queryCount(cityInfo);
    }

}
