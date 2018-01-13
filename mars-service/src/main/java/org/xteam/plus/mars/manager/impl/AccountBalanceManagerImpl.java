package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.AccountBalanceDao;
import org.xteam.plus.mars.domain.AccountBalance;
import org.xteam.plus.mars.manager.AccountBalanceManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ACCOUNT_BALANCE表Manager接口实现类
 */

@Service
public class AccountBalanceManagerImpl implements AccountBalanceManager {
    private static final Log log = LogFactory.getLog(AccountBalanceManagerImpl.class);
    @javax.annotation.Resource
    private AccountBalanceDao accountBalanceDao;


    @Override
    public AccountBalance get(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.get(accountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.insert(accountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<AccountBalance> list) throws Exception {
        return accountBalanceDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.delete(accountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.update(accountBalance);
    }

    @Override
    public List<AccountBalance> query(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.query(accountBalance);
    }

    @Override
    public Integer queryCount(AccountBalance accountBalance) throws Exception {
        return accountBalanceDao.queryCount(accountBalance);
    }

}
