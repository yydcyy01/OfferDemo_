package com.yydcyy.Interview.study.thread;

import java.util.concurrent.TimeUnit;

class HoldLockTread implements  Runnable{
    private String lockB;
    private String lockA;

    public HoldLockTread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t  自己持有的 : " + lockA + "\t 尝试获得的 : " + lockB );
            //暂停下, 让锁的效果更明显
            try {
                TimeUnit.SECONDS.sleep(2);           } catch (InterruptedException e) {                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t  自己持有的 : " + lockB + "\t 尝试获得的 : " + lockA );
            }
        }
    }
}
/**
 * Created by YYDCYY on 2019-07-27.
 *写个死锁,  然后故障排查 Demo
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockTread(lockA,lockB), "ThreadAAA").start();
        new Thread(new HoldLockTread(lockB,lockA), "ThreadBBB").start();
    }
}
