package com.yydcyy.demo;

/**
 * Created by YYDCYY on 2019-08-18.
 * 单例模式实现  懒汉式 / 恶汉式
 * 1) 构造器私有化
 * 2) 自行创建, 并用静态变量保存
 * 3) 向外提供这个实例
 * 4) 强调这是一个单例, 我们可以用 final 修改
 *
 * 一下两个类,  实现Demo
 */
public class T2 {
    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
        /** Test Singleton1 :
         *         com.yydcyy.demo.Singleton1@1540e19d
         */


        Singleton2 s2 = Singleton2.INSTANCE;
        System.out.println(s2);
        /**Test Singleton2 :
         * 打出结果可能不太一样 ,  也是单例
         *    INSTANCE
         */
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