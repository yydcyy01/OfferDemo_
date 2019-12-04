package com.yydcyy.JavaMultiThreadInAction.util.stf;

import java.lang.annotation.*;
/**
 * @author YYDCYY
 * @create 2019-11-29
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ConcurrencyTest {
	int iterations() default 20000;
	int thinkTime() default 0;
}
