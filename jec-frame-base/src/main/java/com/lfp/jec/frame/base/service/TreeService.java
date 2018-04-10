package com.lfp.jec.frame.base.service;

import java.util.List;

/**
 * Title: 树状结构 Service 接口
 * Description: 提供树状结构的公共 Service 方法
 * Date: 2017-2-16
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 */
public interface TreeService<T> {
    /**
     * 获取根节点编号
     * @return cascode  根节点编号【00】
     */
    String RootCode();

    /**
     * 获取当前节点的[可用][子]节点
     * @param pid       当前节点ID
     * @param tenant    租户
     * @param clazz     泛型类
     * @return nodes    子节点集合
     */
    List<T> listChildren(String pid, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[全部][子]节点
     * @param pid       当前节点ID
     * @param tenant    租户
     * @param clazz     泛型类
     * @return nodes    子节点集合
     */
    List<T> listChildrenAll(String pid, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[可用][后代]节点
     * @param pcascode  当前节点pcascode
     * @param tenant    租户
     * @param clazz     泛型类
     * @return nodes    子节点集合
     */
    List<T> listProgeny(String pcascode, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[全部][后代]节点
     * @param pcascode  当前节点pcascode
     * @param tenant    租户
     * @param clazz     泛型类
     * @return nodes    子节点集合
     */
    List<T> listProgenyAll(String pcascode, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[可用][子]节点-数量
     * @param pid       当前节点ID
     * @param tenant    租户
     * @param clazz     泛型类
     * @return count    子节点数量
     */
    long countChildren(String pid, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[全部][子]节点-数量
     * @param pid       当前节点ID
     * @param tenant    租户
     * @param clazz     泛型类
     * @return count    子节点数量
     */
    long countChildrenAll(String pid, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[可用][后代]节点-数量
     * @param pcascode  当前节点pcascode
     * @param tenant    租户
     * @param clazz     泛型类
     * @return count    子节点数量
     */
    long countProgeny(String pcascode, String tenant, Class<T> clazz);

    /**
     * 获取当前节点的[全部][后代]节点-数量
     * @param pcascode  当前节点pcascode
     * @param tenant    租户
     * @param clazz     泛型类
     * @return count    子节点数量
     */
    long countProgenyAll(String pcascode, String tenant, Class<T> clazz);


    /**
     * 将源节点移动到目标节点下指定位置，并重置编号
     * @param source    源节点
     * @param target    目标节点
     * @param code      指定位置编号
     * @param tenant    租户
     * @param clazz     泛型类
     * @throws Exception 异常
     */
    void insert(T source, T target, String code, String tenant, Class<T> clazz) throws Exception;

    /**
     * 从目标节点的指定编号位置删除一个子树
     * @param target    目标节点
     * @param tenant    租户
     * @param clazz     泛型类
     * @return temp     子树
     * @throws Exception 异常
     */
    T remove(T target, String tenant, Class<T> clazz) throws Exception;

    /**
     * 重置目标节点的编号及其后代节点的编号
     * @param root		目标节点
     * @param code		指定编号
     * @param tenant    租户
     * @param clazz     泛型类
     * @throws Exception 异常
     */
    void reset(T root, String code, String tenant, Class<T> clazz) throws Exception;

    /**
     * 移动节点位置，源节点source，移动到目标节点target的相应moveType位置。
     * @param source    源节点
     * @param target    目标节点
     * @param moveType  移动位置
     * @param tenant    租户
     * @param clazz     泛型类
     * @throws Exception 异常
     */
    void move(T source, T target, String moveType, String tenant, Class<T> clazz) throws Exception;


    /**
     * 获取根节点
     * @param tenant    租户
     * @param clazz     泛型类
     * @return root     根节点
     */
    T getRoot(String tenant, Class<T> clazz);

    /**
     * 判定指定联级编号的节点是否已经存在
     * @param obj       待判定节点
     * @param cascode   待判定全码
     * @param tenant    租户
     * @param clazz     泛型类
     * @return  ret     判定结果，为空表示不存在，是可用的全码
     */
    String isExist(T obj, String cascode, String tenant, Class<T> clazz);

    /**
     * 将指定结果集封装为前端接收数据
     * @param dataList  前端接收数据
     * @param nodes     对象集合
     * @param clazz     泛型类
     * @param tenant    租户
     */
    void handleList(List<Object> dataList, List<T> nodes, String tenant, Class<T> clazz);

}
