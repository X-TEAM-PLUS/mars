package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.DeliveredInfoDao;
import org.xteam.plus.mars.domain.DeliveredInfo;
import org.xteam.plus.mars.manager.DeliveredInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_DELIVERED_INFO表Manager接口实现类
 */

@Service
public class DeliveredInfoManagerImpl implements DeliveredInfoManager {
    private static final Log log = LogFactory.getLog(DeliveredInfoManagerImpl.class);
    @javax.annotation.Resource
    private DeliveredInfoDao deliveredInfoDao;


    @Override
    public DeliveredInfo get(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.get(deliveredInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.insert(deliveredInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<DeliveredInfo> list) throws Exception {
        return deliveredInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.delete(deliveredInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.update(deliveredInfo);
    }

    @Override
    public List<DeliveredInfo> query(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.query(deliveredInfo);
    }

    @Override
    public Integer queryCount(DeliveredInfo deliveredInfo) throws Exception {
        return deliveredInfoDao.queryCount(deliveredInfo);
    }

}
