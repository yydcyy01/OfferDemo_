package com.yydcyy.demo4;

/**
 * @author YYDCYY
 * @create 2019-11-29
 */
public class T8 {
   class Super{
        int flag=1;
        Super(){
            test();
        }
        void test(){
            System.out.println("Super.test() flag="+flag);
        }
    }

    class Sub extends Super{
        Sub(int i){
            flag=i;
            System.out.println("Sub.Sub()flag="+flag);
        }
        void test(){
            System.out.println("Sub.test()flag="+flag);
        }
    }

    public static void main(String[] args) {
       new T8().new Sub(5);

    }


}
