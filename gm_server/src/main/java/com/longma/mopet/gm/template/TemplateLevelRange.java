package com.longma.mopet.gm.template;

/**
 * 角色级别闭区间，即起始等级和终止等级都包含在级别段内
 * 
 * @author li.liu
 * @since 2010-10-25
 */
public interface TemplateLevelRange {

	/**
	 * 起始等级
	 */
	int getStartLevel();

	/**
	 * 终止等级
	 */
	int getEndLevel();

	/**
	 * 模板名称
	 */
	String getTemplateName();

	/**
	 * 模板Id
	 */
	int getId();

}
