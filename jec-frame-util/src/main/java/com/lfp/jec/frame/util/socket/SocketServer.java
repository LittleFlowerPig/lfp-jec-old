package com.lfp.jec.frame.util.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        //定义一个 Socket Server
        ServerSocket server = new ServerSocket(port);
        //多线程线程池，防止并发过高时创建过多线程耗尽资源
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        while (true) {
            //server尝试接收Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = server.accept();
            //每接收到一个Socket就建立一个新的线程来处理它，并提交给线程池
            threadPool.submit(new SocketServerTask(socket, (info)-> "[返回]"+info));
        }
    }

}


