package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.ApplyInfoDao;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.ApplyInfoManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_APPLY_INFO表Manager接口实现类
 */

@Service
public class ApplyInfoManagerImpl extends Logging implements ApplyInfoManager {
    private static final Log log = LogFactory.getLog(ApplyInfoManagerImpl.class);

    @Resource
    private UserInfoDao userInfoDao;

    @javax.annotation.Resource
    private ApplyInfoDao applyInfoDao;


    @Override
    public ApplyInfo get(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.get(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ApplyInfo applyInfo) throws Exception {
        if (applyInfo.getApplyType() == ApplayTypeEnum.SOCIAL.getCode()) {
            logInfo("用户ID["+applyInfo.getUserId()+"] 正在进行社工升级申请，系统将直接升级为社工");
            UserInfo userInfo = userInfoDao.get(new UserInfo().setUserId(applyInfo.getUserId()));
            userInfo.setUserLevel(UserLevelEnum.SOCIAL.getCode());
            userInfoDao.update(userInfo);
        }
        return applyInfoDao.insert(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<ApplyInfo> list) throws Exception {
        return applyInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.delete(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.update(applyInfo);
    }

    @Override
    public List<ApplyInfo> query(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.query(applyInfo);
    }

    @Override
    public Integer queryCount(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.queryCount(applyInfo);
    }

}
