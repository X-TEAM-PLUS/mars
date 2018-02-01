package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.AccountDetailDao;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.manager.AccountDetailManager;
import org.xteam.plus.mars.type.AccountDetailTypeEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_ACCOUNT_DETAIL表Manager接口实现类
 */

@Service
public class AccountDetailManagerImpl implements AccountDetailManager {
    private static final Log log = LogFactory.getLog(AccountDetailManagerImpl.class);
    @javax.annotation.Resource
    private AccountDetailDao accountDetailDao;


    @Override
    public AccountDetail get(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.get(accountDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.insert(accountDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<AccountDetail> list) throws Exception {
        return accountDetailDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.delete(accountDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.update(accountDetail);
    }

    @Override
    public List<AccountDetail> query(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.query(accountDetail);
    }

    @Override
    public Integer queryCount(AccountDetail accountDetail) throws Exception {
        return accountDetailDao.queryCount(accountDetail);
    }

    @Override
    public List<AccountDetail> queryBusinessTypes(List<AccountDetailTypeEnum> accountDetailTypeEnums, BigDecimal userId) throws Exception {
        Integer[] types = new Integer[accountDetailTypeEnums.size()];
        int i = 0;
        for (AccountDetailTypeEnum accountDetailTypeEnum : accountDetailTypeEnums) {
            types[i] = accountDetailTypeEnum.getCode();
            i++;
        }
        return accountDetailDao.queryBusinessTypes(types, userId);
    }

}
