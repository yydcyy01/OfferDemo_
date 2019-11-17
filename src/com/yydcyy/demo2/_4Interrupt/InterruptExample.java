package com.yydcyy.demo2._4Interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InterruptExample {

    //内部类
    private static class MyThread1 extends Thread {

        public void run(){
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            Thread thread1 = new MyThread1();
            thread1.start();
            //通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
            thread1.interrupt();
            System.out.println("main run");
        }
    }


    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                // ..
            }
            System.out.println("Thread end");
        }
//调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
        public static void main(String[] args) throws InterruptedException {
            Thread thread2 = new MyThread2();
            thread2.start();
            thread2.interrupt();
            System.out.println("main run");
        }
    }

    //InterruptExample - main()
    public static void main(String[] args) {

        //调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
        ExecutorService executorService = Executors.newCachedThreadPool();

        //以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        System.out.println("Main run");

//如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
        Future<?> future = executorService.submit(() -> {
            // ..
        });
        future.cancel(true);
    }
}