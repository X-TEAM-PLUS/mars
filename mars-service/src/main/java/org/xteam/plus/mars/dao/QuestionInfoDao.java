package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.QuestionInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_QUESTION_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: [T_MARS_QUESTION_INFO]表Mapper接口
 */

public interface QuestionInfoDao {

    /**
     * 获取
     * @param questionInfo
     * @return int
     */
    public QuestionInfo get(QuestionInfo questionInfo) throws Exception;

    /**
     * 新增
     * @param questionInfo
     * @return int 记录数
     */
    public int insert(QuestionInfo questionInfo) throws Exception;

    /**
     * 批量新增
     * @param list   List<QuestionInfo>
     * @return int  记录数
     */
    public int batchInsert(List<QuestionInfo> list) throws Exception;

    /**
     * 删除
     * @param questionInfo
     * @return int
     */
    public int delete(QuestionInfo questionInfo) throws Exception;

    /**
     * 更新
     * @param questionInfo
     * @return int 记录数
     */
    public int update(QuestionInfo questionInfo) throws Exception;

    /**
     * 查询
     * @param questionInfo
     * @return List<QuestionInfo>
     */
    public List<QuestionInfo> query(QuestionInfo questionInfo) throws Exception;

    /**
     * 查询记录数
     * @param  questionInfo
     * @return List<QuestionInfo>
     */
    public Integer queryCount(QuestionInfo questionInfo) throws Exception;
}
