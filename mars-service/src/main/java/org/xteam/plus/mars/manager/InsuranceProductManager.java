package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.InsuranceProduct;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_INSURANCE_PRODUCT表Manager接口
 */

public interface InsuranceProductManager {

    /**
     * 获取
     *
     * @param insuranceProduct
     * @return int
     */
    public InsuranceProduct get(InsuranceProduct insuranceProduct) throws Exception;

    /**
     * 新增
     *
     * @param insuranceProduct
     * @return int 记录数
     */
    public int insert(InsuranceProduct insuranceProduct) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<InsuranceProduct>
     * @return int  记录数
     */
    public int batchInsert(List<InsuranceProduct> list) throws Exception;

    /**
     * 删除
     *
     * @param insuranceProduct
     * @return int
     */
    public int delete(InsuranceProduct insuranceProduct) throws Exception;

    /**
     * 更新
     *
     * @param insuranceProduct
     * @return int 记录数
     */
    public int update(InsuranceProduct insuranceProduct) throws Exception;

    /**
     * 查询
     *
     * @param insuranceProduct
     * @return List<InsuranceProduct>
     */
    public List<InsuranceProduct> query(InsuranceProduct insuranceProduct) throws Exception;

    /**
     * 查询记录数
     *
     * @param insuranceProduct
     * @return List<InsuranceProduct>
     */
    public Integer queryCount(InsuranceProduct insuranceProduct) throws Exception;
}
