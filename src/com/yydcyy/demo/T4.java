package com.yydcyy.demo;

import java.util.Arrays;

/**
 * Created by YYDCYY on 2019-08-18.
 * 方法参数传递机制 :
 * ① 形参是基本数据类型 :   传递数据值
 * ② 实参是引用数据类型 : 传递地址, 特殊类型 : String ,包装类(Integer)等对象不可变性
 */
public class T4 {	public static void main(String[] args) {
    int i = 1;
    String str = "hello";
    Integer num = 200;
    int[] arr = {1,2,3,4,5};
    MyData my = new MyData();

    change(i,str,num,arr,my);

    System.out.println("i = " + i);
    System.out.println("str = " + str);
    System.out.println("num = " + num);
    System.out.println("arr = " + Arrays.toString(arr));
    System.out.println("my.a = " + my.a);
}
    public static void change(int j, String s, Integer n, int[] a,MyData m){
        j += 1;
        s += "world";
        n = 400;
        a[0] += 1;
        m.a += 1;
    }
}
class MyData{
    int a = 10;
}
