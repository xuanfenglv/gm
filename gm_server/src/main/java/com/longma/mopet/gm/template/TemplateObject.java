package com.longma.mopet.gm.template;



import com.longma.mopet.gm.annotation.ExcelCellBinding;
import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.exception.ConfigException;

import java.util.Set;


@ExcelRowBinding
public abstract class TemplateObject {

	public static int NULL_ID = 0;

	@ExcelCellBinding(offset = 0)
	protected int id;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * <pre>
	 * 在{@link TemplateService}加载完所有的模板对象之后调用，主要用于检查各个模板
	 * 表配置是否正确，如果不正确，应抛出{@link ConfigException}类型的异常，并在异常
	 * 消息中记录详细的出错信息
	 * </pre>
	 * 
	 * @throws TemplateConfigException
	 *             TODO
	 */
	public abstract void check();

	/**
	 * <pre>
	 * 非必要性检查，在需要检查某字段唯一性时，重写此方法
	 * 在{@link TemplateService}加载完所有的模板对象之后调用，主要用于检查各个模板
	 * 表配置是否正确，如果不正确，应抛出{@link ConfigException}类型的异常，并在异常
	 * 消息中记录详细的出错信息
	 * </pre>
	 * 
	 * @throws TemplateConfigException
	 *             TODO
	 */
	public void checkUnique(Set<Object> checkSet) throws Exception {

	}

	/**
	 * <pre>
	 * 在{@link TemplateService}加载完所有的模板对象之后调用，主要用于构建各个模板
	 * 对象之间的依赖关系
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void patchUp() throws Exception {
	}


	/**
	 * 返回此模板的名字，可以写的更详细一点，哪个文件的那个页签
	 * 
	 * @return
	 */
	public String getTemplateName() {
		return "";
	}
}
