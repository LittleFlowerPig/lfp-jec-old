package com.lfp.jec.frame.util;

import java.io.*;

/**
 * Project: lfp-jec
 * Title: 对象序列化工具
 * Description: 将对象序列化再转回对象实现对象的clone
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class SerialUtil implements Serializable {

    public static <T> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    public String toString(){
        return "SerialUtil";
    }

    public static void main(String[] args) {
        SerialUtil util = new SerialUtil();

        SerialUtil copy = null;
        try {
            copy = SerialUtil.clone(util);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(copy.toString());
    }

}
