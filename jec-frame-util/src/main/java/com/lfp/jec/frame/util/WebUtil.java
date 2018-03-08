package com.lfp.jec.frame.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Project: lfp-jec
 * Title: web交互工具类
 * Description: 用于处理web交互过程中各种赋值、编码等问题
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class WebUtil {

	/**
	 * 根据浏览器类型编码中文文件名称
	 * @param filename	文件名
	 * @param request	http请求
	 * @return str
	 */
	public static String fileNameForBrowser(String filename, HttpServletRequest request){
		String rtn = filename;
		String userAgent = request.getHeader("USER-AGENT");
		String new_filename;
		try {
			new_filename = URLEncoder.encode(filename, "UTF-8");
			new_filename = new_filename.replaceAll("\\+", "%20");//解决空格变加号的问题
			rtn = "filename=\"" + new_filename + "\"";  
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();  
				if (userAgent.contains("msie"))
					rtn = "filename=\"" + new String(filename.getBytes("GBK"),"ISO8859-1") + "\"";
				else if (userAgent.contains("safari"))
					rtn = "filename=\"" + new String(filename.getBytes("UTF-8"),"ISO8859-1") + "\"";
				else if (userAgent.contains("opera"))
					rtn = "filename*=UTF-8''" + new_filename;
				else if (userAgent.contains("mozilla"))
					rtn = "filename*=UTF-8''" + new_filename;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rtn;
	}

	/**
	 * 解码浏览器传入
	 * @param inStr		输入文本
	 * @return str
	 */
	public static String decodeUrlStr(String inStr){
		String outStr;
		try {
			outStr = URLDecoder.decode(inStr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		return outStr;
	}


	/**
	 * 响应报文中添加浏览器cookie
	 * @param request		http请求
	 * @param response		http响应
	 * @param tenant		租户标识tenant
	 * @param account		登录账号
	 * @param password		登录密码
	 * @throws UnsupportedEncodingException 编码异常
	 */
	public static void addCookies(HttpServletRequest request, HttpServletResponse response, String tenant, String account, String password) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(tenant) && StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
			//创建Cookie
			Cookie tenantCookie = new Cookie("tenant", URLEncoder.encode(tenant,"utf-8"));
			Cookie accountCookie = new Cookie("account", URLEncoder.encode(account,"utf-8"));
			Cookie passwordCookie = new Cookie("password", password);

			//设置Cookie的父路径
			tenantCookie.setPath(request.getContextPath()+"/");
			accountCookie.setPath(request.getContextPath()+"/");
			passwordCookie.setPath(request.getContextPath()+"/");

			//获取是否保存Cookie
			String rememberAcc = request.getParameter("rememberAcc");
			String rememberPwd = request.getParameter("rememberPwd");
			if(rememberAcc== null){//不保存Cookie
				tenantCookie.setMaxAge(0);
				accountCookie.setMaxAge(0);
			}else{//保存Cookie的时间长度，单位为秒
				tenantCookie.setMaxAge(30*24*60*60);
				accountCookie.setMaxAge(30*24*60*60);
			}
			if(rememberPwd == null){//不保存Cookie
				passwordCookie.setMaxAge(0);
			}else{//保存Cookie的时间长度，单位为秒
				passwordCookie.setMaxAge(30*24*60*60);
			}
			//加入Cookie到响应头
			response.addCookie(tenantCookie);
			response.addCookie(accountCookie);
			response.addCookie(passwordCookie);
		}
	}


	/**
	 * 获取请求路径
	 * @param request		http请求
	 * @return path
	 */
	public static String getPath(HttpServletRequest request){
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		return url.substring(contextPath.length());
	}

	/**
	 * 获取请求地址+参数
	 * @param request		http请求
	 * @return url
	 */
	public static String getUrl(HttpServletRequest request){
		String url = request.getRequestURI();
		if(request.getQueryString() != null){
			url += "?"+request.getQueryString();
		}
		return url;
	}

	/**
	 * 获取登录IP
	 * @param request	http请求
	 * @return ip
	 */
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/**
	 * 从请求文本中获取token信息
	 * @param request		http请求
	 * @return token		登录票据token
	 */
	public static String getToken(HttpServletRequest request){
		String token = request.getParameter("token");
		if (token == null || token.length() == 0 || "unknown".equalsIgnoreCase(token)) {
			token = request.getHeader("token");
		}
		if(token == null || token.length() == 0 || "unknown".equalsIgnoreCase(token)){
			HttpSession session = request.getSession();
			if (session!=null){
				Object t = session.getAttribute("token");
				if(t!=null) token = t.toString();
			}
		}
		if(token == null || token.length() == 0 || "unknown".equalsIgnoreCase(token)){
			return null;
		}
		return token;
	}

	/**
	 * 从请求文本中获取tenant信息
	 * @param request		http请求
	 * @return tenant		租户标志
	 */
	public static String getTenant(HttpServletRequest request){
		String tenant = request.getParameter("tenant");
		if (tenant == null || tenant.length() == 0 || "unknown".equalsIgnoreCase(tenant)) {
			tenant = request.getHeader("tenant");
		}
		if(tenant == null || tenant.length() == 0 || "unknown".equalsIgnoreCase(tenant)){
			HttpSession session = request.getSession();
			if (session!=null){
				Object t = session.getAttribute("tenant");
				if(t!=null) tenant = t.toString();
			}
		}
		if(tenant == null || tenant.length() == 0 || "unknown".equalsIgnoreCase(tenant)){
			return null;
		}
		return tenant;
	}

	/**
	 * 从请求文本中获取account信息
	 * @param request		http请求
	 * @return tenant		租户标志
	 */
	public static String getAccount(HttpServletRequest request){
		String account = request.getParameter("account");
		if (account == null || account.length() == 0 || "unknown".equalsIgnoreCase(account)) {
			account = request.getHeader("account");
		}
		if(account == null || account.length() == 0 || "unknown".equalsIgnoreCase(account)){
			HttpSession session = request.getSession();
			if (session!=null){
				Object t = session.getAttribute("account");
				if(t!=null) account = t.toString();
			}
		}
		if(account == null || account.length() == 0 || "unknown".equalsIgnoreCase(account)){
			return null;
		}
		return account;
	}

}
