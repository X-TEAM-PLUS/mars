package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.WithdrawRecord;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_WITHDRAW_RECORD表Manager接口
 */

public interface WithdrawRecordManager {

    /**
     * 获取
     *
     * @param withdrawRecord
     * @return int
     */
    public WithdrawRecord get(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 新增
     *
     * @param withdrawRecord
     * @return int 记录数
     */
    public int insert(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<WithdrawRecord>
     * @return int  记录数
     */
    public int batchInsert(List<WithdrawRecord> list) throws Exception;

    /**
     * 删除
     *
     * @param withdrawRecord
     * @return int
     */
    public int delete(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 更新
     *
     * @param withdrawRecord
     * @return int 记录数
     */
    public int update(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 查询
     *
     * @param withdrawRecord
     * @return List<WithdrawRecord>
     */
    public List<WithdrawRecord> query(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 查询记录数
     *
     * @param withdrawRecord
     * @return List<WithdrawRecord>
     */
    public Integer queryCount(WithdrawRecord withdrawRecord) throws Exception;

    /**
     * 同意提现申请
     * @param withdrawrecord
     * @return
     * @throws Exception
     */
    public int agreeApply(WithdrawRecord withdrawrecord) throws Exception;

    /**
     * 回绝提现申请
     * @param withdrawrecord
     * @return
     * @throws Exception
     */
    public int refuseApply(WithdrawRecord withdrawrecord) throws Exception;

}
