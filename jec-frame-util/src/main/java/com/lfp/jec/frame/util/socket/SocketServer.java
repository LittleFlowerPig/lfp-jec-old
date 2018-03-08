package com.lfp.jec.frame.util.socket;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Project: lfp-jec
 * Title: Socket 服务端
 * Description: Socket 服务端
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class SocketServer {
    /** ServerSocket监听端口 */
    private static final int port = 8899;


    public static void main(String args[]) throws IOException {
        //定义一个ServerSocket
        ServerSocket server = new ServerSocket(port);
        while (true) {
            //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = server.accept();
            //每接收到一个Socket就建立一个新的线程来处理它
            new Thread(new Task(socket)).start();
        }
    }

    /**
     * 多线程任务类，用来处理Socket请求的
     */
    static class Task implements Runnable {

        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                handleSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 跟客户端Socket进行通信
         * @throws Exception 异常
         */
        private void handleSocket() throws Exception {
            //0、初始化读写者
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            ;
            //1、读取信息
            String info = SocketIOUtil.read(reader);
            System.out.println("从客户端["+socket.getInetAddress().getHostAddress()+"]读取: " + info);
            //2、处理信息
            String ret = handle(info);
            //3、反馈信息
            SocketIOUtil.write(writer, ret);

            //e、关闭连接
            writer.close();
            reader.close();
            socket.close();
        }

    }

    public static String handle(String info){
        JSONObject cmdAndParam = JSONObject.parseObject(info);
        String cmd = cmdAndParam.getString("cmd");
        JSONObject param = cmdAndParam.getJSONObject("param");


        JSONObject ret = new JSONObject();
        ret.put("code", 1);
        ret.put("message", "处理命令["+cmd+"]成功，涉及参数"+param.size()+"个");

        return ret.toJSONString();
    }

}


