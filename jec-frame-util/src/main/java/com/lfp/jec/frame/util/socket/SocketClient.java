package com.lfp.jec.frame.util.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Project: lfp-jec
 * Title: Socket 客户端
 * Description: Socket 客户端
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class SocketClient {
    /** Server 主机 */
    private static final String host = "127.0.0.1";
    /** Server 端口 */
    private static final int port = 8899;

    /** 超时时间10秒 */
    private static final int time = 10*1000;

    public static void main(String args[]) throws Exception {
        //与服务端建立连接
        Socket client = new Socket(host, port);
        //设置超时间
        client.setSoTimeout(time);
        //0、初始化读写者
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        Writer writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");

        String ret = "";
        try {
            //1、准备写信息
            String info = "{\"cmd\":\"kkk\"}";
            //2、写数据
            SocketIOUtil.write(writer, info);
            //3、写完以后进行读操作
            ret = SocketIOUtil.read(reader);
        } catch (SocketTimeoutException e) {
            System.out.println("操作超时!");

        }
        System.out.println("从服务端读取: " + ret);

        //e、关闭连接
        writer.close();
        reader.close();
        client.close();
    }

}
