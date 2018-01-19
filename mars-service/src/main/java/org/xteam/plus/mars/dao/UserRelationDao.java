package org.xteam.plus.mars.dao;

import org.apache.ibatis.annotations.Param;
import org.xteam.plus.mars.domain.UserRelation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_RELATION表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_USER_RELATION]表Mapper接口
 */

public interface UserRelationDao {

    /**
     * 获取
     *
     * @param userRelation
     * @return int
     */
    public UserRelation get(UserRelation userRelation) throws Exception;

    /**
     * 新增
     *
     * @param userRelation
     * @return int 记录数
     */
    public int insert(UserRelation userRelation) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<UserRelation>
     * @return int  记录数
     */
    public int batchInsert(List<UserRelation> list) throws Exception;

    /**
     * 删除
     *
     * @param userRelation
     * @return int
     */
    public int delete(UserRelation userRelation) throws Exception;

    /**
     * 更新
     *
     * @param userRelation
     * @return int 记录数
     */
    public int update(UserRelation userRelation) throws Exception;

    /**
     * 查询
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public List<UserRelation> query(UserRelation userRelation) throws Exception;

    /**
     * 关联查询user
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public List<UserRelation> queryForUser(UserRelation userRelation) throws Exception;

    /**
     * 关联查询记录数
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public Integer queryForUserCount(UserRelation userRelation) throws Exception;

    /**
     * 查询记录数
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public Integer queryCount(UserRelation userRelation) throws Exception;

    /**
     * 查询地方常任理事所有关联用户数据
     *
     * @param councilId
     * @param start
     * @param limit
     * @return
     * @throws Exception
     */
    public List<UserRelation> queryForCouncil(@Param("councilId") BigDecimal councilId, @Param("start") int start, @Param("limit") int limit) throws Exception;

    /**
     * 查询地方常任理事所有关联用户数据个数
     *
     * @param councilId
     * @return
     * @throws Exception
     */
    public Integer queryForCouncilCount(@Param("councilId") BigDecimal councilId) throws Exception;
}
