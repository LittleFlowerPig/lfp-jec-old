package com.lfp.jec.frame.base.service;

import com.lfp.jec.frame.base.dict.EntityStatus;
import com.lfp.jec.frame.base.domain.TreeNode;
import com.lfp.jec.frame.base.query.HqlQuery;
import com.lfp.jec.frame.base.query.QueryHelper;
import com.lfp.jec.frame.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Title: 树状结构 Service 实现 [BasicService\FullTextService\TreeService]
 * Project: eac
 * Description:提供全文检索的公共 Service 实现，通过提供泛型类T，实现接口方法
 *              部分无特殊要求的功能模块只继承该实现即可
 *              extends 自 FullTextServiceImpl，具有基础增删改查 Service 和全文检索 Service 的所有方法
 * Date: 2017-8-17
 * Copyright: Copyright (c) 2020
 * Company: 北京中科院软件中心有限公司 (SEC)
 *
 * @author ZhuTao
 * @version 1.0
 * @param <T>   泛型类
 */
@Service
@Transactional
public class TreeServiceImpl<T> extends BasicServiceImpl<T> implements BasicService<T>, TreeService<T> {

    /*---------- TreeService 自身变量 ----------*/
    /** 根节点编码 */
    protected static final String RootCode = "00";

    public String RootCode() {
        return RootCode;
    }

