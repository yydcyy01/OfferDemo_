package com.yydcyy.demo2._5theMutexSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {

    //1. 同步一个代码块

    //对于以下代码，使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
// 2. 同步一个方法
    // 它和同步代码块一样，作用于同一个对象。
    public synchronized void func () {
        // ...
    }

    //3. 同步一个类
    // 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
    public void func2() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
    private static class calss_fun1{


//作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步
        public static void main(String[] args) {
            SynchronizedExample e1 = new SynchronizedExample();
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> e1.func1());
            executorService.execute(() -> e1.func1());
         }
    }

    private static class class_fun2{
        public static void main(String[] args) {
            SynchronizedExample e1 = new SynchronizedExample();
            SynchronizedExample e2 = new SynchronizedExample();
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> e1.func2());
            executorService.execute(() -> e2.func2());
        }
    }

    private static class class_fun3{

    }
}