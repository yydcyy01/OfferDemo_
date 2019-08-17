package com.yydcyy.Interview.study.thread;

/**
 * Created by YYDCYY on 2019-07-27.
 * GC Root 的对象(四种):
 1  虚拟机栈 (栈帧中的局部变量区, 也就局部变量表) 中的引用对象
 2  方法去中的类静态属性引用的对象
 3  方法去中常量引用的对象
 4  本地方法栈中 JNI (Native方法) 引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100 * 1024 * 1024];
//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次完成 GC");
    }
    public static void main(String[] args) {
        m1();
    }
}
