package com.yydcyy.Interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{ //共享资源类
    private volatile boolean FLAG = true; // 默认开启 生产+消费 必须保证线程可见此行
    private AtomicInteger atomicInteger = new AtomicInteger(); // 默认0

    BlockingQueue<String> blockingQueue = null;

    //抽象类构造函数 高级架构师哦, 五年工作经验
    public MyResource(BlockingQueue<String > blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void  myProd() throws InterruptedException {
        String data = null;// 复用, 省空间
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet() + ""; //转为 String
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1); // 停一秒, 等待取出
        }
        System.out.println(Thread.currentThread().getName() + "\t  YY 叫停了, FLAG = false, 生产动作结束");
    }
    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result .equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒钟没有取出蛋糕,  消费退出");
                System.out.println("\n \n");
                return;//需要终止
            }
            //TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "\t  消费队列取出蛋糕"+ result +" 成功 ");
            System.out.println("\n");
        }
    }
    public void stop(){
        this.FLAG = false;
    }
}

/**
 * Created by YYDCYY on 2019-07-26.
 * Version 3.0 版 消费者 - 生产者 阻塞队列版
 * 我们只控制 true / false, 锁不归我们管 [但我知道原理]
 *
 * 涉及 volatile / CAS / atomicInteger / BlockQueue / 线程交互 / 原子引用
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10)); // 阻塞队列落地有7种, 不归我们管 可以用
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "生产者线程启动");
            try {
                myResource.myProd(); //调用生产者
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Product").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "消费者线程启动");
            try {
                myResource.myConsumer(); //调用消费者
                System.out.println("\n \n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        System.out.println("\n \n \n");

        System.out.println("10 s 到了, 大老板 main 线程叫停, 活动结束");
        myResource.stop();
    }
}
