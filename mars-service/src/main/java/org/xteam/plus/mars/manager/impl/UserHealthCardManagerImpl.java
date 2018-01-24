package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserHealthCardDao;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.manager.UserHealthCardManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_HEALTH_CARD表Manager接口实现类
 */

@Service
public class UserHealthCardManagerImpl implements UserHealthCardManager {
    private static final Log log = LogFactory.getLog(UserHealthCardManagerImpl.class);
    @javax.annotation.Resource
    private UserHealthCardDao userHealthCardDao;


    @Override
    public UserHealthCard get(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.get(userHealthCard);
    }

    @Override
    public UserHealthCard getForUser(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.getForUser(userHealthCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.insert(userHealthCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserHealthCard> list) throws Exception {
        return userHealthCardDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.delete(userHealthCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.update(userHealthCard);
    }

    @Override
    public List<UserHealthCard> query(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.query(userHealthCard);
    }

    @Override
    public Integer queryCount(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.queryCount(userHealthCard);
    }

    @Override
    public List<UserHealthCard> queryForActiveUser(UserHealthCard userHealthCard) throws Exception {
        if (userHealthCard.getActivateUserInfo() != null && userHealthCard.getActivateUserInfo().getRealName() != null) {
            String realName = userHealthCard.getActivateUserInfo().getRealName();
            userHealthCard.getActivateUserInfo().setRealName("%" + realName + "%");
        }
        return userHealthCardDao.queryForActiveUser(userHealthCard);
    }

    @Override
    public Integer queryForActiveUserCount(UserHealthCard userHealthCard) throws Exception {
        return userHealthCardDao.queryForActiveUserCount(userHealthCard);
    }

}
