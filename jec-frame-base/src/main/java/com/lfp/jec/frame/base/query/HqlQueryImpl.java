package com.lfp.jec.frame.base.query;

import com.lfp.jec.frame.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
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
public class HqlQueryImpl implements HqlQuery {
    private String headHql = "";
    private String fromHql = "";
    private String whereHql = " WHERE 1=1";
    private String orderHql = "";
    private String addHql = "";
    private Map<String, Object> params = new HashMap<>();

    /**
     * 构造函数，select 语句为默认
     * @param fromHql       初始构造hql，from 语句     eg. FROM Xxx AS obj
     */
    HqlQueryImpl(String fromHql) {
        this.fromHql = fromHql;
    }

    /**
     * 构造函数
     * @param headHql       初始构造hql，select 语句    eg. SELECT new map(name,key)
     * @param fromHql       初始构造hql，from 语句      eg. FROM Xxx AS obj
     */
    HqlQueryImpl(String headHql, String fromHql) {
        this.headHql = headHql;
        this.fromHql = fromHql;
    }


    public HqlQuery formatEqual(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatEqual(name, value, key);
    }

    public HqlQuery formatEqual(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" = :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatNotEqual(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatNotEqual(name, value, key);
    }

    public HqlQuery formatNotEqual(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" <> :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatLt(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatLt(name, value, key);
    }

    public HqlQuery formatLt(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" < :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatLte(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatLte(name, value, key);
    }

    public HqlQuery formatLte(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" <= :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatGt(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatGt(name, value, key);
    }

    public HqlQuery formatGt(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" > :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatGte(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatGte(name, value, key);
    }

    public HqlQuery formatGte(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" >= :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatLike(String name, String value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatLike(name, value, key);
    }

    public HqlQuery formatLike(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, "%"+value+"%");
        }
        return this;
    }

    public HqlQuery formatLikeLeft(String name, String value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatLikeLeft(name, value, key);
    }

    public HqlQuery formatLikeLeft(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, value+"%");
        }
        return this;
    }

    public HqlQuery formatLikeRight(String name, String value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatLikeRight(name, value, key);
    }

    public HqlQuery formatLikeRight(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, "%"+value);
        }
        return this;
    }

    public HqlQuery formatNotLike(String name, String value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatNotLike(name, value, key);
    }

    public HqlQuery formatNotLike(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" NOT LIKE :"+ key;
            this.params.put(key, "%"+value+"%");
        }
        return this;
    }

    public HqlQuery formatBetween(String name, Date value1, Date value2) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        Date beg = (value1==null?null:DateUtil.firstSecond(value1));
        Date end = (value2==null?null:DateUtil.lastSecond(value2));
        return this.formatBetween(name, beg, end, key);
    }

    public HqlQuery formatBetween(String name, Object value1, Object value2) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatBetween(name, value1, value2, key);
    }

    public HqlQuery formatBetween(String name, Object value1, Object value2, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value1) && validValue(value2)){
            this.whereHql += " AND obj."+name+" BETWEEN :"+ key+"_b AND :"+key+"_e";
            this.params.put(key+"_b", value1);
            this.params.put(key+"_e", value2);
        }else if (validValue(value1) && !validValue(value2)){
            return this.formatGte(name, value1);
        }else if (!validValue(value1) && validValue(value2)){
            return this.formatLte(name, value2);
        }
        return this;
    }

    public HqlQuery formatIn(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatIn(name, value, key);
    }

    public HqlQuery formatIn(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" IN (:"+ key+")";
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatNotIn(String name, Object value) {
        if (StringUtils.isBlank(name)) return this;
        String key = formatKey(name);
        return this.formatNotIn(name, value, key);
    }

    public HqlQuery formatNotIn(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereHql += " AND obj."+name+" NOT IN (:"+ key+")";
            this.params.put(key, value);
        }
        return this;
    }

    public HqlQuery formatIsNull(String name) {
        if (StringUtils.isBlank(name)) return this;
        this.whereHql += " AND obj." + name + " IS NULL";
        return this;
    }

    public HqlQuery formatNotNull(String name) {
        if (StringUtils.isBlank(name)) return this;
        this.whereHql += " AND obj." + name + " IS NOT NULL";
        return this;
    }

    public HqlQuery formatHql(String hql) {
        if (StringUtils.isBlank(hql)) return this;
        this.whereHql += " AND " + hql;
        return this;
    }

    public HqlQuery formatHql(String hql, Map<String, Object> params) {
        if (StringUtils.isBlank(hql)) return this;
        this.whereHql += " AND "+hql;
        if (params!=null && params.size()>0)
            this.params.putAll(params);
        return this;
    }


    public HqlQuery orderBy(String sort, String order) {
        if (validValue(sort) && validValue(order)){
            if (this.orderHql.trim().toUpperCase().startsWith("ORDER BY"))
                this.orderHql += ", obj."+sort+" "+order;
            else
                this.orderHql += " ORDER BY obj."+sort+" "+order;
        }else if (validValue(sort)){
            if (this.orderHql.trim().toUpperCase().startsWith("ORDER BY"))
                this.orderHql += ", obj."+sort+" ASC";
            else
                this.orderHql += " ORDER BY obj."+sort+" ASC";
        }
        return this;
    }

    public HqlQuery orderHql(String hql) {
        if (StringUtils.isBlank(hql)) return this;
        if (this.orderHql.trim().toUpperCase().startsWith("ORDER BY"))
            this.orderHql += ", "+hql;
        else
            this.orderHql += " ORDER BY "+hql;
        return this;
    }


    public HqlQuery addHql(String hql) {
        if (StringUtils.isBlank(hql)) return this;
        this.addHql += hql;
        return this;
    }

    public HqlQuery addHql(String hql, Map<String, Object> params) {
        if (StringUtils.isBlank(hql)) return this;
        this.addHql += hql;
        if (params!=null && params.size()>0)
            this.params.putAll(params);
        return this;
    }


    public String getHeadHql() {
        return this.headHql;
    }

    public void setHeadHql(String headHql) {
        this.headHql = headHql;
    }

    public String getFromHql() {
        return this.fromHql;
    }

    public void setFromHql(String fromHql) {
        this.fromHql = fromHql;
    }

    public String getWhereHql() {
        return this.whereHql;
    }

    public void setWhereHql(String whereHql) {
        this.whereHql = whereHql;
    }

    public String getOrderHql() {
        return this.orderHql;
    }

    public void setOrderHql(String orderHql) {
        this.orderHql = orderHql;
    }

    public String getAddHql() {
        return this.addHql;
    }

    public void setAddHql(String addHql) {
        this.addHql = addHql;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    public String getSelectHql() {
        return this.headHql + this.fromHql + this.whereHql + this.orderHql + this.addHql;
    }

    public String getCountHql() {
        return this.fromHql + this.whereHql + this.addHql;
    }


    public boolean containsKey(String key){
        return this.params.containsKey(key);
    }

    public boolean containsName(String name) {
        String key = formatKey(name);
        return this.params.containsKey(key);
    }


    /**
     * 内部方法，根据属性名称获取map键
     * @param name      属性名称
     * @return key      hql参数名
     */
    private String formatKey(String name){
        return name.replace(".", "_");
    }

    /**
     * 内部方法，校验属性值是否需要进行查询
     * @param value     属性值
     * @return boolean  是否有效
     */
    private boolean validValue(Object value) {
        return value != null && (!(value instanceof String) || StringUtils.isNotBlank(value.toString()));
    }

}
