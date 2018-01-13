package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.CouncilInfoDao;
import org.xteam.plus.mars.domain.CouncilInfo;
import org.xteam.plus.mars.manager.CouncilInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_COUNCIL_INFO表Manager接口实现类
 */

@Service
public class CouncilInfoManagerImpl implements CouncilInfoManager {
    private static final Log log = LogFactory.getLog(CouncilInfoManagerImpl.class);
    @javax.annotation.Resource
    private CouncilInfoDao councilInfoDao;


    @Override
    public CouncilInfo get(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.get(councilInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.insert(councilInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<CouncilInfo> list) throws Exception {
        return councilInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.delete(councilInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.update(councilInfo);
    }

    @Override
    public List<CouncilInfo> query(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.query(councilInfo);
    }

    @Override
    public Integer queryCount(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.queryCount(councilInfo);
    }

}
