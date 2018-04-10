package com.lfp.jec.frame.util.socket;

import java.io.*;
import java.net.Socket;
import java.util.Date;

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
public class SocketUtil {

    /** 文本结束标记 */
    private static final String EOF = "#EOF#";
    
    /**
     * 发送信息，获取反馈
     * @param client        客户端
     * @param param         参数信息
     * @return json         反馈信息
     * @throws IOException  异常
     */
    public static String sendAndReceive(Socket client, String param) throws IOException {
        return writeAndRead(client, param);
    }

    /**
     * 读取信息，处理信息，反馈信息
     * @param server        服务端
     * @param handle        处理类
     * @throws IOException  异常
     */
    public static void receiveAndSend(Socket server, SocketServerHandle handle) throws IOException {
        while (true) {
            try {
                readAndWrite(server, handle);
            }catch (Exception e){
                e.printStackTrace();
                server.close();
                return;
            }
        }
    }

    /**
     * 写入信息，读取反馈
     * @param client        客户端连接
     * @param info          写入字符串
     * @return ret          读取字符串
     * @throws IOException  异常
     */
    private static String writeAndRead(Socket client, String info) throws IOException {
        //1、写数据
        write(client.getOutputStream(), info);
        System.out.println(new Date()+"[" + client.getRemoteSocketAddress().toString() + "]SEND：" + info);
        //2、写完以后进行读操作
        String ret = read(client.getInputStream());
        System.out.println(new Date()+"[" + client.getRemoteSocketAddress().toString() + "]RECV：" + ret);
        return ret;
    }

    /**
     * 读取信息，处理信息，反馈信息
     * @param server        服务端连接
     * @param handle        处理方法
     * @throws IOException  异常
     */
    private static void readAndWrite(Socket server, SocketServerHandle handle) throws IOException {
        //1、读取信息
        String info = read(server.getInputStream());
        if (info==null || info.trim().length()==0) return;
        System.out.println(new Date()+"[" + server.getRemoteSocketAddress().toString() + "]RECV：" + info);
        //2、处理信息
        String ret = handle.handle(info);
        //3、反馈信息
        write(server.getOutputStream(), ret);
        System.out.println(new Date()+"[" + server.getRemoteSocketAddress().toString() + "]SEND：" + ret);
    }


    /**
     * 从输入流中读取数据
     * @param inputStream   输入流
     * @return ret          读取字符串
     * @throws IOException  异常
     */
    private static String read(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024 * 8];
        int len = inputStream.read(bytes);
        if (len==-1) return null;
        if (len>0 && bytes[len - 1] == 0) len = len - 1;
        sb.append(new String(bytes, 0, len, "UTF-8"));
        return sb.toString();
    }

    /**
     * 向输出流中写入数据
     * @param outputStream  输出流
     * @param info          写入字符串
     * @throws IOException  异常
     */
    private static void write(OutputStream outputStream, String info) throws IOException {
        outputStream.write(info.getBytes("UTF-8"));
        outputStream.flush();
    }


    /**
     * 从输入流中读取数据
     * @param reader        输入流
     * @return info         信息
     * @throws IOException  异常
     */
    private static String read(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String temp;
        int index;
        while ((temp = reader.readLine()) != null) {
            if ((index = temp.indexOf(EOF)) != -1) {//遇到EOF时就结束接收
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 向输出流中写入数据
     * @param writer        输出流
     * @param info          信息
     * @throws IOException  异常
     */
    private static void write(Writer writer, String info) throws IOException {
        writer.write(info);
        writer.write(EOF+"\n");
        writer.flush();
    }

}
