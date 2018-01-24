package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.UserHealthCard;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_HEALTH_CARD表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_USER_HEALTH_CARD]表Mapper接口
 */

public interface UserHealthCardDao {

    /**
     * 获取
     * @param userHealthCard
     * @return int
     */
    public UserHealthCard get(UserHealthCard userHealthCard) throws Exception;

    /**
     * 获取详情关联用户
     * @param userHealthCard
     * @return
     * @throws Exception
     */
    public UserHealthCard getForUser(UserHealthCard userHealthCard) throws Exception;
    /**
     * 新增
     * @param userHealthCard
     * @return int 记录数
     */
    public int insert(UserHealthCard userHealthCard) throws Exception;

    /**
     * 批量新增
     * @param list   List<UserHealthCard>
     * @return int  记录数
     */
    public int batchInsert(List<UserHealthCard> list) throws Exception;

    /**
     * 删除
     * @param userHealthCard
     * @return int
     */
    public int delete(UserHealthCard userHealthCard) throws Exception;

    /**
     * 更新
     * @param userHealthCard
     * @return int 记录数
     */
    public int update(UserHealthCard userHealthCard) throws Exception;

    /**
     * 查询
     * @param userHealthCard
     * @return List<UserHealthCard>
     */
    public List<UserHealthCard> query(UserHealthCard userHealthCard) throws Exception;

    /**
     * 查询记录数
     * @param  userHealthCard
     * @return List<UserHealthCard>
     */
    public Integer queryCount(UserHealthCard userHealthCard) throws Exception;

    /**
     * 查询
     * @param userHealthCard
     * @return List<UserHealthCard>
     */
    public List<UserHealthCard> queryForActiveUser(UserHealthCard userHealthCard) throws Exception;

    /**
     * 查询记录数
     * @param  userHealthCard
     * @return List<UserHealthCard>
     */
    public Integer queryForActiveUserCount(UserHealthCard userHealthCard) throws Exception;

}
