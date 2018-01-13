package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_CITY_INFO]表
 */

public class CityInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 编号
     */
    private java.math.BigDecimal code;

    /**
     * 名称
     */
    private String name;

    /**
     * 父编号
     */
    private java.math.BigDecimal parentCode;

    /**
     * 类型
     */
    private Integer areaType;

    /**
     * 排序
     */
    private Integer areaOrder;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;


    /**
     * 设置   code
     *
     * @param code (编号)
     */
    public CityInfo setCode(java.math.BigDecimal code) {
        this.code = code;
        return this;
    }

    /**
     * 获取   code (编号)
     *
     * @return
     */
    public java.math.BigDecimal getCode() {
        return this.code;
    }

    /**
     * 设置   name
     *
     * @param name (名称)
     */
    public CityInfo setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取   name (名称)
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置   parentCode
     *
     * @param parentCode (父编号)
     */
    public CityInfo setParentCode(java.math.BigDecimal parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    /**
     * 获取   parentCode (父编号)
     *
     * @return
     */
    public java.math.BigDecimal getParentCode() {
        return this.parentCode;
    }

    /**
     * 设置   areaType
     *
     * @param areaType (类型)
     */
    public CityInfo setAreaType(Integer areaType) {
        this.areaType = areaType;
        return this;
    }

    /**
     * 获取   areaType (类型)
     *
     * @return
     */
    public Integer getAreaType() {
        return this.areaType;
    }

    /**
     * 设置   areaOrder
     *
     * @param areaOrder (排序)
     */
    public CityInfo setAreaOrder(Integer areaOrder) {
        this.areaOrder = areaOrder;
        return this;
    }

    /**
     * 获取   areaOrder (排序)
     *
     * @return
     */
    public Integer getAreaOrder() {
        return this.areaOrder;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public CityInfo setCreated(java.util.Date created) {
        this.created = created;
        return this;
    }

    /**
     * 获取   created (创建时间)
     *
     * @return
     */
    public java.util.Date getCreated() {
        return this.created;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public CityInfo setUpdated(java.util.Date updated) {
        this.updated = updated;
        return this;
    }

    /**
     * 获取   updated (更新时间)
     *
     * @return
     */
    public java.util.Date getUpdated() {
        return this.updated;
    }


    /**
     * 获取开始下标
     *
     * @return
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置开始下标
     *
     * @param start
     */
    public CityInfo setStart(Integer start) {
        this.start = start;
        return this;
    }

    /**
     * 获取界限值
     *
     * @return
     */
    public Integer getLimit() {
        return limit;
    }


    /**
     * 设置界限值
     *
     * @param limit
     */
    public CityInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
