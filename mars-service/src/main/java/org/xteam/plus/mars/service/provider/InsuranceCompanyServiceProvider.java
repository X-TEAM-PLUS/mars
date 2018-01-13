package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.InsuranceCompany;
import org.xteam.plus.mars.manager.InsuranceCompanyManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_INSURANCE_COMPANY表 服务提供者
 */
@RestController
@RequestMapping("/mars/insurancecompany")
public class InsuranceCompanyServiceProvider extends AbstractServiceProvider {

    @Resource
    private InsuranceCompanyManager insurancecompanyManager;

    /**
     * 获取
     *
     * @param insurancecompany
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(InsuranceCompany insurancecompany) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            InsuranceCompany result = insurancecompanyManager.get(insurancecompany);
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
     * @param insurancecompany
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(InsuranceCompany insurancecompany) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = insurancecompanyManager.insert(insurancecompany);
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
     * @param list List<InsuranceCompany>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<InsuranceCompany> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = insurancecompanyManager.batchInsert(list);
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
     * @param insurancecompany
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(InsuranceCompany insurancecompany) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = insurancecompanyManager.delete(insurancecompany);
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
     * @param insurancecompany
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(InsuranceCompany insurancecompany) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = insurancecompanyManager.update(insurancecompany);
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
     * @param insurancecompany
     * @return List<InsuranceCompany>
     */
    @RequestMapping("/list")
    public JsonResult list(InsuranceCompany insurancecompany) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<InsuranceCompany> data = insurancecompanyManager.query(insurancecompany);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", insurancecompanyManager.queryCount(insurancecompany));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
