package com.lfp.jec.frame.util.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.util.Map;

/**
 * Project: lfp-jec
 * Title: Groovy 帮助类
 * Description: Groovy 帮助类
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class GroovyUtil {

	/**
	 * 根据参数对象计算表达式的值
	 * @param expression	表达式
	 * @param object		参数对象 Object
	 * @return object		计算结果
	 */
	public static Object calculate(String expression, Object object){
		Binding binding = new Binding();
		binding.setVariable("obj", object);
		return calculate(binding, expression);
	}

	/**
	 * 根据参数对象计算表达式的值
	 * @param expression	表达式
	 * @param params		参数集合 params
	 * @return object		计算结果
	 */
	public static Object calculate(String expression, Map<String, Object> params){
		Binding binding = new Binding();
		for(String key : params.keySet()){
			binding.setVariable(key, params.get(key));
		}
		return calculate(binding, expression);
	}


	/**
	 * 根据参数对象计算表达式的值
	 * @param expression	表达式
	 * @param object		参数对象 Object
	 * @return str			校验结果
	 */
	public static String validation(String expression, Object object) {
		Binding binding = new Binding();
		binding.setVariable("obj", object);
		Object ret = calculate(binding, expression);
		if (ret!=null) return ret.toString();
		return null;
	}

	/**
	 * 根据参数对象计算表达式的值
	 * @param expression	表达式
	 * @param params		参数集合 params
	 * @return str			校验结果
	 */
	public static String validation(String expression, Map<String, Object> params) {
		Binding binding = new Binding();
		for(String key : params.keySet()){
			binding.setVariable(key, params.get(key));
		}
		Object ret = calculate(binding, expression);
		if (ret!=null) return ret.toString();
		return null;
	}


	/**
	 * 计算表达式的值
	 * @param binding		Groovy对象
	 * @param expression	Groovy表达式
	 * @return Object		计算结果
	 */
	private static Object calculate(Binding binding, String expression) {
		GroovyShell gs = new GroovyShell(binding);
		try{
			return gs.evaluate(expression);
		}catch(Exception e){
			e.printStackTrace();
			return "计算表达式出现错误，请检查表达式和参数配置";
		}
	}


	/**
	 * 执行指定接口方法
	 * @param pathName		groovy类文件全路径地址
	 * @param params		输入参数
	 * @return Object		输出参数
	 * @throws Exception	异常
	 */
	public static Object executeMethod(String pathName, Object params) throws Exception {
		GroovyClassLoader loader= new GroovyClassLoader();
		Class groovyClass =loader.parseClass(new File(pathName));
		GroovyObject object =(GroovyObject) groovyClass.newInstance();
		GroovyExecute exe = (GroovyExecute)object;
		return exe.execute(params);
	}

	/**
	 * 执行指定类方法
	 * @param pathName		groovy类文件全路径地址
	 * @param methodName	groovy类中的方法名
	 * @param params		输入参数
	 * @return Object		输出参数
	 * @throws Exception	异常
	 */
	public static Object executeMethod(String pathName, String methodName, Object params) throws Exception {
		GroovyClassLoader loader= new GroovyClassLoader();
		Class groovyClass =loader.parseClass(new File(pathName));
		GroovyObject object =(GroovyObject) groovyClass.newInstance();
		return object.invokeMethod(methodName,params);
	}

	/**
	 * 执行指定脚本内容
	 * @param script		脚本内容
	 * @param params		输入参数
	 * @return Object		输出参数
	 * @throws Exception	异常
	 */
	public static Object executeScript(String script, Object params) throws Exception {
		Binding binding = new Binding();
		binding.setVariable("params", params);
		GroovyShell gs = new GroovyShell(binding);
		return gs.evaluate(script);
	}

	/**
	 * 执行指定脚本文件
	 * @param url			脚本目录
	 * @param scriptName	脚本名称
	 * @param params		输入参数
	 * @return Object		输出参数
	 * @throws Exception	异常
	 */
	public static Object executeScript(String url, String scriptName, Object params) throws Exception {
		GroovyScriptEngine engine = new GroovyScriptEngine(url);
		Binding binding = new Binding();
		binding.setVariable("params", params);
		return engine.run(scriptName, binding);
	}

}
