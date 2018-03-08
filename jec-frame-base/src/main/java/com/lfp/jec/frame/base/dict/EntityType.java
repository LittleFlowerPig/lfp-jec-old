package com.lfp.jec.frame.base.dict;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Project: lfp-jec
 * Title: 信息实体类型枚举
 * Description: 枚举信息实体的类型
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public enum EntityType {

    /*---------- 枚举类型 ----------*/
    /** 系统内置 */
    SYSTEM("系统内置"),
    /** 用户定义 */
    CUSTOM("用户定义"),
    /** 其他类型 */
    OTHERS("其他类型");

    /*---------- 成员变量 ----------*/
    /** 名称 */
    private String name;

    /*---------- 构造方法 ----------*/
    EntityType(String name) {
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
     * @return EntityType       状态
     */
    public static EntityType parseValue(String value) {
        for (EntityType o : EntityType.values()) {
            if (o.name().equals(value)) return o;
        }
        return null;
    }

    /**
     * 根据枚举类型的名称转化为枚举类型
     * @param name              名称
     * @return EntityType       状态
     */
    public static EntityType parseName(String name) {
        for (EntityType o : EntityType.values()) {
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
        for (EntityType o : EntityType.values()) {
            map.put(o.name(), o.name);
        }
        return map;
    }

    /**
     * 获取枚举类型的set
     * @return EnumSet          set
     */
    public static Set<EntityType> setAll() {
        return EnumSet.allOf(EntityType.class);
    }

    /**
     * 获取枚举类型的map
     * @return EnumMap          map
     */
    public static LinkedHashMap<String, String> mapWell() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(EntityType.SYSTEM.name(), EntityType.SYSTEM.name);
        map.put(EntityType.CUSTOM.name(), EntityType.CUSTOM.name);
        return map;
    }

    /**
     * 获取枚举类型的set
     * @return EnumSet          set
     */
    public static Set<EntityType> setWell() {
        return EnumSet.range(EntityType.SYSTEM, EntityType.CUSTOM);
    }

}