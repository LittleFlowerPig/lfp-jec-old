package com.lfp.jec.frame.base.domain;

/**
 * Title: 多对多关联对象接口
 * Project: eac
 * Description: 处理多对多关联关系的关联实体 implements 该接口，
 *              要求关联对象必须包含以下属性:
 *              String id
 *              String tenant
 *              String oneId
 *              String oneKey
 *              String oneName
 *              String twoId
 *              String twoKey
 *              String twoName
 * Date: 2017-8-17
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface JoinNode {

    String getId();

    String getTenant();

    String getOneId();

    String getOneKey();

    String getOneName();

    String getTwoId();

    String getTwoKey();

    String getTwoName();

}
