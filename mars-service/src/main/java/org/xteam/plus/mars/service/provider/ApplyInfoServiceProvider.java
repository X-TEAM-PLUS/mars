package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.ApplyInfo;
import org.xteam.plus.mars.manager.ApplyInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_APPLY_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/applyinfo")
public class ApplyInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private ApplyInfoManager applyinfoManager;

    /**
     * 获取
     *
     * @param applyinfo
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            ApplyInfo result = applyinfoManager.get(applyinfo);
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
     * @param applyinfo
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = applyinfoManager.insert(applyinfo);
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
     * @param list List<ApplyInfo>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<ApplyInfo> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = applyinfoManager.batchInsert(list);
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
     * @param applyinfo
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = applyinfoManager.delete(applyinfo);
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
     * @param applyinfo
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = applyinfoManager.update(applyinfo);
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
     * @param applyinfo
     * @return List<ApplyInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<ApplyInfo> data = applyinfoManager.queryForUserInfo(applyinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", applyinfoManager.queryForUserInfoCount(applyinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 审批通过
     *
     * @param applyinfo
     * @return int 记录数
     */
    @RequestMapping("/auditpass")
    public JsonResult auditpass(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            boolean isSuccess = applyinfoManager.auditpass(applyinfo.getApplyId());
            if (isSuccess) {
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
     * 审批驳回
     *
     * @param applyinfo
     * @return int 记录数
     */
    @RequestMapping("/dismissal")
    public JsonResult dismissal(ApplyInfo applyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            boolean isSuccess = applyinfoManager.dismissal(applyinfo.getApplyId());
            if (isSuccess) {
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
     * 绿色通道直接升级
     * @param applyId       申请id
     * @param cardNum       购买卡数
     * @return
     * @throws Exception
     */
    @RequestMapping("/greenChannel")
    public JsonResult greenChannel(BigDecimal applyId, Integer cardNum) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            boolean isSuccess = applyinfoManager.greenChannel(applyId, cardNum);
            if (isSuccess) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("更新数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("更新数据失败");
            }
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError(e.getMessage(), e);
            jsonResult.setMessage(e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
