package com.yydcyy.demo2._7AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author YYDCYY
 * @create 2019-11-17
 *
 * 以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10。
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < totalRequestCount; i ++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            });
        }
        executorService.shutdown();
    }
}
