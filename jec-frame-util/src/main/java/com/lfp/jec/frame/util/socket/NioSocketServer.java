package com.lfp.jec.frame.util.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioSocketServer {

    /** ServerSocket监听端口 */
    private static final int port = 8899;


    public static void main(String args[]) throws IOException {
        //定义一个 Socket Server
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);

        //多线程线程池，防止并发过高时创建过多线程耗尽资源
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        while (true) {
            if (selector.select(3000)==0){
                continue;
            }
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                threadPool.submit(new NioSocketServerTask(key, (info)-> "[返回]"+info));
                keyIterator.remove();
            }
        }
    }

}
