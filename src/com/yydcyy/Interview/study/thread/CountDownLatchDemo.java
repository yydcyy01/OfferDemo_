package com.yydcyy.Interview.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by YYDCYY on 2019-07-24.
 */
public class CountDownLatchDemo {
    /**
     * 模拟  学生离开教室, 教室关门 过程.  业务场景 : 必须关门, 但是要最后一个学生离开完毕才可.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6); //  初始化 6个

        for  (int i = 1; i <= 6; i ++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t  下课啦, 离开教室出去浪");
                countDownLatch.countDown();
                //这样写, 修改时直接在Enum中修改, 降低耦合度
            },CountryEnum.forEach_CountryEnum(i).getResMessage()).start();


            //以前是 if()if()if()判断
            // },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t _____________________ 门卫已关门");

    }
}
