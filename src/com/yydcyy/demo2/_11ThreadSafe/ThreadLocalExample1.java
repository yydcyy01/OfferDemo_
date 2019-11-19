package com.yydcyy.demo2._11ThreadSafe;

public class ThreadLocalExample1 {
    public static void main(String[] args) {
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal1.set(1);
            threadLocal2.set(1);

            System.out.println("thread1 :: " + threadLocal1.get() + "  " +  threadLocal2.get());
        });
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(2);
            threadLocal2.set(2);

            System.out.println("thread2 :: " + threadLocal1.get() + "  " +  threadLocal2.get());

        });
        thread1.start();
        thread2.start();
        System.out.println("main :: " + threadLocal1.get() + "  " +  threadLocal2.get());

    }
}