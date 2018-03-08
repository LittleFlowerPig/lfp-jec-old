package com.lfp.jec.frame.base.query;

/**
 * Project: lfp-jec
 * Title: 查询工厂
 * Description: 创建各类查询体
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class QueryHelper {

    /**
     * 创建实体 HQL 查询
     * @param fromHql       FROM 语句
     * @return query        查询体
     */
    public static HqlQuery createHqlQuery(String fromHql) {
        return new HqlQueryImpl(fromHql);
    }

    /**
     * 创建实体 HQL 查询
     * @param headHql       起始语句
     * @param fromHql       FROM 语句
     * @return query        查询体
     */
    public static HqlQuery createHqlQuery(String headHql, String fromHql) {
        return new HqlQueryImpl(headHql, fromHql);
    }

    /**
     * 创建实体 HQL 查询
     * @param entityClass   实体类名
     * @return query        查询体
     */
    public static HqlQuery createHqlSelect(String entityClass) {
        return new HqlQueryImpl(" FROM "+entityClass+" AS obj");
    }

    /**
     * 创建实体 HQL 查询
     * @param entityClass   实体类名
     * @param tenant        租户
     * @return query        查询体
     */
    public static HqlQuery createHqlSelect(String entityClass, String tenant) {
        return new HqlQueryImpl(" FROM "+entityClass+" AS obj")
                .formatEqual("tenant", tenant);
    }

    /**
     * 创建实体 HQL 查询
     * @param clazz         实体类
     * @param tenant        租户
     * @return query        查询体
     */
    public static HqlQuery createHqlSelect(Class clazz, String tenant) {
        return new HqlQueryImpl(" FROM "+clazz.getName()+" AS obj")
                .formatEqual("tenant", tenant);
    }

    /**
     * 创建实体 HQL 删除
     * @param entityClass   实体类名
     * @return query        查询体
     */
    public static HqlQuery createHqlDelete(String entityClass) {
        return new HqlQueryImpl("DELETE", " FROM "+entityClass+" AS obj");
    }

    /**
     * 创建实体 HQL 删除
     * @param entityClass   实体类名
     * @param tenant        租户
     * @return query        查询体
     */
    public static HqlQuery createHqlDelete(String entityClass, String tenant) {
        return new HqlQueryImpl("DELETE", " FROM "+entityClass+" AS obj")
                .formatEqual("tenant", tenant);
    }


    /**
     * 创建实体 SQL 查询
     * @param headHql       起始语句[SELECT/DELETE/INSERT]...
     * @param fromHql       from语句 FROM...
     * @return query        查询体
     */
    public static SqlQuery createSqlQuery(String headHql, String fromHql) {
        return new SqlQueryImpl(headHql, fromHql);
    }

    /**
     * 创建实体 SQL 查询
     * @param tableName     表名
     * @return query        查询体
     */
    public static SqlQuery createSqlSelect(String tableName) {
        return new SqlQueryImpl("SELECT *", " FROM "+tableName+" obj");
    }

    /**
     * 创建实体 SQL 查询
     * @param tableName     表名
     * @param tenant        租户
     * @return query        查询体
     */
    public static SqlQuery createSqlSelect(String tableName, String tenant) {
        return new SqlQueryImpl("SELECT *", " FROM "+tableName+" obj")
                .formatEqual("tenant", tenant);
    }

    /**
     * 创建实体 SQL 删除
     * @param tableName     表名
     * @return query        查询体
     */
    public static SqlQuery createSqlDelete(String tableName) {
        return new SqlQueryImpl("DELETE", " FROM "+tableName+" obj");
    }

    /**
     * 创建实体 SQL 删除
     * @param tableName     表名
     * @param tenant        租户
     * @return query        查询体
     */
    public static SqlQuery createSqlDelete(String tableName, String tenant) {
        return new SqlQueryImpl("DELETE", " FROM "+tableName+" obj")
                .formatEqual("tenant", tenant);
    }

}
