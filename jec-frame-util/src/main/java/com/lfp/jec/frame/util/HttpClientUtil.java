package com.lfp.jec.frame.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Project: lfp-jec
 * Title: HttpClient工具类
 * Description: 利用HttpClient在后台模拟发起http请求，作为接口的访问者
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class HttpClientUtil {

    /**
     * 获取请求参数
     * @param request       http请求参数
     * @return list         参数集
     */
    public static List<NameValuePair> fetchRequestParams(HttpServletRequest request){
        List<NameValuePair> params = new ArrayList<>();
        Enumeration enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName =(String)enu.nextElement();
            params.add(new BasicNameValuePair(paraName, request.getParameter(paraName)));
        }
        return params;
    }

    /**
     * 获取请求参数
     * @param map           map参数
     * @return list         参数集
     */
    public static List<NameValuePair> fetchMapParams(Map<String, String> map){
        List<NameValuePair> params = new ArrayList<>();
        for (String key : map.keySet()){
            params.add(new BasicNameValuePair(key, map.get(key)));
        }
        return params;
    }

    /**
     * 获取请求参数
     * @param json          json参数
     * @return list         参数集
     */
    public static List<NameValuePair> fetchJsonParams(JSONObject json){
        List<NameValuePair> params = new ArrayList<>();
        for (String key : json.keySet()){
            params.add(new BasicNameValuePair(key, json.getString(key)));
        }
        return params;
    }


    /**
     * 根据参数，请求接口，获取返回结果
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param request       http请求
     * @return JSONString   返回结果
     */
    public static String requestApi(String url, String token, String tenant, HttpServletRequest request) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("token", token);
            httpPost.setHeader("tenant", tenant);

            List<NameValuePair> params = fetchRequestParams(request);
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));

            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            return respContent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据参数，请求接口，获取返回结果
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param map           请求参数
     * @return JSONString   返回结果
     */
    public static String requestApi(String url, String token, String tenant, Map<String, String> map) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("token", token);
            httpPost.setHeader("tenant", tenant);

            List<NameValuePair> params = fetchMapParams(map);
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));

            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            return respContent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据参数，请求接口，获取返回结果
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param json          请求参数
     * @return JSONString   返回结果
     */
    public static String requestApi(String url, String token, String tenant, JSONObject json) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("token", token);
            httpPost.setHeader("tenant", tenant);

            List<NameValuePair> params = fetchJsonParams(json);
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));

            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            return respContent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据参数，请求接口，获取返回结果
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param request       http请求
     * @param map           请求参数
     * @param json          请求参数
     * @return JSONString   返回结果
     */
    public static String requestApi(String url, String token, String tenant, HttpServletRequest request, Map<String, String> map, JSONObject json) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("token", token);
            httpPost.setHeader("tenant", tenant);

            List<NameValuePair> params = fetchRequestParams(request);
            if (map!=null && map.size()>0){
                params.addAll(fetchMapParams(map));
            }
            if (json!=null && json.size()>0){
                params.addAll(fetchJsonParams(json));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));

            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            return respContent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据参数，请求接口，上传文件，用于文件传输
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param request       http请求
     * @param inputStream   文件输入流
     * @return JSONString   返回结果
     */
    public static String requestFileUp(String url, String token, String tenant, HttpServletRequest request, InputStream inputStream) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("token", token);
            httpPost.setHeader("tenant", tenant);
            //file
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", inputStream, ContentType.MULTIPART_FORM_DATA, request.getParameter("name"));// 文件流
            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            builder.addTextBody("name", request.getParameter("name"), contentType);
            builder.addTextBody("type", request.getParameter("type"), contentType);
            builder.addTextBody("system", request.getParameter("system")==null?"":request.getParameter("system"), contentType);

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            return respContent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据参数，请求接口，下载文件，用于文件传输
     * @param url           资源定位
     * @param token         访问授权
     * @param tenant        租户标志
     * @param request       http请求
     * @return inputStream  文件输入流
     */
    public static HttpResponse requestFileDown(String url, String token, String tenant, HttpServletRequest request){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url+"?id="+request.getParameter("id"));
            httpGet.addHeader("token", token);
            httpGet.addHeader("tenant", tenant);
            httpGet.addHeader("USER-AGENT",request.getHeader("USER-AGENT"));//用于编码文件名
            return client.execute(httpGet);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
