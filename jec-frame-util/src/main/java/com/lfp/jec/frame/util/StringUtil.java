package com.lfp.jec.frame.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project: lfp-jec
 * Title: 字符串处理工具类
 * Description: 补充commons-lang3，实现部分自定义的字符串处理函数
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class StringUtil {

	/** 分隔符 */
	private static final String SEPARATOR = ",";

	/**
	 * 将字符串List拼接为字符串
	 * @param strList				字符串列表
	 * @return String				字符串
	 */
	public static String spellList(List<String> strList){
		if (strList==null) return null;
		StringBuilder result = new StringBuilder();
		if(strList.size()>0){
			for(int i=0;i<(strList.size()-1);i++){
				result.append(strList.get(i)).append(SEPARATOR);
			}
			result.append(strList.get(strList.size() - 1));
		}
		return result.toString();
	}

	/**
	 * 将字符串Set拼接为字符串
	 * @param strSet				字符串集合
	 * @return String				字符串
	 */
	public static String spellSet(Set<String> strSet){
		if (strSet==null) return null;
		StringBuilder result = new StringBuilder();
		if(strSet.size()>0){
			for (String str : strSet){
				result.append(str).append(SEPARATOR);
			}
			result.deleteCharAt(result.length()-1);
		}
		return result.toString();
	}

	/**
	 * 将字符串Array拼接为字符串
	 * @param strArray				字符串数组
	 * @return String				字符串
	 */
	public static String spellArray(String[] strArray){
		if (strArray==null) return null;
		StringBuilder result = new StringBuilder();
		if(strArray.length>0){
			for (String str : strArray){
				result.append(str).append(SEPARATOR);
			}
			result.deleteCharAt(result.length()-1);
		}
		return result.toString();
	}


	/**
	 * 将逗号分隔的字符串，拆解为字符串List
	 * @param strString				字符串
	 * @return list					字符串列表
	 */
	public static List<String> splitList(String strString){
		if(strString==null) return null;
		List<String> results = new ArrayList<>();
		String []objects = strString.split(SEPARATOR);
		for (String object : objects) {
			if (object != null && !object.equals("")) {
				results.add(object);
			}
		}
		return results;
	}

	/**
	 * 将逗号分隔的字符串，拆解为字符串Set
	 * @param strString				字符串
	 * @return set					字符串集合
	 */
	public static Set<String> splitSet(String strString){
		if(strString==null) return null;
		Set<String> results = new HashSet<>();
		String []objects = strString.split(SEPARATOR);
		for (String object : objects) {
			if (object != null && !object.equals("")) {
				results.add(object);
			}
		}
		return results;
	}

	/**
	 * 将逗号分隔的字符串，拆解为字符串Array
	 * @param strString				字符串
	 * @return set					字符串数组
	 */
	public static String[] splitArray(String strString){
		if(strString==null) return null;
		return strString.split(SEPARATOR);
	}


	/**
	 * 根据字符串情况返回Integer值
	 * @param value					字符串值
	 * @return Integer				整形值
	 */
	public static Integer parseInteger(String value){
		if(value==null || value.equals("")) return null;
		BigDecimal v = new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP);
		return v.intValue();
	}

	/**
	 * 根据字符串情况动态返回不小于指定精度的Double值
	 * 原字符串精度低的保留原精度，原精度高的按指定精度四舍五入
	 * @param value					double值字符串
	 * @param precision				指定精度
	 * @return Double				浮点型值
	 */
	public static Double parseDouble(String value, int precision){
		if(value==null || value.equals("")) return null;
		int len = value.length()-value.indexOf(".")-1;
		if(len < precision) precision = len;
		BigDecimal v = new BigDecimal(value).setScale(precision, BigDecimal.ROUND_HALF_UP);
		return v.doubleValue();
	}


	public static void main(String[] args) {
		System.out.println(StringUtil.spellList(new ArrayList<String>() {{
			add("abc");
			add("def");
		}}));
		System.out.println(StringUtil.splitList("xyz,xyz"));
		System.out.println(StringUtil.spellSet(new HashSet<String>() {{
			add("abc");
			add("abc");
		}}));
		System.out.println(StringUtil.splitSet("xyz,xyz"));
	}

}
