package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.WithdrawRecordDao;
import org.xteam.plus.mars.domain.WithdrawRecord;
import org.xteam.plus.mars.manager.WithdrawRecordManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_WITHDRAW_RECORD表Manager接口实现类
 */

@Service
public class WithdrawRecordManagerImpl implements WithdrawRecordManager {
    private static final Log log = LogFactory.getLog(WithdrawRecordManagerImpl.class);
    @javax.annotation.Resource
    private WithdrawRecordDao withdrawRecordDao;


    @Override
    public WithdrawRecord get(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.get(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.insert(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<WithdrawRecord> list) throws Exception {
        return withdrawRecordDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.delete(withdrawRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.update(withdrawRecord);
    }

    @Override
    public List<WithdrawRecord> query(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.query(withdrawRecord);
    }

    @Override
    public Integer queryCount(WithdrawRecord withdrawRecord) throws Exception {
        return withdrawRecordDao.queryCount(withdrawRecord);
    }

}
