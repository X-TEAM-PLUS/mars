package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.UserLevelEnum;
import org.xteam.plus.mars.wx.bean.WxUserList;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
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
        UserInfo returnValue = userInfoDao.get(userInfo);
        returnValue.setRealName(URLDecoder.decode(returnValue.getRealName(), "utf-8"));
        return returnValue;
    }

    @Override
    public UserInfo getWorker(UserInfo userInfo) throws Exception {
        return userInfoDao.getWorker(userInfo);
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

    @Override
    public List<UserInfo> queryWorker(UserInfo userInfo, Integer applyType) throws Exception {
        return userInfoDao.queryWorker(userInfo, applyType);
    }

    @Override
    public Integer queryWorkerCount(UserInfo userInfo, Integer applyType) throws Exception {
        return userInfoDao.queryWorkerCount(userInfo, applyType);
    }

    @Override
    public Integer queryUserTotalCount(UserLevelEnum userLevelEnum, Date nowDate) {
        return userInfoDao.queryUserTotalCount(userLevelEnum.getCode(), nowDate);
    }

    @Override
    public UserInfo registerWxUserInfo(WxUserList.WxUser wxUser, BigDecimal userId) throws Exception {
        UserInfo userInfo = null;
        if (userId != null) {
            // 更新用户信息
            userInfo = userInfoDao.get(new UserInfo().setUserId(userId));
            if (userInfo == null) {
                List<UserInfo> userInfos = userInfoDao.query(new UserInfo().setWxOpenid(wxUser.getOpenid()));
                if (userInfos != null && userInfos.size() > 0) {
                    userInfo = userInfos.get(0);
                }
            }
        } else {
            List<UserInfo> userInfos = userInfoDao.query(new UserInfo().setWxOpenid(wxUser.getOpenid()));
            if (userInfos != null && userInfos.size() > 0) {
                userInfo = userInfos.get(0);
            }
        }
        if (userInfo == null) {
            userInfo = new UserInfo();
            // 开始注册用户
            if (!insertWxUser(wxUser, userInfo)) {
                throw new Exception("注册新用户成功!");
            }
        } else {
            if (!updateWxUser(wxUser, userInfo)) {
                throw new Exception("更新用户数据失败!");
            }
        }
        return userInfo;
    }

    private boolean updateWxUser(WxUserList.WxUser wxUser, UserInfo userInfo) throws Exception {
        userInfo.setRealName(wxUser.getNickname());
        userInfo.setWxHeadPortrait(wxUser.getHeadimgurl());
        userInfo.setWxOpenid(wxUser.getOpenid());
        if (userInfoDao.update(userInfo) > 0) {
            return true;
        }
        return false;
    }

    private boolean insertWxUser(WxUserList.WxUser wxUser, UserInfo userInfo) throws Exception {
        userInfo.setRealName(wxUser.getNickname());
        userInfo.setWxHeadPortrait(wxUser.getHeadimgurl());
        userInfo.setWxOpenid(wxUser.getOpenid());
        userInfo.setRegisterTime(new Date());
        userInfo.setUserLevel(UserLevelEnum.TOURIST.getCode());
        userInfo.setStatus(1);
        userInfo.setCreated(new Date());
        if (userInfoDao.insert(userInfo) > 0) {
            return true;
        }
        return false;
    }

}
