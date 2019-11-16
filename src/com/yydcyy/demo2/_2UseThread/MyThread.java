package com.yydcyy.demo2._2UseThread;

/**
 * @author YYDCYY
 * @create 2019-11-16
 *
 * 同样也是需要实现 run() 方法，因为 Thread 类也实现了 Runable 接口。
 *
 * 当调用 start() 方法启动一个线程时，虚拟机会将该线程放入就绪队列中等待被调度，当一个线程被调度时会执行该线程的 run() 方法。
 */
public class MyThread extends  Thread{
    public void run(){
        // pass
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();
    }
}
