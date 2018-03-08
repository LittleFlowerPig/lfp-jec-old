package com.lfp.jec.frame.util.ret;

import org.apache.commons.lang3.StringUtils;

/**
 * Project: lfp-jec
 * Title: 返回结果帮助类
 * Description: 帮助构造符合要求的各类返回结果
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class RetUtil {

    private static final String SUCCESS = "操作成功";   //1
    private static final String FAILURE = "操作失败";   //0
    private static final String FINISHED = "操作完成";  //自定义code

    /**
     * 根据信息，封装正确返回结果
     * @return RetObject    返回对象
     */
    public static RetObject returnSuccess(){
        return new RetObject(1, SUCCESS);
    }

    /**
     * 根据信息，封装正确返回结果
     * @param okMsg         正确时返回信息
     * @return RetObject    返回对象
     */
    public static RetObject returnSuccess(String okMsg){
        return new RetObject(1, StringUtils.isNotBlank(okMsg)?okMsg:SUCCESS);
    }

    /**
     * 根据信息，封装错误返回结果
     * @return RetObject    返回对象
     */
    public static RetObject returnFailure(){
        return new RetObject(0, FAILURE);
    }

    /**
     * 根据信息，封装错误返回结果
     * @param noMsg         错误时返回信息
     * @return RetObject    返回对象
     */
    public static RetObject returnFailure(String noMsg){
        return new RetObject(0, StringUtils.isNotBlank(noMsg)?noMsg:FAILURE);
    }


    /**
     * 根据信息，封装返回结果
     * @param noMsg         错误时返回信息
     * @param okMsg         正确时返回信息
     * @return RetObject    返回对象
     */
    public static RetObject returnCommon(String noMsg, String okMsg){
        if (StringUtils.isNotBlank(noMsg)){
            return new RetObject(0, noMsg);
        }
        return new RetObject(1, StringUtils.isNotBlank(okMsg)?okMsg:SUCCESS);
    }


    /**
     * 自定义返回结果
     * @param code          返回代码
     * @return RetObject    返回对象
     */
    public static RetObject returnCustom(int code){
        if (code == 1) return new RetObject(code, SUCCESS);
        if (code == 0) return new RetObject(code, FAILURE);
        return new RetObject(code, FINISHED);
    }

    /**
     * 自定义返回结果
     * @param code          返回代码
     * @param msg           返回信息
     * @return RetObject    返回对象
     */
    public static RetObject returnCustom(int code, String msg){
        return new RetObject(code, msg);
    }

    /**
     * 自定义返回结果
     * @param code          返回代码
     * @param msg           返回信息
     * @param data          返回数据
     * @return RetObject    返回对象
     */
    public static RetObject returnCustom(int code, String msg, Object data){
        return new RetObject(code, msg, data);
    }

    /**
     * 自定义返回结果
     * @param data          返回数据（成功条件下）
     * @return RetObject    返回对象
     */
    public static RetObject returnCustom(Object data){
        return new RetObject(1, SUCCESS, data);
    }

    /**
     * 自定义返回结果
     * @param code          返回代码
     * @param data          返回数据
     * @return RetObject    返回对象
     */
    public static RetObject returnCustom(int code, Object data){
        if (code == 1) return new RetObject(code, SUCCESS, data);
        if (code == 0) return new RetObject(code, FAILURE, data);
        return new RetObject(code, FINISHED, data);
    }

}
