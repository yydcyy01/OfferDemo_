package com.yydcyy.Interview.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{ //资源表
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        //加个锁
        lock.lock();
        try {
            // 1 判断
            while (number != 0){
            //if (number != 0){
                /**
                 * java.util.concurrent.locks.Condition; 中的wait()方法说的 spurious wakeup 虚假唤醒
                 *  面试考点: API 文档说明, 多线程判断用While, 否则控制不了 0,1交替.
                 *  wait() 方法属于 Object
                 *
                 */

                // 等待, 不能生产
                condition.await();
        }
        // 2 干活
            number ++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {           e.printStackTrace();        }
        finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        //加个锁
        lock.lock();
        try {
            // 1 判断
            while (number == 0){
            //if (number == 0){
                // 等待, 不能生产
                condition.await();
            }
            // 2 干活
            number --;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {           e.printStackTrace();        }
        finally {
            lock.unlock();
        }
    }

}


/**
 * Created by YYDCYY on 2019-07-25.
 * 模拟需求 : 初始化一个变量, 两个线程轮流带着其进行操作, 一个 +1, 一个-1; 循环5轮
 *
 * 步骤口诀 :
 * 1 线程     操作(方法)     资源类
 * 2 判断     干活           通知
 * 3 防止虚假唤醒机制
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {

        ShareDate shareDate = new ShareDate();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDate.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA1").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDate.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA2").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDate.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB1").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDate.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB2").start();

        System.out.println( " Over , Over");
    }
}
