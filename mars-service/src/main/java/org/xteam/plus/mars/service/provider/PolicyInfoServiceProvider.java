package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.PolicyInfo;
import org.xteam.plus.mars.manager.PolicyInfoManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能: T_MARS_POLICY_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/policyinfo")
public class PolicyInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private PolicyInfoManager policyinfoManager;

    /**
     * 查询
     *
     * @param policyinfo
     * @return List<PolicyInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(PolicyInfo policyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<PolicyInfo> data = policyinfoManager.query(policyinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", policyinfoManager.queryCount(policyinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
