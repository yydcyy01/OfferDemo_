package com.yydcyy.Interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by YYDCYY on 2019-07-24.
 1 队列
 2 阻塞队列
     2.1  阻塞队列有没有好的一面
     2.2 不得不阻塞, 你如何管理
 */

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //AddAndRemoveDemo();
        //OfferAndPoolDemo();
        //OfferDemo();


    }

    private static void OfferDemo() throws InterruptedException {
        /**
         * blockingQueue.offer("a",2L, TimeUnit.SECONDS)
         * 三个参数, (带插入元素, 队列满等待时间 , 单位)
         */
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("f",2L, TimeUnit.SECONDS));
        //测试 满队列加入元素, 返回结果 : false
        System.out.println("测试 满队列加入元素, 返回  :" + blockingQueue.offer("x",2L, TimeUnit.SECONDS));
    }

    private static void OfferAndPoolDemo() {

        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //测试 存取超过队列容量 返回false,存储失败
        System.out.println("测试 存取超过队列容量 :  "+blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //测试 空队列取出 返回 null
        System.out.println("测试 空队列取出:  "+blockingQueue.poll());
    }

    private static void AddAndRemoveDemo() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3); // 初始化设置队列Queue容量

        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //  blockingQueue.add("x"); //Exception in thread "main" java.lang.IllegalStateException: Queue full

        /**element() 检查队列空不空, 队首[先进]元素是谁[a]
         空Queue 报错 : Exception in thread "main" java.util.NoSuchElementException          */
        System.out.println(blockingQueue.element());

        //队列Queue 先进先出
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//Exception in thread "main" java.util.NoSuchElementException
    }
}
