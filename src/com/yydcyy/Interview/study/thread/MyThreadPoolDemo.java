package com.yydcyy.Interview.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YYDCYY on 2019-07-26.
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
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
