package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserSalesInfoDao;
import org.xteam.plus.mars.domain.UserSalesInfo;
import org.xteam.plus.mars.manager.UserSalesInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_SALES_INFO表Manager接口实现类
 */

@Service
public class UserSalesInfoManagerImpl implements UserSalesInfoManager {
    private static final Log log = LogFactory.getLog(UserSalesInfoManagerImpl.class);
    @javax.annotation.Resource
    private UserSalesInfoDao userSalesInfoDao;


    @Override
    public UserSalesInfo get(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.get(userSalesInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.insert(userSalesInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserSalesInfo> list) throws Exception {
        return userSalesInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.delete(userSalesInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.update(userSalesInfo);
    }

    @Override
    public List<UserSalesInfo> query(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.query(userSalesInfo);
    }

    @Override
    public Integer queryCount(UserSalesInfo userSalesInfo) throws Exception {
        return userSalesInfoDao.queryCount(userSalesInfo);
    }

}
