package com.yydcyy.JavaMultiThreadInAction.ch2;

/**
 * @author YYDCYY
 * @create 2019-11-29
 */
public class NoRaceCondition {

  public int nextSequence(int sequence) {

    // 以下语句使用的是局部变量而非状态变量，并不会产生竞态
    if (sequence >= 999) {
      sequence = 0;
    } else {
      sequence++;
    }
    return sequence;
  }

}