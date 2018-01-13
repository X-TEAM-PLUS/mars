package org.xteam.plus.mars.domain;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * TableName: [T_MARS_QUESTION_INFO]表
 */

public class QuestionInfo implements Serializable {

    /**
     * 开始下标
     */
    private Integer start = 0;

    /**
     * 界限值
     */
    private Integer limit = 20;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 问题ID
     */
    private java.math.BigDecimal questionId;

    /**
     * 标题
     */
    private String questionTitle;

    /**
     * 回复
     */
    private String questionReply;

    /**
     * 创建时间
     */
    private java.util.Date created;


    /**
     * 设置   updated
     *
     * @param updated (更新时间)
     */
    public QuestionInfo setUpdated(java.util.Date updated) {
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
     * 设置   questionId
     *
     * @param questionId (问题ID)
     */
    public QuestionInfo setQuestionId(java.math.BigDecimal questionId) {
        this.questionId = questionId;
        return this;
    }

    /**
     * 获取   questionId (问题ID)
     *
     * @return
     */
    public java.math.BigDecimal getQuestionId() {
        return this.questionId;
    }

    /**
     * 设置   questionTitle
     *
     * @param questionTitle (标题)
     */
    public QuestionInfo setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    /**
     * 获取   questionTitle (标题)
     *
     * @return
     */
    public String getQuestionTitle() {
        return this.questionTitle;
    }

    /**
     * 设置   questionReply
     *
     * @param questionReply (回复)
     */
    public QuestionInfo setQuestionReply(String questionReply) {
        this.questionReply = questionReply;
        return this;
    }

    /**
     * 获取   questionReply (回复)
     *
     * @return
     */
    public String getQuestionReply() {
        return this.questionReply;
    }

    /**
     * 设置   created
     *
     * @param created (创建时间)
     */
    public QuestionInfo setCreated(java.util.Date created) {
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
    public QuestionInfo setStart(Integer start) {
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
    public QuestionInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
