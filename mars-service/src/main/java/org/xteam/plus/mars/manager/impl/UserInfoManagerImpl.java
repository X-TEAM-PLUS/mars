package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.UserInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_INFO表Manager接口实现类
 */

@Service
public class UserInfoManagerImpl implements UserInfoManager {
    private static final Log log = LogFactory.getLog(UserInfoManagerImpl.class);
    @javax.annotation.Resource
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo get(UserInfo userInfo) throws Exception {
        return userInfoDao.get(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserInfo userInfo) throws Exception {
        return userInfoDao.insert(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserInfo> list) throws Exception {
        return userInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserInfo userInfo) throws Exception {
        return userInfoDao.delete(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserInfo userInfo) throws Exception {
        return userInfoDao.update(userInfo);
    }

    @Override
    public List<UserInfo> query(UserInfo userInfo) throws Exception {
        return userInfoDao.query(userInfo);
    }

    @Override
    public Integer queryCount(UserInfo userInfo) throws Exception {
        return userInfoDao.queryCount(userInfo);
    }

}
