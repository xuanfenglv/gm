package com.longma.mopet.gm.template.resource;

import com.longma.mopet.gm.annotation.ExcelCellBinding;
import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.template.resource.base.ItemObjectTemplate;
import com.longma.mopet.gm.util.Assert;
import com.longma.mopet.gm.util.StringUtils;

/**
 * 
 * @author LUO
 * @function 卡片的定义
 * @time 2017/7/28
 */
@ExcelRowBinding
// 卡片名称
public class CardTemplate extends ItemObjectTemplate {
	@ExcelCellBinding(offset = 1)
	private String name;
	// 图标
	@ExcelCellBinding(offset = 2)
	private String icon;
	// 原画
	@ExcelCellBinding(offset = 3)
	private String image;
	// 品质
	@ExcelCellBinding(offset = 4)
	private int quality;
	// 部位
	@ExcelCellBinding(offset = 5)
	private int position;
	// 基础属性类型
	@ExcelCellBinding(offset = 6)
	private String baseAttribute;
	// 基础加成类型
	@ExcelCellBinding(offset = 7)
	private String baseAddType;
	// 基础属性值
	@ExcelCellBinding(offset = 8)
	private String baseValue;
	// 进阶属性类型
	@ExcelCellBinding(offset = 9)
	private String riseAttribute;
	// 进阶加成类型
	@ExcelCellBinding(offset = 10)
	private String riseAddType;
	// 进阶属性数值
	@ExcelCellBinding(offset = 11)
	private String riseValue;
	// 卖出途径
	@ExcelCellBinding(offset = 17)
	private int sale;
	// 价格
	@ExcelCellBinding(offset = 18)
	private int price;

	private int[] basePropIndex;
	private float[] basePropValue;
	private int[] baseValueTypeIndexs;
	private int[] addPropIndex;
	private float[] addPropValue;
	private int[] addValueTypeIndexs;

	@Override
	public void patchUp() throws Exception {
		basePropIndex = StringUtils.getIntArrayByComma(baseAttribute);
		basePropValue = StringUtils.getFloatArray(baseValue, ",");
		baseValueTypeIndexs = StringUtils.getIntArrayByComma(baseAddType);
		addPropIndex = StringUtils.getIntArrayByComma(riseAttribute);
		addPropValue = StringUtils.getFloatArray(riseValue, ",");
		addValueTypeIndexs = StringUtils.getIntArrayByComma(riseAddType);
	}

	@Override
	public void check() {
		Assert.isTrue(basePropIndex.length == basePropValue.length && basePropIndex.length == baseValueTypeIndexs.length,
				"卡片基础属性类型，加成类型，属性值 三列数量错误！");
		Assert.isTrue(addPropIndex.length == addPropValue.length && addPropIndex.length == addValueTypeIndexs.length, "卡片进阶属性类型，加成类型，属性值 三列数量错误！");
	}

	public int[] getBasePropIndex() {
		return basePropIndex;
	}

	public void setBasePropIndex(int[] basePropIndex) {
		this.basePropIndex = basePropIndex;
	}

	public float[] getBasePropValue() {
		return basePropValue;
	}

	public void setBasePropValue(float[] basePropValue) {
		this.basePropValue = basePropValue;
	}

	public int[] getBaseValueTypeIndexs() {
		return baseValueTypeIndexs;
	}

	public void setBaseValueTypeIndexs(int[] baseValueTypeIndexs) {
		this.baseValueTypeIndexs = baseValueTypeIndexs;
	}

	public int[] getAddPropIndex() {
		return addPropIndex;
	}

	public void setAddPropIndex(int[] addPropIndex) {
		this.addPropIndex = addPropIndex;
	}

	public float[] getAddPropValue() {
		return addPropValue;
	}

	public void setAddPropValue(float[] addPropValue) {
		this.addPropValue = addPropValue;
	}

	public int[] getAddValueTypeIndexs() {
		return addValueTypeIndexs;
	}

	public void setAddValueTypeIndexs(int[] addValueTypeIndexs) {
		this.addValueTypeIndexs = addValueTypeIndexs;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getBaseAttribute() {
		return baseAttribute;
	}

	public void setBaseAttribute(String baseAttribute) {
		this.baseAttribute = baseAttribute;
	}

	public String getBaseAddType() {
		return baseAddType;
	}

	public void setBaseAddType(String baseAddType) {
		this.baseAddType = baseAddType;
	}

	public String getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}

	public String getRiseAttribute() {
		return riseAttribute;
	}

	public void setRiseAttribute(String riseAttribute) {
		this.riseAttribute = riseAttribute;
	}

	public String getRiseAddType() {
		return riseAddType;
	}

	public void setRiseAddType(String riseAddType) {
		this.riseAddType = riseAddType;
	}

	public String getRiseValue() {
		return riseValue;
	}

	public void setRiseValue(String riseValue) {
		this.riseValue = riseValue;
	}

	@Override
	public int getOverlayLimit() {
		return 1;
	}

	@Override
	public String getDesc() {
		return "";
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getParameter1() {
		return null;
	}

	@Override
	public String getParameter2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter5() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupId() {
		return 0;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
