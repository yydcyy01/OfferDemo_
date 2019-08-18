package com.yydcyy.demo;

/**
 * Created by YYDCYY on 2019-08-18.
 * 成员变量与局部变量
 */
public class T6 {
    static int s;//静态成员变量，类变量
    int i;//成员变量，实例变量
    int j;//成员变量，实例变量
    {
        int i = 1;//非静态代码块中的局部变量 i 此行 i作用域 :[12,15]行
        i++;
        j++;
        s++;
    }
    public void test(int j){//形参，局部变量,j
        //this.j++;
        j++;  //成员变量j
        i++; // 第9行的i
        s++;
    }
    public static void main(String[] args) {//形参，局部变量，args
        T6 obj1 = new T6();//局部变量，obj1
        T6 obj2 = new T6();//局部变量，obj1
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}
