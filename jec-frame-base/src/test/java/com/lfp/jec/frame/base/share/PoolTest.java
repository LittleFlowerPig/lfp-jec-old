package com.lfp.jec.frame.base.share;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Project: lfp-thread
 * Title:
 * Description:
 * Date: 2018-3-16
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class PoolTest {

    @Test
    public void testOne(){
        CountDownLatch latch = new CountDownLatch(100);
        ThreadPool.getInstance().execute(new Thread(() -> {
            int count = 100;
            while (count > 0) {
                System.out.println(count);
                count--;
                latch.countDown();
            }

        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
