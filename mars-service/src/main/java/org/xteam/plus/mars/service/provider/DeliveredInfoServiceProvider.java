package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.DeliveredInfo;
import org.xteam.plus.mars.manager.DeliveredInfoManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_DELIVERED_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/deliveredinfo")
public class DeliveredInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private DeliveredInfoManager deliveredinfoManager;

    /**
     * 获取
     *
     * @param deliveredinfo
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(DeliveredInfo deliveredinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            DeliveredInfo result = deliveredinfoManager.get(deliveredinfo);
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
     * @param deliveredinfo
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(DeliveredInfo deliveredinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = deliveredinfoManager.insert(deliveredinfo);
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
     * @param list List<DeliveredInfo>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<DeliveredInfo> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = deliveredinfoManager.batchInsert(list);
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
     * @param deliveredinfo
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(DeliveredInfo deliveredinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = deliveredinfoManager.delete(deliveredinfo);
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
     * @param deliveredinfo
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(DeliveredInfo deliveredinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = deliveredinfoManager.update(deliveredinfo);
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
     * @param deliveredinfo
     * @return List<DeliveredInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(DeliveredInfo deliveredinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<DeliveredInfo> data = deliveredinfoManager.query(deliveredinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", deliveredinfoManager.queryCount(deliveredinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
