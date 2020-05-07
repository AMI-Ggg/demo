package com.Pipi.demo.Utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    public static XSSFWorkbook getWorkBook(String sheetName, String[] title, String[][] content) {
        //新建文档实例
        XSSFWorkbook workbook = new XSSFWorkbook();

        //在文档中添加表单
        XSSFSheet sheet = workbook.createSheet(sheetName);

        //创建单元格格式，并设置居中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        //创建第一行，用于填充标题
        XSSFRow titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title.length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[i]);
            //设置单元格样式
            cell.setCellStyle(style);
            XSSFDataFormat dataFormat = workbook.createDataFormat();
            style.setDataFormat(dataFormat.getFormat("@"));
        }

        //填充内容
        if (content==null || content.length==0) {
            return workbook;
        }
        for (int i=0 ; i<content.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        //返回文档实例
        return workbook;
    }
}