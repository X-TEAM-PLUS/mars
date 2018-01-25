package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.manager.OrdersManager;
import org.xteam.plus.mars.manager.UserInfoManager;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_USER_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/dashboard")
public class StatisticsIndexServiceProvider extends AbstractServiceProvider {

    @Resource
    private UserInfoManager userinfoManager;

    @Resource
    private OrdersManager ordersManager;

    /**
     * 获取用户统计数据
     *
     * @param userLevelEnum 用户级别
     * @param isNowDate     是否查询当天
     * @return int
     */
    @RequestMapping("/getUserTotal")
    public JsonResult getUserTotal(UserLevelEnum userLevelEnum, Integer isNowDate) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            Integer result = 0;
            if (isNowDate == null) {
                //获取记录
                result = userinfoManager.queryUserTotalCount(userLevelEnum, null);
            } else {
                result = userinfoManager.queryUserTotalCount(userLevelEnum, new Date());
            }
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
     * 获取订单金额统计数据
     *
     * @param isNowDate     是否查询当天
     * @return int
     */
    @RequestMapping("/getOrderTotal")
    public JsonResult getOrderTotal(Integer isNowDate) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            Integer result = 0;
            if (isNowDate == null) {
                //获取记录
                result = ordersManager.queryOrderTotalCount(null);
            } else {
                result = ordersManager.queryOrderTotalCount(new Date());
            }
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
