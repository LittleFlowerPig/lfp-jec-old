package com.lfp.jec.frame.util.ret;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Project: lfp-jec
 * Title: 标准返回对象
 * Description: 所有接口都以标准返回结果的格式进行返回
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class RetObject implements Serializable {

    /** 返回代码[0:失败, 1:成功, x:其他] */
    private int responseCode;
    /** 返回信息 */
    private String responseMessage;
    /** 返回数据 */
    private Object responseData;


    public RetObject() {
        super();
    }

    public RetObject(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public RetObject(int responseCode, String responseMessage, Object responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }


    /**
     * 对象转JsonString
     * @return      String
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 对象转JSON
     * @return      JSONObject
     */
    public JSONObject toJson(){
        return (JSONObject) JSONObject.toJSON(this);
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

}
