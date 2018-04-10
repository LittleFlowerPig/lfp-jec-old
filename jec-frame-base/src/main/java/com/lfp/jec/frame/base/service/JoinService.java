package com.lfp.jec.frame.base.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JoinService<O, T, J> {

    /**
     * 创建关联对象
     * @param tenant        租户标识
     * @param oneId         一方ID
     * @param oneKey        一方Key
     * @param oneName       一方Name
     * @param twoId         二方ID
     * @param twoKey        二方Key
     * @param twoName       二方Name
     * @return J            关联对象
     */
    J createJoin(String tenant, String oneId, String oneKey, String oneName, String twoId, String twoKey, String twoName);

    /**
     * 获取一方所有的子表
     * @param oneId         一方ID
     * @param tenant        租户标识
     * @param clazz         关联实体类
     * @return list         子表集合
     */
    List<J> listByOne(String oneId, String tenant, Class<J> clazz);

    /**
     * 获取二方所有的子表
     * @param twoId         二方ID
     * @param tenant        租户标识
     * @param clazz         关联实体类
     * @return list         子表集合
     */
    List<J> listByTwo(String twoId, String tenant, Class<J> clazz);

    /**
     * 获取一方的集合
     * @param twoId         二方ID
     * @param tenant        租户标识
     * @param clazz         关联实体类
     * @return map          一方属性串集合
     */
    Map<String, String> mapOneByTwo(String twoId, String tenant, Class<J> clazz);

    /**
     * 获取二方的集合
     * @param oneId         一方ID
     * @param tenant        租户标识
     * @param clazz         关联实体类
     * @return map          一方属性串集合
     */
    Map<String, String> mapTwoByOne(String oneId, String tenant, Class<J> clazz);

    /**
     * 更新关联关系
     * @param one           一方对象
     * @param twos          二方集合
     * @param clazz         关联实体类
     * @return twoIds       发生变化的二方id集合
     */
    Set<String> updateOneByTwo(O one, List<T> twos, Class<J> clazz);

    /**
     * 更新关联关系
     * @param two           二方对象
     * @param ones          一方集合
     * @param clazz         关联实体类
     * @return oneIds       发生变化的一方id集合
     */
    Set<String> updateTwoByOne(T two, List<O> ones, Class<J> clazz);

    /**
     * 格式化为一方集合
     * @param joins         关联对象集合
     * @return list         一方集合
     */
    List<Object> formatOne(List<J> joins);

    /**
     * 格式化为二方集合
     * @param joins         关联对象集合
     * @return list         二方集合
     */
    List<Object> formatTwo(List<J> joins);

}
