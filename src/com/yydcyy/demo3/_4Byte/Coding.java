package com.yydcyy.demo3._4Byte;

import java.io.UnsupportedEncodingException;

/**
 * @author YYDCYY
 * @create 2019-11-19
 */
public class Coding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str1 = "中文";
        byte[] bytes = str1.getBytes("UTF-8");
        String str2 = new String(bytes, "UTF-8");
        System.out.println(str2);

        /**
         * 在调用无参数 getBytes() 方法时，默认的编码方式不是 UTF-16be。
         * getBytes() 的默认编码方式与平台有关，一般为 UTF-8。
         */
        byte[] bytes2 = str1.getBytes();
    }
}
