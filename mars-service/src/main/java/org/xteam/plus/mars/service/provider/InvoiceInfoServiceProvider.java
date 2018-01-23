package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.InvoiceInfo;
import org.xteam.plus.mars.manager.InvoiceInfoManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能: T_MARS_INVOICE_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/invoiceinfo")
public class InvoiceInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private InvoiceInfoManager invoiceinfoManager;

    /**
     * 查询
     *
     * @param invoiceinfo
     * @return List<InvoiceInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(InvoiceInfo invoiceinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<InvoiceInfo> data = invoiceinfoManager.query(invoiceinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", invoiceinfoManager.queryCount(invoiceinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
