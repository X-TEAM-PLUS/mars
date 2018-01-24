package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.HealthCheckRecord;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.HealthCheckRecordManager;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.manager.UserInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_USER_HEALTH_CARD表 服务提供者
 */
@RestController
@RequestMapping("/mars/userhealthcard")
public class UserHealthCardServiceProvider extends AbstractServiceProvider {

    @Resource
    private UserHealthCardManager userhealthcardManager;

    @Resource
    private HealthCheckRecordManager healthCheckRecordManager;

    @Resource
    private UserInfoManager userInfoManager;

    /**
     * 获取
     *
     * @param userhealthcard
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            UserHealthCard result = userhealthcardManager.get(userhealthcard);
            if (result == null || result.getActivateUserId() == null) {
                jsonResult.setMessage("此卡没有进行激活，不能进行查询!");
                jsonResult.setSuccess(false);
                return jsonResult;
            }
            UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(result.getActivateUserId()));
            if (userInfo == null) {
                jsonResult.setMessage("激活用户不存在，不能进行查询!");
                jsonResult.setSuccess(false);
                return jsonResult;
            }
            result.setActivateUserInfo(userInfo);
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
     * @param userhealthcard
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = userhealthcardManager.insert(userhealthcard);
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
     * @param list List<UserHealthCard>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<UserHealthCard> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = userhealthcardManager.batchInsert(list);
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
     * @param userhealthcard
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = userhealthcardManager.delete(userhealthcard);
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
     * @param userhealthcard
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = userhealthcardManager.update(userhealthcard);
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
     * @param userhealthcard
     * @return List<UserHealthCard>
     */
    @RequestMapping("/list")
    public JsonResult list(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserHealthCard> data = userhealthcardManager.query(userhealthcard);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userhealthcardManager.queryCount(userhealthcard));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    @RequestMapping("/listActiveUser")
    public JsonResult listActiveUser(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserHealthCard> data = userhealthcardManager.queryForActiveUser(userhealthcard);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", userhealthcardManager.queryForActiveUserCount(userhealthcard));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    @RequestMapping("/getActiveUser")
    public JsonResult getActiveUser(BigDecimal cardNo, int start, int limit) {
        JsonResult jsonResult = new JsonResult();
        try {
            UserHealthCard userHealthCard = userhealthcardManager.get(new UserHealthCard().setCardNo(cardNo));
            HealthCheckRecord healthCheckRecord = new HealthCheckRecord();
            healthCheckRecord.setStart(start);
            healthCheckRecord.setLimit(limit);
            healthCheckRecord.setUserId(userHealthCard.getActivateUserId());
            List<HealthCheckRecord> data = healthCheckRecordManager.query(healthCheckRecord);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", healthCheckRecordManager.queryCount(healthCheckRecord));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    /**
     * 获取
     *
     * @param userhealthcard
     * @return int
     */
    @RequestMapping("/getForUser")
    public JsonResult getForUser(UserHealthCard userhealthcard) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            UserHealthCard result = userhealthcardManager.getForUser(userhealthcard);
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
}
