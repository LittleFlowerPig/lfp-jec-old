package com.lfp.jec.frame.util.socket;

import java.io.IOException;
import java.net.Socket;

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
public class NioSocketClient {
    /** Server 主机 */
    private static final String host = "127.0.0.1";
    /** Server 端口 */
    private static final int port = 8899;

    /** 超时时间10秒 */
    private static final int time = 10*1000;

    private static Socket client;

    public static void main(String args[]){

        while (true) {

            try {
                init();

                send();

                Thread.sleep(2000);

                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void init() throws Exception {
        if (client==null || client.isClosed()){
            client = new Socket(host, port);
        }else if (client.getInputStream().available()==0){
            client.close();
            client = new Socket(host, port);
        }
    }

    private static void send(){
        try {
            SocketUtil.sendAndReceive(client, "I am zt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
