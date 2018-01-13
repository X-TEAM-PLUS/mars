package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.InsuranceCompany;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_INSURANCE_COMPANY表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_INSURANCE_COMPANY]表Mapper接口
 */

public interface InsuranceCompanyDao {

    /**
     * 获取
     * @param insuranceCompany
     * @return int
     */
    public InsuranceCompany get(InsuranceCompany insuranceCompany) throws Exception;

    /**
     * 新增
     * @param insuranceCompany
     * @return int 记录数
     */
    public int insert(InsuranceCompany insuranceCompany) throws Exception;

    /**
     * 批量新增
     * @param list   List<InsuranceCompany>
     * @return int  记录数
     */
    public int batchInsert(List<InsuranceCompany> list) throws Exception;

    /**
     * 删除
     * @param insuranceCompany
     * @return int
     */
    public int delete(InsuranceCompany insuranceCompany) throws Exception;

    /**
     * 更新
     * @param insuranceCompany
     * @return int 记录数
     */
    public int update(InsuranceCompany insuranceCompany) throws Exception;

    /**
     * 查询
     * @param insuranceCompany
     * @return List<InsuranceCompany>
     */
    public List<InsuranceCompany> query(InsuranceCompany insuranceCompany) throws Exception;

    /**
     * 查询记录数
     * @param  insuranceCompany
     * @return List<InsuranceCompany>
     */
    public Integer queryCount(InsuranceCompany insuranceCompany) throws Exception;
}
