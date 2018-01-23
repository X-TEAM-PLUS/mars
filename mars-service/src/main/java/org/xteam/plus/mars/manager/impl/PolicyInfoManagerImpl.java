package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.xteam.plus.mars.dao.PolicyInfoDao;
import org.xteam.plus.mars.domain.PolicyInfo;
import org.xteam.plus.mars.manager.PolicyInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能:T_MARS_POLICY_INFO表Manager接口实现类
 */

@Service
public class PolicyInfoManagerImpl implements PolicyInfoManager {
    private static final Log log = LogFactory.getLog(PolicyInfoManagerImpl.class);
    @javax.annotation.Resource
    private PolicyInfoDao policyInfoDao;


    @Override
    public List<PolicyInfo> query(PolicyInfo policyInfo) throws Exception {
        return policyInfoDao.query(policyInfo);
    }

    @Override
    public Integer queryCount(PolicyInfo policyInfo) throws Exception {
        return policyInfoDao.queryCount(policyInfo);
    }

}
