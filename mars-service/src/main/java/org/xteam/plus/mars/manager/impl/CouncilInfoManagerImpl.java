package org.xteam.plus.mars.manager.impl;

import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.CouncilInfoDao;
import org.xteam.plus.mars.dao.LocalCouncilMemberDao;
import org.xteam.plus.mars.domain.CouncilInfo;
import org.xteam.plus.mars.domain.CouncilInfoList;
import org.xteam.plus.mars.domain.LocalCouncilMember;
import org.xteam.plus.mars.manager.CouncilInfoManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_COUNCIL_INFO表Manager接口实现类
 */

@Service
public class CouncilInfoManagerImpl implements CouncilInfoManager {
    private static final Log log = LogFactory.getLog(CouncilInfoManagerImpl.class);
    @javax.annotation.Resource
    private CouncilInfoDao councilInfoDao;

    @javax.annotation.Resource
    private LocalCouncilMemberDao localCouncilMemberDao;


    @Override
    public CouncilInfo get(CouncilInfo councilInfo) throws Exception {
        CouncilInfo returnValue = councilInfoDao.get(councilInfo);
        List<LocalCouncilMember> localCouncilMemberList = localCouncilMemberDao.query(new LocalCouncilMember().setCouncilId(councilInfo.getCouncilId()));
        returnValue.setLocalCouncilMemberList(localCouncilMemberList);
        return returnValue;
    }

    @Override
    public CouncilInfoList getTotal(BigDecimal councilInfo) throws Exception {
        return councilInfoDao.getTotal(councilInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(CouncilInfo councilInfo) throws Exception {
        List<LocalCouncilMember> alreadLocalCouncils = localCouncilMemberDao.query(new LocalCouncilMember().setCouncilId(councilInfo.getCouncilId()));
        List<LocalCouncilMember> localCouncilMembers = userToRepeat(alreadLocalCouncils, councilInfo.getUserInfo(), councilInfo);
        if (localCouncilMembers.isEmpty()){
            throw new Exception("没有可以添加的用户，目前已添加的用户为 "+ alreadLocalCouncils.size());
        }
        return localCouncilMemberDao.batchInsert(localCouncilMembers);
    }

    @Override
    public int deleteUser(CouncilInfo councilInfo) throws Exception {
        LocalCouncilMember localCouncilMember = new LocalCouncilMember().setCouncilId(councilInfo.getCouncilId());
        localCouncilMember.setUserId(new BigDecimal(councilInfo.getUserInfo()));
        return localCouncilMemberDao.deleteUser(localCouncilMember);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CouncilInfo councilInfo) throws Exception {
        councilInfo.setCreated(new Date());
        councilInfo.setOpenDate(new Date());
        int count = councilInfoDao.insert(councilInfo);
        if (count <= 0) {
            throw new Exception("创建地方常务理事时异常，数据库插入的数据为0，请检查数据库是否正常!");
        }
        List<LocalCouncilMember> localCouncilMembers = Lists.newArrayList();
        String[] userinfo = councilInfo.getUserInfo().split(",");
        for (String userId : userinfo) {
            LocalCouncilMember localCouncilMember = new LocalCouncilMember();
            localCouncilMember.setCouncilId(councilInfo.getCouncilId());
            localCouncilMember.setUserId(new BigDecimal(userId));
            localCouncilMember.setJobType(councilInfo.getJobType().getCode());
            localCouncilMember.setGrantTime(new Date());
            localCouncilMember.setCreated(new Date());
            localCouncilMembers.add(localCouncilMember);
        }
        return localCouncilMemberDao.batchInsert(localCouncilMembers);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<CouncilInfo> list) throws Exception {
        return councilInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.delete(councilInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.update(councilInfo);
    }

    @Override
    public List<CouncilInfo> query(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.query(councilInfo);
    }

    @Override
    public Integer queryCount(CouncilInfo councilInfo) throws Exception {
        return councilInfoDao.queryCount(councilInfo);
    }

    @Override
    public List<CouncilInfoList> queryTotal(int start,int limit) throws Exception {
        return councilInfoDao.queryTotal(start,limit);
    }

    /**
     * 去重复添加的用户
     *
     * @param alreadLocalCouncils
     * @param userInfo
     * @param councilInfo
     * @return
     */
    private List<LocalCouncilMember> userToRepeat(List<LocalCouncilMember> alreadLocalCouncils, String userInfo, CouncilInfo councilInfo) {
        List<LocalCouncilMember> returnValue = Lists.newArrayList();
        String users[] = userInfo.split(",");
        for (String userId : users) {
            boolean isExist = false;
            for (LocalCouncilMember localCouncilMember : alreadLocalCouncils) {
                if (localCouncilMember.getUserId().toBigInteger().equals(new BigInteger(userId))) {
                    isExist = true;
                }
            }
            if (!isExist) {
                LocalCouncilMember localCouncilMember = new LocalCouncilMember();
                localCouncilMember.setCouncilId(councilInfo.getCouncilId());
                localCouncilMember.setUserId(new BigDecimal(userId));
                localCouncilMember.setJobType(councilInfo.getJobType().getCode());
                localCouncilMember.setGrantTime(new Date());
                localCouncilMember.setCreated(new Date());
                returnValue.add(localCouncilMember);
            }
        }
        return returnValue;
    }
}
