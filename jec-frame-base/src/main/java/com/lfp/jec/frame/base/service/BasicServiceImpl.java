package com.lfp.jec.frame.base.service;

import com.lfp.jec.frame.base.dao.BasicDao;
import com.lfp.jec.frame.base.dict.EntityStatus;
import com.lfp.jec.frame.base.query.HqlQuery;
import com.lfp.jec.frame.base.query.SqlQuery;
import com.lfp.jec.frame.util.JavaBeanUtil;
import com.lfp.jec.frame.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 基础增删改查 Service 实现 [BasicService]
 * Description: 提供基础的公共 Service 实现，通过提供泛型类T，实现接口方法
 *              部分无特殊要求的功能模块只继承该实现即可
 * Date: 2017-2-15
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 * @param <T>   泛型类
 */
@Service
@Transactional
public class BasicServiceImpl<T> implements BasicService<T> {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected BasicDao<T> dao;

    /*---------- 基类提供空方法实现 ----------*/
    public void init(String tenant){
        //提供空方法供多数子类使用，
        //特殊子类需要覆盖时进行 @Override
    }

    /*---------- 基类提供泛型实现 ----------*/
    public T get(Serializable id, Class<T> clazz){
        return dao.get(clazz, id);
    }

    public T fetch(Serializable id, Class<T> clazz){
        return dao.fetch(clazz, id);
    }

    public T getByProperty(String name, Object value, String tenant, Class<T> clazz){
        return dao.getByProperty(clazz, name, value, tenant);
    }

    public T getNormalByProperty(String name, Object value, String tenant, Class<T> clazz){
        return dao.getNormalByProperty(clazz, name, value, tenant);
    }

    public T getWellByProperty(String name, Object value, String tenant, Class<T> clazz){
        return dao.getWellByProperty(clazz, name, value, tenant);
    }

    public List<T> list(String ids, Class<T> clazz){
        List<T> objs = new ArrayList<>();
        if (ids != null) {
            String [] objIds = ids.split(",");
            for (String objId : objIds) {
                if (objId != null && !objId.equals("")) {
                    T obj = this.get(objId, clazz);
                    if (obj != null) {
                        objs.add(obj);
                    }
                }
            }
        }
        return objs;
    }

    public String del(Serializable id, Class<T> clazz){
        try{
            dao.delete(clazz, id);
            return "";
        }catch (Exception e){
            log.error("del方法异常", e);
        }
        return "删除失败";
    }

