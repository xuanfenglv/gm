package com.longma.mopet.gm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *标识类中的属性为集合属性，包括Map、Set、List等三类；
 * 
 * @author taohl
 * @since 2010-3-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD })
public @interface ExcelCollectionMapping {

	/** 集合中Class */
	Class<?> clazz();

	/**
	 * excel中的列编号串
	 */
	String collectionNumber();
}
