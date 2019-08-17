package com.yydcyy.Interview.study.thread;

import java.util.concurrent.*;

/**
 * Created by YYDCYY on 2019-07-26.
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //查看自己服务器核数

        //threadPoolInit();
        /**
         * 照猫画虎, 模仿源码写个 ThreadPool
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy() //最后一个, 记得初始化. 因为默认是Integer.MAX_VALUE
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 1; i <= 15; i ++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t  办理业务");
                });
                //  TimeUnit.MILLISECONDS.sleep(200);
            }
            /**
             * AbortPolicy()最大安全 : coreSize + 阻塞队列数 (5 + 3) 没人敢用RejectedExecutionException , 一言不合报错 java.util.concurrent.RejectedExecutionException: Task com.yydcyy.Interview.study.thread.MyThreadPoolDemo$$Lambda$1/1078694789@3b9a45b3 rejected from java.util.concurrent.ThreadPoolExecutor@7699a589[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
             * CallerRunsPolicy : 谁让你来你找谁  main	  办理业务(超过 8 , main让你来的? 你去找它)     pool-1-thread-1	  办理业务
             * ThreadPoolExecutor.DiscardOldestPolicy()
             * ThreadPoolExecutor.DiscardPolicy()  都返回 8 个处理结果
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    private static void threadPoolInit() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池 5 个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池一个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一池 N 个处理线程
        try {
            for (int i = 1; i <= 20; i ++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t  办理业务");
                });
              //  TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
