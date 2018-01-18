package org.xteam.plus.mars.common.excel;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.xteam.plus.mars.common.JsonUtils;

import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yankun on 2017/2/28.
 */
public class ExcelUtils {
    private static final Log log  = LogFactory.getLog(ExcelUtils.class);

    /**
     * 导入数据
     * @param dataIndexs  属性名
     * @param classType   对象类型
     * @param inputStream  导入文件输入流
     * @param <T>
     * @return
     * @throws Exception
     */
    public  static  <T>  List<T> load(String[] dataIndexs,Class<T> classType, InputStream inputStream) throws  Exception{
        List<T> results = null;
        Workbook workbook =null;
        try{
            workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            results = new ArrayList<>();
            for (Row row : sheet) {
                Map<String,String> rowMap = new HashMap<String,String>();
                for(int i=0; i< dataIndexs.length;i++){
                    rowMap.put(dataIndexs[i],row.getCell(i).getStringCellValue());
                }
                results.add(JsonUtils.transform(rowMap,classType));
            }
            }catch (Exception e){
            log.error("导入数据异常",e);
        }finally {
            if(workbook!=null){
                workbook.close();
            }
        }
        return  results;
    }


    public static void export(String[] heads, String[] dataIndexs, List data, OutputStream outputStream, String sheetName) throws Exception {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(sheetName);
        XSSFCellStyle headStyle = getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < heads.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(heads[i]);
        }

        //构建表体数据
        if(data!=null){
            for(int rowNum = 0 ; rowNum <data.size(); rowNum++){
                XSSFRow bodyRow = sheet.createRow(rowNum + 1);
                for(int columNum = 0 ;columNum<dataIndexs.length;columNum++  ){
                    cell = bodyRow.createCell(columNum);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(BeanUtils.getProperty(data.get(rowNum),dataIndexs[columNum]));
                }
            }
        }
        workBook.write(outputStream);
        outputStream.flush();
    }


    /**
     * 合并单元格后给合并后的单元格加边框
     *
     * @param region
     * @param cs
     */
    private void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs, XSSFSheet sheet) {
        int toprowNum = region.getFirstRow();
        for (int i = toprowNum; i <= region.getLastRow(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.getCell(j);
                // (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 设置表头的单元格样式
     *
     * @return
     */
    private static XSSFCellStyle getHeadStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(new XSSFColor(Color.BLUE));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBold(true);
        font.setFontName("微软雅黑");
        font.setFontHeight((short) 20);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     *
     * @return
     */
    private static XSSFCellStyle getBodyStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBold(true);
        font.setFontName("微软雅黑");
        font.setFontHeight((short) 20);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

}
