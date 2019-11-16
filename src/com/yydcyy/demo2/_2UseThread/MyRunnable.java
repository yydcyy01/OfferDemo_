package com.yydcyy.demo2._2UseThread;

/**
 * @author YYDCYY
 * @create 2019-11-16
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        //....

        //使用sleep
        try {
            Thread.sleep(3000);
            System.out.println("我睡醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行 仅仅是向线程调度器的建议: 建议同优先级其他线程可运行
        Thread.yield();
    }

    /**
     * Runnable 使用方法 :
     * 实现 run() 方法 :
     * 通过 Thread 调用 start() 方法来启动线程。
     * @param args
     */
    public static void main(String[] args) {
        MyRunnable instance = new MyRunnable();
        Thread thread = new Thread(instance);
        thread.start();
    }
}
