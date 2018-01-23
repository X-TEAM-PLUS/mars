package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.xteam.plus.mars.dao.InvoiceInfoDao;
import org.xteam.plus.mars.domain.InvoiceInfo;
import org.xteam.plus.mars.manager.InvoiceInfoManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能:T_MARS_INVOICE_INFO表Manager接口实现类
 */

@Service
public class InvoiceInfoManagerImpl implements InvoiceInfoManager {
    private static final Log log = LogFactory.getLog(InvoiceInfoManagerImpl.class);
    @javax.annotation.Resource
    private InvoiceInfoDao invoiceInfoDao;

    @Override
    public List<InvoiceInfo> query(InvoiceInfo invoiceInfo) throws Exception {
        return invoiceInfoDao.query(invoiceInfo);
    }

    @Override
    public Integer queryCount(InvoiceInfo invoiceInfo) throws Exception {
        return invoiceInfoDao.queryCount(invoiceInfo);
    }

}
