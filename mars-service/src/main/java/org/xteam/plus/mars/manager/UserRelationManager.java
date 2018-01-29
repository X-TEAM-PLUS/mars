package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.UserRelation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_USER_RELATION表Manager接口
 */

public interface UserRelationManager {

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
     * 查询记录数
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public Integer queryCount(UserRelation userRelation) throws Exception;

    /**
     * 关联查询user
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public List<UserRelation> queryForUser(UserRelation userRelation) throws Exception;

    /**
     * 关联查询user记录数
     *
     * @param userRelation
     * @return List<UserRelation>
     */
    public Integer queryForUserCount(UserRelation userRelation) throws Exception;

    /**
     * 根据地方常任理事会主键查询所有关联用户
     *
     * @param councilId 常任理事主键
     * @param start
     * @param limit
     * @return
     */
    public List<UserRelation> queryForCouncil(BigDecimal councilId, int start, int limit) throws Exception;

    /**
     * 根据地方常任理事会主键查询所有关联用户
     *
     * @param councilId 常任理事主键
     * @return
     */
    public Integer queryForCouncilCount(BigDecimal councilId) throws Exception;

    /**
     * 查询当天用户下以及团队下级人员所有推荐用户（不支持地方常任理事会用户）
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<UserRelation> queryThisAndNextLevelUser(BigDecimal userId, Integer start, Integer limit) throws Exception;

    /**
     * 查询当天用户下以及团队下级人员所有推荐用户（不支持地方常任理事会用户）
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<UserRelation> queryThisAndNextLevelUserCount(BigDecimal userId) throws Exception;
}
