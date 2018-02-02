package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.Message;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-02-02
 * Time: 19:34
 * 功能:T_MARS_MESSAGE表Manager接口
 */

public interface MessageManager {

    /**
     * 获取
     *
     * @param message
     * @return int
     */
    public Message get(Message message) throws Exception;

    /**
     * 新增
     *
     * @param message
     * @return int 记录数
     */
    public int insert(Message message) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<Message>
     * @return int  记录数
     */
    public int batchInsert(List<Message> list) throws Exception;

    /**
     * 删除
     *
     * @param message
     * @return int
     */
    public int delete(Message message) throws Exception;

    /**
     * 更新
     *
     * @param message
     * @return int 记录数
     */
    public int update(Message message) throws Exception;

    /**
     * 查询
     *
     * @param message
     * @return List<Message>
     */
    public List<Message> query(Message message) throws Exception;

    /**
     * 查询记录数
     *
     * @param message
     * @return List<Message>
     */
    public Integer queryCount(Message message) throws Exception;
}
