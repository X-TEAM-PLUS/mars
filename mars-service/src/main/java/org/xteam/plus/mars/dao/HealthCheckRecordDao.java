package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.HealthCheckRecord;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_HEALTH_CHECK_RECORD表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_HEALTH_CHECK_RECORD]表Mapper接口
 */

public interface HealthCheckRecordDao {

    /**
     * 获取
     * @param healthCheckRecord
     * @return int
     */
    public HealthCheckRecord get(HealthCheckRecord healthCheckRecord) throws Exception;

    /**
     * 新增
     * @param healthCheckRecord
     * @return int 记录数
     */
    public int insert(HealthCheckRecord healthCheckRecord) throws Exception;

    /**
     * 批量新增
     * @param list   List<HealthCheckRecord>
     * @return int  记录数
     */
    public int batchInsert(List<HealthCheckRecord> list) throws Exception;

    /**
     * 删除
     * @param healthCheckRecord
     * @return int
     */
    public int delete(HealthCheckRecord healthCheckRecord) throws Exception;

    /**
     * 更新
     * @param healthCheckRecord
     * @return int 记录数
     */
    public int update(HealthCheckRecord healthCheckRecord) throws Exception;

    /**
     * 查询
     * @param healthCheckRecord
     * @return List<HealthCheckRecord>
     */
    public List<HealthCheckRecord> query(HealthCheckRecord healthCheckRecord) throws Exception;

    /**
     * 查询记录数
     * @param  healthCheckRecord
     * @return List<HealthCheckRecord>
     */
    public Integer queryCount(HealthCheckRecord healthCheckRecord) throws Exception;
}
