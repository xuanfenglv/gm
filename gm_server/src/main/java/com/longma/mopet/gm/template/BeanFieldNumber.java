package com.longma.mopet.gm.template;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解类属性在类中的编号
 * 
 * @author taohl
 * @since 2010-3-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface BeanFieldNumber {
	int number();
}
