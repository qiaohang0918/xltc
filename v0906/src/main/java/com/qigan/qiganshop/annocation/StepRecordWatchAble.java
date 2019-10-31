package com.qigan.qiganshop.annocation;

import java.lang.annotation.*;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/3 8:57
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StepRecordWatchAble {

    String name() default "" ;

    String value() default  "";
}
