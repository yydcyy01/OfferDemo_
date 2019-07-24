package com.yydcyy.Interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by YYDCYY on 2019-07-23.
 * ABA 问题解决 : AtomicStampedReference
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    public static void main(String[] args) {
        System.out.println("=============一下是 ABA 问题的产生=====");
        new Thread(()->{
            atomicReference.compareAndSet(100, 101); // 100 -> 101 -> 100 ABA
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e)  {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============ABA 问题解决=========");
//        new Thread(()->{
//            int stamp = atomicStampDre
//            )
//        }).start();
    }
}
