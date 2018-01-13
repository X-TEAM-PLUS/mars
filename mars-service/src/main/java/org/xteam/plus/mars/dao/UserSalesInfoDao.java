package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.UserSalesInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_SALES_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_USER_SALES_INFO]表Mapper接口
 */

public interface UserSalesInfoDao {

    /**
     * 获取
     * @param userSalesInfo
     * @return int
     */
    public UserSalesInfo get(UserSalesInfo userSalesInfo) throws Exception;

    /**
     * 新增
     * @param userSalesInfo
     * @return int 记录数
     */
    public int insert(UserSalesInfo userSalesInfo) throws Exception;

    /**
     * 批量新增
     * @param list   List<UserSalesInfo>
     * @return int  记录数
     */
    public int batchInsert(List<UserSalesInfo> list) throws Exception;

    /**
     * 删除
     * @param userSalesInfo
     * @return int
     */
    public int delete(UserSalesInfo userSalesInfo) throws Exception;

    /**
     * 更新
     * @param userSalesInfo
     * @return int 记录数
     */
    public int update(UserSalesInfo userSalesInfo) throws Exception;

    /**
     * 查询
     * @param userSalesInfo
     * @return List<UserSalesInfo>
     */
    public List<UserSalesInfo> query(UserSalesInfo userSalesInfo) throws Exception;

    /**
     * 查询记录数
     * @param  userSalesInfo
     * @return List<UserSalesInfo>
     */
    public Integer queryCount(UserSalesInfo userSalesInfo) throws Exception;
}
