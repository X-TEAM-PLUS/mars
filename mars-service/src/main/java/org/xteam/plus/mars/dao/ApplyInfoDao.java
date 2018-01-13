package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.ApplyInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_APPLY_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_APPLY_INFO]表Mapper接口
 */

public interface ApplyInfoDao {

    /**
     * 获取
     * @param applyInfo
     * @return int
     */
    public ApplyInfo get(ApplyInfo applyInfo) throws Exception;

    /**
     * 新增
     * @param applyInfo
     * @return int 记录数
     */
    public int insert(ApplyInfo applyInfo) throws Exception;

    /**
     * 批量新增
     * @param list   List<ApplyInfo>
     * @return int  记录数
     */
    public int batchInsert(List<ApplyInfo> list) throws Exception;

    /**
     * 删除
     * @param applyInfo
     * @return int
     */
    public int delete(ApplyInfo applyInfo) throws Exception;

    /**
     * 更新
     * @param applyInfo
     * @return int 记录数
     */
    public int update(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询
     * @param applyInfo
     * @return List<ApplyInfo>
     */
    public List<ApplyInfo> query(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询记录数
     * @param  applyInfo
     * @return List<ApplyInfo>
     */
    public Integer queryCount(ApplyInfo applyInfo) throws Exception;
}
