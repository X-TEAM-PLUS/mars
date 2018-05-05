package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-05-05
 * Time: 18:13
 * TableName: [T_MARS_CARD_KEYS]表
 */

public class CardKeys implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 卡密
     */
    private String cardKeys;

    /**
     * 激活日期
     */
    private Date activateTime;

    /**
     * 流水号
     */
    private BigDecimal id;


    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public CardKeys setCreated(Date created) {
        this.created = created;
        return this;
    }

    /**
     * 获取   created (创建时间)
     *
     * @return
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public CardKeys setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    /**
     * 获取   updated (更新时间)
     *
     * @return
     */
    public Date getUpdated() {
        return this.updated;
    }

    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public CardKeys setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取   status (状态)
     *
     * @return
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置   cardKeys
     *
     * @param cardKeys (卡密)
     */
    public CardKeys setCardKeys(String cardKeys) {
        this.cardKeys = cardKeys;
        return this;
    }

    /**
     * 获取   cardKeys (卡密)
     *
     * @return
     */
    public String getCardKeys() {
        return this.cardKeys;
    }

    /**
     * 设置   activateTime
     *
     * @param activateTime (激活日期)
     */
    public CardKeys setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
        return this;
    }

    /**
     * 获取   activateTime (激活日期)
     *
     * @return
     */
    public Date getActivateTime() {
        return this.activateTime;
    }

    /**
     * 设置   id
     *
     * @param id (流水号)
     */
    public CardKeys setId(BigDecimal id) {
        this.id = id;
        return this;
    }

    /**
     * 获取   id (流水号)
     *
     * @return
     */
    public BigDecimal getId() {
        return this.id;
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
    public CardKeys setStart(Integer start) {
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
    public CardKeys setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
