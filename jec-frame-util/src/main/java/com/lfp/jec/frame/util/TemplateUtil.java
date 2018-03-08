package com.lfp.jec.frame.util;

import com.alibaba.fastjson.JSONObject;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: lfp-jec
 * Title: Freemarker 模板解析器
 * Description: 引入 Freemarker，解决EL表达式的问题
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class TemplateUtil {

	/**
	 * EL文本解析
	 * @param expression	EL表达式文本
	 * @param object		参数，可以是obj对象，也可以是map对象
	 * @return ret			解析后文本
	 */
	public static String parse(String expression, Object object){
    	try {
			Configuration stringCfg = new Configuration(Configuration.VERSION_2_3_23);
    		StringTemplateLoader stringLoader = new StringTemplateLoader();
    		stringLoader.putTemplate("tempTemplate",expression);
        	stringCfg.setTemplateLoader(stringLoader);
            Template template = stringCfg.getTemplate("tempTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(object, writer);
            return writer.toString();
        } catch (Exception e) {
    		System.err.println("EL文本编译解析失败");
			System.err.println("表达式为:"+expression);
			System.err.println("对象为:"+JSONObject.toJSONString(object));
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
		Map root = new HashMap();
		root.put("a", "a1212");
		root.put("b", "b3434");
		System.out.println(TemplateUtil.parse("第一个测试程序：${a!} ${b!} ${c!}",root));

	}
	
}
