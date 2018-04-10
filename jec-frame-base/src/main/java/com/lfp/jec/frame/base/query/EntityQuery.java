package com.lfp.jec.frame.base.query;

import java.util.Date;

/**
 * Project: lfp-jec
 * Title: 实体查询接口
 * Description: 作为各类实体查询的父接口，又各子类接口继承
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface EntityQuery<T> {

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatEqual(String name, Object value);

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatEqual(String name, Object value, String key);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatNotEqual(String name, Object value);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatNotEqual(String name, Object value, String key);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatLt(String name, Object value);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatLt(String name, Object value, String key);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatLte(String name, Object value);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatLte(String name, Object value, String key);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatGt(String name, Object value);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatGt(String name, Object value, String key);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatGte(String name, Object value);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatGte(String name, Object value, String key);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatLike(String name, String value);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatLike(String name, String value, String key);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatLikeLeft(String name, String value);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatLikeLeft(String name, String value, String key);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatLikeRight(String name, String value);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatLikeRight(String name, String value, String key);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatNotLike(String name, String value);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatNotLike(String name, String value, String key);

    /**
     * 之间-时间
     * @param name      属性名
     * @param value1    起始时间
     * @param value2    结束时间
     * @return query    查询体
     */
    T formatBetween(String name, Date value1, Date value2);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @return query    查询体
     */
    T formatBetween(String name, Object value1, Object value2);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @param key       参数名
     * @return query    查询体
     */
    T formatBetween(String name, Object value1, Object value2, String key);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatIn(String name, Object value);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatIn(String name, Object value, String key);

    /**
     * 不在内
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    T formatNotIn(String name, Object value);

    /**
     * 不在内
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    T formatNotIn(String name, Object value, String key);

    /**
     * 为空
     * @param name      属性名
     * @return query    查询体
     */
    T formatIsNull(String name);

    /**
     * 不为空
     * @param name      属性名
     * @return query    查询体
     */
    T formatNotNull(String name);


    /**
     * 判定指定的查询参数是否已经存在
     * @param key       查询参数key eg. user_name
     * @return true/false
     */
    boolean containsKey(String key);

    /**
     * 判定指定的查询属性是否已经存在
     * @param name       查询属性name eg. user.name
     * @return true/false
     */
    boolean containsName(String name);

}
