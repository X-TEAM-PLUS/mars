package org.xteam.plus.mars.service.provider;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.domain.CouncilInfo;
import org.xteam.plus.mars.domain.CouncilInfoList;
import org.xteam.plus.mars.manager.CouncilInfoManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: T_MARS_COUNCIL_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/councilinfo")
public class CouncilInfoServiceProvider extends AbstractServiceProvider {

    @Resource
    private CouncilInfoManager councilinfoManager;

    /**
     * 获取
     *
     * @param councilinfo
     * @return int
     */
    @RequestMapping("/get")
    public JsonResult get(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            CouncilInfo result = councilinfoManager.get(councilinfo);
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
     * @param councilinfo
     * @return int 记录数
     */
    @RequestMapping("/post")
    public JsonResult post(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = councilinfoManager.insert(councilinfo);
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
     * @param list List<CouncilInfo>
     * @return int  记录数
     */
    @RequestMapping("/batchPost")
    public JsonResult batchPost(List<CouncilInfo> list) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = councilinfoManager.batchInsert(list);
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
     * @param councilinfo
     * @return int
     */
    @RequestMapping("/delete")
    public JsonResult delete(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //删除记录
            int rowCount = councilinfoManager.delete(councilinfo);
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
     * @param councilinfo
     * @return int 记录数
     */
    @RequestMapping("/put")
    public JsonResult put(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //更新记录
            int rowCount = councilinfoManager.update(councilinfo);
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
     * @param councilinfo
     * @return List<CouncilInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<CouncilInfo> data = councilinfoManager.query(councilinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", councilinfoManager.queryCount(councilinfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    /**
     * 查询列表（后台）
     *
     * @return List<CouncilInfo>
     */
    @RequestMapping("/totalList")
    public JsonResult totalList() throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<CouncilInfoList> data = councilinfoManager.queryTotal();
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", councilinfoManager.queryCount(new CouncilInfo()));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 查询详情（后台）
     * @param councilId 地方常任理事ID
     * @return
     * @throws Exception
     */
    @RequestMapping("/getTotal")
    public JsonResult getTotal(BigDecimal councilId) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取记录
            CouncilInfoList result = councilinfoManager.getTotal(councilId);
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
     * 增加成员
     *
     * @param councilinfo
     * @return int 记录数
     */
    @RequestMapping("/postUser")
    public JsonResult postUser(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = councilinfoManager.insertUser(councilinfo);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("保存数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("保存数据失败");
            }
        } catch (Exception e) {
            logError("提交数据异常", e);
            jsonResult.setMessage("提交数据异常 "+e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    /**
     * 增加成员
     *
     * @param councilinfo
     * @return int 记录数
     */
    @RequestMapping("/deleteUser")
    public JsonResult deleteUser(CouncilInfo councilinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //保存
            int rowCount = councilinfoManager.deleteUser(councilinfo);
            if (rowCount > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("保存数据成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("保存数据失败");
            }
        } catch (Exception e) {
            logError("提交数据异常", e);
            jsonResult.setMessage("提交数据异常 "+e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
