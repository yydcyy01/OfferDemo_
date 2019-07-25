package com.yydcyy.Interview.study.thread;

/*
 * Created by YYDCYY on 2019-07-26.
 *题目 : synchronized 和 Lock 有什么区别 ? 用新的lock有什么好处? 请举例说说
 *
 * 1  原始构成
 *       Synchronized 是关键字 属于 JVM 层面
 *          monitorenter (底层是monitor对象来完成, 其实 wait / notify 等方法依赖monitor对象只有在同步代码块中 / 同步方法中才可以调用wait / notify等方法
 *          monitorexit [javap 反编译, 汇编代码可以看到]
 *       Lock 是具体的类 (java.util.concurrent.locks.Lock) 是 API 层面的锁, 通过 new 产生.
 *
 * 2  使用方法
 *        synchronized 不需要用户去释放锁, 当 synchronized 代码执行完毕 / 异常时, 系统自动会让线程释放锁的占用
 *        ReentrantLock 需要用户主动释放锁, 否则就可能导致死锁出现 . [finally { lock.unlock(); } 习惯, 并成对出现, Demo 中演示过]
 *
 * 3  等待是否可中断
 *         synchronized 不可中断, 除非抛出异常或者正常运行完成
 *         ReetrantLock 可中断, 方法 ①  设置超时方法, tryLock(long timeout , TimeUnit unit);
 *                                    ② lockInterruptibly 放入代码中, 调用 interrupt() 方法可中断
 *
 * 4  加锁是否公平
 *      synchronized 非公平锁
 *      ReentrantLock 两者都可以, 初始化 boolean 设置, true 为公平锁, false 为 非公平锁
 *
 * 5  锁绑定多个条件 Condition
 *      Synchronized 没有
 *      ReentrantLock 用来实现分组唤醒需要唤醒的线程, 可以精准唤醒, 而不是像synchronized 要么随机唤醒一个线程, 要么随机唤醒全部线程
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目 : 多线程之间 ,按顺序调用, 实现 A -> B -> C 三个线程启动, 要求入刑:
 * AA 打印 5次, BB 打印10次, CC 打印 15次;
 *
 * 然后接着 AA打印 5 次, BB打印10次, cc 打印 15次
 *  ....
 *  循环10轮
 *
 *
 *  用 synchronized 很麻烦, 而用 retrantlockDemo  比较简单
 *  {
 *      精确唤醒不可以用 notifyAll
 *  }
 */

class ShareResource{
    //一把锁, 三把备用钥匙
    private int number = 1; // A = 1; B = 2; C = 3;
    private Lock lock = new ReentrantLock(); // 可重入锁
    private Condition c1 = lock.newCondition(); // c1 A 条件
    private Condition c2 = lock.newCondition(); //B
    private Condition c3 = lock.newCondition(); // C

    //1 判别
    //2 干活
    //3 通知


    public void print5(){

        lock.lock();
        try {
            //1 判别
            while (number != 1){ // 自旋不等于1, 等待 | while而不是if
                c1.await();
            }
            //2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 2; // 通过共享变量通知2号
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public  void print10(){

        lock.lock();
        try {
            //1 判别
            while (number != 2){ // 自旋 | while
                c2.await();
            }
            //2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 3; // 通过共享变量通知3号, 精准唤醒
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }}
    public  void print15(){

        lock.lock();
        try {
            //1 判别
            while (number != 3){ // 自旋 | while
                c3.await();
            }
            //2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 1; // 通过共享变量通知1号
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }}


}
public class SynAndReetrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            //按需求, 循环10次, 执行对应方法
            for (int i = 1; i <=10 ; i++) {
                shareResource.print5();
            }
        }, "AA").start();

        new Thread(()->{
            //按需求, 循环10次, 执行对应方法
            for (int i = 1; i <=10 ; i++) {
                shareResource.print10();
            }
        }, "BB").start();

        new Thread(()->{
            //按需求, 循环10次, 执行对应方法
            for (int i = 1; i <=10 ; i++) {
                shareResource.print15();
            }
        }, "CC").start();
    }
}
