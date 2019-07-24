package com.yydcyy.Interview.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by YYDCYY on 2019-07-24.
 */
public class CyclicBarrierDemo {
    //CyclicBarrier(int parties , Runnable barrierAction)

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{      System.out.println("***********召唤神龙");    });

        for (int i  = 1; i <= 7; i ++){
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 收集到第: " + tempInt + "龙珠");
                try {
                    cyclicBarrier.await( );
                } catch (InterruptedException e) {       e.printStackTrace();      } catch (BrokenBarrierException e) {     e.printStackTrace();                }
            },String.valueOf(i)).start();
        }
    }

}
