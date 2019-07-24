package com.yydcyy.Interview.study.thread.ReenterLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by YYDCYY on 2019-07-23.
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 自己实现 自旋锁, 照源代码抄
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t myLocy coming! ");

        //若为null , 换成thread, 并跳出自旋 ; 不为 null, 一直自旋
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        // 解除锁, 若为 thread 换成 null
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() +"\t invoked myUnLock()");
    }
    public static void main(String[] args) {
        //原子引用线程
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.myUnLock();
        }, "AAA").start();

        new Thread(()->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.myUnLock();
        }, "BBB").start();
        /**
         * AAA	 myLocy coming!
         BBB	 myLocy coming!
         AAA	 invoked myUnLock()
         BBB	 invoked myUnLock()
         说明 : A停顿5秒, B停顿1秒, B一直自旋等待A解锁, B进入.
         要知道自旋锁适用范围.   别当 "多线程并发程序员实习生进入公司, 项目组代码变慢了, 一走就变快了"

         */
    }
}
