package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.AccountBalance;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ACCOUNT_BALANCE表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_ACCOUNT_BALANCE]表Mapper接口
 */

public interface AccountBalanceDao {

    /**
     * 获取
     * @param accountBalance
     * @return int
     */
    public AccountBalance get(AccountBalance accountBalance) throws Exception;

    /**
     * 新增
     * @param accountBalance
     * @return int 记录数
     */
    public int insert(AccountBalance accountBalance) throws Exception;

    /**
     * 批量新增
     * @param list   List<AccountBalance>
     * @return int  记录数
     */
    public int batchInsert(List<AccountBalance> list) throws Exception;

    /**
     * 删除
     * @param accountBalance
     * @return int
     */
    public int delete(AccountBalance accountBalance) throws Exception;

    /**
     * 更新
     * @param accountBalance
     * @return int 记录数
     */
    public int update(AccountBalance accountBalance) throws Exception;

    /**
     * 查询
     * @param accountBalance
     * @return List<AccountBalance>
     */
    public List<AccountBalance> query(AccountBalance accountBalance) throws Exception;

    /**
     * 查询记录数
     * @param  accountBalance
     * @return List<AccountBalance>
     */
    public Integer queryCount(AccountBalance accountBalance) throws Exception;
}
