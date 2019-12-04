package com.yydcyy.JavaMultiThreadInAction.ch2;

import com.yydcyy.JavaMultiThreadInAction.util.stf.*;

/**
 * @author YYDCYY
 * @create 2019-11-29
 *
 * 再现JIT指令重排序的Demo
 */
@ConcurrencyTest(iterations = 200000)
public class JITReorderingDemo {
  private int externalData = 1;
  private Helper helper;

  @Actor
  public void createHelper() {
    helper = new Helper(externalData);
  }

  @Observer({
      @Expect(desc = "Helper is null", expected = -1),
      @Expect(desc = "Helper is not null,but it is not initialized",
          expected = 0),
      @Expect(desc = "Only 1 field of Helper instance was initialized",
          expected = 1),
      @Expect(desc = "Only 2 fields of Helper instance were initialized",
          expected = 2),
      @Expect(desc = "Only 3 fields of Helper instance were initialized",
          expected = 3),
      @Expect(desc = "Helper instance was fully initialized", expected = 4) })
  public int consume() {
    int sum = 0;

    /*
     * 由于我们未对共享变量helper进行任何处理（比如采用volatile关键字修饰该变量），
     * 因此，这里可能存在可见性问题，即当前线程读取到的变量值可能为null。
     */
    final Helper observedHelper = helper;
    if (null == observedHelper) {
      sum = -1;
    } else {
      sum = observedHelper.payloadA + observedHelper.payloadB
          + observedHelper.payloadC + observedHelper.payloadD;
    }

    return sum;
  }

  static class Helper {
    int payloadA;
    int payloadB;
    int payloadC;
    int payloadD;

    public Helper(int externalData) {
      this.payloadA = externalData;
      this.payloadB = externalData;
      this.payloadC = externalData;
      this.payloadD = externalData;
    }

    @Override
    public String toString() {
      return "Helper [" + payloadA + ", " + payloadB + ", " + payloadC + ", "
          + payloadD + "]";
    }

  }

  public static void main(String[] args) throws InstantiationException,
      IllegalAccessException {
    // 调用测试工具运行测试代码
    TestRunner.runTest(JITReorderingDemo.class);
  }
}

/**
 <<Simple Concurrency Test Framework report>>:
 ===========================2019-11-29 14:22:22 星期五=================================
 expected:-1		occurrences:3		==>Helper is null
 expected:0		occurrences:0		==>Helper is not null,but it is not initialized
 expected:1		occurrences:0		==>Only 1 field of Helper instance was initialized
 expected:2		occurrences:0		==>Only 2 fields of Helper instance were initialized
 expected:3		occurrences:0		==>Only 3 fields of Helper instance were initialized
 expected:4		occurrences:199997		==>Helper instance was fully initialized
 =====================================END=============================================
 */