package org.xteam.plus.mars.dao;

import org.xteam.plus.mars.domain.InvoiceInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能:T_MARS_INVOICE_INFO表Mapper接口
 */

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能: [T_MARS_INVOICE_INFO]表Mapper接口
 */

public interface InvoiceInfoDao {
    /**
     * 查询
     * @param invoiceInfo
     * @return List<InvoiceInfo>
     */
    public List<InvoiceInfo> query(InvoiceInfo invoiceInfo) throws Exception;

    /**
     * 查询记录数
     * @param  invoiceInfo
     * @return List<InvoiceInfo>
     */
    public Integer queryCount(InvoiceInfo invoiceInfo) throws Exception;
}
