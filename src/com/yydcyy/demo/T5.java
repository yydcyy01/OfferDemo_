package com.yydcyy.demo;

import org.junit.Test;

/**
 * Created by YYDCYY on 2019-08-18.
 */
public class T5 {
    public static void main(String[] args) {
       new Loop().test();
        System.out.println();
       new recursion().test();
    }

}

/**
 * 递归实现
 */
class recursion{
    @Test
    public static void test(){
        long start = System.currentTimeMillis();
        System.out.println(f(30));//165580141
        long end = System.currentTimeMillis();
        System.out.println((end-start) );//586ms
    }

    //实现f(n)：求n步台阶，一共有几种走法
    public static  int f(int n){
        if(n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n==1 || n==2){
            return n;
        }
        return f(n-2) + f(n-1);
    }
}

/**
 * 迭代
 */
class Loop{
    @Test
    public static  void test(){
        long start = System.currentTimeMillis();
        System.out.println(loop(30));//165580141
        long end = System.currentTimeMillis();
        System.out.println(end-start);//<1ms
    }

    public static int loop(int n){
        if(n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n==1 || n==2){
            return n;
        }

        int one = 2;//初始化为走到第二级台阶的走法
        int two = 1;//初始化为走到第一级台阶的走法
        int sum = 0;

        for(int i=3; i<=n; i++){
            //最后跨2步 + 最后跨1步的走法
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}
