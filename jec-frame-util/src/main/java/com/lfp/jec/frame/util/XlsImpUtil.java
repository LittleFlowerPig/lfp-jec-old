package com.lfp.jec.frame.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project: lfp-jec
 * Title: xls文件导入
 * Description: 按照指定的内容导入excel文件
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class XlsImpUtil {
	
	/**
	 * 读取EXCEL文件中所有的内容,转化为对应的java对象。
	 * @param is			输入文件流
	 * @return 				Map的List，一个Map代表一个sheet的内容。 其数据结构为 [[sheetName:'xxxx', rows:[[],[]...]],...]
	 */
	public static List<Object> readBook(InputStream is){
		List<Object> ret = new ArrayList<>();
		try{
			HSSFWorkbook wb = new HSSFWorkbook(is);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Map<String, Object> sm = new HashMap<>();
				HSSFSheet sheet = wb.getSheetAt(i);
				sm.put("sheetName", sheet.getSheetName());
				sm.put("rows", getSheetRows(sheet));
				ret.add(sm);
			}
		}catch(Exception e) {
            e.printStackTrace(); 
        }
		return ret;
	}
	
	/**
	 * 读取EXCEL文件中某个sheet的内容,转化为对应的java对象。
	 * @param is			输入文件流
	 * @param sheetName		表单名
	 * @return				2维List，代表一个sheet的内容。其数据结构为 [[xx,xx,...],[],...]
	 */
	public static List<Object> readSheet(InputStream is, String sheetName){
        List<Object> ret = new ArrayList<>();
        try{
        	HSSFWorkbook wb = new HSSFWorkbook(is);
        	HSSFSheet sheet = wb.getSheet(sheetName);
        	if(sheet == null) return ret;
        	ret = getSheetRows(sheet);
        }catch (Exception e) {       
            e.printStackTrace(); 
        }
        return ret;
    }

	/**
	 * 读取EXCEL文件中某个sheet的内容,转化为对应的java对象。
	 * @param sheet			excel 的 sheet单页
	 * @return 				2维List，代表一个sheet的内容。其数据结构为 [[xx,xx,...],[],...]
	 */
	private static List<Object> getSheetRows(HSSFSheet sheet) {
		List<Object> rows = new ArrayList<>();
		int totalRow = sheet.getPhysicalNumberOfRows();			//行数
		if(totalRow<1) return null;	//空白excel
		int cells = sheet.getRow(0).getPhysicalNumberOfCells(); //列数（以标题列为准）
		for(int i = 0; i < totalRow; i++){
            HSSFRow row = sheet.getRow(i);
            if(row != null){
            	List<Object> cols = new ArrayList<>();
            	for(int j = 0; j < cells; j++){
            		HSSFCell cell = row.getCell(j);//单元格
            		if(cell != null){
            			Object cv = getCellValue(cell);
            			if(cv == null) {   //假设此格为合并单元格导致无值
                            cv = getMergedRegionValue(sheet, i, j);   
                        }
            			cols.add(cv);
            		}else{
            			cols.add(null);
            		}
            	}
            	rows.add(cols);
            }
		}
		return rows;
	}

	/**
	 * 获得当前单元格的内容
	 * @param cell			excel 的 单元格
	 * @return 				单元格的string值
	 */
	private static String getCellValue(HSSFCell cell) {
		if(cell == null) return null;
        switch (cell.getCellTypeEnum()) {
        	case NUMERIC:			//数据类型
	            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){	//时间类型
	            	Date date = cell.getDateCellValue(); 
	            	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					return String.valueOf(format.format(date));
	            }else{								//数字类型
					return getNumericCellValue(cell);
	            }
            case STRING:			//字符串
				return cell.getRichStringCellValue().getString();
            case FORMULA: 			//公式类型
            	try{
					return getNumericCellValue(cell);
            	}catch(IllegalStateException e){
					return String.valueOf(cell.getRichStringCellValue());
            	}
            case BOOLEAN:			//布尔类型
				return cell.getBooleanCellValue()+"";
            default:
				return null;
        }
	}

	/**
	 * 获取单元格的数值
	 * @param cell			excel 的 单元格
	 * @return				单元格的数值
	 */
	private static String getNumericCellValue(HSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		if(cellValue.toString().contains("E")){		//科学计数法
			DecimalFormat df = new DecimalFormat("#.#");
			return String.valueOf(df.format(cellValue));
		}else if(cellValue == cellValue.longValue()){	//长整形
			return cellValue.longValue()+"";
		}else if(cellValue == cellValue.intValue()){	//短整型
			return cellValue.intValue()+"";
		}else{
			return cellValue +"";
		}
	}

	/**
	 * 获取合并单元格的值 
	 * @param sheet			指定页签
	 * @param row			指定行
	 * @param column		指定列
	 * @return 				单元格的string值
	 */
	private static Object getMergedRegionValue(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();  
        for(int i = 0 ; i < sheetMergeCount ; i++){  
        	CellRangeAddress ca = sheet.getMergedRegion(i);  
            int firstColumn = ca.getFirstColumn();  
            int lastColumn = ca.getLastColumn();  
            int firstRow = ca.getFirstRow();  
            int lastRow = ca.getLastRow();  
  
            if(row >= firstRow && row <= lastRow){  
                if(column >= firstColumn && column <= lastColumn){  
                	HSSFRow fRow = sheet.getRow(firstRow);  
                	HSSFCell fCell = fRow.getCell(firstColumn);                      
                    return getCellValue(fCell) ;  
                }  
            }
        }
        return null;
	}

}
