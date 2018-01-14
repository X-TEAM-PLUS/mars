package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.DeliveredInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_DELIVERED_INFO表Manager接口
 */

public interface DeliveredInfoManager {

    /**
     * 获取
     *
     * @param deliveredInfo
     * @return int
     */
    public DeliveredInfo get(DeliveredInfo deliveredInfo) throws Exception;

    /**
     * 新增
     *
     * @param deliveredInfo
     * @return int 记录数
     */
    public int insert(DeliveredInfo deliveredInfo) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<DeliveredInfo>
     * @return int  记录数
     */
    public int batchInsert(List<DeliveredInfo> list) throws Exception;

    /**
     * 删除
     *
     * @param deliveredInfo
     * @return int
     */
    public int delete(DeliveredInfo deliveredInfo) throws Exception;

    /**
     * 更新
     *
     * @param deliveredInfo
     * @return int 记录数
     */
    public int update(DeliveredInfo deliveredInfo) throws Exception;

    /**
     * 查询
     *
     * @param deliveredInfo
     * @return List<DeliveredInfo>
     */
    public List<DeliveredInfo> query(DeliveredInfo deliveredInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param deliveredInfo
     * @return List<DeliveredInfo>
     */
    public Integer queryCount(DeliveredInfo deliveredInfo) throws Exception;
}