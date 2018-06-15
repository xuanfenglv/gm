package com.longma.mopet.gm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 单行模板<br/>
 * 加上此类型注解的模板类只会加载第一行，并且有且仅有一行，如果不是一行，则需要抛出异常
 * 
 * @author wenji.fan f123wj@gmail.com
 * @since @2012-12-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface SingleRow {

}
