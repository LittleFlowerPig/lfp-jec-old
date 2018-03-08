package com.lfp.jec.frame.util.socket;

import java.io.*;

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
public class SocketIOUtil {

    /** 文本结束标记 */
    private static final String EOF = "#EOF#";

    /**
     * 从输入流中读取信息
     * @param reader        输入流
     * @return info         信息
     * @throws Exception    异常
     */
    public static String read(BufferedReader reader) throws Exception {
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
     * 将信息写到输出流中
     * @param writer        输出流
     * @param info          信息
     * @throws Exception    异常
     */
    public static void write(Writer writer, String info) throws Exception {
        writer.write(info);
        writer.write(EOF+"\n");
        writer.flush();
    }

}
