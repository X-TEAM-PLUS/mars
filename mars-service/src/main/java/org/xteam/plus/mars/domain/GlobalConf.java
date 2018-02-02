package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_GLOBAL_CONF]表
 */

public class GlobalConf implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * ID
     */
    private java.math.BigDecimal id;

    /**
     * 参数名
     */
    private String parameterKey;

    /**
     * 参数值
     */
    private String parameterValue;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 参数类型
     */
    private BigDecimal parameterType;


    /**
     * 设置   id
     *
     * @param id (ID)
     */
    public GlobalConf setId(java.math.BigDecimal id) {
        this.id = id;
        return this;
    }

    /**
     * 获取   id (ID)
     *
     * @return
     */
    public java.math.BigDecimal getId() {
        return this.id;
    }

    /**
     * 设置   parameterKey
     *
     * @param parameterKey (参数名)
     */
    public GlobalConf setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
        return this;
    }

    /**
     * 获取   parameterKey (参数名)
     *
     * @return
     */
    public String getParameterKey() {
        return this.parameterKey;
    }

    /**
     * 设置   parameterValue
     *
     * @param parameterValue (参数值)
     */
    public GlobalConf setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
        return this;
    }

    /**
     * 获取   parameterValue (参数值)
     *
     * @return
     */
    public String getParameterValue() {
        return this.parameterValue;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public GlobalConf setCreated(java.util.Date created) {
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
    public GlobalConf setUpdated(java.util.Date updated) {
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
    public GlobalConf setStart(Integer start) {
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
    public GlobalConf setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public BigDecimal getParameterType() {
        return parameterType;
    }

    public GlobalConf setParameterType(BigDecimal parameterType) {
        this.parameterType = parameterType;
        return this;
    }
}
