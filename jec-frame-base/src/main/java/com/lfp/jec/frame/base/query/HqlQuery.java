package com.lfp.jec.frame.base.query;

import java.util.Date;
import java.util.Map;

/**
 * Project: lfp-jec
 * Title: 格式化 HQL 查询语句和参数
 * Description: 通过链式方法添加查询语句和参数
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface HqlQuery extends EntityQuery {

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatEqual(String name, Object value);

    /**
     * 相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatEqual(String name, Object value, String key);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatNotEqual(String name, Object value);

    /**
     * 不相等
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatNotEqual(String name, Object value, String key);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatLt(String name, Object value);

    /**
     * 小于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatLt(String name, Object value, String key);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatLte(String name, Object value);

    /**
     * 小于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatLte(String name, Object value, String key);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatGt(String name, Object value);

    /**
     * 大于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatGt(String name, Object value, String key);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatGte(String name, Object value);

    /**
     * 大于等于
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatGte(String name, Object value, String key);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatLike(String name, String value);

    /**
     * 相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatLike(String name, String value, String key);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatLikeLeft(String name, String value);

    /**
     * 左侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatLikeLeft(String name, String value, String key);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatLikeRight(String name, String value);

    /**
     * 右侧相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatLikeRight(String name, String value, String key);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatNotLike(String name, String value);

    /**
     * 不相似
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatNotLike(String name, String value, String key);

    /**
     * 之间-时间
     * @param name      属性名
     * @param value1    起始时间
     * @param value2    结束时间
     * @return query    查询体
     */
    HqlQuery formatBetween(String name, Date value1, Date value2);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @return query    查询体
     */
    HqlQuery formatBetween(String name, Object value1, Object value2);

    /**
     * 之间
     * @param name      属性名
     * @param value1    起始值
     * @param value2    结束值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatBetween(String name, Object value1, Object value2, String key);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatIn(String name, Object value);

    /**
     * 在内
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatIn(String name, Object value, String key);

    /**
     * 不在内
     * @param name      属性名
     * @param value     输入值
     * @return query    查询体
     */
    HqlQuery formatNotIn(String name, Object value);

    /**
     * 不在内
     * @param name      属性名
     * @param value     输入值
     * @param key       参数名
     * @return query    查询体
     */
    HqlQuery formatNotIn(String name, Object value, String key);

    /**
     * 为空
     * @param name      属性名
     * @return query    查询体
     */
    HqlQuery formatIsNull(String name);

    /**
     * 不为空
     * @param name      属性名
     * @return query    查询体
     */
    HqlQuery formatNotNull(String name);

    /**
     * 自定义HQL语句
     * @param hql       HQL语句
     * @return query    查询体
     */
    HqlQuery formatHql(String hql);

    /**
     * 自定义HQL语句和参数
     * @param hql       HQL语句
     * @param params    参数
     * @return query    查询体
     */
    HqlQuery formatHql(String hql, Map<String, Object> params);


    /**
     * 属性排序
     * @param sort      属性名
     * @param order     排序类型，[asc,desc]
     * @return query    查询体
     */
    HqlQuery orderBy(String sort, String order);

    /**
     * 排序语句
     * @param hql       HQL语句
     * @return query    查询体
     */
    HqlQuery orderHql(String hql);


    /**
     * 自定义语句
     * @param hql       HQL语句
     * @return query    查询体
     */
    HqlQuery addHql(String hql);

    /**
     * 自定义语句
     * @param hql       HQL语句
     * @param params    参数
     * @return query    查询体
     */
    HqlQuery addHql(String hql, Map<String, Object> params);


    /**
     * 获取 HQL 的头部
     * @return headHql  SELECT ...
     */
    String getHeadHql();

    /**
     * 设置 HQL 的头部
     * @param headHql   SELECT ...
     */
    void setHeadHql(String headHql);

    /**
     * 获取 HQL 的from
     * @return fromHql  FROM ...
     */
    String getFromHql();

    /**
     * 设置 HQL 的from
     * @param fromHql   FROM ...
     */
    void setFromHql(String fromHql);

    /**
     * 获取 HQL 的 where
     * @return whereHql WHERE ...
     */
    String getWhereHql();

    /**
     * 设置 HQL 的 where
     * @param whereHql  WHERE ...
     */
    void setWhereHql(String whereHql);

    /**
     * 获取 HQL 的 order
     * @return orderHql ORDER BY ...
     */
    String getOrderHql();

    /**
     * 设置 HQL 的 order
     * @param orderHql  ORDER BY ...
     */
    void setOrderHql(String orderHql);

    /**
     * 获取 HQL 的 add
     * @return addHql   [GROUP BY/HAVING...]...
     */
    String getAddHql();

    /**
     * 设置 HQL 的 add
     * @param addHql    [GROUP BY/HAVING...]...
     */
    void setAddHql(String addHql);

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
     * @return selectHql查询语句
     */
    String getSelectHql();

    /**
     * 获取查询数量的HQL语句，查询（无SELECT子句，无排序语句）
     * @return countHql  无 headHql 和 orderHql 的查询
     */
    String getCountHql();


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