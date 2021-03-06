package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.InsuranceProduct;
import org.xteam.plus.mars.manager.InsuranceProductManager;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_INSURANCE_PRODUCT表 服务提供者
 */
@RestController
@RequestMapping("/mars/insuranceproduct")
public class InsuranceProductServiceProvider extends AbstractServiceProvider {

    @Resource
    private InsuranceProductManager insuranceproductManager;

    /**
     * 获取
     *
     * @param insuranceproduct
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(InsuranceProduct insuranceproduct) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            InsuranceProduct result = insuranceproductManager.get(insuranceproduct);
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
     * @param insuranceproduct
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(InsuranceProduct insuranceproduct) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            insuranceproduct.setStatus(1);
            insuranceproduct.setCreated(new Date());
            insuranceproduct.setUpdated(new Date());
            //保存
            int rowCount = insuranceproductManager.insert(insuranceproduct);
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
     * @param list List<InsuranceProduct>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<InsuranceProduct> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = insuranceproductManager.batchInsert(list);
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
     * @param insuranceproduct
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(InsuranceProduct insuranceproduct) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = insuranceproductManager.delete(insuranceproduct);
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
     * @param insuranceproduct
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(InsuranceProduct insuranceproduct) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = insuranceproductManager.update(insuranceproduct);
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
     * @param insuranceproduct
     * @return List<InsuranceProduct>
     */
    @RequestMapping("/list")
    public JsonResult list(InsuranceProduct insuranceproduct) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<InsuranceProduct> data = insuranceproductManager.query(insuranceproduct);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", insuranceproductManager.queryCount(insuranceproduct));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
