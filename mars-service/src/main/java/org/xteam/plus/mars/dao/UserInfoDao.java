package org.xteam.plus.mars.dao;

import org.apache.ibatis.annotations.Param;
import org.xteam.plus.mars.domain.UserInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_USER_INFO]表Mapper接口
 */

public interface UserInfoDao {

    /**
     * 获取
     *
     * @param userInfo
     * @return int
     */
    public UserInfo get(UserInfo userInfo) throws Exception;

    /**
     * 获取社工，理事与常任理事详情
     * @param userInfo
     * @return
     * @throws Exception
     */
    public UserInfo getWorker(UserInfo userInfo) throws Exception;

    /**
     * 新增
     *
     * @param userInfo
     * @return int 记录数
     */
    public int insert(UserInfo userInfo) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<UserInfo>
     * @return int  记录数
     */
    public int batchInsert(List<UserInfo> list) throws Exception;

    /**
     * 删除
     *
     * @param userInfo
     * @return int
     */
    public int delete(UserInfo userInfo) throws Exception;

    /**
     * 更新
     *
     * @param userInfo
     * @return int 记录数
     */
    public int update(UserInfo userInfo) throws Exception;

    /**
     * 查询
     *
     * @param userInfo
     * @return List<UserInfo>
     */
    public List<UserInfo> query(UserInfo userInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param userInfo
     * @return List<UserInfo>
     */
    public Integer queryCount(UserInfo userInfo) throws Exception;

    /**
     * 查询社工，理事，常务理事
     *
     * @param userInfo
     * @parma 申请时的申请类型 	申请类型 1:社工 2理事 3常任理事
     * @return List<UserInfo>
     */
    public List<UserInfo> queryWorker(@Param("userinfo") UserInfo userInfo, @Param("applyType") Integer applyType) throws Exception;

    /**
     * 查询社工，理事，常务理事记录数
     *
     * @param userInfo
     * @parma 申请时的申请类型 	申请类型 1:社工 2理事 3常任理事
     * @return List<UserInfo>
     */
    public Integer queryWorkerCount(@Param("userinfo") UserInfo userInfo, @Param("applyType") Integer applyType) throws Exception;
}
