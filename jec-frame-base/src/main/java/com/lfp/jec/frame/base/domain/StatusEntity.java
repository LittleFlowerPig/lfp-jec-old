package com.lfp.jec.frame.base.domain;

import com.lfp.jec.frame.base.dict.EntityStatus;

import javax.persistence.*;

/**
 * Title: 状态实体抽象基类
 * Project: eac
 * Description: 规定带状态属性的基础对象，继承 BasicEntity ，添加 EntityStatus 属性。
 * Date: 2017/9/29
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class StatusEntity extends BasicEntity {

    /** 状态[正常、禁用、(软删除)] */
    @Enumerated(EnumType.STRING)
    protected EntityStatus status;

    /*-------------------- get/set方法 --------------------*/
    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

}
