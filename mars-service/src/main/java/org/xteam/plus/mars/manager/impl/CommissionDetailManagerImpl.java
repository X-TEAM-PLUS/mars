package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.CommissionDetailDao;
import org.xteam.plus.mars.domain.CommissionDetail;
import org.xteam.plus.mars.manager.CommissionDetailManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-04-13
 * Time: 16:56
 * 功能:T_MARS_COMMISSION_DETAIL表Manager接口实现类
 */

@Service
public class CommissionDetailManagerImpl implements CommissionDetailManager {
    private static final Log log = LogFactory.getLog(CommissionDetailManagerImpl.class);
    @javax.annotation.Resource
    private CommissionDetailDao commissionDetailDao;


    @Override
    public CommissionDetail get(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.get(commissionDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.insert(commissionDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<CommissionDetail> list) throws Exception {
        return commissionDetailDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.delete(commissionDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.update(commissionDetail);
    }

    @Override
    public List<CommissionDetail> query(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.query(commissionDetail);
    }

    @Override
    public Integer queryCount(CommissionDetail commissionDetail) throws Exception {
        return commissionDetailDao.queryCount(commissionDetail);
    }

}
