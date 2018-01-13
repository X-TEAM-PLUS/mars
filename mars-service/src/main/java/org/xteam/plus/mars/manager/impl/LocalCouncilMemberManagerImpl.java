package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.LocalCouncilMemberDao;
import org.xteam.plus.mars.domain.LocalCouncilMember;
import org.xteam.plus.mars.manager.LocalCouncilMemberManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_LOCAL_COUNCIL_MEMBER表Manager接口实现类
 */

@Service
public class LocalCouncilMemberManagerImpl implements LocalCouncilMemberManager {
    private static final Log log = LogFactory.getLog(LocalCouncilMemberManagerImpl.class);
    @javax.annotation.Resource
    private LocalCouncilMemberDao localCouncilMemberDao;


    @Override
    public LocalCouncilMember get(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.get(localCouncilMember);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.insert(localCouncilMember);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<LocalCouncilMember> list) throws Exception {
        return localCouncilMemberDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.delete(localCouncilMember);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.update(localCouncilMember);
    }

    @Override
    public List<LocalCouncilMember> query(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.query(localCouncilMember);
    }

    @Override
    public Integer queryCount(LocalCouncilMember localCouncilMember) throws Exception {
        return localCouncilMemberDao.queryCount(localCouncilMember);
    }

}