    /*---------- TreeService 泛型实现 ----------*/
    public List<T> listChildren(String pid, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("parent.id",pid)
                .formatEqual("status", EntityStatus.NORMAL)
                .orderBy("cascode", "asc");
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public List<T> listChildrenAll(String pid, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("parent.id",pid)
                .formatNotEqual("status", EntityStatus.DELETE)
                .orderBy("cascode", "asc");
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public List<T> listProgeny(String pcascode, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatLikeLeft("cascode",pcascode)
                .formatEqual("status", EntityStatus.NORMAL)
                .orderBy("cascode", "asc");
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public List<T> listProgenyAll(String pcascode, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatLikeLeft("cascode",pcascode)
                .formatNotEqual("status", EntityStatus.DELETE)
                .orderBy("cascode", "asc");
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public long countChildren(String pid, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("parent.id",pid)
                .formatEqual("status", EntityStatus.NORMAL);
        return this.countByHql(query.getCountHql(), query.getParams());
    }

    public long countChildrenAll(String pid, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("parent.id",pid)
                .formatNotEqual("status", EntityStatus.DELETE);
        return this.countByHql(query.getCountHql(), query.getParams());
    }

    public long countProgeny(String pcascode, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatLikeLeft("cascode",pcascode)
                .formatEqual("status", EntityStatus.NORMAL);
        return this.countByHql(query.getCountHql(), query.getParams());
    }

    public long countProgenyAll(String pcascode, String tenant, Class<T> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatLikeLeft("cascode",pcascode)
                .formatNotEqual("status", EntityStatus.DELETE);
        return this.countByHql(query.getCountHql(), query.getParams());
    }

    public void insert(T source, T target, String code, String tenant, Class<T> clazz) throws Exception {
        int seqd = Integer.parseInt(code);
        List<T> brothers = this.listChildrenAll(((TreeNode)target).getId(), tenant, clazz);
        brothers.remove(source);
        for(int i=seqd-1;i<brothers.size();i++){
            T obj = brothers.get(i);
            String cc = String.format("%02d", i+2);
            reset(obj, cc, tenant, clazz);
        }
        ((TreeNode)source).setParent(target);
        reset(source, code, tenant, clazz);
    }

    public T remove(T target, String tenant, Class<T> clazz) throws Exception {
        TreeNode t = (TreeNode) target;
        TreeNode root = (TreeNode) t.getParent();
        int seqs = Integer.parseInt(t.getCode());
        List<T> brothers = this.listChildrenAll(root.getId(), tenant, clazz);
        for(int i=seqs; i<brothers.size(); i++){
            T obj = brothers.get(i);
            String code = String.format("%02d", i);
            reset(obj, code, tenant, clazz);
        }
        t.setParent(null);
        this.save(target);
        return target;
    }

    public void reset(T root, String code, String tenant, Class<T> clazz) throws Exception {
        TreeNode r = (TreeNode) root;
        r.setCode(code);
        if(r.getParent()!=null){
            TreeNode parent = (TreeNode) r.getParent();
            r.setCascode(parent.getCascode()+"-"+code);
        }else{
            r.setCascode(code);
        }
        this.save(root);
        //查找子节点
        List<T> c = this.listChildrenAll(r.getId(), tenant, clazz);
        //循环递归子树
        if(c.size()>0){//不是叶子节点，递归执行
            for(int i=0;i<c.size();i++){
                T obj = c.get(i);
                String cc = String.format("%02d", i+1);
                reset(obj, cc, tenant, clazz);
            }
        }
    }

    public void move(T source, T target, String moveType, String tenant, Class<T> clazz) throws Exception {
        this.remove(source, tenant, clazz);
        TreeNode t = (TreeNode) target;
        if("prev".equals(moveType)){		//插在目标位置之前
            this.insert(source, (T) t.getParent(), t.getCode(), tenant, clazz);
        }else if("next".equals(moveType)){	//插在目标位置之后
            String code = String.format("%02d", Integer.parseInt(t.getCode())+1);
            this.insert(source, (T) t.getParent(), code, tenant, clazz);
        }else{								//插在目标位置之中
            String code = String.format("%02d", this.countChildrenAll(t.getId(),tenant, clazz)+1);
            this.insert(source, target, code, tenant, clazz);
        }
    }

    public T getRoot(String tenant, Class<T> clazz) {
        return this.getByProperty("cascode", RootCode, tenant, clazz);
    }

    public String isExist(T obj, String cascode, String tenant, Class<T> clazz) {
        if (StringUtils.isBlank(cascode)){
            return "联级编号不能为空";
        }
        T find = this.getWellByProperty("cascode", cascode, tenant, clazz);
        if(find == null) return "";
        if (!obj.equals(find)) return "该编号已经被使用";
        return "";
    }

    public void handleList(List<Object> dataList, List<T> nodes, String tenant, Class<T> clazz) {
        for(T node : nodes){
            TreeNode o = (TreeNode) node;
            TreeNode p = null;
            if (o.getParent()!=null){
                p = (TreeNode) o.getParent();
            }
            //树结构只显示 正常状态的节点
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", o.getId());
            map.put("pId", p!=null ? p.getId() : "");
            map.put("name", o.getName());
            map.put("pName", p!=null ? p.getName() : "");
            map.put("cascode", o.getCascode());
            map.put("open", o.getParent()!=null ? "false" : "true");//只打开根节点
            map.put("isParent", this.countChildren(o.getId(), tenant, clazz)>0);
            if (RootCode.equals(o.getCascode())){//根节点特殊处理
                map.put("drag", "false");
                map.put("iconSkin", "pIcon01");
            }
            dataList.add(map);
        }
    }

    /*---------- BasicService 覆盖 Override ----------*/
    @Override
    public String delete(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            TreeNode node = (TreeNode) o;
            if (RootCode.equals(node.getCascode())){
                ret.append(o.toString()).append("根节点，无法删除<br>");
                continue;
            }
            if(this.countChildrenAll(node.getId(), node.getTenant(), clazz)>0){
                ret.append(o.toString()).append("存在下级节点，无法删除<br>");
                continue;
            }
            try {
                this.remove(o, node.getTenant(), clazz);
                node.setStatus(EntityStatus.DELETE);
                if(!this.save(o).equals("")){
                    ret.append(o.toString()).append("删除失败<br>");
                }
            } catch (Exception e) {
                log.error(o.toString()+"删除失败",e);
                ret.append(o.toString()).append("删除失败<br>");
            }

        }
        return ret.toString();
    }

    @Override
    public String normal(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            TreeNode node = (TreeNode) o;
            if (RootCode.equals(node.getCascode())){
                continue;
            }
            if (!EntityStatus.LOCKED.equals(node.getStatus())){
                ret.append(o.toString()).append("非锁定状态，无法激活<br>");
                continue;
            }
            if(node.getParent()!=null){
                TreeNode p = (TreeNode) node.getParent();
                if (EntityStatus.LOCKED.equals(p.getStatus())){
                    ret.append(o.toString()).append("父节点未激活，无法激活<br>");
                    continue;
                }
            }
            node.setStatus(EntityStatus.NORMAL);
            if(!this.save(o).equals("")){
                ret.append(o.toString()).append("激活失败<br>");
            }
        }
        return ret.toString();
    }

    @Override
    public String locked(String ids, Class<T> clazz) {
        List<String> objIds = StringUtil.splitList(ids);
        if (objIds==null) return "参数错误，未选定操作记录！";
        StringBuilder ret = new StringBuilder();
        for (String id : objIds){
            T o = this.get(id, clazz);
            TreeNode node = (TreeNode) o;
            if (RootCode.equals(node.getCascode())){
                ret.append(o.toString()).append("根节点，无法锁定<br>");
                continue;
            }
            if (!EntityStatus.NORMAL.equals(node.getStatus())){
                ret.append(o.toString()).append("非激活状态，无法锁定<br>");
                continue;
            }
            if(this.countChildren(node.getId(), node.getTenant(), clazz)>0){
                ret.append(o.toString()).append("存在激活的下级节点，无法锁定<br>");
                continue;
            }
            node.setStatus(EntityStatus.LOCKED);
            if(!this.save(o).equals("")){
                ret.append(o.toString()).append("锁定失败<br>");
            }
        }
        return ret.toString();
    }

}
