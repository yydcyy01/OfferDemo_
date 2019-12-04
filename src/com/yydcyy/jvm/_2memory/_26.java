package com.yydcyy.jvm._2memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YYDCYY
 * @create 2019-12-04
 */
public class _26 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true){
            list.add(String.valueOf(i ++).intern());
            System.out.println(i);
        }
    }
}
