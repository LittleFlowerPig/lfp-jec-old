package com.lfp.jec.frame.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Title: Hibernate DAO 接口
 * Project: eac
 * Description: 发布基础DAO的所有功能接口方法
 * Date: 2017-2-14
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface BasicDao<T> {

    /**
     * 根据主键加载实体（持久态）
     * @param clazz     实体类
     * @param id        主键ID
     * @return obj      实体对象
     */
    T get(Class<T> clazz, Serializable id);

    /**
     * 根据主键获取实体（托管态）
     * @param clazz     实体类
     * @param id        主键ID
     * @return obj      实体对象
     */
    T fetch(Class<T> clazz, Serializable id);

    /**
     * 根据逻辑唯一键加载实体（持久态）
     * @param clazz     实体类
     * @param name      属性名
     * @param value     属性值
     * @param tenant    指定租户
     * @return obj      实体对象
     */
    T getByProperty(Class<T> clazz, String name, Object value, String tenant);

    /**
     * 根据逻辑唯一键加载非 DELETE 状态实体（持久态）
     * @param clazz     实体类
     * @param name      属性名
     * @param value     属性值
     * @param tenant    指定租户
     * @return obj      实体对象
     */
    T getWellByProperty(Class<T> clazz, String name, Object value, String tenant);

    /**
     * 根据逻辑唯一键加载 NORMAL 状态实体（持久态）
     * @param clazz     实体类
     * @param name      属性名
     * @param value     属性值
     * @param tenant    指定租户
     * @return obj      实体对象
     */
    T getNormalByProperty(Class<T> clazz, String name, Object value, String tenant);


    /**
     * 根据主键删除实体
     * @param clazz     实体类
     * @param id        主键ID
     */
    void delete(Class<T> clazz, Serializable id);

    /**
     * 根据主键串删除实体集合
     * @param clazz     实体类
     * @param ids       主键串
     */
    void deletes(Class<T> clazz, List<String> ids);

    /**
     * 删除所有实体
     * @param clazz     实体类
     */
    void deleteAll(Class<T> clazz);

    /**
     * 获取所有实体
     * @param clazz     实体类
     * @return objs     实体List
     */
    List<T> getAll(Class<T> clazz);


    /**
     * 保存实体
     * @param obj       实体对象
     * @return id       主键ID
     */
    Serializable save(T obj);

    /**
     * 更新实体
     * @param obj       实体对象
     */
    void update(T obj);

    /**
     * 合并保存实体
     * @param obj       实体对象
     */
    void merge(T obj);

    /**
     * 保存或更新实体
     * @param obj       实体对象
     */
    void saveOrUpdate(T obj);

    /**
     * 托管实体
     * @param obj       实体对象
     */
    void evict(T obj);

    /**
     * 删除实体
     * @param obj       实体对象
     */
    void delete(T obj);

    /**
     * 刷新缓存
     */
    void flush();


    /**
     * 根据HQL语句获取单个实体，多个只返回一个
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
     * 根据HQL语句获取单个实体,多个只返回一个
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
     * 根据SQL语句获取单个记录，多个只返回一个
     * @param sql       SQL语句，SELECT...
     * @return obj      实体
     */
    T getBySql(String sql);

    /**
     * 根据SQL语句获取所有记录
     * @param sql       SQL语句，SELECT...
     * @return objs     记录List
     */
    List<T> listBySql(String sql);

    /**
     * 根据SQL语句获取分页记录
     * @param sql       SQL语句，SELECT...
     * @param page      当前页
     * @param rows      每页数
     * @return objs     记录List
     */
    List<T> pageBySql(String sql, int page, int rows);

    /**
     *根据SQL语句获取记录数量
     * @param sql       SQL语句，SELECT...
     * @return count    记录数量
     */
    long countBySql(String sql);

    /**
     * 根据SQL语句执行
     * @param sql       SQL语句，UPDATE/DELETE...
     * @return count    影响记录数量
     */
    int executeBySql(String sql);


    /**
     * 根据SQL语句获取所有记录,多个结果只返回一个
     * @param sql       SQL语句，SELECT...
     * @param params    查询参数
     * @return obj      实体
     */
    T getBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句获取所有记录
     * @param sql       SQL语句，SELECT...
     * @param params    查询参数
     * @return objs     记录List
     */
    List<T> listBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句获取分页记录
     * @param sql       SQL语句，SELECT...
     * @param params    查询参数
     * @param page      当前页
     * @param rows      每页数
     * @return objs     记录List
     */
    List<T> pageBySql(String sql, Map<String, Object> params, int page, int rows);

    /**
     * 根据SQL语句获取记录数量
     * @param sql       SQL语句，SELECT...
     * @param params    查询参数
     * @return count    记录数量
     */
    long countBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句执行
     * @param sql       SQL语句，UPDATE/DELETE...
     * @param params    查询参数
     * @return count    影响记录数量
     */
    int executeBySql(String sql, Map<String, Object> params);

}
