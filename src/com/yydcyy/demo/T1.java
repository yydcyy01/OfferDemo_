package com.yydcyy.demo;

/**
 * Created by YYDCYY on 2019-08-18.
 */
public class T1 {
    /**
     * 局部变量表   操作数栈 理解
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        i = i ++;  //只有这一个特殊, i = 2 又被赋值为1 其他都好理解
        int j = i ++;
        int k = i + ++i * i++;
        System.out.println("i =" + i);
        System.out.println("j = " + j);
        System.out.println("k = " + k);
        /**
         * i = 4; k = 11; j = 1
         */
    }
}
