package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.common.excel.ExcelUtils;
import org.xteam.plus.mars.domain.PolicyInfo;
import org.xteam.plus.mars.manager.PolicyInfoManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-23
 * Time: 17:05
 * 功能: T_MARS_POLICY_INFO表 服务提供者
 */
@RestController
@RequestMapping("/mars/policyinfo")
public class PolicyInfoServiceProvider extends AbstractServiceProvider {
    private static final String CHARSET = "UTF-8";
    @Resource
    private PolicyInfoManager policyinfoManager;

    /**
     * 查询
     *
     * @param policyinfo
     * @return List<PolicyInfo>
     */
    @RequestMapping("/list")
    public JsonResult list(PolicyInfo policyinfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            List<PolicyInfo> data = policyinfoManager.query(policyinfo);
            // 设置结果集
            jsonResult.put("list", data);
            jsonResult.put("rowCount", policyinfoManager.queryCount(policyinfo));
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
     * @param policyinfo
     * @return
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response, PolicyInfo policyinfo) {
        OutputStream out = null;
        try {
            //查询
            policyinfo.setLimit(Integer.MAX_VALUE);
            List<PolicyInfo> data = policyinfoManager.query(policyinfo);
            //下载文件名
            String fileName = "下保单(全部)数据.xlsx";
            String sheetName = "全部";
            if (policyinfo.getStartDate() != null && policyinfo.getEndDate() != null) {
                sheetName = new SimpleDateFormat("yyyy年MM月dd日").format(policyinfo.getStartDate()) + "-" + new SimpleDateFormat("yyyy年MM月dd日").format(policyinfo.getEndDate());
                fileName = "下保单(" + sheetName + ")数据.xlsx";
            } else if (policyinfo.getStartDate() != null) {
                sheetName = "从" + new SimpleDateFormat("yyyy年MM月dd日").format(policyinfo.getStartDate()) + "起";
                fileName = "下保单(" + sheetName + ")数据.xlsx";
            } else if (policyinfo.getEndDate() != null) {
                sheetName = "截止到" + new SimpleDateFormat("yyyy年MM月dd日").format(policyinfo.getStartDate()) + "";
                fileName = "下保单(" + sheetName + ")数据.xlsx";
            }
            // 设置响应参数
            response.setCharacterEncoding(CHARSET);
            response.setContentType("text/csv; charset=" + CHARSET);
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(CHARSET), "ISO8859-1"));
            out = response.getOutputStream();
            //表头
            String[] header = {
                    "会员卡号"
                    , "姓名"
                    , "年龄"
                    , "出生日期"
                    , "身份证号"
                    , "手机号"
                    , "联系地址"
                    , "激活日期"
                    , "保费"
                    , "会员卡有效期"
                    , "续期缴费次数"
            };
            //获取dataIndex
            String[] dataIndexs = {
                    "cardNo"
                    , "realName"
                    , "age"
                    , "birthDate"
                    , "idNumber"
                    , "mobileNo"
                    , "linkAddress"
                    , "cardActivateTime"
                    , "premium"
                    , "cardLifeTime"
                    , "payTimes"
            };
            //导出
            ExcelUtils.export(header, dataIndexs, data, out, sheetName);
        } catch (Exception e) {
            logError("导出数据时异常", e);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logError("close BufferedOutputStream error:" + e);
                }

            }
        }
    }

    @RequestMapping(value = "/uploadImport")
    @ResponseBody
    public JsonResult uploadImport(@RequestParam(required = false) MultipartFile uploadFile) {
        JsonResult jsonResult = new JsonResult();
        try {
            if (uploadFile == null) {
                throw new Exception("导出文件为空!");
            }
            //获取dataIndex
            String[] dataIndexs = {
                    "cardNo"
                    , "realName"
                    , "age"
                    , "birthDate"
                    , "idNumber"
                    , "mobileNo"
                    , "linkAddress"
                    , "cardActivateTime"
                    , "premium"
                    , "cardLifeTime"
                    , "payTimes"
            };
            List<PolicyInfo> policyInfo = ExcelUtils.load(dataIndexs, PolicyInfo.class, uploadFile.getInputStream());
            jsonResult.setMessage("导入成功  总导入"+policyInfo.size());
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("导入异常!", e);
            jsonResult.setMessage("导入异常");
            jsonResult.setSuccess(false);

        } finally {
            return jsonResult;
        }
    }
}
