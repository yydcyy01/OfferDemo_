package com.yydcyy.demo4;

import java.util.TreeSet;

/**
 * @author YYDCYY
 * @create 2019-11-27
 * 猜猜打印结果
 */
public class T1 {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        TreeSet<Integer> subSet = new TreeSet<Integer>();
        for(int i=606;i<613;i++){
            if(i%2==0){
                set.add(i);
            }
        }
        subSet = (TreeSet)set.subSet(608,true,611,true);
        set.add(629);
        System.out.println(set+" "+subSet);
    }
}
