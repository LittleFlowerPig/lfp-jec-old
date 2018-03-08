package com.lfp.jec.frame.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Project: lfp-jec
 * Title: 文件上传工具类
 * Description: 用于处理文件上传下载功能
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class UploadUtil {

	/** 单次拷贝缓存大小 */
	private static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * 将指定文件存储到文档中心
	 * @param inputStream	指定文件输入流
	 * @param docUrl		文档中心位置（指定文档中心）Globals.depotUrl D\:\\eac\\uploads\\attachment
	 * @param docDir		文档所属目录（指定文件目录）默认为当前时间文件夹 yyyy-MM-dd
	 * @param docId			文档在文档中心中的名称默认为上传id，或者指定为atch的id
	 * @return docPath		文档相对文档中心的位置
	 * @throws Exception	异常
	 */
	public static String saveAtch(InputStream inputStream, String docUrl, String docDir, String docId) throws Exception {
		//0、初始化必要的参数
		if(docDir==null || docDir.trim().length()==0){
			docDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if(docId==null || docId.trim().length()==0){
			docId = UUID.randomUUID().toString()+".ori";
		}
		//1、定位目标文件夹
		String dstDirPath = docUrl + "/" + docDir;
		File dstDir = new File(dstDirPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		//2、定位目标文件
		String dstPath = dstDirPath + "/" + docId;
		File dstFile = new File(dstPath);
		if (dstFile.exists()) {// 文件已存在（上传了同名的文件）
			dstFile.delete();
			dstFile = new File(dstPath);
		}
		//3、存储文件
		FileUtil.copy(inputStream, dstFile);
		return "/" + docDir + "/" + docId;
	}

	/**
	 * 从文档中心拷贝文件到指定位置
	 * @param docUrl		文档中心位置（指定文档中心）Globals.ATTACHMENT_DIR
	 * @param docDir		文档相对文档中心的位置
	 * @param docId			文档ID
	 * @param objUrl		目标文档中心
	 * @param objDir		目标文档目录
	 * @param objName		目标文档名称
	 * @return File			目标文件
	 * @throws Exception	异常
	 */
	public static File copyAtch(String docUrl, String docDir, String docId, String objUrl, String objDir, String objName) throws Exception {
		//1、定位文档
		String srcAbsPath = docUrl + docDir + "/" + docId;
		File srcFile = new File(srcAbsPath);
		//2、定位目标文件夹
		String objAbsPath = objUrl + objDir;
		File dstDir = new File(objAbsPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		//3、定位目标文件
		objAbsPath = objAbsPath + "/" + objName;
		File dstFile = new File(objAbsPath);
		if (dstFile.exists()) {// 文件已存在（上传了同名的文件）
			dstFile.delete();
			dstFile = new File(objAbsPath);
		}
		//4、文件拷贝
		FileUtil.copy(srcFile, dstFile);
		return dstFile;
	}

	/**
	 * 从目标位置读取文件到输出流中
	 * @param docUrl		文档中心位置（指定文档中心）
	 * @param docDir		文件目录（文件相对文档中心的目录位置）
	 * @param docId			文件ID（文件在文档中心中的索引名称）
	 * @param name			文件名称（当时上传时的名称）
	 * @param type			文件类型（上传时记录）
	 * @param request		http 请求
	 * @param response		http 响应
	 */
	public static void fetchAtch(String docUrl, String docDir, String docId, String name, String type, HttpServletRequest request, HttpServletResponse response){
		String dstPath = docUrl + docDir + "/" +docId;
		File file = new File(dstPath);
		name = WebUtil.fileNameForBrowser(name, request);
		response.setContentType(type);
		response.setHeader("Content-disposition", "attachment; " + name);
		response.addHeader("Content-Length", "" + file.length());
		outputFile(file, response);
	}


	/**
	 * 将文件添加到输出流中，
	 * @param filename		指定文件
	 * @param response	http response
	 */
	public static void outputFile(String filename, HttpServletResponse response){
		InputStream ins = null;			//构造一个读取文件的IO流对象
		try {
			ins = new FileInputStream(filename);
		} catch (Exception e) {
			System.err.println("filename="+filename);
			e.printStackTrace();
		}
		outputFile(ins, response);
	}

	/**
	 * 将文件添加到输出流中，
	 * @param file		指定文件
	 * @param response	http response
	 */
	public static void outputFile(File file, HttpServletResponse response){
		InputStream ins = null;			//构造一个读取文件的IO流对象
		if (file.exists()) {
			try {
				ins = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		outputFile(ins, response);
	}

	/**
	 * 将文件添加到输出流中，
	 * @param ins		指定文件
	 * @param response	http response
	 */
	public static void outputFile(InputStream ins, HttpServletResponse response){
		if (ins==null){
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.println("<script>alert('未找到指定文件！');</script>");
			} catch (IOException ee) {
				ee.printStackTrace();
			}finally{
				if (writer!=null) {
					writer.flush();
					writer.close();
				}
			}
			return;
		}
		try {
			BufferedInputStream bins = new BufferedInputStream(ins);	//放到缓冲流里面
			OutputStream outs = response.getOutputStream();				//获取文件输出IO流
			BufferedOutputStream bouts = new BufferedOutputStream(outs);
			int bytesRead;
			byte[] buffer = new byte[BUFFER_SIZE];
			//开始向网络传输文件流
			while ((bytesRead = bins.read(buffer, 0, BUFFER_SIZE)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			bouts.flush();//这里一定要调用flush()方法
			ins.close();
			bins.close();
			outs.close();
			bouts.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.println("<script>alert('文件输出失败！');</script>");
			} catch (IOException ee) {
				ee.printStackTrace();
			}finally{
				if (writer!=null) {
					writer.flush();
					writer.close();
				}
			}
		}
	}

}
