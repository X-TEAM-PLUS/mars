package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.CityInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_CITY_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_CITY_INFO]表Mapper接口
 */

public interface CityInfoDao {

    /**
     * 获取
     * @param cityInfo
     * @return int
     */
    public CityInfo get(CityInfo cityInfo) throws Exception;

    /**
     * 新增
     * @param cityInfo
     * @return int 记录数
     */
    public int insert(CityInfo cityInfo) throws Exception;

    /**
     * 批量新增
     * @param list   List<CityInfo>
     * @return int  记录数
     */
    public int batchInsert(List<CityInfo> list) throws Exception;

    /**
     * 删除
     * @param cityInfo
     * @return int
     */
    public int delete(CityInfo cityInfo) throws Exception;

    /**
     * 更新
     * @param cityInfo
     * @return int 记录数
     */
    public int update(CityInfo cityInfo) throws Exception;

    /**
     * 查询
     * @param cityInfo
     * @return List<CityInfo>
     */
    public List<CityInfo> query(CityInfo cityInfo) throws Exception;

    /**
     * 查询记录数
     * @param  cityInfo
     * @return List<CityInfo>
     */
    public Integer queryCount(CityInfo cityInfo) throws Exception;
}
