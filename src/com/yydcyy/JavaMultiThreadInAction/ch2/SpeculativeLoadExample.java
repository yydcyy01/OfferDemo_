package com.yydcyy.JavaMultiThreadInAction.ch2;

/**
 * 2019年11月29日
 * 猜测执行示例代码
 * 2-11
 * 处理器的指令重排序不会对单线程程序的正确性产生影响, 但他可能导致多线程程序出现非预期的结果
 */
public class SpeculativeLoadExample {
  private boolean ready = false;
  private int[] data = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };

  public void writer() {
    int[] newData = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
    for (int i = 0; i < newData.length; i++) {// 语句①（for循环语句）

      // 此处包含读内存的操作
      newData[i] = newData[i] - i;
    }
    data = newData;
    // 此处包含写内存的操作
    ready = true;// 语句②
  }

  public int reader() {
    int sum = 0;
    int[] snapshot;
    if (ready) {// 语句③（if语句）
      snapshot = data;
      for (int i = 0; i < snapshot.length; i++) {// 语句④（for循环语句）
        sum += snapshot[i];// 语句⑤
      }

    }
    return sum;
  }
}