package org.xteam.plus.mars.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-02-02
 * Time: 19:34
 * TableName: [T_MARS_MESSAGE]表
 */

public class Message implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 消息ID
     */
    private BigDecimal messageId;

    /**
     * 用户ID
     */
    private BigDecimal userId;

    /**
     * 标题
     */
    private String messageTitle;

    /**
     * 内容
     */
    private String messageContent;


    /**
     * 设置   status
     *
     * @param status (状态)
     */
    public Message setStatus(Integer status) {
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
     * 设置   created
     *
     * @param created (创建时间)
     */
    public Message setCreated(Date created) {
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
    public Message setUpdated(Date updated) {
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
     * 设置   messageId
     *
     * @param messageId (消息ID)
     */
    public Message setMessageId(BigDecimal messageId) {
        this.messageId = messageId;
        return this;
    }

    /**
     * 获取   messageId (消息ID)
     *
     * @return
     */
    public BigDecimal getMessageId() {
        return this.messageId;
    }

    /**
     * 设置   userId
     *
     * @param userId (用户ID)
     */
    public Message setUserId(BigDecimal userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取   userId (用户ID)
     *
     * @return
     */
    public BigDecimal getUserId() {
        return this.userId;
    }

    /**
     * 设置   messageTitle
     *
     * @param messageTitle (标题)
     */
    public Message setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
        return this;
    }

    /**
     * 获取   messageTitle (标题)
     *
     * @return
     */
    public String getMessageTitle() {
        return this.messageTitle;
    }

    /**
     * 设置   messageContent
     *
     * @param messageContent (内容)
     */
    public Message setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    /**
     * 获取   messageContent (内容)
     *
     * @return
     */
    public String getMessageContent() {
        return this.messageContent;
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
    public Message setStart(Integer start) {
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
    public Message setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
