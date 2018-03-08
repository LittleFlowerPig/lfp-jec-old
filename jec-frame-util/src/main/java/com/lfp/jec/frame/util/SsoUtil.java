package com.lfp.jec.frame.util;

/**
 * Project: lfp-jec
 * Title: SSO 工具类
 * Description: 处理单点登录相关内容
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class SsoUtil {

    /**
     * 获取向认证地址请求的文本
     * @param ssoUrl        认证地址
     * @param returnUrl     返回地址
     * @param originalUrl   原始地址
     * @param sessionId     待认证session
     * @return String       请求文本
     */
    public static String getPostContent(String ssoUrl, String returnUrl, String originalUrl, String sessionId) {
        String ret = "";
        ret += "<html><head>";
        ret += "</head><body onload=\"document.Form1.submit()\">";
        ret += "<form name='Form1' method='post' action='" + ssoUrl + "'>";
        ret += "<input name=\"returnUrl\" type=\"hidden\" value=\"" + returnUrl + "\">";
        ret += "<input name=\"sessionId\" type=\"hidden\" value=\"" + sessionId + "\">";
        ret += "<input name=\"originalUrl\" type=\"hidden\" value=\"" + originalUrl + "\">";
        ret += "</form>";
        ret += "</body></html>";
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(SsoUtil.getPostContent("http://localhost:11080/", "http://localhost:11080/", "http://localhost:11080/", "123456"));
    }

}
