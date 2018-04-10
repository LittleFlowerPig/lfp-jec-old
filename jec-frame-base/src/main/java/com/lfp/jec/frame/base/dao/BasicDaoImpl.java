package com.lfp.jec.frame.base.dao;

import com.lfp.jec.frame.base.dict.EntityStatus;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Title: Hibernate DAO 实现
 * Project: eac
 * Description: 实现基础DAO的所有功能接口方法，
 *              用于在service中注入，使用提供的持久化方法
 * Date: 2017-2-14
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
@Repository
public class BasicDaoImpl<T> implements BasicDao<T> {

    @Autowired
    private SessionFactory sessionFactory;


    public T get(Class<T> clazz, Serializable id){
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public T fetch(Class<T> clazz, Serializable id){
        Object obj = sessionFactory.getCurrentSession().get(clazz, id);
        sessionFactory.getCurrentSession().evict(obj);
        return (T) obj;
    }

    public T getByProperty(Class<T> clazz, String name, Object value, String tenant){
        if (StringUtils.isNotBlank(tenant)){
            return (T) sessionFactory.getCurrentSession()
                    .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name AND obj.tenant = :tenant")
                    .setParameter("name", value).setParameter("tenant", tenant).uniqueResult();
        }
        return (T) sessionFactory.getCurrentSession()
                .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name")
                .setParameter("name", value).uniqueResult();
    }

    public T getWellByProperty(Class<T> clazz, String name, Object value, String tenant){
        if (StringUtils.isNotBlank(tenant)){
            return (T) sessionFactory.getCurrentSession()
                    .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name AND obj.status <> :status AND obj.tenant = :tenant")
                    .setParameter("name", value).setParameter("status", EntityStatus.DELETE).setParameter("tenant", tenant).uniqueResult();
        }
        return (T) sessionFactory.getCurrentSession()
                .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name AND obj.status <> :status")
                .setParameter("name", value).setParameter("status", EntityStatus.DELETE).uniqueResult();
    }

    public T getNormalByProperty(Class<T> clazz, String name, Object value, String tenant){
        if (StringUtils.isNotBlank(tenant)){
            return (T) sessionFactory.getCurrentSession()
                    .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name AND obj.status = :status AND obj.tenant = :tenant")
                    .setParameter("name", value).setParameter("status", EntityStatus.NORMAL).setParameter("tenant", tenant).uniqueResult();
        }
        return (T) sessionFactory.getCurrentSession()
                .createQuery("FROM "+clazz.getName()+" obj WHERE obj."+name+" = :name AND obj.status = :status")
                .setParameter("name", value).setParameter("status", EntityStatus.NORMAL).uniqueResult();
    }


    public void delete(Class<T> clazz, Serializable id){
        sessionFactory.getCurrentSession()
                .createQuery("DELETE "+clazz.getName()+" obj WHERE obj.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public void deletes(Class<T> clazz, List<String> ids){
        sessionFactory.getCurrentSession()
                .createQuery("DELETE "+clazz.getName()+" obj WHERE obj.id IN(:ids)")
                .setParameterList("ids", ids).executeUpdate();
    }

    public void deleteAll(Class<T> clazz){
        sessionFactory.getCurrentSession()
                .createQuery("DELETE "+clazz.getName()).executeUpdate();
    }

    public List<T> getAll(Class<T> clazz){
        return sessionFactory.getCurrentSession()
                .createQuery("FROM "+clazz.getName()).list();
    }


    public Serializable save(T obj){
        return sessionFactory.getCurrentSession().save(obj);
    }

    public void update(T obj){
        sessionFactory.getCurrentSession().update(obj);
    }

    public void merge(T obj){
        sessionFactory.getCurrentSession().merge(obj);
    }

    public void saveOrUpdate(T obj){
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }

    public void evict(T obj){
        sessionFactory.getCurrentSession().evict(obj);
    }

    public void delete(T obj){
        sessionFactory.getCurrentSession().delete(obj);
    }

    public void flush(){
        sessionFactory.getCurrentSession().flush();
    }


    public T getByHql(String hql){
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if(query.getResultList().size()==0 ){
            return null;
        }else{
            return (T) query.setMaxResults(1).getSingleResult();
        }
    }

    public List<T> listByHql(String hql){
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public List<T> pageByHql(String hql, int page, int rows){
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setFirstResult((page-1)*rows)
                .setMaxResults(rows)
                .list();
    }

    public long countByHql(String hql){
        List<?> ret = sessionFactory.getCurrentSession().createQuery("SELECT count(*) "+hql).list();
        if (ret!=null && ret.size()==1){
            return Long.parseLong(ret.get(0).toString());
        }
        return 0;
    }

    public int executeByHql(String hql){
        return sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }


    public T getByHql(String hql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        this.assignment(query, params);
        if(query.getResultList().size()==0 ){
            return null;
        }else{
            return (T) query.setMaxResults(1).getSingleResult();
        }
    }

    public List<T> listByHql(String hql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        this.assignment(query, params);
        return query.list();
    }

    public List<T> pageByHql(String hql, Map<String,Object> params, int page, int rows){
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        this.assignment(query, params);
        return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
    }

    public long countByHql(String hql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) "+hql);
        this.assignment(query, params);
        List<?> ret = query.list();
        if (ret!=null && ret.size()==1){
            return Long.parseLong(ret.get(0).toString());
        }
        return 0;
    }

    public int executeByHql(String hql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        this.assignment(query, params);
        return query.executeUpdate();
    }


    public T getBySql(String sql){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        if(query.getResultList().size()==0)
            return  null;
        else
            return (T) query.setMaxResults(1).getSingleResult();
    }

    public List<T> listBySql(String sql){
        return sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }

    public List<T> pageBySql(String sql, int page, int rows){
        return sessionFactory.getCurrentSession().createNativeQuery(sql)
                .setFirstResult((page-1)*rows)
                .setMaxResults(rows)
                .list();
    }

    public long countBySql(String sql){
        List<?> ret = sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        if (ret!=null && ret.size()==1){
            return Long.parseLong(ret.get(0).toString());
        }
        return 0;
    }

    public int executeBySql(String sql){
        return sessionFactory.getCurrentSession().createNativeQuery(sql).executeUpdate();
    }


    public T getBySql(String sql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        this.assignment(query, params);
        if(query.getResultList().size()==0)
            return null;
        else
            return (T) query.setMaxResults(1).getSingleResult();
    }

    public List<T> listBySql(String sql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        this.assignment(query, params);
        return query.list();
    }

    public List<T> pageBySql(String sql, Map<String,Object> params, int page, int rows){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        this.assignment(query, params);
        return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
    }

    public long countBySql(String sql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        this.assignment(query, params);
        List<?> ret = query.list();
        if (ret!=null && ret.size()==1){
            return Long.parseLong(ret.get(0).toString());
        }
        return 0;
    }

    public int executeBySql(String sql, Map<String,Object> params){
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        this.assignment(query, params);
        return query.executeUpdate();
    }


    /**
     * HQL查询参数赋值
     * @param query         查询
     * @param params        参数
     */
    private void assignment(Query query, Map<String,Object> params){
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if(value instanceof Collection){
                query.setParameterList(key, (Collection) value);
            }else{
                query.setParameter(key, value);
            }
        }
    }

}
