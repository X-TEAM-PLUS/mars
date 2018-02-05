package org.xteam.plus.mars.dao;

import org.apache.ibatis.annotations.Param;
import org.xteam.plus.mars.domain.UserInsurance;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_INSURANCE表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_USER_INSURANCE]表Mapper接口
 */

public interface UserInsuranceDao {

    /**
     * 获取
     *
     * @param userInsurance
     * @return int
     */
    public UserInsurance get(UserInsurance userInsurance) throws Exception;

    /**
     * 新增
     *
     * @param userInsurance
     * @return int 记录数
     */
    public int insert(UserInsurance userInsurance) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<UserInsurance>
     * @return int  记录数
     */
    public int batchInsert(List<UserInsurance> list) throws Exception;

    /**
     * 删除
     *
     * @param userInsurance
     * @return int
     */
    public int delete(UserInsurance userInsurance) throws Exception;

    /**
     * 更新
     *
     * @param userInsurance
     * @return int 记录数
     */
    public int update(UserInsurance userInsurance) throws Exception;

    /**
     * 查询
     *
     * @param userInsurance
     * @return List<UserInsurance>
     */
    public List<UserInsurance> query(UserInsurance userInsurance) throws Exception;

    /**
     * 查询记录数
     *
     * @param userInsurance
     * @return List<UserInsurance>
     */
    public Integer queryCount(UserInsurance userInsurance) throws Exception;

    /**
     * 关联查询，获取产品信息跟保险公司
     *
     * @param userInsurance
     * @return
     */
    public List<UserInsurance> queryForProduct(UserInsurance userInsurance) throws Exception;

    /**
     * 关联查询，获取产品信息跟保险公司（个数）
     *
     * @param userInsurance
     * @return
     */
    public int queryForProductCount(UserInsurance userInsurance) throws Exception;

    /**
     * 关联查询用户卡信息
     * @param insuranceOrderId
     * @return
     */
    public UserInsurance getForProduct(@Param("insuranceOrderId") BigDecimal insuranceOrderId);
}
