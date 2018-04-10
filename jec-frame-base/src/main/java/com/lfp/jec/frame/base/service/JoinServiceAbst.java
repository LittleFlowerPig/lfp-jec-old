package com.lfp.jec.frame.base.service;

import com.lfp.jec.frame.base.domain.JoinNode;
import com.lfp.jec.frame.base.domain.MainNode;
import com.lfp.jec.frame.base.query.HqlQuery;
import com.lfp.jec.frame.base.query.QueryHelper;

import java.util.*;

public abstract class JoinServiceAbst<O, T, J> extends BasicServiceImpl<J> implements BasicService<J>, JoinService<O, T, J> {

    public List<J> listByOne(String oneId, String tenant, Class<J> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("oneId", oneId);
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public List<J> listByTwo(String twoId, String tenant, Class<J> clazz) {
        HqlQuery query = QueryHelper.createHqlSelect(clazz, tenant)
                .formatEqual("twoId", twoId);
        return this.listByHql(query.getSelectHql(), query.getParams());
    }

    public Map<String, String> mapOneByTwo(String twoId, String tenant, Class<J> clazz) {
        List<J> js = this.listByTwo(twoId, tenant, clazz);
        Map<String, String> ret = new HashMap<>();
        StringBuilder ids = new StringBuilder();
        StringBuilder keys = new StringBuilder();
        StringBuilder names = new StringBuilder();
        for(J j : js){
            JoinNode join = (JoinNode)j;
            ids.append(join.getOneId()).append(",");
            keys.append(join.getOneKey()).append(",");
            names.append(join.getOneName()).append(",");
        }
        if(ids.length()>0){
            ret.put("ids", ids.substring(0, ids.length()-1));
            ret.put("keys", keys.substring(0, keys.length()-1));
            ret.put("names", names.substring(0, names.length()-1));
        }
        return ret;
    }

    public Map<String, String> mapTwoByOne(String oneId, String tenant, Class<J> clazz) {
        List<J> js = this.listByOne(oneId, tenant, clazz);
        Map<String, String> ret = new HashMap<>();
        StringBuilder ids = new StringBuilder();
        StringBuilder keys = new StringBuilder();
        StringBuilder names = new StringBuilder();
        for(J j : js){
            JoinNode join = (JoinNode)j;
            ids.append(join.getTwoId()).append(",");
            keys.append(join.getTwoKey()).append(",");
            names.append(join.getTwoName()).append(",");
        }
        if(ids.length()>0){
            ret.put("ids", ids.substring(0, ids.length()-1));
            ret.put("keys", keys.substring(0, keys.length()-1));
            ret.put("names", names.substring(0, names.length()-1));
        }
        return ret;
    }

    public Set<String> updateOneByTwo(O one, List<T> twos, Class<J> clazz) {
        Set<String> chgTwos = new HashSet<>();
        MainNode oneNode = (MainNode)one;
        //1、初始化参数
        List<J> oldJoins = this.listByOne(oneNode.getId(), oneNode.getTenant(), clazz);
        List<J> delJoins = new ArrayList<>();
        List<J> newJoins = new ArrayList<>();
        //2、遍历老记录，并更新重复记录
        for (J j : oldJoins) {
            JoinNode o = (JoinNode)j;
            boolean flag = false;
            for (T t : twos) {
                MainNode n = (MainNode)t;
                if (o.getTwoId().equals(n.getId())) {
                    /* 相同二方，无需处理 */
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                /* 陈旧用户，标记删除 */
                delJoins.add(j);
                //一方去除了该二方，二方标记为已变更
                chgTwos.add(o.getTwoId());
            }
        }
        //3、遍历新纪录，并新建全新记录
        for (T t : twos) {
            MainNode n = (MainNode)t;
            boolean flag = false;
            for (J j : oldJoins) {
                JoinNode o = (JoinNode)j;
                if (o.getTwoId().equals(n.getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                /* 新增二方，标记新增 */
                J join = this.createJoin(oneNode.getTenant(),oneNode.getId(),oneNode.getKey(),oneNode.getName(),
                        n.getId(),n.getKey(),n.getName());
                newJoins.add(join);
                //一方新增了该二方，二方标记为已变更
                chgTwos.add(n.getId());
            }
        }
        //4、处理标记记录
        //4.1、删除 关联
        this.delete(delJoins);
        //4.2、生成 关联
        this.save(newJoins);
        //5、返回调整的二方（新增+删除）。
        return chgTwos;
    }

    public Set<String> updateTwoByOne(T two, List<O> ones, Class<J> clazz) {
        Set<String> chgOnes = new HashSet<>();
        MainNode twoNode = (MainNode)two;
        //1、初始化参数
        List<J> oldJoins = this.listByTwo(twoNode.getId(), twoNode.getTenant(), clazz);
        List<J> delJoins = new ArrayList<>();
        List<J> newJoins = new ArrayList<>();
        //2、遍历老记录，并更新重复记录
        for (J j : oldJoins) {
            JoinNode o = (JoinNode)j;
            boolean flag = false;
            for (O t : ones) {
                MainNode n = (MainNode)t;
                if (o.getTwoId().equals(n.getId())) {
                    /* 相同一方，无需处理 */
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                /* 陈旧用户，标记删除 */
                delJoins.add(j);
                //二方去除了该一方，一方标记为已变更
                chgOnes.add(o.getTwoId());
            }
        }
        //3、遍历新纪录，并新建全新记录
        for (O t : ones) {
            MainNode n = (MainNode)t;
            boolean flag = false;
            for (J j : oldJoins) {
                JoinNode o = (JoinNode)j;
                if (o.getTwoId().equals(n.getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                /* 新增一方，标记新增 */
                J join = this.createJoin(twoNode.getTenant(),n.getId(),n.getKey(),n.getName(),
                        twoNode.getId(),twoNode.getKey(),twoNode.getName());
                newJoins.add(join);
                //二方新增了该一方，一方标记为已变更
                chgOnes.add(n.getId());
            }
        }
        //4、处理标记记录
        //4.1、删除 关联
        this.delete(delJoins);
        //4.2、生成 关联
        this.save(newJoins);
        //5、返回调整的一方（新增+删除）。
        return chgOnes;
    }

    public List<Object> formatOne(List<J> joins) {
        List<Object> dataList = new ArrayList<>();
        if (joins!=null && joins.size()>0) {
            for (J j : joins) {
                JoinNode join = (JoinNode)j;
                HashMap<String, Object> usr = new HashMap<>();
                usr.put("id", join.getOneId());
                usr.put("key", join.getOneKey());
                usr.put("name", join.getOneName());
                dataList.add(usr);
            }
        }
        return dataList;
    }

    public List<Object> formatTwo(List<J> joins) {
        List<Object> dataList = new ArrayList<>();
        if (joins!=null && joins.size()>0) {
            for (J j : joins) {
                JoinNode join = (JoinNode)j;
                HashMap<String, Object> usr = new HashMap<>();
                usr.put("id", join.getTwoId());
                usr.put("key", join.getTwoKey());
                usr.put("name", join.getTwoName());
                dataList.add(usr);
            }
        }
        return dataList;
    }

}
