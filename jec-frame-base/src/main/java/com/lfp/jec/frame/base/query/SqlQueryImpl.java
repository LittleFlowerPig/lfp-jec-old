package com.lfp.jec.frame.base.query;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
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
public class SqlQueryImpl extends EntityQueryAbst<SqlQuery> implements SqlQuery {
    private String headSql;
    private String fromSql;
    private String whereSql = " WHERE 1=1";
    private String orderSql = "";
    private String addSql = "";
    private Map<String, Object> params = new HashMap<>();


    /**
     * 构造函数
     * @param headSql       初始构造sql，select 语句    eg. SELECT new map(name,key)
     * @param fromSql       初始构造sql，from 语句      eg. FROM Xxx AS obj
     */
    SqlQueryImpl(String headSql, String fromSql) {
        this.headSql = headSql;
        this.fromSql = fromSql;
    }


    public SqlQuery formatEqual(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" = :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatNotEqual(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" <> :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatLt(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" < :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatLte(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" <= :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatGt(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" > :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatGte(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" >= :"+ key;
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatLike(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, "%"+value+"%");
        }
        return this;
    }

    public SqlQuery formatLikeLeft(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, value+"%");
        }
        return this;
    }

    public SqlQuery formatLikeRight(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" LIKE :"+ key;
            this.params.put(key, "%"+value);
        }
        return this;
    }

    public SqlQuery formatNotLike(String name, String value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" NOT LIKE :"+ key;
            this.params.put(key, "%"+value+"%");
        }
        return this;
    }

    public SqlQuery formatBetween(String name, Object value1, Object value2, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value1) && validValue(value2)){
            this.whereSql += " AND obj."+name+" BETWEEN :"+ key+"_b AND :"+key+"_e";
            this.params.put(key+"_b", value1);
            this.params.put(key+"_e", value2);
        }else if (validValue(value1) && !validValue(value2)){
            return this.formatGte(name, value1);
        }else if (!validValue(value1) && validValue(value2)){
            return this.formatLte(name, value2);
        }
        return this;
    }

    public SqlQuery formatIn(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" IN (:"+ key+")";
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatNotIn(String name, Object value, String key) {
        if (StringUtils.isBlank(name)) return this;
        if (validValue(value)){
            this.whereSql += " AND obj."+name+" NOT IN (:"+ key+")";
            this.params.put(key, value);
        }
        return this;
    }

    public SqlQuery formatIsNull(String name) {
        if (StringUtils.isBlank(name)) return this;
        this.whereSql += " AND obj." + name + " IS NULL";
        return this;
    }

    public SqlQuery formatNotNull(String name) {
        if (StringUtils.isBlank(name)) return this;
        this.whereSql += " AND obj." + name + " IS NOT NULL";
        return this;
    }

    public SqlQuery formatSql(String sql) {
        if (StringUtils.isBlank(sql)) return this;
        this.whereSql += " AND " + sql;
        return this;
    }

    public SqlQuery formatSql(String sql, Map<String, Object> params) {
        if (StringUtils.isBlank(sql)) return this;
        this.whereSql += " AND "+sql;
        if (params!=null && params.size()>0)
            this.params.putAll(params);
        return this;
    }


    public SqlQuery orderBy(String sort, String order) {
        if (validValue(sort) && validValue(order)){
            if (this.orderSql.trim().toUpperCase().startsWith("ORDER BY"))
                this.orderSql += ", obj."+sort+" "+order;
            else
                this.orderSql += " ORDER BY obj."+sort+" "+order;
        }else if (validValue(sort)){
            if (this.orderSql.trim().toUpperCase().startsWith("ORDER BY"))
                this.orderSql += ", obj."+sort+" ASC";
            else
                this.orderSql += " ORDER BY obj."+sort+" ASC";
        }
        return this;
    }

    public SqlQuery orderSql(String sql) {
        if (StringUtils.isBlank(sql)) return this;
        if (this.orderSql.trim().toUpperCase().startsWith("ORDER BY"))
            this.orderSql += ", "+sql;
        else
            this.orderSql += " ORDER BY "+sql;
        return this;
    }


    public SqlQuery addSql(String sql) {
        if (StringUtils.isBlank(sql)) return this;
        this.addSql += sql;
        return this;
    }

    public SqlQuery addSql(String sql, Map<String, Object> params) {
        if (StringUtils.isBlank(sql)) return this;
        this.addSql += sql;
        if (params!=null && params.size()>0)
            this.params.putAll(params);
        return this;
    }


    public String getHeadSql() {
        return this.headSql;
    }

    public void setHeadSql(String headSql) {
        this.headSql = headSql;
    }

    public String getFromSql() {
        return this.fromSql;
    }

    public void setFromSql(String fromSql) {
        this.fromSql = fromSql;
    }

    public String getWhereSql() {
        return this.whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    public String getOrderSql() {
        return this.orderSql;
    }

    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }

    public String getAddSql() {
        return this.addSql;
    }

    public void setAddSql(String addSql) {
        this.addSql = addSql;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    public String getSelectSql() {
        return this.headSql + this.fromSql + this.whereSql + this.orderSql + this.addSql;
    }

    public String getCountSql() {
        return "SELECT count(*)" + this.fromSql + this.whereSql + this.addSql;
    }


    public boolean containsKey(String key){
        return this.params.containsKey(key);
    }

    public boolean containsName(String name) {
        String key = formatKey(name);
        return this.params.containsKey(key);
    }

}
