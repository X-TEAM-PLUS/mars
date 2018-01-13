package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.AccountDetail;
import org.xteam.plus.mars.manager.AccountDetailManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_ACCOUNT_DETAIL表 服务提供者
 */
@RestController
@RequestMapping("/mars/accountdetail")
public class AccountDetailServiceProvider extends AbstractServiceProvider {

    @Resource
    private AccountDetailManager accountdetailManager;

    /**
     * 获取
     *
     * @param accountdetail
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(AccountDetail accountdetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            AccountDetail result = accountdetailManager.get(accountdetail);
            if (result != null) {
                jsonResult.setData(result);
                jsonResult.setSuccess(true);
            } else {
                jsonResult.setMessage("获取数据失败");
                jsonResult.setSuccess(false);
            }
        } catch (Exception e) {
            logError("获取数据异常", e);
            jsonResult.setMessage("获取数据异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 新增
     *
     * @param accountdetail
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(AccountDetail accountdetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = accountdetailManager.insert(accountdetail);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("保存数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("保存数据失败");
            }
        } catch (Exception e) {
            logError("提交数据异常", e);
            jsonResult.setMessage("提交数据异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 批量新增
     *
     * @param list List<AccountDetail>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<AccountDetail> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = accountdetailManager.batchInsert(list);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("保存数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("保存数据失败");
            }
        } catch (Exception e) {
            logError("提交数据异常", e);
            jsonResult.setMessage("提交数据异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 删除
     *
     * @param accountdetail
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(AccountDetail accountdetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = accountdetailManager.delete(accountdetail);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("删除数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("删除数据失败");
            }
        } catch (Exception e) {
            logError("删除数据异常", e);
            jsonResult.setMessage("删除数据异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 更新
     *
     * @param accountdetail
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(AccountDetail accountdetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = accountdetailManager.update(accountdetail);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("更新数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("更新数据失败");
            }
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("更新数据异常", e);
            jsonResult.setMessage("更新数据异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 查询
     *
     * @param accountdetail
     * @return List<AccountDetail>
     */
    @RequestMapping("/list")
    public JsonResult list(AccountDetail accountdetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<AccountDetail> data = accountdetailManager.query(accountdetail);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", accountdetailManager.queryCount(accountdetail));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
