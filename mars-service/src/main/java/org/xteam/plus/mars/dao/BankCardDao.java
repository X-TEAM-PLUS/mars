package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.BankCard;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_BANK_CARD表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_BANK_CARD]表Mapper接口
 */

public interface BankCardDao {

    /**
     * 获取
     * @param bankCard
     * @return int
     */
    public BankCard get(BankCard bankCard) throws Exception;

    /**
     * 新增
     * @param bankCard
     * @return int 记录数
     */
    public int insert(BankCard bankCard) throws Exception;

    /**
     * 批量新增
     * @param list   List<BankCard>
     * @return int  记录数
     */
    public int batchInsert(List<BankCard> list) throws Exception;

    /**
     * 删除
     * @param bankCard
     * @return int
     */
    public int delete(BankCard bankCard) throws Exception;

    /**
     * 更新
     * @param bankCard
     * @return int 记录数
     */
    public int update(BankCard bankCard) throws Exception;

    /**
     * 查询
     * @param bankCard
     * @return List<BankCard>
     */
    public List<BankCard> query(BankCard bankCard) throws Exception;

    /**
     * 查询记录数
     * @param  bankCard
     * @return List<BankCard>
     */
    public Integer queryCount(BankCard bankCard) throws Exception;
}
