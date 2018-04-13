package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.CommissionDetail;
import org.xteam.plus.mars.manager.CommissionDetailManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-04-13
 * Time: 16:56
 * 功能: T_MARS_COMMISSION_DETAIL表 服务提供者
 */
@RestController
@RequestMapping("/mars/commissiondetail")
public class CommissionDetailServiceProvider extends AbstractServiceProvider {

    @Resource
    private CommissionDetailManager commissiondetailManager;

    /**
     * 获取
     *
     * @param commissiondetail
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(CommissionDetail commissiondetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            CommissionDetail result = commissiondetailManager.get(commissiondetail);
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
     * @param commissiondetail
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(CommissionDetail commissiondetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = commissiondetailManager.insert(commissiondetail);
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
     * @param list List<CommissionDetail>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<CommissionDetail> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = commissiondetailManager.batchInsert(list);
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
     * @param commissiondetail
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(CommissionDetail commissiondetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = commissiondetailManager.delete(commissiondetail);
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
     * @param commissiondetail
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(CommissionDetail commissiondetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = commissiondetailManager.update(commissiondetail);
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
     * @param commissiondetail
     * @return List<CommissionDetail>
     */
    @RequestMapping("/list")
    public JsonResult list(CommissionDetail commissiondetail) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<CommissionDetail> data = commissiondetailManager.query(commissiondetail);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", commissiondetailManager.queryCount(commissiondetail));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
