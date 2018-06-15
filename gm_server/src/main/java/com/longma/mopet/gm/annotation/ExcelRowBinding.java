package com.longma.mopet.gm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  标注一个类可以从一个Excel表格里加载
 *  
 * @author Yvon
 * @since 2010-3-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
public @interface ExcelRowBinding {
	int startOffset() default 0;
}
