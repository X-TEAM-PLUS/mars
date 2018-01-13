package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.UserRelation;

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
     * @param userRelation
     * @return int
     */
    public UserRelation get(UserRelation userRelation) throws Exception;

    /**
     * 新增
     * @param userRelation
     * @return int 记录数
     */
    public int insert(UserRelation userRelation) throws Exception;

    /**
     * 批量新增
     * @param list   List<UserRelation>
     * @return int  记录数
     */
    public int batchInsert(List<UserRelation> list) throws Exception;

    /**
     * 删除
     * @param userRelation
     * @return int
     */
    public int delete(UserRelation userRelation) throws Exception;

    /**
     * 更新
     * @param userRelation
     * @return int 记录数
     */
    public int update(UserRelation userRelation) throws Exception;

    /**
     * 查询
     * @param userRelation
     * @return List<UserRelation>
     */
    public List<UserRelation> query(UserRelation userRelation) throws Exception;

    /**
     * 查询记录数
     * @param  userRelation
     * @return List<UserRelation>
     */
    public Integer queryCount(UserRelation userRelation) throws Exception;
}
