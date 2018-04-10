package com.lfp.jec.frame.util.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Project: lfp-jec
 * Title:
 * Description:
 * Date: 07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class NioSocketUtil {


    public static void receiveAndSend(SelectionKey key, SocketServerHandle handle) throws IOException {
        if (key.isAcceptable()){
            SocketChannel channel = ((ServerSocketChannel)key.channel()).accept();
            channel.configureBlocking(false);
            channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }else if (key.isReadable()){
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            if (channel.read(buffer)==-1){
                channel.close();
            }else{
                buffer.flip();
                String info = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                String ret = handle.handle(info);

                buffer = ByteBuffer.wrap(ret.getBytes("UTF-8"));
                channel.write(buffer);
                channel.close();
            }
        }
    }
}
