package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.CardKeysDao;
import org.xteam.plus.mars.domain.CardKeys;
import org.xteam.plus.mars.manager.CardKeysManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-05-05
 * Time: 18:14
 * 功能:T_MARS_CARD_KEYS表Manager接口实现类
 */

@Service
public class CardKeysManagerImpl implements CardKeysManager {
    private static final Log log = LogFactory.getLog(CardKeysManagerImpl.class);
    @javax.annotation.Resource
    private CardKeysDao cardKeysDao;


    @Override
    public CardKeys get(CardKeys cardKeys) throws Exception {
        return cardKeysDao.get(cardKeys);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CardKeys cardKeys) throws Exception {
        return cardKeysDao.insert(cardKeys);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<CardKeys> list) throws Exception {
        return cardKeysDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(CardKeys cardKeys) throws Exception {
        return cardKeysDao.delete(cardKeys);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CardKeys cardKeys) throws Exception {
        return cardKeysDao.update(cardKeys);
    }

    @Override
    public List<CardKeys> query(CardKeys cardKeys) throws Exception {
        return cardKeysDao.query(cardKeys);
    }

    @Override
    public Integer queryCount(CardKeys cardKeys) throws Exception {
        return cardKeysDao.queryCount(cardKeys);
    }

}
