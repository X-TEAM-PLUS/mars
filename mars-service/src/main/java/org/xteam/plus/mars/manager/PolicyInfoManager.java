package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.PolicyInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能:T_MARS_POLICY_INFO表Manager接口
 */

public interface PolicyInfoManager {

    /**
     * 查询
     *
     * @param policyInfo
     * @return List<PolicyInfo>
     */
    public List<PolicyInfo> query(PolicyInfo policyInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param policyInfo
     * @return List<PolicyInfo>
     */
    public Integer queryCount(PolicyInfo policyInfo) throws Exception;
}
