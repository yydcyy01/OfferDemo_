package com.yydcyy.demo2._11ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YYDCYY
 * @create 2019-11-18
 *
 * 不可变的类型：
 *
 * final 关键字修饰的基本数据类型
 * String
 * 枚举类型
 * Number 部分子类，如 Long 和 Double 等数值包装类型，BigInteger 和 BigDecimal 等大数据类型。但同为 Number 的原子类 AtomicInteger 和 AtomicLong 则是可变的。
 * 对于集合类型，可以使用 Collections.unmodifiableXXX() 方法来获取一个不可变的集合。
 *
 * 修改会报异常 : Exception in thread "main" java.lang.UnsupportedOperationException
 *
 * public V put(K key, V value) {
 *     throw new UnsupportedOperationException();
 * }
 */
public class ImmutableExample {


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a", 1);
    }
}
