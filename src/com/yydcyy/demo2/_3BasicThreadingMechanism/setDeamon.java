package com.yydcyy.demo2._3BasicThreadingMechanism;

import com.yydcyy.demo2._2UseThread.MyRunnable;

/**
 * @author YYDCYY
 * @create 2019-11-16
 *
 *
 */
public class setDeamon {

    /**
     * main() 属于非守护线程。
     *
     * 在线程启动之前使用 setDaemon() 方法可以将一个线程设置为守护线程。
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.setDaemon(true);
    }
}
