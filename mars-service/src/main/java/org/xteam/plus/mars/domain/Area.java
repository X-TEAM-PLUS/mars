package org.xteam.plus.mars.domain;


import java.io.Serializable;

/**
 * Created by codegeneratorplugin
 * User: yankun-develop@outlook.com
 * Date: 2015年09月23日
 * Time: 15:30:49
 * TableName:T_MANAGE_AREA
 */

public class Area implements Serializable {
    private String name;
    private Integer code;

    /**
     * 0国家，1省份 2城市 3县级
     */
    private Integer areaType;
    private Integer parentCode;
    private java.util.Date updated;
    private Integer areaOrder;
    private String operator;
    private java.util.Date created;

    private String codeValue;
    private String codeName;
    /**
     * ０：当前有效数据１：历史数据 ２：已册除
     */
    private String dataType;

    /**
     * 获取 name
     * return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 parentCode
     * return
     */
    public Integer getParentCode() {
        return this.parentCode;
    }

    /**
     * 设置 parentCode
     *
     * @param parentCode
     */
    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取 updated
     * return
     */
    public java.util.Date getUpdated() {
        return this.updated;
    }

    /**
     * 设置 updated
     *
     * @param updated
     */
    public void setUpdated(java.util.Date updated) {
        this.updated = updated;
    }

    /**
     * 获取 areaOrder
     * return
     */
    public Integer getAreaOrder() {
        return this.areaOrder;
    }

    /**
     * 设置 areaOrder
     *
     * @param areaOrder
     */
    public void setAreaOrder(Integer areaOrder) {
        this.areaOrder = areaOrder;
    }

    /**
     * 获取 operator
     * return
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * 设置 operator
     *
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取 created
     * return
     */
    public java.util.Date getCreated() {
        return this.created;
    }

    /**
     * 设置 created
     *
     * @param created
     */
    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    /**
     * 获取 dataType
     * return
     */
    public String getDataType() {
        return this.dataType;
    }

    /**
     * 设置 dataType
     *
     * @param dataType ０：当前有效数据１：历史数据 ２：已册除
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取 code
     * return
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * 设置 code
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
