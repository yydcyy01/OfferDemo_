package com.yydcyy.demo3._5Serializable;

import java.io.*;

/**
 * @author YYDCYY
 * @create 2019-11-19
 *
 * 演示序列化 Demo
 */
public class Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        A a1 = new A(123, "abc");

        //文件名
        String objectFile = "a1";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile));
        objectOutputStream.writeObject(a1);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(objectFile));
        A a2 = (A) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(a2);
    }


    private static class A implements java.io.Serializable {

        private int x;

        private String y;

        A(int x, String y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "x = " + x + "  " + "y = " + y;
        }

    }

}
