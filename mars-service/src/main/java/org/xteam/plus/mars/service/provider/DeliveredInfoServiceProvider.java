package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.common.excel.ExcelUtils;
import org.xteam.plus.mars.domain.DeliveredInfo;
import org.xteam.plus.mars.domain.UserHealthCard;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.manager.DeliveredInfoManager;
import org.xteam.plus.mars.manager.UserHealthCardManager;
import org.xteam.plus.mars.manager.UserInfoManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static final String CHARSET= "UTF-8";

    @Resource
    private DeliveredInfoManager deliveredinfoManager;

    @Resource
    private UserHealthCardManager  userHealthCardManager;

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
            List<DeliveredInfo> data =  null;
            if(deliveredinfo.getStatus()!=null && deliveredinfo.getStatus().intValue()==0){
                data = deliveredinfoManager.queryExportData(deliveredinfo);
                // 设置结果集
                jsonResult.put("list", data);
                jsonResult.put("rowCount", deliveredinfoManager.queryExportDataCount(deliveredinfo));
            }else{
                data = deliveredinfoManager.query(deliveredinfo);
                // 设置结果集
                jsonResult.put("list", data);
                jsonResult.put("rowCount", deliveredinfoManager.queryCount(deliveredinfo));
            }

            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    @RequestMapping("/listForUser")
    public JsonResult listForUser(DeliveredInfo deliveredInfo) throws Exception{
        JsonResult jsonResult = new JsonResult();
        try {
            List<DeliveredInfo> data = deliveredinfoManager.queryForUser(deliveredInfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", deliveredinfoManager.queryForUserCount(deliveredInfo));
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    /**
     * 导出
     *
     * @param deliveredinfo
     * @return
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response, DeliveredInfo deliveredinfo) throws Exception{
        OutputStream out = null;
        try{
            //查询
            deliveredinfo.setLimit(Integer.MAX_VALUE);
            List<DeliveredInfo> data = deliveredinfoManager.queryExportData(deliveredinfo);
            //下载文件名
            String fileName ="发货信息(全部)数据.xlsx";
            String sheetName = "全部";
            if(deliveredinfo.getStartDate()!=null && deliveredinfo.getEndDate()!=null) {
                sheetName = new SimpleDateFormat("yyyy年MM月dd日").format(deliveredinfo.getStartDate()) + "-" + new SimpleDateFormat("yyyy年MM月dd日").format(deliveredinfo.getEndDate());
                fileName = "发货信息(" + sheetName + ")数据.xlsx";
            }else if (deliveredinfo.getStartDate()!=null ){
                sheetName ="从" + new SimpleDateFormat("yyyy年MM月dd日").format(deliveredinfo.getStartDate())+ "起";
                fileName = "发货信息(" + sheetName + ")数据.xlsx";
            }else if(deliveredinfo.getEndDate()!=null){
                sheetName = "截止到"+new SimpleDateFormat("yyyy年MM月dd日").format(deliveredinfo.getStartDate())+ "";
                fileName = "发货信息(" + sheetName + ")数据.xlsx";
            }
            // 设置响应参数
            response.setCharacterEncoding(CHARSET);
            response.setContentType("text/csv; charset=" + CHARSET);
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(CHARSET), "ISO8859-1"));
            out = response.getOutputStream();
            //表头
            String[] header = {
                    "会员卡号"
                    ,"姓名"
                    ,"手机号"
                    ,"省"
                    ,"市"
                    ,"区县"
                    ,"联系地址"
                    ,"激活日期"
                    ,"有效期"
                    ,"发货次数"
                    ,"快递费"
            };
            //获取dataIndex
            String[] dataIndexs = {
                    "userHealthCard.cardNo"
                    ,"userInfo.realName"
                    ,"userInfo.mobileNo"
                    ,"userInfo.provinceName"
                    ,"userInfo.cityName"
                    ,"userInfo.countyName"
                    ,"userInfo.linkAddress"
                    ,"userHealthCard.cardActivateTime"
                    ,"userHealthCard.cardDeadline"
                    ,"userHealthCard.sendCount"
                    ,"deliveredFee"
            };
            //导出
            ExcelUtils.export(header,dataIndexs,data,out,sheetName);
        }catch (Exception e){
            logError("导出数据时异常", e);
        }finally {
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logError("close BufferedOutputStream error:" ,e);
                }

            }
        }
    }


    @RequestMapping(value = "/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            //获取dataIndex
            String[] dataIndexs = {
                    "userHealthCard.cardNo"
                    ,"userInfo.realName"
                    ,"userInfo.mobileNo"
                    ,"province"
                    ,"city"
                    ,"area"
                    ,"address"
                    ,"waybillNo"
                    ,"deliveredDate"
            };

            List<DeliveredInfo> deliveredInfos = ExcelUtils.load(dataIndexs,DeliveredInfo.class,file.getInputStream(),true);

            //查询用户信息
            for (DeliveredInfo  deliveredInfo: deliveredInfos){
                UserHealthCard   userHealthCard = userHealthCardManager.get(new UserHealthCard().setCardNo(deliveredInfo.getUserHealthCard().getCardNo()));
                deliveredInfo.setUserId(userHealthCard.getActivateUserId());
                deliveredInfo.setCreated(new Date());
                deliveredInfo.setUpdated(new Date());
                deliveredInfo.setDeliveredCount(1);
                deliveredInfo.setStatus(BigDecimal.ONE);
            }
            int count = deliveredinfoManager.batchInsert(deliveredInfos);
            if(count>0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage("已成功导入"+count+"条数据。");
            }else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("导入数据不成功。");
            }
        } catch (Exception e) {
            logError("查询异常", e);
            jsonResult.setMessage("查询异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
}
