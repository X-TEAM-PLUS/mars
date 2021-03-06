package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.CouncilInfo;
import org.xteam.plus.mars.domain.CouncilInfoList;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_COUNCIL_INFO表Manager接口
 */

public interface CouncilInfoManager {

    /**
     * 获取
     *
     * @param councilInfo
     * @return int
     */
    public CouncilInfo get(CouncilInfo councilInfo) throws Exception;

    /**
     * 获取单个地方常任理事会统计数据
     * @param councilInfo
     * @return
     * @throws Exception
     */
    public CouncilInfoList getTotal(BigDecimal councilInfo) throws Exception;

    /**
     * 新增
     *
     * @param councilInfo
     * @return int 记录数
     */
    public int insert(CouncilInfo councilInfo) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<CouncilInfo>
     * @return int  记录数
     */
    public int batchInsert(List<CouncilInfo> list) throws Exception;

    /**
     * 删除
     *
     * @param councilInfo
     * @return int
     */
    public int delete(CouncilInfo councilInfo) throws Exception;

    /**
     * 更新
     *
     * @param councilInfo
     * @return int 记录数
     */
    public int update(CouncilInfo councilInfo) throws Exception;

    /**
     * 查询
     *
     * @param councilInfo
     * @return List<CouncilInfo>
     */
    public List<CouncilInfo> query(CouncilInfo councilInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param councilInfo
     * @return List<CouncilInfo>
     */
    public Integer queryCount(CouncilInfo councilInfo) throws Exception;

    /**
     * 查询地方常任理事列表
     *
     * @return
     * @throws Exception
     */
    public List<CouncilInfoList> queryTotal(int start,int limit) throws Exception;

    /**
     * 添加成员
     * @param councilInfo
     * @return
     * @throws Exception
     */
    public int insertUser(CouncilInfo councilInfo) throws Exception;

    /**
     * 删除用户
     * @param councilInfo
     * @return
     * @throws Exception
     */
    public int deleteUser(CouncilInfo councilInfo) throws Exception;
}
