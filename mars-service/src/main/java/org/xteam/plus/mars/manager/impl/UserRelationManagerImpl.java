package org.xteam.plus.mars.manager.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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

    @Resource
    private UserInfoDao userInfoDao;

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

    @Override
    public HashMap<String, Object> queryMyTeamCountAndNextLevelCount(BigDecimal userId) throws Exception {
        HashMap returnValue = Maps.newHashMap();
        returnValue.put("refereeUserCount", userRelationDao.queryRefereeUserIdCount(userId));
        returnValue.put("userLevel", UserLevelEnum.valueOf(userInfoDao.get(new UserInfo().setUserId(userId)).getUserLevel()).getInfo());

        List<UserRelation> nextRefereeUser = userRelationDao.queryNextRefereeUserCount(userId);
        returnValue.put("userSize", nextRefereeUser.size());
        if (nextRefereeUser != null && !nextRefereeUser.isEmpty()) {
            List myTeamList = Lists.newArrayList();
            for (UserRelation userRelation : nextRefereeUser) {
                HashMap map = Maps.newHashMap();
                map.put("userCount", userRelation.getUserCount());
                map.put("userId", userRelation.getRefereeUserId());
                myTeamList.add(map);
            }
            returnValue.put("userTeamList", myTeamList);
        }
        return returnValue;
    }

    @Override
    public HashMap<String, Object> queryMyTeamCountAndNewUserLevelCount(BigDecimal userId, Date beginDate, Date endDate) throws Exception {
        HashMap returnValue = Maps.newHashMap();
        UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
        if (userInfo == null) {
            throw new Exception("用户ID[" + userId + "] 不存在");
        }
        returnValue.put("userLevel", UserLevelEnum.valueOf(userInfo.getUserLevel()).getInfo());
        returnValue.put("userNewCount", userRelationDao.queryNewUserWhereDate(userId, beginDate, endDate));
        returnValue.put("userVipNewCount", userRelationDao.queryNewUserVIPWhereDate(userId, beginDate, endDate));
        List<HashMap> userLevelCount = userRelationDao.queryAllLevelCount(userId);
        for (HashMap map : userLevelCount) {
            Integer userLevel = (Integer) map.get("USER_LEVEL");
            map.put("USER_LEVEL", UserLevelEnum.valueOf(userLevel).getInfo());
        }
        returnValue.put("userLevelMap", userLevelCount);

        return returnValue;
    }


}

