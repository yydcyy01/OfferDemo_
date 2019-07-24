package com.yydcyy.Interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by YYDCYY on 2019-07-23.
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // main do thing...
        System.out.println(atomicInteger.compareAndSet(5, 2019) +"\t current data : " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2020) +"\t current data : " + atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
