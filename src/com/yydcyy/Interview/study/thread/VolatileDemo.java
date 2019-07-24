package com.yydcyy.Interview.study.thread;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by YYDCYY on 2019-07-22.
 */

class MyData {
   //int number = 0;
    volatile int number = 0;
    public void addT060(){
        this.number = 60;
    }

    //注意, 此时number前面是加了volatile 关键字修饰 , volatile 不保证原子性喔
    public void addplusplus(){
        number ++;
    }

    // 相当于 带原子性的number
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
/**
 * 1. 验证 volatile 的可见性
 *      1.1 假如 int  number = 0; number 变量之前根本没有添加 volatile 关键字修饰, 没有可见性
 *
 * 2.
 *    2.1原子性意思 ?
 *     不可分割, 完整性.  即某个线程正在做某个具体业务, 中间不可以呗加塞或者被分割, 需要整体完整
 *     要么同时成功, 要么同时失败
 *
 *    2.2 volatile 不保证原子性 Demo
 *
 *    2.3 why  [图解]
 *
 *    2.4 如何解决原子性?
 *      加 synchronized
 *      使用我们 J.U.C. 下的 AtomicInteger . 原子,最新不可分割.
 *     凭什么AtomicInteger 可以解决? CAS ,hzxuDemo
 */
public class VolatileDemo {
    public static void main(String[] args) {  // 一切方法起始处
        MyData myData = new MyData();

        //20个线程, 每个线程点1000次
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addplusplus();
                    myData.addAtomic();
                }

            }, String.valueOf(i)).start();
        }

        //等待上面 20 线程全部计算完成后,  再加 main线程取的最终结果值看是多少?
        while (Thread.activeCount() > 2){
            Thread.yield();
            //main , GC线程
        }

        //若 volatile 修饰的 number 不保证原子性, 故测试结果值[不保证]是 20000
        System.out.println(Thread.currentThread().getName() + "\t int  Type , finally number value : " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type , finally number value : " + myData.atomicInteger);

    }

    //volatile 可以保证可见性, 及时通知其他线程, 主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData(); // 资源类

        new Thread(() -> {
           System.out.println(Thread.currentThread().getName() + "\n come in ");
           // 暂停一会线程
           try {
               TimeUnit.SECONDS.sleep(3); // 休眠 3 秒后, 看结果
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           myData.addT060();
           System.out.println(Thread.currentThread().getName() + "\t updated number value :  " + myData.number);
       }, "AAAA").start();

        while (myData.number == 0){
            // main 线程一直在这里等待循环, 直到 number 值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + " \t mission is over");
    }
}
/**
 * 结果说明:
 1) 第 12 行 int number = 0;
结果:   AAAA
 come in
 AAAA	 updated number value :  60
且 main 线程一直运行, 死循环.
 说明 : number 变量没有 "可见性", main线程继续运行

2) 第 13 行 volatile int number = 0
 AAAA
 come in
 AAAA	 updated number value :  60
 main 	 mission is over
且 main 线程终止.
 说明, volatile 修饰的 number 变量 具有"可见性" , main线程 终止



 ______________________________
 //debug模式下 出现过 20000.  19294  20000  19974
 */