package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.LocalCouncilMember;
import org.xteam.plus.mars.manager.LocalCouncilMemberManager;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_LOCAL_COUNCIL_MEMBER表 服务提供者
 */
@RestController
@RequestMapping("/mars/localcouncilmember")
public class LocalCouncilMemberServiceProvider extends AbstractServiceProvider {

    @Resource
    private LocalCouncilMemberManager localcouncilmemberManager;

    /**
     * 获取
     *
     * @param localcouncilmember
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(LocalCouncilMember localcouncilmember) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            LocalCouncilMember result = localcouncilmemberManager.get(localcouncilmember);
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
     * @param localcouncilmember
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(LocalCouncilMember localcouncilmember) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = localcouncilmemberManager.insert(localcouncilmember);
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
     * @param list List<LocalCouncilMember>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<LocalCouncilMember> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = localcouncilmemberManager.batchInsert(list);
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
     * @param localcouncilmember
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(LocalCouncilMember localcouncilmember) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = localcouncilmemberManager.delete(localcouncilmember);
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
     * @param localcouncilmember
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(LocalCouncilMember localcouncilmember) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = localcouncilmemberManager.update(localcouncilmember);
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
     * @param localcouncilmember
     * @return List<LocalCouncilMember>
     */
    @RequestMapping("/list")
    public JsonResult list(LocalCouncilMember localcouncilmember) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<LocalCouncilMember> data = localcouncilmemberManager.query(localcouncilmember);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", localcouncilmemberManager.queryCount(localcouncilmember));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
