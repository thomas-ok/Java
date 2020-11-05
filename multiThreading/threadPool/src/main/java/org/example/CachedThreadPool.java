package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author Andrew
 * version V1.0
 * 2020/11/5 22:07
 * Description:
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + " j = " + j);
                    }
                }
            };
            threadPool.execute(task);
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("休眠100毫秒");
        }
    }
}
