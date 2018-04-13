package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.CommissionDetail;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-04-13
 * Time: 16:56
 * 功能:T_MARS_COMMISSION_DETAIL表Manager接口
 */

public interface CommissionDetailManager {

    /**
     * 获取
     *
     * @param commissionDetail
     * @return int
     */
    public CommissionDetail get(CommissionDetail commissionDetail) throws Exception;

    /**
     * 新增
     *
     * @param commissionDetail
     * @return int 记录数
     */
    public int insert(CommissionDetail commissionDetail) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<CommissionDetail>
     * @return int  记录数
     */
    public int batchInsert(List<CommissionDetail> list) throws Exception;

    /**
     * 删除
     *
     * @param commissionDetail
     * @return int
     */
    public int delete(CommissionDetail commissionDetail) throws Exception;

    /**
     * 更新
     *
     * @param commissionDetail
     * @return int 记录数
     */
    public int update(CommissionDetail commissionDetail) throws Exception;

    /**
     * 查询
     *
     * @param commissionDetail
     * @return List<CommissionDetail>
     */
    public List<CommissionDetail> query(CommissionDetail commissionDetail) throws Exception;

    /**
     * 查询记录数
     *
     * @param commissionDetail
     * @return List<CommissionDetail>
     */
    public Integer queryCount(CommissionDetail commissionDetail) throws Exception;
}
