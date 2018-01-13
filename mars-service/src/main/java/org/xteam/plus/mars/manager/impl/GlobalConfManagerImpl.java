package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.GlobalConfDao;
import org.xteam.plus.mars.domain.GlobalConf;
import org.xteam.plus.mars.manager.GlobalConfManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_GLOBAL_CONF表Manager接口实现类
 */

@Service
public class GlobalConfManagerImpl implements GlobalConfManager {
    private static final Log log = LogFactory.getLog(GlobalConfManagerImpl.class);
    @javax.annotation.Resource
    private GlobalConfDao globalConfDao;


    @Override
    public GlobalConf get(GlobalConf globalConf) throws Exception {
        return globalConfDao.get(globalConf);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(GlobalConf globalConf) throws Exception {
        return globalConfDao.insert(globalConf);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<GlobalConf> list) throws Exception {
        return globalConfDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(GlobalConf globalConf) throws Exception {
        return globalConfDao.delete(globalConf);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(GlobalConf globalConf) throws Exception {
        return globalConfDao.update(globalConf);
    }

    @Override
    public List<GlobalConf> query(GlobalConf globalConf) throws Exception {
        return globalConfDao.query(globalConf);
    }

    @Override
    public Integer queryCount(GlobalConf globalConf) throws Exception {
        return globalConfDao.queryCount(globalConf);
    }

}
