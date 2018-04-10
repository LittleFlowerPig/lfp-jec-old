package com.lfp.jec.frame.base.query;

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
public interface HqlQuery extends EntityQuery<HqlQuery> {

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

}