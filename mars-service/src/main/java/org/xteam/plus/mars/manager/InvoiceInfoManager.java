package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.InvoiceInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能:T_MARS_INVOICE_INFO表Manager接口
 */

public interface InvoiceInfoManager {

    /**
     * 查询
     *
     * @param invoiceInfo
     * @return List<InvoiceInfo>
     */
    public List<InvoiceInfo> query(InvoiceInfo invoiceInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param invoiceInfo
     * @return List<InvoiceInfo>
     */
    public Integer queryCount(InvoiceInfo invoiceInfo) throws Exception;
}
