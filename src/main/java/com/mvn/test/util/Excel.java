package com.mvn.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class Excel {

    private static final Logger log = LoggerFactory.getLogger(Excel.class);

    private static final DecimalFormat df = new DecimalFormat("#");

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明: 获取excel中的所有数据
     * 
     * @param file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static List<?> getExcelContent(MultipartFile file) throws IOException {
        ArrayList<Map<Integer, Object>> list = new ArrayList<>();
        String filename = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (filename.endsWith(".xls")) {
            wb = new HSSFWorkbook(is); // 处理以.xls结尾的excel
        } else {
            wb = new XSSFWorkbook(is);// 处理以.xlsx结尾的excel
        }
        Sheet sheet = wb.getSheetAt(0);// 获取第一个sheet
        // 获取行, 如果sheet中一行数据都没有则返回-1, 只有第一行有数据则返回0, 最后有数据的行是第n行则返回 n-1
        int rsRows = sheet.getLastRowNum() + 1;
        // 获取列, 如果sheet中一列数据都没有则返回-1, 只有第一列有数据则返回1, 最后有数据的列是第n列则返回 n
        int rsColumns = sheet.getRow(0).getLastCellNum();
        log.debug("总行数: {}, 总列数: {}", rsRows, rsColumns);
        for (int i = 1; i < rsRows; i++) {
            Map<Integer, Object> map = new HashMap<>();
            Object obj = null;
            Row row = sheet.getRow(i);
            if (row == null) {// 排除空行
                log.debug("第{}行为空行", i + 1);
                continue;
            }
            for (int j = 0; j < rsColumns; j++) {
                Cell cell = row.getCell(j);
                if (null == cell || "".equals(cell) || cell.getCellTypeEnum() == CellType.BLANK) {// 排除空单元格
                    log.debug("第{}行第{}列为空列", i + 1, j + 1);
                    continue;
                }
                obj = getColumnContent(cell, j);
                map.put(j, obj);
            }
            if (map.isEmpty()) {
                continue;
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明: 获取单元格内容
     * 
     * @param <T>
     * @param cell
     * @return
     * @return
     */
    public static Object getColumnContent(Cell cell, Integer index) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CellType type = cell.getCellTypeEnum();
        switch (type) {
        case NUMERIC:// 数字(整数、小数、日期)
            double value = cell.getNumericCellValue();
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 时间格式
                Date date = HSSFDateUtil.getJavaDate(value);
                String dateStr = format.format(date);
                return dateStr;
            } else {// 其他格式
                DecimalFormat df = new DecimalFormat("#");
                String dfValue = df.format(value);
                return dfValue;
            }
        case STRING:// 字符串(文本)
            return cell.getStringCellValue();
        case FORMULA:// 公式
            // return cell.getCellFormula();
        case BOOLEAN:// 布尔
            // return cell.getBooleanCellValue();
        case _NONE:// 未知类型
            break;
        case BLANK:// 空白
            break;
        case ERROR:// 错误
            break;
        default:
            break;
        }
        return null;
    }

    // *************************************************************** Excel校验  *****************************************************************

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明: 获取excel中的所有数据
     * 
     * @param file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static Map<String, Object> verify(MultipartFile file) throws IOException {
        // 记录错误数据
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        final String msg = "格式不正确";
        String filename = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (filename.endsWith(".xls")) {
            wb = new HSSFWorkbook(is); // 处理以.xls结尾的excel
        } else {
            wb = new XSSFWorkbook(is);// 处理以.xlsx结尾的excel
        }
        Sheet sheet = wb.getSheetAt(0);// 获取第一个sheet
        // 获取行, 如果sheet中一行数据都没有则返回-1, 只有第一行有数据则返回0, 最后有数据的行是第n行则返回 n-1
        int rsRows = sheet.getLastRowNum() + 1;
        // 获取列, 如果sheet中一列数据都没有则返回-1, 只有第一列有数据则返回1, 最后有数据的列是第n列则返回 n
        int rsColumns = sheet.getRow(0).getLastCellNum();
        log.debug("总行数: {}, 总列数: {}", rsRows, rsColumns);

        if (rsRows < 2) {// 最少两行
            return map;
        }

        if (4 != rsColumns) {// 列数固定
            return map;
        }

        boolean b = verifyTitle(sheet);// 校验标题
        if (!b) {
            return map;
        }

        forRow: for (int i = 1; i < rsRows; i++) {// 校验内容
            Row row = sheet.getRow(i);
            if (row == null) {// 排除空行
                log.debug("第{}行为空行", i + 1);
                continue;
            }
            for (int j = 0; j < rsColumns; j++) {
                Cell cell = row.getCell(j);
                if (null == cell || "".equals(cell) || cell.getCellTypeEnum() == CellType.BLANK) {// 排除空单元格
                    log.debug("第{}行第{}列为空列", i + 1, j + 1);
                    map.put("行内有空的表格,请补充完整!", "第  "+ (i+1) + " 行");
                    continue forRow; //行内有空值, 当前行不再继续校验, 直接下一行
                }
                boolean flag = verifyColumn(cell, j);
                if (!flag) {
                    switch (j) {
                    case 0:
                        map.put("部门: " + cell.toString(), msg);
                        break;
                    case 1:
                        map.put("姓名: " + cell.toString(), msg);
                        break;
                    case 2:
                        map.put("账号: " + cell.toString(), msg);
                        break;
                    case 3:
                        map.put("手机号: " + df.format(cell.getNumericCellValue()), msg);
                        break;
                    default:
                        map.put("未知类型: " + cell.toString(), "未知类型");
                        break;
                    }
                }
            }
        }

        return map;
    }

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明: 检验标题
     * 
     * @return false: 不通过, true: 通过
     */
    public static boolean verifyTitle(Sheet sheet) {
        for (int i = 0; i < 4; i++) {
            Cell cell = sheet.getRow(0).getCell(i); // 单元格数据
            if (null == cell || "".equals(cell) || cell.getCellTypeEnum() == CellType.BLANK)
                return false; // 排除空单元格
            String value = cell.toString();
            switch (i) {
            case 0:// 部门
                if (!"部门".equals(value))
                    return false;
                break;
            case 1:// 姓名
                if (!"姓名".equals(value))
                    return false;
                break;
            case 2:// 账号
                if (!"账号".equals(value))
                    return false;
                break;
            case 3:// 手机号
                if (!"手机号".equals(value))
                    return false;
                break;
            default:
                return false;
            }
        }
        return true;
    }

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明: 校验单元格内容
     * 
     * @param <T>
     * @param cell
     * @param index
     * @return false: 不通过, true: 通过
     */
    public static boolean verifyColumn(Cell cell, Integer index) {
        // TODO 排除行内的空单元格,行内有空单元格,则提示该行格式不正确
        /*
         * if (null == cell || "".equals(cell) || cell.getCellTypeEnum() ==
         * CellType.BLANK) return false; // 排除空单元格
         */
        String value = cell.toString();
        switch (index) {
        case 0: // 部门
            value = value.replace("/", "");
            boolean b = FormatCheck.isNumber(value); // 不能为纯数字
            if (b)
                return false;
            return FormatCheck.isHanziA0(value);
        case 1: // 姓名
            boolean b2 = FormatCheck.isNumber(value); // 不能为纯数字
            if (b2)
                return false;
            return FormatCheck.isHanziA0(value);
        case 2: // email
            return FormatCheck.isEmail(value);
        case 3: // 手机号
            double phone = cell.getNumericCellValue();
            return FormatCheck.isPhone(df.format(phone));
        default:
            return false;
        }
    }

}
