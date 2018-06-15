package com.longma.mopet.gm.template;

import com.longma.mopet.gm.annotation.SingleRow;

import java.net.URL;
import java.util.Map;

/**
 * 
 * 
 * @author zhangwh
 * @since 2010-4-8
 */
public interface ITemplateService {

	/**
	 * 初始化配置文件，并加载excel脚本
	 * 
	 * @param cfgpath
	 *            配置文件路径
	 * @param check
	 *            是否对模板进行校验
	 * 
	 */
	void init(URL cfgpath, boolean check);

	/**
	 * @param <T>
	 * @param id
	 * @param clazz
	 * @return
	 */
	<T extends TemplateObject> T get(int id, Class<T> clazz);
	
	/**
	 * 获取单个模板<br/>
	 * 使用此方法获取的模板必须有且仅有一行数据(即模板类包含{@link SingleRow}注解)
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T extends TemplateObject> T getSingle(Class<T> clazz);

	/**
	 * @param <T>
	 * @param t
	 */
	<T extends TemplateObject> void add(T t);

	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T extends TemplateObject> Map<Integer, T> getAll(Class<T> clazz);

	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T extends TemplateObject> Map<Integer, T> removeAll(Class<T> clazz);

	/**
	 * 判断某一对象是否存在
	 * 
	 * @param <T>
	 * @param id
	 * @param clazz
	 * @return
	 */
	<T extends TemplateObject> boolean isTemplateExist(int id, Class<T> clazz);

}
