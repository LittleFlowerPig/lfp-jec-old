package com.lfp.jec.frame.base.domain;

/**
 * Title: 多对多主体对象接口
 * Project: eac
 * Description: 处理多对多关联关系的主体对象实体 implements 该接口，
 *              要求关联对象必须包含以下属性:
 *              String id
 *              String tenant
 *              String key
 *              String name
 * Date: 2017-8-17
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface MainNode {

    String getId();

    String getTenant();

    String getKey();

    String getName();

}
