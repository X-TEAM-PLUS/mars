package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.ApplyInfoDao;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.manager.ApplyInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_APPLY_INFO表Manager接口实现类
 */

@Service
public class ApplyInfoManagerImpl implements ApplyInfoManager {
    private static final Log log = LogFactory.getLog(ApplyInfoManagerImpl.class);
    @javax.annotation.Resource
    private ApplyInfoDao applyInfoDao;


    @Override
    public ApplyInfo get(ApplyInfo applyInfo) throws Exception {
        return applyInfoDao.get(applyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ApplyInfo applyInfo) throws Exception {
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
