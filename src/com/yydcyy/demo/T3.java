package com.yydcyy.demo;

/**
 * Created by YYDCYY on 2019-08-18.
 */
public class T3 {
    /**
     * 类初始化过程 :
     *      ① 若创建实例,需先初始化该类 [main方法所在类需先加载和初始化
     *      ② 若初始化子类,需先初始化父类
     *      ③ 类初始化即执行<clinit>方法 (编译器自动加)
     *              ※<clinit>() 由静态变量显式赋值 和 静态代码块组成
     *              ※此方法初始化过程从上到下
     *              ※此方法只执行一次 , 即 静态变量,静态代码 只被初始化一次
     *
     *   实例初始化过程: 就是执行<init>方法
     *          ※ 此方法(<init>)可能重载有几个, 几个构造器construct , 就有几个<init>方法
     *          ※ 此方法由 非静态实例变量显式赋值代码 + 非静态的代码块 + 对应构造器的代码组成
     *          ※ 非静态实例变量的显式赋值 和静态代码块 执行顺序从上到下书序执行, 对应构造器最后执行
     *          ※ 创建实例, 调用对应构造器, 执行的是<init> 方法
     *          <init> 方法首行是 super() 或 super(参数列表), 及对应父类<init>方法
     *              */
    public static void main(String[] args) {
        Son s1 = new Son(); //测试初始化  由结果看初始化顺序
        System.out.println();
        Son s2 = new Son();  // 由结果看测试哪些只初始化一次
        System.out.println();
        Son s3 = new Son();
        /**
         * (5)(1)(10)(6)(4)(3)(2)(9)(8)(7)
         (4)(3)(2)(9)(8)(7)
         */
    }
}
class Father{
    private int i = test();
    private static  int j = method();

    static {
        System.out.print("(1)");
    }

    Father(){
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    private int test() {
        System.out.print("(4)");
        return 1;
    }
    private static int method() {
        System.out.print("(5)");
        return 1;
    }
}

class Son extends  Father{
    private int i = test();
    private static  int j = method();

    static {
        System.out.print("(6)");
    }

    Son(){
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    private int test() {
        System.out.print("(9)");
        return 1;
    }
    private static int method() {
        System.out.print("(10)");
        return 1;
    }
}