package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.BankCardDao;
import org.xteam.plus.mars.domain.BankCard;
import org.xteam.plus.mars.manager.BankCardManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_BANK_CARD表Manager接口实现类
 */

@Service
public class BankCardManagerImpl implements BankCardManager {
    private static final Log log = LogFactory.getLog(BankCardManagerImpl.class);
    @javax.annotation.Resource
    private BankCardDao bankCardDao;


    @Override
    public BankCard get(BankCard bankCard) throws Exception {
        return bankCardDao.get(bankCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BankCard bankCard) throws Exception {
        return bankCardDao.insert(bankCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<BankCard> list) throws Exception {
        return bankCardDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(BankCard bankCard) throws Exception {
        return bankCardDao.delete(bankCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(BankCard bankCard) throws Exception {
        return bankCardDao.update(bankCard);
    }

    @Override
    public List<BankCard> query(BankCard bankCard) throws Exception {
        return bankCardDao.query(bankCard);
    }

    @Override
    public Integer queryCount(BankCard bankCard) throws Exception {
        return bankCardDao.queryCount(bankCard);
    }

}
