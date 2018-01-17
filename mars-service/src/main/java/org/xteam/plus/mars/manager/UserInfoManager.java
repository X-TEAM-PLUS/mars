package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.UserInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_INFO表Manager接口
 */

public interface UserInfoManager {

    /**
     * 获取
     *
     * @param userInfo
     * @return int
     */
    public UserInfo get(UserInfo userInfo) throws Exception;

    /**
     * 获取社工，理事，常任理事详细信息
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
     * 查询社工，理事，常务理事列表
     *
     * @return
     * @throws Exception
     */
    public List<UserInfo> queryWorker(UserInfo userInfo,Integer applyType) throws Exception;

    /**
     * 查询社工，理事，常务理事个数
     * @param userInfo
     * @return
     * @throws Exception
     */
    public Integer queryWorkerCount(UserInfo userInfo,Integer applyType) throws Exception;
}
