package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.manager.MessageManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-02-02
 * Time: 19:34
 * 功能: T_MARS_MESSAGE表 服务提供者
 */
@RestController
@RequestMapping("/mars/message")
public class MessageServiceProvider extends AbstractServiceProvider {

    @Resource
    private MessageManager messageManager;

    /**
     * 获取
     *
     * @param message
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(Message message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            Message result = messageManager.get(message);
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
     * @param message
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(Message message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = messageManager.insert(message);
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
     * @param list List<Message>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<Message> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = messageManager.batchInsert(list);
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
     * @param message
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(Message message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = messageManager.delete(message);
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
     * @param message
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(Message message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = messageManager.update(message);
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
     * @param message
     * @return List<Message>
     */
    @RequestMapping("/list")
    public JsonResult list(Message message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<Message> data = messageManager.query(message);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", messageManager.queryCount(message));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
