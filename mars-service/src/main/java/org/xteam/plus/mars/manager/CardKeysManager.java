package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.CardKeys;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-05-05
 * Time: 18:14
 * 功能:T_MARS_CARD_KEYS表Manager接口
 */

public interface CardKeysManager {
    /**
     * 批量操作的记录数
     */
    Integer BATCH_SIZE = 500;

    /**
     * 获取
     *
     * @param cardKeys
     * @return int
     */
    public CardKeys get(CardKeys cardKeys) throws Exception;

    /**
     * 新增
     *
     * @param cardKeys
     * @return int 记录数
     */
    public int insert(CardKeys cardKeys) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<CardKeys>
     * @return int  记录数
     */
    public int batchInsert(List<CardKeys> list) throws Exception;

    /**
     * 删除
     *
     * @param cardKeys
     * @return int
     */
    public int delete(CardKeys cardKeys) throws Exception;

    /**
     * 更新
     *
     * @param cardKeys
     * @return int 记录数
     */
    public int update(CardKeys cardKeys) throws Exception;

    /**
     * 查询
     *
     * @param cardKeys
     * @return List<CardKeys>
     */
    public List<CardKeys> query(CardKeys cardKeys) throws Exception;

    /**
     * 查询记录数
     *
     * @param cardKeys
     * @return List<CardKeys>
     */
    public Integer queryCount(CardKeys cardKeys) throws Exception;

    /**
     * 批量生成卡密
     * @param quantity  数量
     * @throws Exception
     */
    public void batchGenerate(Integer quantity)throws Exception;
}
