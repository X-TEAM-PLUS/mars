package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.manager.UserRelationManager;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_RELATION表Manager接口实现类
 */

@Service
public class UserRelationManagerImpl implements UserRelationManager {
    private static final Log log = LogFactory.getLog(UserRelationManagerImpl.class);
    @javax.annotation.Resource
    private UserRelationDao userRelationDao;


    @Override
    public UserRelation get(UserRelation userRelation) throws Exception {
        return userRelationDao.get(userRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserRelation userRelation) throws Exception {
        return userRelationDao.insert(userRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserRelation> list) throws Exception {
        return userRelationDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserRelation userRelation) throws Exception {
        return userRelationDao.delete(userRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserRelation userRelation) throws Exception {
        return userRelationDao.update(userRelation);
    }

    @Override
    public List<UserRelation> query(UserRelation userRelation) throws Exception {
        return userRelationDao.query(userRelation);
    }

    @Override
    public Integer queryCount(UserRelation userRelation) throws Exception {
        return userRelationDao.queryCount(userRelation);
    }

    @Override
    public List<UserRelation> queryForUser(UserRelation userRelation) throws Exception {
        return userRelationDao.queryForUser(userRelation);
    }

    @Override
    public Integer queryForUserCount(UserRelation userRelation) throws Exception {
        return userRelationDao.queryForUserCount(userRelation);
    }

    @Override
    public List<UserRelation> queryForCouncil(BigDecimal councilId, int start, int limit) throws Exception {
        return userRelationDao.queryForCouncil(councilId, start, limit);
    }

    @Override
    public Integer queryForCouncilCount(BigDecimal councilId) throws Exception {
        return userRelationDao.queryForCouncilCount(councilId);
    }

    @Override
    public List<UserRelation> queryThisAndNextLevelUser(BigDecimal userId, Integer start, Integer limit) throws Exception {
        return userRelationDao.queryThisAndNextLevelUser(userId, new BigDecimal(start.intValue()), new BigDecimal(limit));
    }

    @Override
    public List<UserRelation> queryThisAndNextLevelUserCount(BigDecimal userId) throws Exception {
        return userRelationDao.queryThisAndNextLevelUserCount(userId);
    }

}
