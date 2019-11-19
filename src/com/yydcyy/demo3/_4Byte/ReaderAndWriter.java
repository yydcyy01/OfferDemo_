package com.yydcyy.demo3._4Byte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author YYDCYY
 * @create 2019-11-19
 */
public class ReaderAndWriter {
    public static void readFileContent(String filePath) throws IOException {

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        bufferedReader.close();
    }
}
