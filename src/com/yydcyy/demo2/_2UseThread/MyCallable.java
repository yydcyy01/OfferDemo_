package com.yydcyy.demo2._2UseThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author YYDCYY
 * @create 2019-11-16
 *
 * 与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
 */
public class MyCallable implements Callable<Integer> {
    public Integer call(){

        return 235;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println(ft.get());
    }

}

