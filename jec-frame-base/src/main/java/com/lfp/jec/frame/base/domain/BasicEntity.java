package com.lfp.jec.frame.base.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title: 基础实体抽象基类
 * Project: eac
 * Description: 规定对象的基础属性，包括 id、version、tenant、trace 等，由常规的实体所继承，
 *              方便在后期进行统一处理
 * Date: 2017/9/29
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BasicEntity implements Serializable {
    //--------------- 固定属性 ---------------//
    /** 唯一性标识（hibernate自动维护） */
    @Id
    @GeneratedValue(generator = "fk_uuid")
    @GenericGenerator(name = "fk_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;
    /** 乐观锁（hibernate自动维护） */
    @Version
    @Column(name = "version_")
    protected Integer version;
    /** 租户标识（后台统一维护） */
    protected String tenant;
    /** 审计留痕（后台统一维护） */
    protected String creator;       //创建人
    protected String createTime;     //创建时间
    protected String updater;       //更新人
    protected String updateTime;    //更新时间
    /*-------------------- 基础功能方法 --------------------*/
    /**
     * 判定对象相同的方法，一般按照对象的ID进行区分，
     * 不Override，默认按照对象的内存地址区分，在Set去重、以及equals、contain方法中会出现BUG
     * @param o             目标对象
     * @return boolean      是否相同
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEntity obj = (BasicEntity) o;
        return id != null ? id.equals(obj.id) : obj.id == null;
    }

    /**
     * 与equals配合，一般两者一起Override，是equals判定的依据
     * @return int          hash值
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /*-------------------- get/set方法 --------------------*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
