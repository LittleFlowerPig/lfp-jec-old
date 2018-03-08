package com.lfp.jec.frame.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Project: lfp-jec
 * Title: 逆波兰表达式工具
 * Description: 将字符串分解为逆波兰表达式串集合
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class RPNUtil {

    /** 操作符集合 */
    public static Set<String> OPTS = new HashSet<String>() {{add("&");add("|");}};

    /**
     * 将表达式分解为RPN串集合
     * @param equation      原始字符串表达式
     * @return list         RPN串集合
     */
    public static List<String> getRPN(String equation){
        return createRPN(getEquation(equation));
    }

    /**
     * 将表达式分解为因子串集合
     * @param equation      原始字符串表达式
     * @return list         因子串集合
     */
    public static List<String> getEquation(String equation){
        List<String> items = new ArrayList<>();
        if (StringUtils.isNotBlank(equation)) {
            //获得输入内容后，将字符串内的空格都给消掉
            equation = equation.replaceAll("\\s*", "");
            char[] list = equation.toCharArray();
            int head = 0;
            for (int i = 0; i < list.length; i++) {
                if (list[i] == '|' || list[i] == '&' || list[i] == '(' || list[i] == ')') {
                    items.add(equation.substring(head, i));
                    items.add(equation.substring(i, i + 1));
                    head = i + 1;
                }
            }
            if (head < list.length) {
                items.add(equation.substring(head, list.length));
            }
        }
        return items;
    }

    /**
     * 将表达式因子串集合转化为RPN串集合
     * @param items         因子串集合
     * @return list         RPN串集合
     */
    public static List<String> createRPN(List<String> items){
        if (items==null || items.size()==0) return items;
        List<String> sign = new ArrayList<>();
        List<String> rpnList = new ArrayList<>();
        sign.add("#");
        int signlen = 0;
        String x;
        for (String item : items) {
            //依次读取字符，进行判断
            x = item.trim();
            if ("(".equals(x)) {
                //符号位加1
                sign.add(x);
                signlen++;
            } else if (")".equals(x)) {
                //依次将括号内容转入到rpnList中
                rpnList.add(sign.get(signlen));
                //运算符号位减位
                sign.remove(sign.get(sign.size() - 1));
                signlen--;
            } else if ("|".equals(x)) {
                for (int j = signlen; j >= 0; j--) {
                    //|的运算优先级比&的低
                    if (sign.get(j).equals("(") || signlen == 0) {
                        break;
                    } else {
                        //直接将现在的栈顶元素搞到rpnList中
                        rpnList.add(sign.get(j));
                        //运算符号位减位
                        sign.remove(sign.get(sign.size() - 1));
                        signlen--;
                    }
                }
                sign.add(x);
                signlen++;
            } else if ("&".equals(x)) {
                for (int j = signlen; j >= 0; j--) {
                    //运算优先级，&的运算优先级高于|
                    if (sign.get(j).equals("(") || sign.get(j).equals("|") || signlen == 0) {
                        break;
                    } else {
                        //把现在的顶部运算符拿出来
                        rpnList.add(sign.get(j));
                        //运算符号位减位
                        sign.remove(sign.get(sign.size() - 1));
                        signlen--;
                    }
                }
                sign.add(x);
                signlen++;
            } else {
                rpnList.add(x);
            }
        }
        //当符号数组不为空，倒置放到rpn数组
        while(signlen>0){
            rpnList.add(sign.get(signlen));
            sign.remove(sign.get(sign.size()-1));
            signlen--;
        }
        return rpnList;
    }

    /**
     * 数组打印功能
     * @param items         集合
     */
    public static void printList(List<String> items){
        for (String item : items) {
            System.out.print(item+" ");
        }
    }

    /**
     * 集合打印功能
     * @param items         集合
     */
    public static void printSet(Set<Object> items){
        for (Object item : items) {
            System.out.print(item+" ");
        }
    }

    /**
     * 计算交并集的结果
     * @param tokens        对象串
     * @return Set          结果集合
     */
    public static Set<Object> evalRPN(List<Object> tokens) {
        Stack<Set<Object>> stack = new Stack<>();
        for (Object t : tokens) {
            if (t instanceof String && (t.equals("&")||t.equals("|"))) {
                Set<Object> a = stack.pop();
                Set<Object> b = stack.pop();
                if ("&".equals(t)){
                    Set<Object> temp = new HashSet<>();
                    temp.addAll(a);
                    temp.retainAll(b);
                    stack.push(temp);
                }else if ("|".equals(t)){
                    Set<Object> temp = new HashSet<>();
                    temp.addAll(a);
                    temp.addAll(b);
                    stack.push(temp);
                }
            } else {
                stack.push((Set<Object>) t);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String equation = "ORG_ID=123&ROLE_ID=234|ORG_NAME=zz&GROUP_ID=345";
        List<String> items = getEquation(equation);
        printList(items);
        System.out.println("");
        List<String> lists = createRPN(items);
        printList(lists);
        System.out.println("");

        List<Object> tokens = new ArrayList<>();
        tokens.add(new HashSet<String>(){{add("1");add("2");}});
        tokens.add(new HashSet<String>(){{add("2");add("3");}});
        tokens.add("|");
        tokens.add(new HashSet<String>(){{add("3");add("4");}});
        tokens.add(new HashSet<String>(){{add("4");add("5");}});
        tokens.add("|");
        tokens.add("|");

        Set<Object> set = evalRPN(tokens);
        printSet(set);
    }

}
