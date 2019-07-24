package com.yydcyy.Interview.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache_Before { // 资源类
    private volatile Map<String ,Object> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    //读写锁二合一
    //private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    // 模拟写资源操作
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        map.put(key, value);

        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }
    // 模拟读资源操作
    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "\t 正在读取: " + key);

        try {
            // 暂停一会线程
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object result = map.get(key);

        System.out.println(Thread.currentThread().getName() + "\t 读取完成");
    }
}

/**
 * 采用 ReetrantReadWriteLock, 不许加塞.保证数据一致性, 也保证数据并发性. 读写一致 完美.
 * main函数, Lock = new MyCach_After , try{ }catch{}finally{}  加锁, 关闭锁
 */
class MyCache_After { // 资源类
    private volatile Map<String ,Object> map = new HashMap<>();  // 看源码, 涉及"缓存"东西, 用 volatile写, 保证可见性,及时通知
    //读写锁二合一
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 模拟写资源操作
    public void put(String key, Object value){
        rwLock.writeLock().lock(); // 标明写锁 记得关闭
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);

            //暂停一会线程
            try{    TimeUnit.MILLISECONDS.sleep(300);     }catch (InterruptedException e) {         e.printStackTrace();    }

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock(); // finally 里写锁关闭
        }
    }
    // 模拟读资源操作
    public void get(String key){
       rwLock.readLock().lock(); // 标明读锁 记得关闭
        try {
            // 暂停一会线程
            System.out.println(Thread.currentThread().getName() + "\t 正在读取: ");
            try{           TimeUnit.MILLISECONDS.sleep(300);        } catch (Exception e) {             e.printStackTrace();  }

            Object result = map.get(key);

            System.out.println(Thread.currentThread().getName() + "\t 读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock(); // finally 读锁关闭
        }
    }
}
/**
 * Created by YYDCYY on 2019-07-24.
 * 理论 :
 * 多线程同时读一个资源类没任何问题, 为了满足并发需求, 应该允许读取共享资源
 * 但是, 若有某线程写共享资源, 就不该有其他线程进行读取(脏读
 *  总结 :
 *      读-读 共存
 *      读-写 / 写-读不共存
 *      写-写 不共存
 *
 * 写操作 = 原子  +  独占
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        /**
         * Before
         * 说明 : 模拟 5 个写入线程, 5个 读取线程 ,运行结果发现写入和读取被"加塞" 了, 不符合应用场景, 现在进行处理. 结果在 After
         */
       // MyCache_Before myCache = new MyCache_Before();
        MyCache_After myCache = new MyCache_After();

        for (int i = 1; i <= 5; i ++){
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt + "", tempInt + ""); // 转成 String
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i ++){
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");// 转成 String
            }, String.valueOf(i)).start();
        }

    }

}
