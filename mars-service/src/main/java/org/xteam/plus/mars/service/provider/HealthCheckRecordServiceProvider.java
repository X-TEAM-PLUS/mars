package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_HEALTH_CHECK_RECORD表 服务提供者
 */
@RestController
@RequestMapping("/mars/healthcheckrecord")
public class HealthCheckRecordServiceProvider extends AbstractServiceProvider {

    @Resource
    private HealthCheckRecordManager healthcheckrecordManager;

    /**
     * 获取
     *
     * @param healthcheckrecord
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(HealthCheckRecord healthcheckrecord) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            HealthCheckRecord result = healthcheckrecordManager.get(healthcheckrecord);
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
     * @param healthcheckrecord
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(HealthCheckRecord healthcheckrecord) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = healthcheckrecordManager.insert(healthcheckrecord);
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
     * @param list List<HealthCheckRecord>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<HealthCheckRecord> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = healthcheckrecordManager.batchInsert(list);
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
     * @param healthcheckrecord
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(HealthCheckRecord healthcheckrecord) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = healthcheckrecordManager.delete(healthcheckrecord);
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
     * @param healthcheckrecord
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(HealthCheckRecord healthcheckrecord) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = healthcheckrecordManager.update(healthcheckrecord);
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
     * @param healthcheckrecord
     * @return List<HealthCheckRecord>
     */
    @RequestMapping("/list")
    public JsonResult list(HealthCheckRecord healthcheckrecord) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<HealthCheckRecord> data = healthcheckrecordManager.query(healthcheckrecord);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", healthcheckrecordManager.queryCount(healthcheckrecord));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
