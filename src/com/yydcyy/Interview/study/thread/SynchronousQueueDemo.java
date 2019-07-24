package com.yydcyy.Interview.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by YYDCYY on 2019-07-24.
 * SynchronousQueue演示
 * SynchronousQueue : 不存储元素的阻塞队列, 即单个元素的队列 [专属] 即取出才可放入.
 运行结果:
 * AAA	 put 1
 BBB	1   被取出了
 AAA	 put 2
 BBB	2   被取出了
 AAA	 put 3
 BBB	3   被取出了

 说明 : 线程 AAA 存入 "1" ,线程A准备存入"2",满,未遂;  线程B 睡了5秒取出1. 线程A方可存入"2" .
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                try{
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e) {   e.printStackTrace();    }
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take() + "   被取出了");

                try{
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e) {   e.printStackTrace();    }
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take() + "   被取出了");

                try{
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e) {   e.printStackTrace();    }
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take() + "   被取出了");



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
