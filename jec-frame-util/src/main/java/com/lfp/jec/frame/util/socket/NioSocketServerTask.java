package com.lfp.jec.frame.util.socket;


import java.io.IOException;
import java.nio.channels.SelectionKey;

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
public class NioSocketServerTask implements Runnable {
    /** socket */
    private SelectionKey key;
    /** 处理类 */
    private SocketServerHandle handle;

    public NioSocketServerTask(SelectionKey key, SocketServerHandle handle) {
        this.key = key;
        this.handle = handle;
    }

    @Override
    public void run() {
        try {
            while (true) {
                NioSocketUtil.receiveAndSend(key, handle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
