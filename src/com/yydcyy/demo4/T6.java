package com.yydcyy.demo4;

/**
 * @author YYDCYY
 * @create 2019-11-28
 */
public class T6 {
        public static void main(String[] args) {
            System.out.println("return value of getValue(): " + getValue());
        }
        public static int getValue() {
            int i = 1;
            try {
                i = 4;
            } finally{
                i++;
                return i;
            }
        }
     static class Parent {
        private void m1(){}
        void m2(){}
        protected void m3(){}
        public static void m4(){}
    }
}
