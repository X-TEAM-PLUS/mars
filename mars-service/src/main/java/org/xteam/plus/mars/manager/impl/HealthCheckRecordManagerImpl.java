package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.HealthCheckRecordDao;
import org.xteam.plus.mars.dao.UserHealthCardDao;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_HEALTH_CHECK_RECORD表Manager接口实现类
 */

@Service
public class HealthCheckRecordManagerImpl implements HealthCheckRecordManager {
    private static final Log log = LogFactory.getLog(HealthCheckRecordManagerImpl.class);
    @javax.annotation.Resource
    private HealthCheckRecordDao healthCheckRecordDao;
    @javax.annotation.Resource
    private UserHealthCardDao healthCardDao;


    @Override
    public HealthCheckRecord get(HealthCheckRecord healthCheckRecord) throws Exception {
        return healthCheckRecordDao.get(healthCheckRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(HealthCheckRecord healthCheckRecord) throws Exception {
        //次数减一
        UserHealthCard userHealthCard = healthCardDao.queryForProductByActive(
                new UserHealthCard().setActivateUserId(healthCheckRecord.getUserId())
        );
        userHealthCard.setSendCount(userHealthCard.getSendCount() + 1);
        userHealthCard.setUpdated(new Date());
        healthCardDao.update(userHealthCard);
        return healthCheckRecordDao.insert(healthCheckRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<HealthCheckRecord> list) throws Exception {
        return healthCheckRecordDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(HealthCheckRecord healthCheckRecord) throws Exception {
        return healthCheckRecordDao.delete(healthCheckRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(HealthCheckRecord healthCheckRecord) throws Exception {
        return healthCheckRecordDao.update(healthCheckRecord);
    }

    @Override
    public List<HealthCheckRecord> query(HealthCheckRecord healthCheckRecord) throws Exception {
        return healthCheckRecordDao.query(healthCheckRecord);
    }

    @Override
    public Integer queryCount(HealthCheckRecord healthCheckRecord) throws Exception {
        return healthCheckRecordDao.queryCount(healthCheckRecord);
    }

}
