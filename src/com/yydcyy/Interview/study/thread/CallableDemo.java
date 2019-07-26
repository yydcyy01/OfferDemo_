package com.yydcyy.Interview.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread1 implements Runnable{
    @Override
    public void run() {

    }
}

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("************** come in callable");

        try {
            TimeUnit.SECONDS.sleep(2);        } catch (InterruptedException e) {            e.printStackTrace();        }
        return 1024;
    }
}
/**
 * Created by YYDCYY on 2019-07-26.
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread();
        t1.start();

         // 两个线程, 一个 main 主线程, 一个是 AAfutureTask
        // FutureTask(Callable<V> vallable)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());

        new Thread(futureTask,  "AA").start();
        new Thread(futureTask,  "BB").start(); // MyTread中 call 只被计算1次.证明: 你看 call中输出, 只输出一次
         int result2 = futureTask.get();

        System.out.println("This is "+Thread.currentThread().getName() + "*********************");
        int result01 = 100;
/*        while ( !futureTask.isDone() ){ // 类似自旋, main线程没算完等着  这里是 future 关键点; 简单版不需要

        }*/
        int result02 = futureTask.get();  // 要求获得Callable 线程的计算结果, 若没有计算完成就要去强求, 会导致堵塞, 值计算完成
        System.out.println("***************** result : " + (result01 + result02));

    }

}
