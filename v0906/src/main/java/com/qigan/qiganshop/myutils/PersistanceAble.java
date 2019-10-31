package com.qigan.qiganshop.myutils;

import java.lang.annotation.*;

/**
 * 可持久化注解
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersistanceAble {

    String value() default "";

    String tableName() default  "";

    boolean ignoreCloumnMapping() default false;

}
