package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.CouncilInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_COUNCIL_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_COUNCIL_INFO]表Mapper接口
 */

public interface CouncilInfoDao {

    /**
     * 获取
     * @param councilInfo
     * @return int
     */
    public CouncilInfo get(CouncilInfo councilInfo) throws Exception;

    /**
     * 新增
     * @param councilInfo
     * @return int 记录数
     */
    public int insert(CouncilInfo councilInfo) throws Exception;

    /**
     * 批量新增
     * @param list   List<CouncilInfo>
     * @return int  记录数
     */
    public int batchInsert(List<CouncilInfo> list) throws Exception;

    /**
     * 删除
     * @param councilInfo
     * @return int
     */
    public int delete(CouncilInfo councilInfo) throws Exception;

    /**
     * 更新
     * @param councilInfo
     * @return int 记录数
     */
    public int update(CouncilInfo councilInfo) throws Exception;

    /**
     * 查询
     * @param councilInfo
     * @return List<CouncilInfo>
     */
    public List<CouncilInfo> query(CouncilInfo councilInfo) throws Exception;

    /**
     * 查询记录数
     * @param  councilInfo
     * @return List<CouncilInfo>
     */
    public Integer queryCount(CouncilInfo councilInfo) throws Exception;
}
