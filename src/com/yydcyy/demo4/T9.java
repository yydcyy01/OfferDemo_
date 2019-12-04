package com.yydcyy.demo4;

/**
 * @author YYDCYY
 * @create 2019-11-29
 */

    class A {

        static { System.out.println("class A static"); }
        public A() {
                System.out.println("class A");
            }
        { System.out.println("I'm A class"); }

    }


    public class T9 extends A {
        static { System.out.println("class B static"); }
        public T9() {
            System.out.println("class B");
        }
        { System.out.println("I'm B class"); }

        public static void main(String[] args) {
            new T9();
        }
    }

