package com.lfp.jec.frame.base.share;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Project: lfp-thread
 * Title: 多线程线程池
 * Description: 用于管理全局多线程，所有线程直接提交到池中
 * Date: 2018-3-16
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class ThreadPool {
    /** 单例模式 */
    private static ThreadPool instance = new ThreadPool();
    /** 获取单例 */
    public static ThreadPool getInstance() {return instance;}
    /** 构造必要属性 */
    private ThreadPool() {
        threadPool = Executors.newFixedThreadPool(100);
    }

    /** 内置线程池 */
    private ExecutorService threadPool;

    /**
     * 执行任务
     * @param task  任务
     */
    public void execute(Runnable task){
        threadPool.execute(task);
    }

    /**
     * 提交任务并获取返回
     * @param task  任务
     * @return ret  返回
     */
    public Future<?> submit(Runnable task){
        return threadPool.submit(task);
    }

    /**
     * 提交任务并获取返回
     * @param task  任务
     * @return ret  返回
     */
    public Future<?> submit(Callable<?> task){
        return threadPool.submit(task);
    }



}
