package com.yydcyy.JavaMultiThreadInAction.ch2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YYDCYY
 * @create 2019-11-29
 *  工具类, Request ID 生成器
 */
public final class RequestIDGenerator implements CircularSeqGenerator {
  /**
   * 保存该类的唯一实例
   */
  private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
  private final static short SEQ_UPPER_LIMIT = 999;
  private short sequence = -1;

  // 私有构造器
  private RequestIDGenerator() {
    // 什么也不做
  }

  /**
   * 生成循环递增序列号
   *
   * @return
   */
/*
// 线程不安全写法
  @Override
  public short nextSequence() {
    if (sequence >= SEQ_UPPER_LIMIT) {
      sequence = 0;
    } else {
      sequence++;
    }
    return sequence;
  }
*/
//线程安全写法
  @Override
  public synchronized short nextSequence() {
    if (sequence >= 999) {
      sequence = 0;

    } else {
      sequence++;
    }
    return sequence;
  }
  /**
   * 生成一个新的Request ID
   *
   * @return
   */
  public String nextID() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    String timestamp = sdf.format(new Date());
    DecimalFormat df = new DecimalFormat("000");

    // 生成请求序列号
    short sequenceNo = nextSequence();

    return "0049" + timestamp + df.format(sequenceNo);
  }

  /**
   * 返回该类的唯一实例
   *
   * @return
   */
  public static RequestIDGenerator getInstance() {
    return INSTANCE;
  }
}