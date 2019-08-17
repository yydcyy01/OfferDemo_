package com.yydcyy.Interview.study.thread;

/**
 * Created by YYDCYY on 2019-07-27.
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(" Hellp , GC ");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
