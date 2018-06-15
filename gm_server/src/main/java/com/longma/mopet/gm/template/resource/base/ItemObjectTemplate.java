package com.longma.mopet.gm.template.resource.base;

import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.template.TemplateObject;

@ExcelRowBinding
public abstract class ItemObjectTemplate extends TemplateObject {
	public abstract int getOverlayLimit();

	public abstract String getName();

	public abstract String getDesc();

	public abstract String getIcon();

	public abstract int getQuality();

	public abstract int getLevel();

	public abstract String getParameter1();

	public abstract String getParameter2();

	public abstract String getParameter3();

	public abstract String getParameter4();

	public abstract String getParameter5();

	public abstract int getGroupId();

	public abstract int getPrice();

	public abstract int getSale();

}