package com.lfp.jec.frame.util;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * Project: lfp-jec
 * Title: 加解密工具类
 * Description: 用于处理加密和解密等操作
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class MD5Util {
	
	/**
	 * MD5编码
	 * @param s             明文
	 * @return String       密文
	 */
    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * MD5和BASE64混合编码
     * @param s             明文
     * @return String       密文
     */
    public static String MD5BASE64(String s) {
    	try {
    		MessageDigest md = MessageDigest.getInstance("md5");
    		byte[] md5 = md.digest(s.getBytes());
            return new String(Base64.getEncoder().encode(md5));
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
    }


    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("123456"));
        System.out.println(MD5Util.MD5BASE64("666666"));
    }
    
}