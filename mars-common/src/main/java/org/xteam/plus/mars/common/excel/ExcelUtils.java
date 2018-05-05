package org.xteam.plus.mars.common.excel;



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
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

/**
 * Created by yankun on 2017/2/28.
 */
public class ExcelUtils {
    private static final Log log = LogFactory.getLog(ExcelUtils.class);
    /**
     * 导入数据
     *
     * @param dataIndexs  属性名
     * @param classType   对象类型
     * @param inputStream 导入文件输入流
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> load(String[] dataIndexs, Class<T> classType, InputStream inputStream) throws Exception {
        return load(dataIndexs,classType,inputStream,false);
    }

    /**
     * 导入数据
     *
     * @param dataIndexs  属性名
     * @param classType   对象类型
     * @param inputStream 导入文件输入流
     * @param  hasHeader
     * @return
     * @throws Exception
     */
    public static <T> List<T> load(String[] dataIndexs, Class<T> classType, InputStream inputStream,boolean hasHeader) throws Exception {
        List<T> results = null;
        Workbook workbook = null;
        try {
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheetAt(0);
            boolean ifFirst = true;
            results = new ArrayList<>();
            for (Row row : sheet) {
                if(hasHeader && ifFirst) {
                    ifFirst=false;
                    continue;
                }else{
                    Map<String, Object> rowMap = getRowMap(dataIndexs,row);

                    T transform = JsonUtils.transform(rowMap, classType);
                    if (transform != null) {
                        results.add(transform);
                    }
                }
            }
        } catch (Exception e) {
            log.error("导入数据异常", e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return results;
    }

    /**
     * 获取多级属性Map(注意 ：只支持两级)
     * @param dataIndexs
     * @param row
     * @return
     */
    private static  Map<String, Object> getRowMap(String[] dataIndexs, Row row){
        Map<String, Object> rowMap = new HashMap<String, Object>();
        for (int i = 0; i < dataIndexs.length; i++) {
            row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            String[] keyNames = dataIndexs[i].split("\\.");
            if(keyNames.length>1){
                if(rowMap.containsKey(keyNames[0])){
                    Map<String, Object> map = (Map<String, Object>) rowMap.get(keyNames[0]);
                    map.put(keyNames[1],row.getCell(i).getStringCellValue());
                }else{
                    Map<String, Object> map = new HashMap<>();
                    map.put(keyNames[1],row.getCell(i).getStringCellValue());
                    rowMap.put(keyNames[0],map);
                }
            }else{
                rowMap.put(dataIndexs[i], row.getCell(i).getStringCellValue());
            }
        }

        return  rowMap;
    }

    public static void export(String[] heads, String[] dataIndexs, List data, OutputStream outputStream, String sheetName, Map<String, CellFormater> cellFormaterMap) throws Exception {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(sheetName);
        XSSFCellStyle headStyle = getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = getBodyStyle(workBook);
        //默认列宽
        sheet.setDefaultColumnWidth(20);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < heads.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(heads[i]);
        }

        //构建表体数据
        if (data != null) {
            for (int rowNum = 0; rowNum < data.size(); rowNum++) {
                XSSFRow bodyRow = sheet.createRow(rowNum + 1);
                for (int columNum = 0; columNum < dataIndexs.length; columNum++) {
                    cell = bodyRow.createCell(columNum);
                    cell.setCellStyle(bodyStyle);
                    if (cellFormaterMap != null) {
                        cell.setCellValue(getCellValue(data.get(rowNum), dataIndexs[columNum], cellFormaterMap.get(dataIndexs[columNum])));
                    } else {
                        cell.setCellValue(getCellValue(data.get(rowNum), dataIndexs[columNum], null));
                    }

                }
            }
        }
        workBook.write(outputStream);
        outputStream.flush();
    }

    public static void export(String[] heads, String[] dataIndexs, List data, OutputStream outputStream, String sheetName) throws Exception {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(sheetName);
        XSSFCellStyle headStyle = getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = getBodyStyle(workBook);
        //默认列宽
        sheet.setDefaultColumnWidth(20);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < heads.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(heads[i]);
        }

        //构建表体数据
        if (data != null) {
            XSSFCellStyle cellStyle = workBook.createCellStyle();
            for (int rowNum = 0; rowNum < data.size(); rowNum++) {
                XSSFRow bodyRow = sheet.createRow(rowNum + 1);
                for (int columNum = 0; columNum < dataIndexs.length; columNum++) {
                    cell = bodyRow.createCell(columNum);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(getValue(data.get(rowNum),dataIndexs[columNum]));
                    //setCellValueByKey(data.get(rowNum), dataIndexs[columNum], workBook, cell,cellStyle);
                }
            }
        }
        workBook.write(outputStream);
        outputStream.flush();
    }


    private static String getCellValue(Object row, String fieldName, CellFormater cellFormater) throws Exception {
        Map<String, Object> objectMap = JsonUtils.transform(row, HashMap.class);
        if (cellFormater != null) {
            return cellFormater.format(objectMap.get(fieldName).toString());
        } else {
            if (null == objectMap.get(fieldName)) {
                return "";
            }
            return objectMap.get(fieldName).toString();
        }
    }


    public static String getValue(Object row, String fieldName){
         String[] keys = fieldName.split("\\.");
        Map<String, Object> objectMap = JsonUtils.transform(row, HashMap.class);
        for(String key: keys){
            Object value = objectMap.get(key);
            if(value instanceof HashMap){
                objectMap = (Map<String, Object>) value;
            }else if(value!=null){
                return value.toString();
            }else{
                break;
            }
        }
        return  "";
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
        cellStyle.setFillForegroundColor(new XSSFColor(Color.GRAY));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(false);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBold(true);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 12);// 设置字体大小
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
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(false);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 12);// 设置字体大小
        cellStyle.setFont(font);

        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 设置cell值
     *
     * @param obj 对象
     * @param key 键
     * @return null
     */
    public static void setCellValueByKey(Object obj, String key, XSSFWorkbook workBook, XSSFCell cell,XSSFCellStyle cellStyle) {
        Class cla = (Class) obj.getClass();
        Field[] fs = cla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            try {
                String type = f.getType().toString();
                if (f.getName().endsWith(key)) {
                    if (null == f.get(obj)) {
                        cell.setCellValue("");
                    } else if (type.endsWith("Date")) {
                        cell.setCellValue((Date) f.get(obj));
                        if(cellStyle != null){
                            XSSFDataFormat dataFormat = workBook.createDataFormat();
                            cellStyle.setDataFormat(dataFormat.getFormat("yyyy/MM/dd hh:mm:ss"));
                            // 设置单元格字体样式
                            XSSFFont font = workBook.createFont();
                            font.setFontHeightInPoints((short) 12);// 设置字体大小
                            cellStyle.setFont(font);
                            cell.setCellStyle(cellStyle);
                        }
                    } else {
                        cell.setCellValue(f.get(obj).toString());
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
