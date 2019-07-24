package com.yydcyy.Interview.study.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by YYDCYY on 2019-07-24.
 * 值是伸缩的, 增一减一.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟 3 个车位
        for (int i = 1; i <= 6; i ++) //模拟 6 部车位
            new Thread(()->{

                try {
                    semaphore.acquire(); //sync.acquireSharedInterruptibly(1);
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位! ");
                    try {
                        //暂停 3 秒
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException e) {   e.printStackTrace();  }
                    System.out.println(Thread.currentThread().getName() + "\t 停车 3 秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();//sync.releaseShared(1);
                }
            }, String.valueOf(i)).start();
    }
}
