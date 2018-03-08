package com.lfp.jec.frame.base.query;

import java.util.Map;

/**
 * Project: lfp-jec
 * Title: 格式化 SQL 查询语句和参数
 * Description: 通过链式方法添加查询语句和参数
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface SqlQuery extends EntityQuery {

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatEqual(String name, Object value);

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatEqual(String name, Object value, String key);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatNotEqual(String name, Object value);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatNotEqual(String name, Object value, String key);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatLt(String name, Object value);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatLt(String name, Object value, String key);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatLte(String name, Object value);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatLte(String name, Object value, String key);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatGt(String name, Object value);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatGt(String name, Object value, String key);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatGte(String name, Object value);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatGte(String name, Object value, String key);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatLike(String name, String value);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatLike(String name, String value, String key);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatLikeLeft(String name, String value);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatLikeLeft(String name, String value, String key);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatLikeRight(String name, String value);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatLikeRight(String name, String value, String key);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatNotLike(String name, String value);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatNotLike(String name, String value, String key);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @return query    查询体
     */
    SqlQuery formatBetween(String name, Object value1, Object value2);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatBetween(String name, Object value1, Object value2, String key);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    SqlQuery formatIn(String name, Object value);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    SqlQuery formatIn(String name, Object value, String key);

    /**
     * 为空
     * @param name      属性名
     * @return query    查询体
     */
    SqlQuery formatIsNull(String name);

    /**
     * 不为空
     * @param name      属性名
     * @return query    查询体
     */
    SqlQuery formatNotNull(String name);

    /**
     * 自定义HQL语句
     * @param sql       HQL语句
     * @return query    查询体
     */
    SqlQuery formatSql(String sql);

    /**
     * 自定义HQL语句和参数
     * @param sql       HQL语句
     * @param params    参数
     * @return query    查询体
     */
    SqlQuery formatSql(String sql, Map<String, Object> params);


    /**
     * 属性排序
     * @param sort      属性名
     * @param order     排序类型，[asc,desc]
     * @return query    查询体
     */
    SqlQuery orderBy(String sort, String order);

    /**
     * 排序语句
     * @param sql       HQL语句
     * @return query    查询体
     */
    SqlQuery orderSql(String sql);


    /**
     * 自定义语句
     * @param sql       HQL语句
     * @return query    查询体
     */
    SqlQuery addSql(String sql);

    /**
     * 自定义语句
     * @param sql       HQL语句
     * @param params    参数
     * @return query    查询体
     */
    SqlQuery addSql(String sql, Map<String, Object> params);


    /**
     * 获取 HQL 的头部
     * @return headSql  SELECT ...
     */
    String getHeadSql();

    /**
     * 设置 HQL 的头部
     * @param headSql   SELECT ...
     */
    void setHeadSql(String headSql);

    /**
     * 获取 HQL 的from
     * @return fromSql  FROM ...
     */
    String getFromSql();

    /**
     * 设置 HQL 的from
     * @param fromSql   FROM ...
     */
    void setFromSql(String fromSql);

    /**
     * 获取 HQL 的 where
     * @return whereSql WHERE ...
     */
    String getWhereSql();

    /**
     * 设置 HQL 的 where
     * @param whereSql  WHERE ...
     */
    void setWhereSql(String whereSql);

    /**
     * 获取 HQL 的 order
     * @return orderSql ORDER BY ...
     */
    String getOrderSql();

    /**
     * 设置 HQL 的 order
     * @param orderSql  ORDER BY ...
     */
    void setOrderSql(String orderSql);

    /**
     * 获取 HQL 的 add
     * @return addSql   [GROUP BY/HAVING...]...
     */
    String getAddSql();

    /**
     * 设置 HQL 的 add
     * @param addSql    [GROUP BY/HAVING...]...
     */
    void setAddSql(String addSql);

    /**
     * 获取查询参数
     * @return params   查询参数
     */
    Map<String, Object> getParams();

    /**
     * 设置查询参数
     * @param params    查询参数
     */
    void setParams(Map<String, Object> params);


    /**
     * 获取查询语句
     * @return selectSql查询语句
     */
    String getSelectSql();

    /**
     * 获取查询数量的语句，查询（SELECT count(*)子句，无排序语句）
     * @return countSql  无 orderSql 的查询
     */
    String getCountSql();


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