package com.longma.mopet.gm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 标注一个方法，该方法会在标注了@ExcelRowBinding的对象
 * 从Excel里加载相应列，并把加载的列作为字符串数组参数来调用相应方法
 *
 * @author Yvon
 * @since 2010-3-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FixUpByCellRange {
   int start();
   int len();
}
