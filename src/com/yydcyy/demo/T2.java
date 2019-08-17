package com.yydcyy.demo;

import java.util.Properties;
import java.util.concurrent.*;

/**
 * Created by YYDCYY on 2019-08-18.
 * 单例模式实现  懒汉式 / 恶汉式
 * 1) 构造器私有化
 * 2) 自行创建, 并用静态变量保存
 * 3) 向外提供这个实例
 * 4) 强调这是一个单例, 我们可以用 final 修改
 *
 * 一下两个类,  实现Demo
 *
 * 恶汉式 : Java 类加载器设计模式导致  没有现成安全问题
 * 缺点 : 万一不用 ,你就创建了, 讨厌~
 *
 * => 引出懒汉式, 延迟创建对象
 *
 * 1) 构造器私有化
 * 2) 用一个静态变量保存这个唯一实例
 * 3) 提供一个静态方法, 获取这个实例对象
 *
 * class 4 可能有线程安全问题
 */
public class T2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("====================恶汉式 : Singleton1 =============================");

        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
        /** Test Singleton1 :
         *         com.yydcyy.demo.Singleton1@1540e19d
         */

        System.out.println("====================恶汉式 : Singleton2 =============================");

        Singleton2 s2 = Singleton2.INSTANCE;
        System.out.println(s2);
        /**Test Singleton2 :
         * 打出结果可能不太一样 ,  也是单例
         *    INSTANCE
         */

        System.out.println("====================恶汉式 :Singleton3 =============================");

        Singleton3 s3 = Singleton3.INSTANCE;
        System.out.println(s3);
        System.out.println(s3.getInfo());
        /**Test Singleton2 :
         *   也是单例
             com.yydcyy.demo.Singleton3@14ae5a5
             demo
         */

        System.out.println("====================懒汉式 : Singleton4 =============================");
        Singleton4 s4 = Singleton4.getINSTANCE();
        Singleton4 s5 = Singleton4.getINSTANCE();

        System.out.println("s4 == s5 ? " + (s4 == s5));
        System.out.println(s4);
        System.out.println(s5);
        /**
         * s4 == s5true
         com.yydcyy.demo.Singleton4@7f31245a
         com.yydcyy.demo.Singleton4@7f31245a
         */
        System.out.println("====================懒汉式 : Singleton4 多线程Demo =============================");

        Callable<Singleton4> call = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getINSTANCE();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(call);
        Future<Singleton4> f2 = es.submit(call);

        Singleton4 sf1 = f1.get();
        Singleton4 sf2 = f2.get();

        es.shutdown();
    }
}

class Singleton1{
    public static final Singleton1 INSTANCE = new Singleton1(); // 静态变量一般用大写
    private Singleton1(){

    }
}

/**
 * 1.5 之后 : 枚举类型, 表示该类型对象有限个
 * 可限定一个, 就成了单例
 */
enum Singleton2{
    INSTANCE
}

/**
 * 这种单例方式看着麻烦,但是有些场景很方便 : 无法new() 实例, 而是文件读取, 读一堆初始化数据时候  一样的效果
 */
class Singleton3{
    public static final Singleton3 INSTANCE;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;

    static {

        try {Properties pro = new Properties();
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new Singleton3(pro.getProperty("info"));
        }   catch (Exception e)    {          throw new RuntimeException() ;     }

    }

    private Singleton3(String info){
        this.info = info;
    }
}


//===================懒汉式=======================================
class Singleton4{
    private static Singleton4 INSTANCE;
    private Singleton4() throws ExecutionException, InterruptedException {

    }
    public static Singleton4 getINSTANCE() throws ExecutionException, InterruptedException {
        if (INSTANCE == null) {
            Thread.sleep(100);  //休眠, 使暴露出懒汉式 线程不安全特性
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }



}