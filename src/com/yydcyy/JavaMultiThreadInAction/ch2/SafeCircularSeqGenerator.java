package com.yydcyy.JavaMultiThreadInAction.ch2;

/**
 * @author YYDCYY
 * @create 2019-11-29
 */
public class SafeCircularSeqGenerator implements CircularSeqGenerator {
  private short sequence = -1;

  @Override
  public synchronized short nextSequence() {
    if (sequence >= 999) {
      sequence = 0;
    } else {
      sequence++;
    }
    return sequence;
  }
}