package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.UserInsurance;
import org.xteam.plus.mars.manager.UserInsuranceManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_USER_INSURANCE表 服务提供者
 */
@RestController
@RequestMapping("/mars/userinsurance")
public class UserInsuranceServiceProvider extends AbstractServiceProvider {

    @Resource
    private UserInsuranceManager userinsuranceManager;

    /**
     * 获取
     *
     * @param userinsurance
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            UserInsurance result = userinsuranceManager.get(userinsurance);
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
     * @param userinsurance
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = userinsuranceManager.insert(userinsurance);
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
     * @param list List<UserInsurance>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<UserInsurance> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = userinsuranceManager.batchInsert(list);
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
     * @param userinsurance
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = userinsuranceManager.delete(userinsurance);
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
     * @param userinsurance
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = userinsuranceManager.update(userinsurance);
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
     * @param userinsurance
     * @return List<UserInsurance>
     */
    @RequestMapping("/list")
    public JsonResult list(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserInsurance> data = userinsuranceManager.query(userinsurance);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userinsuranceManager.queryCount(userinsurance));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 查询
     *
     * @param userinsurance
     * @return List<UserInsurance>
     */
    @RequestMapping("/listForProuct")
    public JsonResult listForProuct(UserInsurance userinsurance) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserInsurance> data = userinsuranceManager.queryForProduct(userinsurance);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userinsuranceManager.queryForProductCount(userinsurance));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