    public String delete(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            try {
                JavaBeanUtil.setFieldValueByName(o, "status", EntityStatus.DELETE);
                if(!this.save(o).equals("")){
                    ret.append(o.toString()).append("删除失败<br>");
                }
            } catch (Exception e) {
                log.error("删除失败", e);
                ret.append(o.toString()).append("删除失败<br>");
            }
        }
        return ret.toString();
    }

    public String normal(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            try {
                Object oldVal = JavaBeanUtil.getFieldValueByName(o, "status");
                if (!EntityStatus.LOCKED.equals(oldVal)){
                    ret.append(o.toString()).append("非锁定状态，无法激活<br>");
                    continue;
                }
                JavaBeanUtil.setFieldValueByName(o, "status", EntityStatus.NORMAL);
                if(!this.save(o).equals("")){
                    ret.append(o.toString()).append("激活失败<br>");
                }
            } catch (Exception e) {
                log.error("激活失败", e);
                ret.append(o.toString()).append("激活失败<br>");
            }
        }
        return ret.toString();
    }

    public String locked(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            try {
                Object oldVal = JavaBeanUtil.getFieldValueByName(o, "status");
                if (!EntityStatus.NORMAL.equals(oldVal)){
                    ret.append(o.toString()).append("非激活状态，无法锁定<br>");
                    continue;
                }
                JavaBeanUtil.setFieldValueByName(o, "status", EntityStatus.LOCKED);
                if(!this.save(o).equals("")){
                    ret.append(o.toString()).append("锁定失败<br>");
                }
            } catch (Exception e) {
                log.error("锁定失败", e);
                ret.append(o.toString()).append("锁定失败<br>");
            }
        }
        return ret.toString();
    }

    /*---------- 基类提供抽象类实现 ----------*/
    public String save(T obj){
        try{
            dao.saveOrUpdate(obj);
            return "";
        }catch (Exception e){
            log.error("save方法异常", e);
        }
        return "保存失败";
    }

    public String evict(T obj){
        try{
            dao.evict(obj);
            return "";
        }catch (Exception e){
            log.error("evict方法异常", e);
        }
        return "托管失败";
    }

    public String del(T obj){
        try{
            dao.delete(obj);
            return "";
        }catch (Exception e){
            log.error("delete方法异常", e);
        }
        return "删除失败";
    }

    public String save(List<T> objs){
        try{
            for (T obj : objs){
                dao.saveOrUpdate(obj);
            }
            return "";
        }catch (Exception e){
            log.error("saves方法异常", e);
        }
        return "批量保存失败";
    }

    public String delete(List<T> objs){
        try{
            for (T obj : objs){
                dao.delete(obj);
            }
            return "";
        }catch (Exception e){
            log.error("deletes方法异常", e);
        }
        return "批量删除失败";
    }

    public String flush(){
        try{
            dao.flush();
            return "";
        }catch (Exception e){
            log.error("flush方法异常", e);
        }
        return "刷新失败！";
    }

    /*---------- HQL 查询相关 ----------*/
    public T getByHql(String hql) {
        return dao.getByHql(hql);
    }

    public List<T> listByHql(String hql){
        return dao.listByHql(hql);
    }

    public List<T> pageByHql(String hql, int page, int rows){
        return dao.pageByHql(hql, page, rows);
    }

    public long countByHql(String hql){
        return dao.countByHql(hql);
    }

    public int executeByHql(String hql){
        return dao.executeByHql(hql);
    }


    public T getByHql(String hql, Map<String, Object> params) {
        return dao.getByHql(hql, params);
    }

    public List<T> listByHql(String hql, Map<String,Object> params){
        return dao.listByHql(hql, params);
    }

    public List<T> pageByHql(String hql, Map<String,Object> params, int page, int rows){
        return dao.pageByHql(hql, params, page, rows);
    }

    public long countByHql(String hql, Map<String,Object> params){
        return dao.countByHql(hql, params);
    }

    public int executeByHql(String hql, Map<String,Object> params){
        return dao.executeByHql(hql, params);
    }


    public T getByHql(HqlQuery query) {
        return dao.getByHql(query.getSelectHql(), query.getParams());
    }

    public List<T> listByHql(HqlQuery query) {
        return dao.listByHql(query.getSelectHql(), query.getParams());
    }

    public List<T> pageByHql(HqlQuery query, int page, int rows) {
        return dao.pageByHql(query.getSelectHql(), query.getParams(), page, rows);
    }

    public long countByHql(HqlQuery query) {
        return dao.countByHql(query.getCountHql(), query.getParams());
    }

    public int executeByHql(HqlQuery query) {
        return dao.executeByHql(query.getSelectHql(), query.getParams());
    }

    public Map<String, Object> pageHql(HqlQuery query, int page, int rows) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rows", dao.pageByHql(query.getSelectHql(), query.getParams(), page, rows));
        responseData.put("total", dao.countByHql(query.getCountHql(), query.getParams()));
        return responseData;
    }

    /*---------- SQL 查询相关 ----------*/
    public T getBySql(String sql) {
        return dao.getBySql(sql);
    }

    public List<T> listBySql(String sql){
        return dao.listBySql(sql);
    }

    public List<T> pageBySql(String sql, int page, int rows){
        return dao.pageBySql(sql, page, rows);
    }

    public long countBySql(String sql){
        return dao.countBySql(sql);
    }

    public int executeBySql(String sql){
        return dao.executeBySql(sql);
    }


    public T getBySql(String sql, Map<String, Object> params) {
        return dao.getBySql(sql, params);
    }

    public List<T> listBySql(String sql, Map<String, Object> params) {
        return dao.listBySql(sql, params);
    }

    public List<T> pageBySql(String sql, Map<String, Object> params, int page, int rows) {
        return dao.pageBySql(sql, params, page, rows);
    }

    public long countBySql(String sql, Map<String, Object> params) {
        return dao.countBySql(sql, params);
    }

    public int executeBySql(String sql, Map<String, Object> params) {
        return dao.executeBySql(sql, params);
    }


    public T getBySql(SqlQuery query) {
        return dao.getBySql(query.getSelectSql(), query.getParams());
    }

    public List<T> listBySql(SqlQuery query) {
        return dao.listBySql(query.getSelectSql(), query.getParams());
    }

    public List<T> pageBySql(SqlQuery query, int page, int rows) {
        return dao.pageBySql(query.getSelectSql(), query.getParams(), page, rows);
    }

    public long countBySql(SqlQuery query) {
        return dao.countBySql(query.getCountSql(), query.getParams());
    }

    public int executeBySql(SqlQuery query) {
        return dao.executeBySql(query.getSelectSql(), query.getParams());
    }

    public Map<String, Object> pageSql(SqlQuery query, int page, int rows) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rows", dao.pageBySql(query.getSelectSql(), query.getParams(), page, rows));
        responseData.put("total", dao.countBySql(query.getCountSql(), query.getParams()));
        return responseData;
    }

}
