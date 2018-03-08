package com.lfp.jec.frame.util;

import java.io.*;
import java.net.URL;

/**
 * Project: lfp-jec
 * Title: 文件操作工具类
 * Description: 用于处理文件系统的新增、删除、拷贝等功能
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class FileUtil {

	/** 单次拷贝缓存大小 */
	private static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * 获取文件扩展名
	 * @param filename		文件全名
	 * @return str
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * @param filename		文件全名
	 * @param defExt		默认扩展名
	 * @return str
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 获取文件名称[不含后缀名]
	 * 不去掉文件目录的空格
	 * @param filename		文件全名
	 * @return String
	 */
	public static String getFilePrefix(String filename) {
		int splitIndex = filename.lastIndexOf(".");
		return filename.substring(0, splitIndex);
	}

	/**
	 * 获取文件名称[不含后缀名]
	 * 去掉文件目录的空格
	 * @param filename		文件全名
	 * @return String
	 */
	public static String getFilePrefixNoSpace(String filename) {
		int splitIndex = filename.lastIndexOf(".");
		return filename.substring(0, splitIndex).replaceAll("\\s*", "");
	}

	/**
	 * 从路径中获取文件名
	 * @param path			全路径名
	 * @return fileName		xx.xx
	 */
	public static String getFileName(String path) {
		int splitIndex = path.lastIndexOf("/");
		return path.substring(splitIndex+1);
	}

	/**
	 * 从路径中获取文件名
	 * @param path			全路径名
	 * @return docName		/xxxx
	 */
	public static String getDocName(String path) {
		int splitIndex = path.lastIndexOf("/");
		return path.substring(0, splitIndex);
	}

	/**
	 * 删除指定的文件
	 * @param file			指定文件
	 * @return 				如果删除成功true否则false
	 */
	public static boolean delete(File file) {
		return !(!file.exists() || !file.isFile()) && file.delete();
	}

	/**
	 * 删除指定的文件
	 * @param strFileName	指定绝对路径的文件名
	 * @return 				如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);
		return !(!fileDelete.exists() || !fileDelete.isFile()) && fileDelete.delete();
	}

	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 			将要删除的文件目录
     * @return 				如果删除成功true否则false
     */
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
			if (children !=null){
				for (String child : children) {
					boolean success = deleteDir(new File(dir, child));
					if (!success) {
						return false;
					}
				}
			}
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dirStr		将要删除的文件目录名称
	 * @return 				如果删除成功true否则false
	 */
	public static boolean deleteDir(String dirStr) {
		File dirDelete = new File(dirStr);
		if ( !dirDelete.exists() ) {
			return false;
		}
		return deleteDir(dirDelete);
	}

	/**
	 * 文件复制
	 * @param inputFile		原始文件
	 * @param outputFile	拷贝文件
	 * @throws Exception	异常
	 */
	public static void copyFile(String inputFile, String outputFile) throws Exception {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		copy(sFile,tFile);
	}

	/**
	 * 文件复制
	 * @param src			原始文件
	 * @param dst			拷贝文件
	 * @throws Exception	异常
	 */
	public static void copy(File src, File dst) throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dst);
			int temp;
			byte[] buf = new byte[BUFFER_SIZE];
        	while((temp = fis.read(buf))!=-1){   
        		fos.write(buf, 0, temp);   
            }
        } finally{
			if (fis!=null) fis.close();
			if (fos!=null) fos.close();
		}
	}

	/**
	 * 把文件输入流写入指定文件中
	 * @param src			原始文件
	 * @param dst			拷贝文件
	 * @throws Exception	异常
	 */
	public static void copy(InputStream src, File dst) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(dst);
			int temp;
			byte[] buf = new byte[BUFFER_SIZE];
			while((temp = src.read(buf))!=-1){
				fos.write(buf, 0, temp);
			}
		} finally{
			if (fos!=null) fos.close();
		}
	}
	
	/**
	 * 文件读取：从绝对目录中读取文件字符串
	 * @param absolutePath	文件绝对地址
	 * @return result
	 */
	public static String readFile(String absolutePath){
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try{
			FileInputStream fileInputStream = new FileInputStream(absolutePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString;
			while((tempString = reader.readLine()) != null){
				result.append(tempString);
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	/**
	 * 文件读取：从输入流中读取文件字符串
	 * @param in		输入流
	 * @return result
	 */
	public static String readFile(InputStream in) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try{
			InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString;
			while((tempString = reader.readLine()) != null){
				result.append(tempString);
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	/**
	 * 获取 WEB-INF 文件夹地址
	 * @return path		文件夹地址
	 */
	public static String getWebInfPath(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		if (url==null) url = ClassLoader.getSystemResource("");
		if (url==null) url = FileUtil.class.getClassLoader().getResource("");
		if (url==null) url = FileUtil.class.getResource("/");
		String path = url.getPath();
		if (path.indexOf("WEB-INF")>0){
			path = path.substring(0, path.indexOf("WEB-INF"))+"WEB-INF/";
		} else if (path.indexOf("classes")>0){
			path = path.substring(0, path.indexOf("classes"));
		}
		return path;
	}

	/**
	 * 获取 classes 文件夹地址
	 * @return path		文件夹地址
	 */
	public static String getClassesPath(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		if (url==null) url = ClassLoader.getSystemResource("");
		if (url==null) url = FileUtil.class.getClassLoader().getResource("");
		if (url==null) url = FileUtil.class.getResource("/");
		String path = url.getPath();
		if (path.indexOf("classes")>0){
			path = path.substring(0, path.indexOf("classes"))+"classes/";
		} else if (path.indexOf("WEB-INF")>0){
			path = path.substring(0, path.indexOf("WEB-INF"))+"WEB-INF/classes/";
		}
		return path;
	}

	/**
	 * 获取 resources 文件夹地址
	 * @return path		文件夹地址
	 */
	public static String getResourcePath(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		if (url==null) url = ClassLoader.getSystemResource("");
		if (url==null) url = FileUtil.class.getClassLoader().getResource("");
		if (url==null) url = FileUtil.class.getResource("/");
		String path = url.getPath();
		if (path.indexOf("resources")>0){
			path = path.substring(0, path.indexOf("resources"))+"resources/";
		} else if (path.indexOf("classes")>0){
			path = path.substring(0, path.indexOf("classes"))+"resources/";
		} else if (path.indexOf("WEB-INF")>0){
			path = path.substring(0, path.indexOf("WEB-INF"))+"WEB-INF/resources/";
		}
		return path;
	}

	public static void main(String[] args) throws Exception {  
        // null System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		// null System.out.println(ClassLoader.getSystemResource("/"));
		System.out.println(ClassLoader.getSystemResource(""));
        // null System.out.println(FileUtil.class.getClassLoader().getResource("/"));
		System.out.println(FileUtil.class.getClassLoader().getResource(""));

		System.out.println(FileUtil.class.getResource("/"));
        System.out.println(FileUtil.class.getResource(""));

        //Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(new File(FileUtil.class.getResource("/")+"bpmn").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));

		System.out.println(getWebInfPath());
		System.out.println(getClassesPath());
		System.out.println(getResourcePath());
    }

}
