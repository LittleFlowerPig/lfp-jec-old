package com.lfp.jec.frame.util.socket;

/**
 * Project: lfp-jec
 * Title: Socket 服务端处理类接口
 * Description: 采用策略模式，解决多服务端处理任务
 * Date: 2018-03-07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
@FunctionalInterface
public interface SocketServerHandle {

    /**
     * Socket 服务端处理类接口方法
     * @param info      入参
     * @return ret      出参
     */
    String handle(String info);

}
