package com.yydcyy.demo3._7NIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author YYDCYY
 * @create 2019-11-19
 *
 * 7.52g 电影,
 *  缓冲区 1024 字节 , 39s NIO 复制完
 *        1024 * 10 字节, 15s 复制完
 *        1024 * 1000 字节  , 10s 复制完
 *        1024 * 1000 * 6 , 11s 复制完.  可见并不是缓冲区越大也好.  [ 目测每秒 700m 复制速度?  ]
 */
public class NIODemo {
    @Test
   // public static void fastCopy(String src, String dist) throws IOException {
    public  void fastCopy() throws IOException {
        long start = System.nanoTime();
        /* 获得源文件的输入字节流 */
        //FileInputStream fin = new FileInputStream(src);
        //FileInputStream fin = new FileInputStream("/Users/yuyang/Movies/1/牯岭街少年杀人事件.mkv");
        FileInputStream fin = new FileInputStream("/Users/yuyang/Movies/1/牯岭街少年杀人事件.mkv");

        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();

        /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream("/Users/yuyang/Movies/1991.牯岭街少年杀人事件/");

        /* 获取输出字节流的文件通道 */
        FileChannel fcout = fout.getChannel();

        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*1000*6);

        while (true) {

            /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);

            /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }

            /* 切换读写 */
            buffer.flip();

            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);

            /* 清空缓冲区 */
            buffer.clear();
        }

        long end = System.nanoTime();
        System.out.println("Took : "  + ((end - start) / 1000000000) + "s");
    }

}
