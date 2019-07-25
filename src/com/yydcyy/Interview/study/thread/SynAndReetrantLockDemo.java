package com.yydcyy.Interview.study.thread;

/**
 * Created by YYDCYY on 2019-07-26.
 *题目 : synchronized 和 Lock 有什么区别 ? 用心的lock有什么好处? 请举例说说
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
 *      ReentrantLock 用来实现分组唤醒需要唤醒的线程, 可以精准唤醒, 而不是像synchronized 要么随机唤醒一个县城, 要么随机唤醒全部线程
 */
public class SynAndReetrantLockDemo {
}
