package com.lfp.jec.frame.util.socket;


import java.io.IOException;
import java.net.Socket;

/**
 * Project: lfp-jec
 * Title: Socket 服务端处理任务
 * Description: 采用多线程处理
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class SocketServerTask implements Runnable {
    /** socket */
    private Socket socket;
    /** 处理类 */
    private SocketServerHandle handle;

    public SocketServerTask(Socket socket, SocketServerHandle handle) {
        this.socket = socket;
        this.handle = handle;
    }

    @Override
    public void run() {
        try {
            SocketUtil.receiveAndSend(socket, handle);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
