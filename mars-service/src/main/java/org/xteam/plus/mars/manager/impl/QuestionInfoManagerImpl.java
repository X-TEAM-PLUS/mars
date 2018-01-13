package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.QuestionInfoDao;
import org.xteam.plus.mars.domain.QuestionInfo;
import org.xteam.plus.mars.manager.QuestionInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_QUESTION_INFO表Manager接口实现类
 */

@Service
public class QuestionInfoManagerImpl implements QuestionInfoManager {
    private static final Log log = LogFactory.getLog(QuestionInfoManagerImpl.class);
    @javax.annotation.Resource
    private QuestionInfoDao questionInfoDao;


    @Override
    public QuestionInfo get(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.get(questionInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.insert(questionInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<QuestionInfo> list) throws Exception {
        return questionInfoDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.delete(questionInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.update(questionInfo);
    }

    @Override
    public List<QuestionInfo> query(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.query(questionInfo);
    }

    @Override
    public Integer queryCount(QuestionInfo questionInfo) throws Exception {
        return questionInfoDao.queryCount(questionInfo);
    }

}
