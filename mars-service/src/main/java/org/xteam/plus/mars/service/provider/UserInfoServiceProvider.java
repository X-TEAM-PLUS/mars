package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.ApplayTypeEnum;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_USER_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/userinfo")
public class UserInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private UserInfoManager userinfoManager;

    /**
     * 获取
     *
     * @param userinfo
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            UserInfo result = userinfoManager.get(userinfo);
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
     * 获取
     *
     * @param userinfo
     * @return int
     */
    @RequestMapping("/getWorker")
    public JsonResult getWorker(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            UserInfo result = userinfoManager.getWorker(userinfo);
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
     * @param userinfo
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            userinfo.setRegisterTime(new Date());
            userinfo.setCreated(new Date());
            //保存
            int rowCount = userinfoManager.insert(userinfo);
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
     * @param list List<UserInfo>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<UserInfo> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = userinfoManager.batchInsert(list);
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
     * @param userinfo
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = userinfoManager.delete(userinfo);
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
     * @param userinfo
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = userinfoManager.update(userinfo);
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
     * @param userinfo
     * @return List<UserInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(UserInfo userinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserInfo> data = userinfoManager.query(userinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userinfoManager.queryCount(userinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 社工查询
     *
     * @param userInfo
     * @return List<UserInfo>
     */
    @RequestMapping("/socialWorkerList")
    public JsonResult socialWorkerList(UserInfo userInfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            userInfo.setUserLevel(UserLevelEnum.SOCIAL.getCode());
            List<UserInfo> data = userinfoManager.queryWorker(userInfo, ApplayTypeEnum.SOCIAL.getCode());
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userinfoManager.queryWorkerCount(userInfo, ApplayTypeEnum.SOCIAL.getCode()));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
