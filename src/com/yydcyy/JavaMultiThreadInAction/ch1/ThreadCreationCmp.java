package com.yydcyy.JavaMultiThreadInAction.ch1;

import com.yydcyy.JavaMultiThreadInAction.util.Tools;

/**
 * @author YYDCYY
 * @create 2019-11-28
 */
public class ThreadCreationCmp {
    public static void main(String[] args) {
        Thread t;

        CountingTask ct = new CountingTask();

        // 获取处理器个数
        final int numberofProceesors = Runtime.getRuntime().availableProcessors();
        System.out.println("numberofProcessors : " + numberofProceesors);

        for (int i = 0; i < 2 * numberofProceesors; i++) {
            // 直接创建线程
            t = new Thread(ct);
            t.start();
        }

        for (int i = 0; i < 2 * numberofProceesors; i++) {
            // 以子类方式创建线程
            t = new CountingThread();
            t.start();
        }
    }

    static class Counter{
        private int count = 0;

        public void increment(){
            count ++;
        }

        public int value(){
            return count;
        }
    }

    //实现的静态内部类
    static class CountingTask implements Runnable{
        private Counter counter = new Counter();
        @Override
        public void run() {
            for (int i = 0; i < 100; i ++){
                doSomething();
                counter.increment();
            }
            System.out.println("CountingTask : " + counter.value());
        }

        private void doSomething() {
            // 引入工具类 util.Tools / Debug
            // 使之休眠随机时间
            Tools.randomPause(80);
        }
    }

    //继承的静态内部类
    static class CountingThread extends Thread{
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                doSomething();
                counter.increment();
            }
            System.out.println("CountingThread : " + counter.value());
        }

        private void doSomething() {
            // 使之休眠随机时间
            Tools.randomPause(80);
        }
    }
}

