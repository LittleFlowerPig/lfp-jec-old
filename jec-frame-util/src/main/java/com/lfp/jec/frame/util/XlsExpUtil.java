package com.lfp.jec.frame.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: lfp-jec
 * Title: xls文件导出
 * Description: 按照指定的内容导出excel文件
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class XlsExpUtil {

	/**
	 * 导出模板文件
	 * @param filename		导出后的文件名
	 * @param tempPath		目标路径
	 * @param template		导出文件的模板文件，位于模板文件仓库中的位置
	 * @param request		请求
	 * @param response		响应
	 */
	public static void exportTemplate(String filename, String tempPath, String template, HttpServletRequest request, HttpServletResponse response) {
		filename = WebUtil.fileNameForBrowser(filename, request);
		response.setHeader("Content-disposition","attachment; " + filename + template.substring(template.lastIndexOf("."),template.length()));
		response.setContentType("application/octet-stream");
		//String path = request.getSession().getServletContext().getRealPath(tempPath + "/" + template);
		String path;
		URL url = XlsExpUtil.class.getClassLoader().getResource(tempPath + "/" + template);
		if (url != null){
			path = url.getPath();
		}else {
			path = request.getSession().getServletContext().getRealPath(tempPath + "/" + template);
		}
		if(new File(path).exists()){
			UploadUtil.outputFile(path, response);
		}else{
			UploadUtil.outputFile(XlsExpUtil.class.getClassLoader().getResourceAsStream(tempPath + "/" + template),response);
		}
	}


	/**
	 * 导出对象数据
	 * @param filename		导出后的文件名
	 * @param contents		导出内容
	 * @param request		请求
	 * @param response		响应
	 */
	public static void export(String filename, List contents, HttpServletRequest request, HttpServletResponse response){
		try {
			filename = WebUtil.fileNameForBrowser(filename, request);
			response.setHeader("Content-disposition","attachment; " + filename + ".xls");
			response.setContentType("application/msexcel");
			export(response.getOutputStream(), contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出对象数据
	 * @param filename		导出后的文件名
	 * @param titles		标题列表
	 * @param props			属性列表
	 * @param objects		对象列表
	 * @param request		请求
	 * @param response		响应
	 */
	public static void export(String filename, List titles, List<String> props, List objects, HttpServletRequest request, HttpServletResponse response){
		try {
			filename = WebUtil.fileNameForBrowser(filename, request);
			response.setHeader("Content-disposition","attachment; " + filename + ".xls");
			response.setContentType("application/msexcel");
			List<Object> titleList = new ArrayList<>();
			titleList.add(titles);
			export(response.getOutputStream(), titleList, props, objects);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据写入到输出流中
	 * @param out			输出流
	 * @param contents		内容数据
	 */
	private static void export(OutputStream out, List contents) {
		HSSFWorkbook wb = new HSSFWorkbook();
		for (int i=0;i<contents.size();i++){
			Map<String, Object> content = (Map<String, Object>) contents.get(i);
			writeSheet(wb, content, i);
		}
		try {
			wb.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据写入到输出流中
	 * @param out			输出流
	 * @param titles		标题列表
	 * @param props			属性列表
	 * @param objects		对象列表
	 */
	private static void export(OutputStream out, List titles, List<String> props, List objects){
		HSSFWorkbook wb = new HSSFWorkbook();
		Map<String, Object> content = new HashMap<>();
		content.put("titles", titles);
		content.put("props", props);
		content.put("objects", objects);
		writeSheet(wb, content, 0);
		try {
			wb.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 将数据写入到单一的sheet页中
	 * @param wb			excel对象
	 * @param content		内容
	 */
	private static void writeSheet(HSSFWorkbook wb, Map content, int count){
		// 1、初始化信息
		//页签名称【默认sheetName】
		String sheetName = (String) content.get("sheetName");
		//标题，支持多行【*】
		List titles = (List) content.get("titles");
		//属性【*】
		List<String> props = (List<String>) content.get("props");
		//对象集合【*】
		List objects = (List) content.get("objects");
		//json反序列化【默认处理】
		List<SerializeFilter> filters = (List<SerializeFilter>) content.get("filters");
		List<SerializerFeature> features  = (List<SerializerFeature>) content.get("features");

		//单元格宽度【默认】
		Integer columnWidth = (Integer) content.get("columnWidth");
		//合并单元格【默认无】
		List<CellRangeAddress> cellRanges = (List<CellRangeAddress>) content.get("cellRanges");
		//标题样式【默认处理】
		HSSFCellStyle styleTitle = (HSSFCellStyle) content.get("styleTitle");
		//单元格样式【默认处理】
		HSSFCellStyle styleCell = (HSSFCellStyle) content.get("styleCell");

		// 2、创建页签
		if(sheetName == null || sheetName.equals("")) sheetName = "Sheet"+(count+1);
		HSSFSheet sheet = wb.createSheet(sheetName);
		if (columnWidth!=null) sheet.setDefaultColumnWidth(columnWidth);
		HSSFRow row;
		HSSFCell cell;

		// 3、填充标题行
		//设置标题单元格样式
		if (styleTitle==null) styleTitle = defStyleTitle(wb);
		//填充标题
		int titleNo = 0;
		if (titles!=null && titles.size()>0){
			titleNo = titles.size();
			for (int i=0;i<titleNo;i++){
				row = sheet.createRow(i);
				List<String> ts = (List<String>) titles.get(i);
				if (ts!=null && ts.size()>0) {
					for (int j = 0; j < ts.size(); j++) {
						cell = row.createCell(j);
						cell.setCellStyle(styleTitle);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(ts.get(j));
					}
				}
			}
		}

		// 4、填充数据行
		if (objects==null || objects.size()==0) {
			return;
		}
		//设置数据单元格样式
		if (styleCell==null) styleCell = defStyleCell(wb);

		//初始化 fast json 序列化过滤器
		SerializeFilter[] fs = null;
		if (filters!=null && filters.size()>0){
			fs = new SerializeFilter[filters.size()];
			filters.toArray(fs);
		}
		//初始化 fast json 序列化特征集
		SerializerFeature[] ss;
		if (features!=null && features.size()>0){
			ss = new SerializerFeature[features.size()];
			features.toArray(ss);
		}else{
			ss = new SerializerFeature[3];
			ss[0] = SerializerFeature.WriteEnumUsingToString;
			ss[1] = SerializerFeature.WriteNullStringAsEmpty;
			ss[2] = SerializerFeature.WriteDateUseDateFormat;
		}

		//填充数据
		int rowNo = objects.size();
		for(int i=0;i<rowNo;i++){
			row = sheet.createRow(i+titleNo);
			//处理对象，转为 json 对象
			String text = JSON.toJSONString(objects.get(i), fs, ss);
			JSONObject obj = (JSONObject) JSON.parse(text);
			//将 json 对象导出
			for(int j=0;j<props.size();j++){
				cell = row.createCell(j);
				cell.setCellStyle(styleCell);
				String[] prs = props.get(j).split("\\.");
				Object value;
				try{
					value = getValueByProp(obj,prs);
				}catch(Exception e){
					System.out.println("获取属性值失败！");
					e.printStackTrace();
					value = null;
				}
				cell.setCellValue(value != null ? value.toString() : "");
			}
		}

		// 5、合并单元格
		if (cellRanges!=null && cellRanges.size()>0){
			for (CellRangeAddress cellRange:cellRanges){
				sheet.addMergedRegion(cellRange);
				RegionUtil.setBorderTop(BorderStyle.THIN, cellRange, sheet); 	// 上边框
				RegionUtil.setBorderRight(BorderStyle.THIN, cellRange, sheet); 	// 右边框
				RegionUtil.setBorderBottom(BorderStyle.THIN, cellRange, sheet); // 下边框
				RegionUtil.setBorderLeft(BorderStyle.THIN, cellRange, sheet); 	// 左边框
			}
		}
	}


	/**
	 * 获取json对象属性值
	 * @param obj			json对象
	 * @param props			属性集合
	 * @return object		属性值
	 */
	private static Object getValueByProp(JSONObject obj, String[] props){
		if(props.length>1){
			for(int i=0;i<props.length-1;i++){
				if(obj.get(props[i])!=null){
					Object object=obj.get(props[i]);
					if (object.getClass().getSimpleName().equals("JSONArray")) {
						return getValuesByProp((JSONArray)object, getCutArray(props,i+1));
					}
					obj = (JSONObject) obj.get(props[i]);
				}else{
					return null;
				}
			}
			if(obj!=null && !obj.toString().equals("null")){
				return obj.get(props[props.length-1]);
			}else{
				return null;
			}
		}
		return obj.get(props[0]);
	}

	/**
	 * 获取json 数组对象属性值
	 * @param array			json 数组对象
	 * @param props			属性集合
	 * @return object		属性值
	 */
	private static Object getValuesByProp(JSONArray array, String[] props){
		StringBuilder valueStr= new StringBuilder();
		for (int i = 0; i < array.size(); i++) {
			Object obj=getValueByProp(array.getJSONObject(i), props);
			valueStr.append(obj == null ? "" : "," + obj.toString());
		}
		if (valueStr.length()>0) {
			valueStr = new StringBuilder(valueStr.substring(1));
		}
		return valueStr.toString();
	}

	/**
	 * 获取截断的数组
	 * @param arrays		原数组
	 * @param start			开始截断位置
	 * @return String[]
	 */
	private static String[] getCutArray(String[] arrays, int start){
		String[] newArrays=new String[arrays.length-start];
		System.arraycopy(arrays, start, newArrays, 0, newArrays.length);
		return newArrays;
	}


	/**
	 * 默认的标题单元格样式
	 * @param wb			excel对象
	 * @return HSSFCellStyle
	 */
	private static HSSFCellStyle defStyleTitle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		font.setBold(true);
		style.setFont(font);//字体
		style.setAlignment(HorizontalAlignment.CENTER);//居中
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//前景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//完整填充
		style.setBorderBottom(BorderStyle.THIN); //下边框
		style.setBorderLeft(BorderStyle.THIN);//左边框
		style.setBorderTop(BorderStyle.THIN);//上边框
		style.setBorderRight(BorderStyle.THIN);//右边框
		return style;
	}

	/**
	 * 默认的数据单元格样式
	 * @param wb			excel对象
	 * @return HSSFCellStyle
	 */
	private static HSSFCellStyle defStyleCell(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("宋体");
		style.setFont(font);//字体
		style.setBorderBottom(BorderStyle.THIN); //下边框
		style.setBorderLeft(BorderStyle.THIN);//左边框
		style.setBorderTop(BorderStyle.THIN);//上边框
		style.setBorderRight(BorderStyle.THIN);//右边框
		style.setWrapText(true);//设置自动换行
		return style;
	}

}
