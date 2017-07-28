package com.xxh.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Excel相关工具类
 * Created by wulongtao on 2017/7/26.
 */
public class ExcelUtils {

    /**
     * 读取表格，支持xlsx与xls格式
     * @param clazz 映射工具类
     * @param filePath 文件路径
     * @param <T>
     * @return ArrayList<T>
     */
    public static <T> List<T> readExcelFile(Class<?> clazz, String filePath) {
        //构造对象
        List<T> lstData = new ArrayList<>();
        try {
            InputStream input = new FileInputStream(filePath);
            Workbook wb = null;
            if (filePath.contains("xlsx")) {
                wb = new XSSFWorkbook(input);
            } else if (filePath.contains("xls")) {
                wb = new HSSFWorkbook(input);
            } else {
                throw new IllegalExcelFileException("不合法的文件名");
            }

            Sheet sheet = wb.getSheetAt(0);

            List<String> columnIndex = new ArrayList<>();
            Row firstRow = sheet.getRow(0);
            Iterator<Cell> firstCells = firstRow.cellIterator();
            while (firstCells.hasNext()) {
                columnIndex.add(getCellValue(firstCells.next()));
            }

            Iterator<Row> rows = sheet.rowIterator();

            while (rows.hasNext()) {
                Row row = rows.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
                T obj = (T) clazz.newInstance();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    //获取字段的类型（首字母小写）
                    String columnName = columnIndex.get(cell.getColumnIndex());
                    if (columnName == null) {
                        throw new NoSuchCellNameException("没有找到匹配的列名称");
                    }

                    String fieldName = new String(new char[]{columnName.charAt(0)}).toLowerCase();
                    if (columnName.length() > 1) {
                        fieldName += columnName.substring(1);
                    }

                    //获取变量
                    Field field = clazz.getDeclaredField(fieldName);
                    Class fieldType = field.getType();
                    //构造set方法
                    String methodName = "set" + columnName;
                    Method method = clazz.getMethod(methodName, fieldType);

                    String cellValue = getCellValue(cell).trim();


                    if (fieldType == int.class || fieldType == Integer.class) {
                        method.invoke(obj, Integer.parseInt(cellValue));
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        method.invoke(obj, Boolean.parseBoolean(cellValue));
                    } else if (fieldType == double.class || fieldType == Double.class) {
                        method.invoke(obj, Double.parseDouble(cellValue));
                    } else if (fieldType == String.class) {
                        method.invoke(obj, cellValue);
                    }
                }

                lstData.add(obj);
            }

            wb.close();

        } catch (InstantiationException
                | IllegalAccessException
                | IOException | NoSuchMethodException
                | NoSuchFieldException
                | InvocationTargetException e) {
            e.printStackTrace();
        }


        return lstData;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                cellValue = "";
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                if (cell.getNumericCellValue() == (int)(cell.getNumericCellValue())) {
                    cellValue = Integer.toString((int) (cell.getNumericCellValue()));
                } else {
                    cellValue = Double.toString(cell.getNumericCellValue());
                }
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\dev\\java\\xxh-common-master\\src\\main\\resources\\demo\\test1.xlsx";
        List<User> lstData = readExcelFile(User.class, filePath);
        for (User user : lstData) {
            System.out.println(user);
        }
    }

}