package com.yydcyy.Interview.study.thread;

/**
 * Created by YYDCYY on 2019-07-23.
 */
public class SingletonDemo {
    // 单例模式中, 经典的懒汉式
    //private static SingletonDemo instance = null;
    private static volatile SingletonDemo instance = null; // instance 加 volatile , 禁止多线程中指令重排,避免重排导致未初始化先获取, 获取到 null 情况
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法 SingletonDemo(), 看看我出现几次");
    }

    public static SingletonDemo getInstance(){
   // public static synchronized SingletonDemo getInstance(){
        //DCL (Double Check Lock 双端监测机制) 即 两个 if 判断, 起了个名字罢了
        if (instance == null){
            //代码块锁
           synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
           }
        }
        return instance;
    }
    public static void main(String[] args) {
        /**说明是 单例模式
         * 单线程 (main 线程操作条件
         */
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        /**
         * 多线程下,  多运行几次会发现 "看我出现几次" 次数不一定是1
         * 14 行使用 synchronized 可以解决, 但是把所有代码 "锁住" , 效率低, 不是我想要的.  引出 DCL
         * 解决问题 : 把不确定事变成确定事情
         */
        for (int i = 1; i <= 10; i ++){
            new Thread( ()->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
