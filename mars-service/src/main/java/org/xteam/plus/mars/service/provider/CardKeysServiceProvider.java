package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.CardKeys;
import org.xteam.plus.mars.manager.CardKeysManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-05-05
 * Time: 18:14
 * 功能: T_MARS_CARD_KEYS表 服务提供者
 */
@RestController
@RequestMapping("/mars/cardkeys")
public class CardKeysServiceProvider extends AbstractServiceProvider {

    @Resource
    private CardKeysManager cardkeysManager;

    /**
     * 获取
     *
     * @param cardkeys
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(CardKeys cardkeys) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            CardKeys result = cardkeysManager.get(cardkeys);
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
     * @param cardkeys
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(CardKeys cardkeys) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = cardkeysManager.insert(cardkeys);
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
     * @param list List<CardKeys>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<CardKeys> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = cardkeysManager.batchInsert(list);
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
     * @param cardkeys
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(CardKeys cardkeys) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = cardkeysManager.delete(cardkeys);
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
     * @param cardkeys
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(CardKeys cardkeys) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = cardkeysManager.update(cardkeys);
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
     * @param cardkeys
     * @return List<CardKeys>
     */
    @RequestMapping("/list")
    public JsonResult list(CardKeys cardkeys) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<CardKeys> data = cardkeysManager.query(cardkeys);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", cardkeysManager.queryCount(cardkeys));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
