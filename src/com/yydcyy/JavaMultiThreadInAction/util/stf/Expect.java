package com.yydcyy.JavaMultiThreadInAction.util.stf;

import java.lang.annotation.*;
/**
 * @author YYDCYY
 * @create 2019-11-29
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Expect {
	int expected();
	String desc();
}
