package com.yydcyy.Interview.study.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by YYDCYY on 2019-07-23.
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
       Map<String, String> map = new ConcurrentHashMap<>();
       new HashMap<>();
/**
 *
 * ArrayList Set Map 线程都不安全 , 相同原理 解决
 */
        // ArrayList<>() 线程不安全, 举例.  跑几次 会发现 Exception in thread "2" java.util.ConcurrentModificationException 错误
        for (int i = 1; i <= 100; i ++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        List<String> list = new ArrayList<>();
        //① List<String> list = new Vector<>();
        //② List<String> list = Collections.synchronizedList(new ArrayList<>());
        //③ List<String> list = new CopyOnWriteArrayList<>(); // 客官 来都来了,不点进去看看?
        /**

         */
        // ArrayList<>() 线程不安全, 举例.  跑几次 会发现 Exception in thread "2" java.util.ConcurrentModificationException 错误
        for (int i = 1; i <= 100; i ++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 找工作 / 面试 都是 : 不单单靠经验, 还有学习能力, 总结 内功;
     * 1  故障现象
     Exception in thread "2" java.util.ConcurrentModificationException
     * 2 导致原因
     线程不安全 , 并发争抢修改导致,
     *
     * 3  解决方案
     *    ①      * jdk 1.0 版本就出现的 Vector 加锁可以解决, 但是 看源码, ArrayList 是1.2版本的, 若是 加锁解决, 要你干嘛?
     *    ②   Collection, Collections 可以把不安全变为安全   //map set 都是不安全的, 举一反三, 都可以这么解决
     *    ③  CopyOnWriteArrayList<>(); 可以解决,  说明你可以
     *       笔试意义, 过滤学渣.  过滤学不进去的人, 屁股坐得住, 进大公司好好培养, 用自己的框架
     * 4 优化方案 (同样的错误不犯第 2 次)
     *
     */
}
