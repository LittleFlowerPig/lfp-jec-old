package com.lfp.jec.frame.base.service;

import com.lfp.jec.frame.base.query.HqlQuery;
import com.lfp.jec.frame.base.query.SqlQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Title: 基础增删改查 Service 接口
 * Description: 提供基础增删改查的公共 Service 方法
 * Date: 2017-2-14
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface BasicService<T> {

    /*---------- 基类提供空方法实现 ----------*/
    /**
     * 初始化：开户
     * @param tenant    租户
     */
    void init(String tenant);

    /*---------- 基类提供泛型实现 ----------*/
    /**
     * 根据主键加载实体（持久态）
     * @param id        主键ID
     * @param clazz     实体类
     * @return obj      实体对象
     */
    T get(Serializable id, Class<T> clazz);

    /**
     * 根据主键获取实体（托管态）
     * @param id        主键ID
     * @param clazz     实体类
     * @return obj      实体对象
     */
    T fetch(Serializable id, Class<T> clazz);

    /**
     * 根据某属性获取实体
     * @param name      属性名
     * @param value     属性值
     * @param tenant    租户
     * @param clazz     实体类
     * @return obj      实体对象
     */
    T getByProperty(String name, Object value, String tenant, Class<T> clazz);

    /**
     * 根据某属性获取实体(限定正常状态)
     * @param name      属性名
     * @param value     属性值
     * @param tenant    租户
     * @param clazz     实体类
     * @return obj      实体对象
     */
    T getNormalByProperty(String name, Object value, String tenant, Class<T> clazz);

    /**
     * 根据某属性获取实体(限定非删除状态)
     * @param name      属性名
     * @param value     属性值
     * @param tenant    租户
     * @param clazz     实体类
     * @return obj      实体对象
     */
    T getWellByProperty(String name, Object value, String tenant, Class<T> clazz);

    /**
     * 根据主键集合批量获取实体
     * @param ids       主键ID
     * @param clazz     实体类
     * @return list     实体集合
     */
    List<T> list(String ids, Class<T> clazz);

    /**
     * 根据主键删除实体（真删除）
     * @param id        主键ID
     * @param clazz     实体类
     * @return ret      操作结果
     */
    String del(Serializable id, Class<T> clazz);

    /**
     * 根据主键集合批量删除实体（状态管理）（假删除）
     * @param ids       主键ID
     * @param clazz     实体类
     * @return ret      操作结果
     */
    String delete(String ids, Class<T> clazz);

    /**
     * 根据主键集合批量激活实体（状态管理）
     * @param ids       主键ID
     * @param clazz     实体类
     * @return ret      操作结果
     */
    String normal(String ids, Class<T> clazz);

    /**
     * 根据主键集合批量锁定实体（状态管理）
     * @param ids       主键ID
     * @param clazz     实体类
     * @return ret      操作结果
     */
    String locked(String ids, Class<T> clazz);

    /*---------- 基类提供完全实现 ----------*/
    /**
     * 保存实体
     * @param obj       实体对象
     * @return ret      处理结果，""表示成功
     */
    String save(T obj);

    /**
     * 托管实体
     * @param obj       实体对象
     * @return ret      处理结果，""表示成功
     */
    String evict(T obj);

    /**
     * 删除实体
     * @param obj       实体对象
     * @return ret      处理结果，""表示成功
     */
    String del(T obj);

    /**
     * 批量保存实体
     * @param objs      实体对象集合
     * @return ret      处理结果，""表示成功
     */
    String save(List<T> objs);

    /**
     * 批量删除实体
     * @param objs      实体对象集合
     * @return ret      处理结果，""表示成功
     */

    String delete(List<T> objs);

    /**
     * 刷新缓存
     * @return ret      处理结果，""表示成功
     */
    String flush();

    /*---------- HQL 查询相关 ----------*/
    /**
     * 根据HQL语句获取单个实体
     * @param hql       HQL语句，FROM...
     * @return obj      实体
     */
    T getByHql(String hql);

    /**
     * 根据HQL语句获取所有实体
     * @param hql       HQL语句，FROM...
     * @return objs     实体List
     */
    List<T> listByHql(String hql);

    /**
     * 根据HQL语句获取分页实体
     * @param hql       HQL语句，FROM...
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    List<T> pageByHql(String hql, int page, int rows);

    /**
     * 根据HQL语句获取实体数量
     * @param hql       HQL语句，FROM...
     * @return count    实体数量
     */
    long countByHql(String hql);

    /**
     * 根据HQL语句执行
     * @param hql       HQL语句，UPDATE/DELETE...
     * @return count    影响记录数量
     */
    int executeByHql(String hql);


    /**
     * 根据HQL语句获取单个实体
     * @param hql       HQL语句，FROM...
     * @param params    查询参数
     * @return obj      实体
     */
    T getByHql(String hql, Map<String, Object> params);

    /**
     * 根据HQL语句获取所有实体
     * @param hql       HQL语句，FROM...
     * @param params    查询参数
     * @return objs     实体List
     */
    List<T> listByHql(String hql, Map<String, Object> params);

    /**
     * 根据HQL语句获取分页实体
     * @param hql       HQL语句，FROM...
     * @param params    查询参数
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    List<T> pageByHql(String hql, Map<String, Object> params, int page, int rows);

    /**
     * 根据HQL语句获取实体数量
     * @param hql       HQL语句，FROM...
     * @param params    查询参数
     * @return count    实体数量
     */
    long countByHql(String hql, Map<String, Object> params);

    /**
     * 根据HQL语句执行
     * @param hql       HQL语句，UPDATE/DELETE...
     * @param params    查询参数
     * @return count    影响记录数量
     */
    int executeByHql(String hql, Map<String, Object> params);


    /**
     * 根据HQL语句获取单个实体
     * @param query     EntityQuery
     * @return obj      实体
     */
    T getByHql(HqlQuery query);

    /**
     * 根据 Query 语句获取所有实体
     * @param query     EntityQuery
     * @return objs     实体List
     */
    List<T> listByHql(HqlQuery query);

    /**
     * 根据 Query 语句获取分页实体
     * @param query     EntityQuery
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    List<T> pageByHql(HqlQuery query, int page, int rows);

    /**
     * 根据 Query 语句获取实体数量
     * @param query     EntityQuery
     * @return count    实体数量
     */
    long countByHql(HqlQuery query);

    /**
     * 根据 Query 语句执行
     * @param query     EntityQuery
     * @return count    影响记录数量
     */
    int executeByHql(HqlQuery query);

    /**
     * 根据 Query 语句获取分页 Bean
     * @param query     EntityQuery
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    Map<String, Object> pageHql(HqlQuery query, int page, int rows);

    /*---------- SQL 查询相关 ----------*/
    /**
     * 根据SQL语句获取所有记录
     * @param sql       SQL语句，SELECT...
     * @return obj      实体
     */
    T getBySql(String sql);

    /**
     * 根据SQL语句获取所有实体
     * @param sql   SQL语句，SELECT...
     * @return objs 实体List
     */
    List<T> listBySql(String sql);

    /**
     * 根据SQL语句获取分页实体
     * @param sql   SQL语句，SELECT...
     * @param page  当前页
     * @param rows  每页数
     * @return objs 实体List
     */
    List<T> pageBySql(String sql, int page, int rows);

    /**
     * 根据SQL语句获取实体数量
     * @param sql   SQL语句，SELECT...
     * @return count实体数量
     */
    long countBySql(String sql);

    /**
     * 根据SQL语句执行
     * @param sql   SQL语句，UPDATE/DELETE...
     * @return count影响记录数量
     */
    int executeBySql(String sql);


    /**
     * 根据SQL语句获取所有记录
     * @param sql       SQL语句，SELECT...
     * @param params    查询参数
     * @return obj      实体
     */
    T getBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句获取所有实体
     * @param sql       SQL语句，FROM...
     * @param params    查询参数
     * @return objs     实体List
     */
    List<T> listBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句获取分页实体
     * @param sql       SQL语句，FROM...
     * @param params    查询参数
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    List<T> pageBySql(String sql, Map<String, Object> params, int page, int rows);

    /**
     * 根据SQL语句获取实体数量
     * @param sql       SQL语句，FROM...
     * @param params    查询参数
     * @return count    实体数量
     */
    long countBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句执行
     * @param sql       SQL语句，UPDATE/DELETE...
     * @param params    查询参数
     * @return count    影响记录数量
     */
    int executeBySql(String sql, Map<String, Object> params);


    /**
     * 根据SQL语句获取所有记录
     * @param query     EntityQuery
     * @return obj      实体
     */
    T getBySql(SqlQuery query);

    /**
     * 根据 Query 语句获取所有实体
     * @param query     EntityQuery
     * @return objs     实体List
     */
    List<T> listBySql(SqlQuery query);

    /**
     * 根据 Query 语句获取分页实体
     * @param query     EntityQuery
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    List<T> pageBySql(SqlQuery query, int page, int rows);

    /**
     * 根据 Query 语句获取实体数量
     * @param query     EntityQuery
     * @return count    实体数量
     */
    long countBySql(SqlQuery query);

    /**
     * 根据 Query 语句执行
     * @param query     EntityQuery
     * @return count    影响记录数量
     */
    int executeBySql(SqlQuery query);

    /**
     * 根据 Query 语句获取分页 Bean
     * @param query     EntityQuery
     * @param page      当前页
     * @param rows      每页数
     * @return objs     实体List
     */
    Map<String, Object> pageSql(SqlQuery query, int page, int rows);

}
