package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.GlobalConf;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_GLOBAL_CONF表Manager接口
 */

public interface GlobalConfManager {

    /**
     * 获取
     *
     * @param globalConf
     * @return int
     */
    public GlobalConf get(GlobalConf globalConf) throws Exception;

    /**
     * 新增
     *
     * @param globalConf
     * @return int 记录数
     */
    public int insert(GlobalConf globalConf) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<GlobalConf>
     * @return int  记录数
     */
    public int batchInsert(List<GlobalConf> list) throws Exception;

    /**
     * 删除
     *
     * @param globalConf
     * @return int
     */
    public int delete(GlobalConf globalConf) throws Exception;

    /**
     * 更新
     *
     * @param globalConf
     * @return int 记录数
     */
    public int update(GlobalConf globalConf) throws Exception;

    /**
     * 查询
     *
     * @param globalConf
     * @return List<GlobalConf>
     */
    public List<GlobalConf> query(GlobalConf globalConf) throws Exception;

    /**
     * 查询记录数
     *
     * @param globalConf
     * @return List<GlobalConf>
     */
    public Integer queryCount(GlobalConf globalConf) throws Exception;
}
