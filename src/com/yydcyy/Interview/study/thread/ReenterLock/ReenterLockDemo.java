package com.yydcyy.Interview.study.thread.ReenterLock;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
     //验证 synchronized 锁是可重入锁
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()" );
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t *************invoked sendEmail()");
    }

    //----------------------------------------------------------
    //验证 ReetrantLock() 锁是可重入锁
    Lock lock = new ReentrantLock();
    @Override
    public void run(){
        get();
    }

    public void get(){
        lock.lock();
       try{
           System.out.println(Thread.currentThread().getName()+"\t *************invoked get()");
           set();
       }finally {
           lock.unlock();
       }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t *************invoked set()");
        }finally {
            lock.unlock();
        }
    }
}
/**
 * Created by YYDCYY on 2019-07-23.
 * 可重入锁 (递归锁) : 指的是同一线程外层函数获取锁后, 内层函数仍然获取该锁的代码.
 *  同一线程在外层方法获取锁的时候, 在进入内层方法会自动获取锁
 *  即 线程可以进入任意一个他已获取锁的同步着的代码块
 *
 t1	 invoked sendSMS()
 t1	 *************invoked sendEmail()
 t2	 invoked sendSMS()
 t2	 *************invoked sendEmail()
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
//验证 synchronized 锁是可重入锁
        new Thread(()->{
            phone.sendSMS();

        },"t1").start();

           new Thread(()->{
            phone.sendSMS();
        },"t2").start();
/**
 *
 相当于
 */
        try {
            //暂停一会线程
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //验证 ReetrantLock() 锁是可重入锁
        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);

        t3.start();
        t4.start();

        int num ;
        ArrayList<String> list = new ArrayList<>();
    }

}


