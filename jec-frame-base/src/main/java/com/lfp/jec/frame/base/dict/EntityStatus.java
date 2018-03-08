package com.lfp.jec.frame.base.dict;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Project: lfp-jec
 * Title: 信息实体状态枚举
 * Description: 枚举信息实体的状态类型，用于激活、锁定、假删除等
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public enum EntityStatus {

    /*---------- 枚举类型 ----------*/
    /** 激活状态 */
    NORMAL("已激活"),
    /** 锁定状态 */
    LOCKED("已锁定"),
    /** 删除状态 */
    DELETE("已删除");

    /*---------- 成员变量 ----------*/
    /** 名称 */
    private String name;

    /*---------- 构造方法 ----------*/
    EntityStatus(String name) {
        this.name = name;
    }

    /*---------- 基本方法 ----------*/
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*---------- 定义方法 ----------*/
    /**
     * 根据枚举类型的名称转化为枚举类型
     * @param value             存储值
     * @return EntityStatus     状态
     */
    public static EntityStatus parseValue(String value) {
        for (EntityStatus o : EntityStatus.values()) {
            if (o.name().equals(value)) return o;
        }
        return null;
    }

    /**
     * 根据枚举类型的名称转化为枚举类型
     * @param name              名称
     * @return EntityStatus     状态
     */
    public static EntityStatus parseName(String name) {
        for (EntityStatus o : EntityStatus.values()) {
            if (o.name.equals(name)) return o;
        }
        return null;
    }

    /**
     * 获取枚举类型的map
     * @return EnumMap          map
     */
    public static LinkedHashMap<String, String> mapAll() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (EntityStatus o : EntityStatus.values()) {
            map.put(o.name(), o.name);
        }
        return map;
    }

    /**
     * 获取枚举类型的set
     * @return EnumSet          set
     */
    public static Set<EntityStatus> setAll() {
        return EnumSet.allOf(EntityStatus.class);
    }

    /**
     * 获取枚举类型的map
     * @return EnumMap          map
     */
    public static LinkedHashMap<String, String> mapWell() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(EntityStatus.NORMAL.name(), EntityStatus.NORMAL.name);
        map.put(EntityStatus.LOCKED.name(), EntityStatus.LOCKED.name);
        return map;
    }

    /**
     * 获取枚举类型的set
     * @return EnumSet          set
     */
    public static Set<EntityStatus> setWell() {
        return EnumSet.range(EntityStatus.NORMAL, EntityStatus.LOCKED);
    }

}