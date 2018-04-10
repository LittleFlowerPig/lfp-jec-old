package com.lfp.jec.frame.base.domain;

import com.lfp.jec.frame.base.dict.EntityStatus;

/**
 * Title: 树结构节点抽象接口
 * Project: eac
 * Description: 需要进行树结构处理的实体 implements 该接口，在树结构处理时，泛型对象T会转化为 TreeNode 接口类型，
 *              代理完树结构操作后，保存泛型对象T.
 *              要求泛型对象必须包含以下属性:
 *              String id
 *              String tenant
 *              String name
 *              String casname[Transient]
 *              String code
 *              String cascode
 *              T parent
 *              EntityStatus status
 * Date: 2017-8-17
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface TreeNode<T> {

    String getId();

    String getTenant();

    String getName();

    String getCasname();
    

    String getCode();

    void setCode(String code);

    String getCascode();

    void setCascode(String cascode);

    T getParent();

    void setParent(T parent);

    EntityStatus getStatus();

    void setStatus(EntityStatus status);

}
