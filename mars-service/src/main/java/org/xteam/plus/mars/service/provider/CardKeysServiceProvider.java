package org.xteam.plus.mars.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.common.JsonResult;
import org.xteam.plus.mars.common.excel.CellFormater;
import org.xteam.plus.mars.common.excel.ExcelUtils;
import org.xteam.plus.mars.domain.CardKeys;
import org.xteam.plus.mars.manager.CardKeysManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private static final String CHARSET= "UTF-8";

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


    /**
     * 批量生成
     *
     * @param quantity
     * @return List<CardKeys>
     */
    @RequestMapping("/batchGenerate")
    public JsonResult batchGenerate(Integer quantity)throws Exception{
        JsonResult jsonResult = new JsonResult();
        try {
            cardkeysManager.batchGenerate(quantity);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            logError("批量生成异常", e);
            jsonResult.setMessage("批量生成异常");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    /**
     * 导出
     *
     * @param cardkeys
     * @return
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response, CardKeys cardkeys) throws Exception{
        OutputStream out = null;
        try{
            //查询
            cardkeys.setLimit(Integer.MAX_VALUE);
            List<CardKeys> data = cardkeysManager.query(cardkeys);
            //下载文件名

            String sheetName = new SimpleDateFormat("yyyy年MM月dd日").format(cardkeys.getCreated());
            String fileName = "卡密表(" + sheetName + ")数据.xlsx";
            // 设置响应参数
            response.setCharacterEncoding(CHARSET);
            response.setContentType("text/csv; charset=" + CHARSET);
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(CHARSET), "ISO8859-1"));
            out = response.getOutputStream();
            //表头
            String[] header = {
                    "流水号"
                    ,"卡密"
                    ,"状态"
                    ,"创建时间"
            };
            //获取dataIndex
            String[] dataIndexs = {
                    "id"
                    ,"cardKeys"
                    ,"status"
                    ,"created"
            };

            Map<String, CellFormater> cellFormaterMap = new HashMap<>();
            cellFormaterMap.put("status", new CellFormater() {
                @Override
                public String format(String value) throws Exception {
                    Integer status = Integer.valueOf(value);
                    switch (status){
                        case 0:
                            return "未上线";
                        case 1:
                            return "已上线";
                        case 2:
                            return "已激活";
                    }
                    return null;
                }
            });
            //导出
            ExcelUtils.export(header,dataIndexs,data,out,sheetName,cellFormaterMap);
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
}
