package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.AccountDetail;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ACCOUNT_DETAIL表Manager接口
 */

public interface AccountDetailManager {

    /**
     * 获取
     *
     * @param accountDetail
     * @return int
     */
    public AccountDetail get(AccountDetail accountDetail) throws Exception;

    /**
     * 新增
     *
     * @param accountDetail
     * @return int 记录数
     */
    public int insert(AccountDetail accountDetail) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<AccountDetail>
     * @return int  记录数
     */
    public int batchInsert(List<AccountDetail> list) throws Exception;

    /**
     * 删除
     *
     * @param accountDetail
     * @return int
     */
    public int delete(AccountDetail accountDetail) throws Exception;

    /**
     * 更新
     *
     * @param accountDetail
     * @return int 记录数
     */
    public int update(AccountDetail accountDetail) throws Exception;

    /**
     * 查询
     *
     * @param accountDetail
     * @return List<AccountDetail>
     */
    public List<AccountDetail> query(AccountDetail accountDetail) throws Exception;

    /**
     * 查询记录数
     *
     * @param accountDetail
     * @return List<AccountDetail>
     */
    public Integer queryCount(AccountDetail accountDetail) throws Exception;
}
