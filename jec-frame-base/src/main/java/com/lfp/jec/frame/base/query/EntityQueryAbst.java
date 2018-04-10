package com.lfp.jec.frame.base.query;

import com.lfp.jec.frame.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Project: lfp-jec
 * Title: 实体查询接口 抽象实现
 * Description: 实现部分共通的方法
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public abstract class EntityQueryAbst<T> implements EntityQuery<T> {
    
    public T formatEqual(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatEqual(name, value, key);
    }
    

    public T formatNotEqual(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatNotEqual(name, value, key);
    }
    

    public T formatLt(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatLt(name, value, key);
    }
    

    public T formatLte(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatLte(name, value, key);
    }
    

    public T formatGt(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatGt(name, value, key);
    }
    

    public T formatGte(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatGte(name, value, key);
    }
    
    public T formatLike(String name, String value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatLike(name, value, key);
    }

    public T formatLikeLeft(String name, String value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatLikeLeft(name, value, key);
    }

    public T formatLikeRight(String name, String value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatLikeRight(name, value, key);
    }

    public T formatNotLike(String name, String value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatNotLike(name, value, key);
    }

    public T formatBetween(String name, Date value1, Date value2) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        Date beg = (value1==null?null: DateUtil.firstSecond(value1));
        Date end = (value2==null?null:DateUtil.lastSecond(value2));
        return this.formatBetween(name, beg, end, key);
    }

    public T formatBetween(String name, Object value1, Object value2) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatBetween(name, value1, value2, key);
    }
    

    public T formatIn(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatIn(name, value, key);
    }
    

    public T formatNotIn(String name, Object value) {
        if (StringUtils.isBlank(name)) return (T) this;
        String key = formatKey(name);
        return this.formatNotIn(name, value, key);
    }

    /**
     * 内部方法，根据属性名称获取map键
     * @param name      属性名称
     * @return key      hql参数名
     */
    String formatKey(String name){
        return name.replace(".", "_");
    }

    /**
     * 内部方法，校验属性值是否需要进行查询
     * @param value     属性值
     * @return boolean  是否有效
     */
    boolean validValue(Object value) {
        return value != null && (!(value instanceof String) || StringUtils.isNotBlank(value.toString()));
    }
    
}
