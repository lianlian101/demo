package com.demo.excel;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class CreateExcel {

    @Test
    public void createExcel() throws Exception{
        // 创建excel对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建sheet，并指定名称
        HSSFSheet sheet = wb.createSheet("sheet-1");
        // 创建字体，红色，粗体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFFont.COLOR_RED);// 设置字体颜色
        font.setBold(true);// 设置字体为粗体
        // 创建单元格格式，如：居中、对齐等
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 水平方向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直方向居中
        // 设置字体
        style.setFont(font);
        // 创建一个三行三列的表格，第一行为表头
        // 1-1. 创建表头
        HSSFRow row = sheet.createRow(0);
        // 1-2. 创建列，三列
        for(int i = 0; i < 3; i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue("标题-" + (i+1));
            cell.setCellStyle(style);
        }
        // 2-1. 创建剩余的行
        for(int i = 1; i < 3; i++){
            HSSFRow row2 = sheet.createRow(i);
            // 2-2. 创建三列
            for(int j = 0; j < 3; j++){
                HSSFCell cell2 = row2.createCell(j);
                cell2.setCellValue("第"+(i+1)+"行第"+(j+1)+"列");
            }
        }
        // 将文件写到磁盘
        FileOutputStream fos = new FileOutputStream("C:\\upload\\test.xls");
        wb.write(fos);
        fos.flush();
        wb.close();
    }
    
    @Test
    public void createExcel2() throws Exception{
        // 创建excel对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建sheet，并指定名称
        HSSFSheet sheet = wb.createSheet("sheet-1");
        // 创建字体，红色，粗体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFFont.COLOR_RED);// 设置字体颜色
        font.setBold(true);// 设置字体为粗体
        // 创建单元格格式，如：居中、对齐等
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 水平方向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直方向居中
        // 设置字体
        style.setFont(font);
        // 创建一个三行三列的表格，第一行为表头
        // 1-1. 创建表头
        HSSFRow row = sheet.createRow(0);
        // 1-2. 创建列，三列
        for(int i = 0; i < 3; i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue("标题-" + (i+1));
            cell.setCellStyle(style);
        }
        // 2-1. 创建剩余的行
        for(int i = 1; i < 3; i++){
            HSSFRow row2 = sheet.createRow(i);
            // 2-2. 创建三列
            for(int j = 0; j < 3; j++){
                HSSFCell cell2 = row2.createCell(j);
                cell2.setCellValue("第"+(i+1)+"行第"+(j+1)+"列");
                cell2.setCellStyle(style);
            }
        }
        // 合并单元格
        CellRangeAddress cra = new CellRangeAddress(1, 2, 0, 1);
        
        sheet.addMergedRegion(cra);
        // 将文件写到磁盘
        FileOutputStream fos = new FileOutputStream("C:\\upload\\test2.xls");
        wb.write(fos);
        fos.flush();
        wb.close();
    }
   
    /**
     * 日期：2019年11月27日
     * 作者：zhb
     * 说明：设置单元格的样式
     * 
     * @param wb Excel对象
     * @param fontName 字体
     * @param fontHeightInPoints 字体大小
     * @param bold 是否加粗
     * @param ha 水平样式
     * @param va 垂直样式
     * @param color 单元格的填充色数值
     * @param border 是否更改边框样式
     * @return style HSSFCellStyle
     */
    private HSSFCellStyle cellStyle(HSSFWorkbook wb, String fontName, int fontHeightInPoints, boolean bold, 
            HorizontalAlignment ha, VerticalAlignment va, Integer color, boolean border) {
        // 设置字体
        HSSFFont font = wb.createFont();
        // 设置字体样式
        font.setFontName(fontName);
        // 设置字体大小
        font.setFontHeightInPoints((short) fontHeightInPoints);
        // 字体加粗
        font.setBold(bold);
        // 创建内容样式
        HSSFCellStyle cs = wb.createCellStyle();
        cs.setFont(font); // 设置字体样式
        cs.setWrapText(true); // 设置文本是否应该被包装
        cs.setAlignment(ha); // 设置文字水平样式
        cs.setVerticalAlignment(va); // 设置文字垂直样式
        // 设置表格的填充色
        if(color != null){
            cs.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 开启前景色设置
            cs.setFillForegroundColor(color.shortValue()); // 设置单元格前景色，在背景色之前设置
        }
        // 设置边框
        if(border){
            cs.setBorderTop(BorderStyle.THIN);
            cs.setBorderBottom(BorderStyle.THIN);
            cs.setBorderLeft(BorderStyle.THIN);
            cs.setBorderRight(BorderStyle.THIN);
            cs.setTopBorderColor(IndexedColors.GREY_25_PERCENT.index);
            cs.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.index);
            cs.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.index);
            cs.setRightBorderColor(IndexedColors.GREY_25_PERCENT.index);
        }
        return cs;
    }
    
    @Test
    public void createExcel3() throws Exception{
        // 创建Excel
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建Sheet
        HSSFSheet sheet = wb.createSheet("sheet-1");
        // 创建3行3列的表格
        // 创建第一行：标题
        HSSFRow rowTitle = sheet.createRow(0);
        for(int i = 0; i < 3; i++){
            // 创建单元格
            HSSFCell cell = rowTitle.createCell(i);
            // 设置类型
            cell.setCellType(CellType.STRING);
            // 设置内容
            cell.setCellValue("标题-"+(i+1));
            // 设置样式
            cell.setCellStyle(cellStyle(wb, "黑体", 20, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 22, true));
        }
        // 设置第二行：内容
        HSSFRow row1 = sheet.createRow(1);
        for(int i = 0; i < 3; i++){
            // 创建单元格
            HSSFCell cell = row1.createCell(i);
            // 设置类型
            cell.setCellType(CellType.STRING);
            // 设置内容
            if(i == 0)
                cell.setCellValue("2019/11/27");
            if(i == 1)
                cell.setCellValue("上午");
            if(i == 2)
                cell.setCellValue("A");
            // 设置样式
            cell.setCellStyle(cellStyle(wb, "宋体", 14, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, null, false));
        }
        // 设置第三行：内容
        HSSFRow row2 = sheet.createRow(2);
        for(int i = 0; i < 3; i++){
            if(i == 0)
                continue;
            // 创建单元格
            HSSFCell cell = row2.createCell(i);
            // 设置类型
            cell.setCellType(CellType.STRING);
            // 设置内容
            if(i == 1)
                cell.setCellValue("下午");
            if(i == 2)
                cell.setCellValue("B");
            // 设置样式
            cell.setCellStyle(cellStyle(wb, "宋体", 14, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, null, false));
        }
        // 合并单元格，第2行第3行第1列
        sheet.addMergedRegion(new CellRangeAddress(1,2,0,0));
        
        sheet.autoSizeColumn((short)0); //调整第一列宽度
        sheet.autoSizeColumn((short)1); //调整第二列宽度
        sheet.autoSizeColumn((short)2); //调整第三列宽度
        // 写入到磁盘
        FileOutputStream fos = new FileOutputStream("C:\\upload\\test3.xls");
        wb.write(fos);
        fos.flush();
    }
    
    
}
