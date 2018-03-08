package com.lfp.jec.frame.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: lfp-jec
 * Title: JavaBean工具类
 * Description: 用于处理JavaBean的反射
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class JavaBeanUtil {

	/**
	 * 获取对象List指定属性的List
	 * @param objects			对象list
	 * @param methodName 		属性获取方法名，一般为get方法
	 * @return list
	 */
	public static List<Object> getValueListByMethod(List<Object> objects, String methodName) throws Exception  {
		List<Object> props = new ArrayList<>();
		for (Object obj : objects) {
			Method getMethod = obj.getClass().getMethod(methodName);
			Object value = getMethod.invoke(obj);
			props.add(value);
		}
		return props;
	}


	/**
	 * 根据属性名称获取静态属性值
	 * @param object    	对象
	 * @param fieldName 	属性名
	 * @return Object       属性值
	 * @throws Exception 	异常
	 */
	public static Object getStaticValueByName(Object object, String fieldName) throws Exception {
		if (fieldName == null || fieldName.trim().length() == 0) return null;
		Field field = object.getClass().getDeclaredField(fieldName);
		return field.get(object.getClass());
	}


	/**
	 * 根据属性名称获取属性值
	 * @param object    	对象
	 * @param fieldName 	属性名
	 * @return Object       属性值
	 * @throws Exception 	异常
	 */
	public static Object getFieldValueByName(Object object, String fieldName) throws Exception {
		if (fieldName == null || fieldName.trim().length() == 0) return null;
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		Method method = object.getClass().getMethod(getter);
		return method.invoke(object);
	}

	/**
	 * 根据递归的属性名称获取属性值
	 * @param object    	实体对象
	 * @param fieldName 	属性名称a.b.c
	 * @return Object       属性值
	 * @throws Exception 	异常
	 */
	public static Object getRecursiveFieldValueByName(Object object, String fieldName) throws Exception {
		if (fieldName == null || fieldName.trim().length() == 0) return null;
		int place = fieldName.indexOf(".");
		//存在多级子属性
		if (place != -1) {
			//本级子属性名称
			String firstProp = fieldName.substring(0, place);
			//次级子属性名称
			String secondProp = fieldName.substring(place + 1);
			//获取本级子属性
			Object obj = getFieldValueByName(object, firstProp);
			//递归执行
			return getRecursiveFieldValueByName(obj, secondProp);
		} else {
			//只有一级子属性
			return getFieldValueByName(object, fieldName);
		}
	}


	/**
	 * 设置指定对象指定属性的值
	 * @param object    	对象
	 * @param fieldName 	属性名
	 * @param value     	属性值
	 * @throws Exception 	异常
	 */
	public static void setFieldValueByName(Object object, String fieldName, Object value) throws Exception {
		if (fieldName == null || fieldName.trim().length() == 0) return;
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String setter = "set" + firstLetter + fieldName.substring(1);
		object.getClass().getMethod(setter, new Class[]{value.getClass()}).invoke(object, value);
	}

	/**
	 * 设置指定对象指定根据递归属性的值
	 * @param object    	实体对象
	 * @param fieldName 	属性名称a.b.c
	 * @param value     	属性值
	 * @throws Exception 	异常
	 */
	public static void setRecursiveFieldValueByName(Object object, String fieldName, Object value) throws Exception {
		if (fieldName == null || fieldName.trim().length() == 0) return;
		int place = fieldName.indexOf(".");
		//存在多级子属性
		if (place != -1) {
			//本级子属性名称
			String firstProp = fieldName.substring(0, place);
			//次级子属性名称
			String secondProp = fieldName.substring(place + 1);
			//获取本级子属性
			Object obj = getFieldValueByName(object, firstProp);
			//递归执行
			setRecursiveFieldValueByName(obj, secondProp, value);
		} else {
			//只有一级子属性
			setFieldValueByName(object, fieldName, value);
		}
	}


	public static void main(String[] args) {

	}

}