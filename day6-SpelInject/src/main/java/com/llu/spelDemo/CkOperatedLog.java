package com.llu.spelDemo;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CkOperatedLog {

    /**
     * 所属模块
     */
    String module() default "";

    /**
     * 操作类型add,del等
     */
    String operation() default "";

    /**
     * 描述
     */
    String description() default "";

    /**
     * 访问参数
     */
    String param() default "";


}
