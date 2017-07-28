package com.xxh.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Excel相关工具类
 * Created by wulongtao on 2017/7/26.
 */
public class ExcelUtilsDemo {
    String[] outNames = new String[]{""};

    @Test
    public void testSingle() throws Exception {
        InputStream input = new FileInputStream("D:\\dev\\java\\xxh-common-master\\src\\main\\resources\\demo\\answer.xls");
        POIFSFileSystem fs = new POIFSFileSystem(input);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheet("Doc_GJJ_Percent");

        HSSFRow row1 = sheet.getRow(1);
        Iterator<Cell> cells1 = row1.cellIterator();
        List<String> lstName = new ArrayList<>();
        List<String> lstUrl = new ArrayList<>();
        while (cells1.hasNext()) {
            HSSFCell cell = (HSSFCell) cells1.next();
            String str = cell.getStringCellValue();
            String name = str.substring(str.indexOf('（')+1, str.length()-1);
            String urlName = str.substring(0, str.indexOf('（'));
            if (Objects.equals(urlName, "Supplier") || Objects.equals(urlName, "Province")
                    || Objects.equals(urlName, "City") || Objects.equals(urlName, "Update_Time")
                    || Objects.equals(urlName, "Area")) {
                continue;
            }

            String url = "http://120.76.65.137:58984/show/getData?tableName=Doc_GJJ_Percent&tableColumns="+urlName+"&tableFilters=product~";


            lstName.add("养老" + name);
            lstUrl.add(url+"养老");
            lstName.add("医疗" + name);
            lstUrl.add(url+"医疗");
            lstName.add("失业" + name);
            lstUrl.add(url+"失业");
            lstName.add("工伤" + name);
            lstUrl.add(url+"工伤");
            lstName.add("生育" + name);
            lstUrl.add(url+"生育");
            lstName.add("住房公积金" + name);
            lstUrl.add(url+"住房公积金");
            lstName.add("其他收费项目（大病）" + name);
            lstUrl.add(url+"其他收费项目（大病）");
            lstName.add("残疾人保障金" + name);
            lstUrl.add(url+"残疾人保障金");
        }

        for (String name : lstName) {
            System.out.println(name);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (String url : lstUrl) {
            System.out.println(url);
        }


    }

    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream("D:\\dev\\java\\xxh-common-master\\src\\main\\resources\\demo\\answer.xls");
        POIFSFileSystem fs = new POIFSFileSystem(input);
//        System.out.println(fs.getShortDescription());
        HSSFWorkbook wb = new HSSFWorkbook(fs);
//        System.out.println("numbers of sheet:" + wb.getNumberOfSheets());
//        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFSheet sheet = wb.getSheet("Doc_SHB_Certificate");

        HSSFRow row1 = sheet.getRow(1);
        Iterator<Cell> cells1 = row1.cellIterator();
        while (cells1.hasNext()) {
            HSSFCell cell = (HSSFCell) cells1.next();
            System.out.println(cell.getStringCellValue());
        }


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        HSSFRow row2 = sheet.getRow(2);
        Iterator<Cell> cells2 = row2.cellIterator();
        while (cells2.hasNext()) {
            HSSFCell cell = (HSSFCell) cells2.next();
            System.out.println("http://120.76.65.137:58984/show/getData?tableName=Doc_SHB_Certificate&tableColumns="+cell.getStringCellValue());
        }

        /*Iterator<Row> rows = sheet.rowIterator();
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();
//            System.out.println("Row # " + row.getRowNum());

            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                HSSFCell cell = (HSSFCell) cells.next();
                System.out.print("index:" + cell.getColumnIndex() + "==");
                System.out.print("type:" + cell.getCellTypeEnum() + "==");
                switch (cell.getCellTypeEnum()) {
                    case _NONE:
                        System.out.println("value=none");
                        break;
                    case STRING:
                        System.out.println("value:" + cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.println("value:" + cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.println("value:" + cell.getNumericCellValue());
                        break;
                }
            }
        }*/
    }

}
