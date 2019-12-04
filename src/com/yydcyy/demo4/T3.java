package com.yydcyy.demo4;

/**
 * @author YYDCYY
 * @create 2019-11-27
 */
public class T3 {
    static boolean foo(char c)
    {
        System.out.print(c);
        return true;
    }
    public static void main(String[] args) {
        int i =0;
        for(foo('A');foo('B')&&(i<2);foo('C'))
        {
            i++;
            foo('D');
        }

    }

}
