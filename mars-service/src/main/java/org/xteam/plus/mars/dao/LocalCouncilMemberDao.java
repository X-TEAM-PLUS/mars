package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.LocalCouncilMember;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_LOCAL_COUNCIL_MEMBER表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_LOCAL_COUNCIL_MEMBER]表Mapper接口
 */

public interface LocalCouncilMemberDao {

    /**
     * 获取
     * @param localCouncilMember
     * @return int
     */
    public LocalCouncilMember get(LocalCouncilMember localCouncilMember) throws Exception;

    /**
     * 新增
     * @param localCouncilMember
     * @return int 记录数
     */
    public int insert(LocalCouncilMember localCouncilMember) throws Exception;

    /**
     * 批量新增
     * @param list   List<LocalCouncilMember>
     * @return int  记录数
     */
    public int batchInsert(List<LocalCouncilMember> list) throws Exception;

    /**
     * 删除
     * @param localCouncilMember
     * @return int
     */
    public int delete(LocalCouncilMember localCouncilMember) throws Exception;

    /**
     * 删除
     * @param localCouncilMember
     * @return int
     */
    public int deleteUser(LocalCouncilMember localCouncilMember) throws Exception;
    /**
     * 更新
     * @param localCouncilMember
     * @return int 记录数
     */
    public int update(LocalCouncilMember localCouncilMember) throws Exception;

    /**
     * 查询
     * @param localCouncilMember
     * @return List<LocalCouncilMember>
     */
    public List<LocalCouncilMember> query(LocalCouncilMember localCouncilMember) throws Exception;

    /**
     * 查询记录数
     * @param  localCouncilMember
     * @return List<LocalCouncilMember>
     */
    public Integer queryCount(LocalCouncilMember localCouncilMember) throws Exception;
}
