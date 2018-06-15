package com.longma.mopet.gm.template.resource;

import com.longma.mopet.gm.annotation.ExcelCellBinding;
import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.template.resource.base.ItemObjectTemplate;
import com.longma.mopet.gm.util.Assert;
import com.longma.mopet.gm.util.Groupable;
import com.longma.mopet.gm.util.StringUtils;

import java.util.List;

@ExcelRowBinding
public class EquipTemplate extends ItemObjectTemplate implements Groupable {
	// 装备的名称
	@ExcelCellBinding(offset = 1)
	protected String name;
	// 装备描述
	@ExcelCellBinding(offset = 2)
	protected String desc;
	// 图标
	@ExcelCellBinding(offset = 3)
	protected String icon;
	// 部位，装备的位置
	@ExcelCellBinding(offset = 4)
	protected int type;
	// 品质
	@ExcelCellBinding(offset = 5)
	protected int quality;
	// 等级
	@ExcelCellBinding(offset = 6)
	protected int level;
	// 装备组
	@ExcelCellBinding(offset = 7)
	private int group;
	// 进阶装备
	@ExcelCellBinding(offset = 8)
	protected int nextGroup;
	// 职业
	@ExcelCellBinding(offset = 9)
	protected String job;
	// 主属性类型
	@ExcelCellBinding(offset = 10)
	protected String mainType;
	// 主属性空间下
	@ExcelCellBinding(offset = 11)
	protected String mainValueDown;
	// 主属性空间上
	@ExcelCellBinding(offset = 12)
	protected String mainValueUp;
	// 附加的随机属性库
	@ExcelCellBinding(offset = 13)
	protected int extraGroup;
	// 进阶道具，升级道具
	@ExcelCellBinding(offset = 14)
	protected String improve;
	// 进阶消耗道具的数量
	@ExcelCellBinding(offset = 15)
	protected String improveCost;
	// 进阶消耗货币的数量
	@ExcelCellBinding(offset = 16)
	protected int money;
	// 装备的技能
	@ExcelCellBinding(offset = 17)
	protected String skill;
	// 排序优先级
	@ExcelCellBinding(offset = 18)
	private int priority;
	// 类型说明
	@ExcelCellBinding(offset = 19)
	private String typeDesc;
	// 功能说明
	@ExcelCellBinding(offset = 20)
	private String funDesc;
	// 功能说明
	@ExcelCellBinding(offset = 21)
	private String operate;
	// 卖出途径
	@ExcelCellBinding(offset = 22)
	private int sale;
	// 价格
	@ExcelCellBinding(offset = 23)
	private int price;

	private int[] basePropIndex;
	private float[] randomDown;
	private float[] randomUp;
	private List<Integer> jobs;

	@Override
	public void patchUp() throws Exception {
		basePropIndex = StringUtils.getIntArrayByComma(mainType);
		randomDown = StringUtils.getFloatArray(mainValueDown, ",");
		randomUp = StringUtils.getFloatArray(mainValueUp, ",");
		this.setJobs(StringUtils.getIntList(job, ","));
	}

	public boolean canEquipByJob(int jobId) {
		return getJobs().contains(jobId);
	}

	private List<Integer> getJobs() {
		return this.jobs;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	@Override
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getGroupId() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getNextGroup() {
		return nextGroup;
	}

	public void setNextGroup(int nextGroup) {
		this.nextGroup = nextGroup;
	}

	public String getMainType() {
		return mainType;
	}

	public int getGroup() {
		return group;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setJobs(List<Integer> jobs) {
		this.jobs = jobs;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getMainValueDown() {
		return mainValueDown;
	}

	public void setMainValueDown(String mainValueDown) {
		this.mainValueDown = mainValueDown;
	}

	public String getMainValueUp() {
		return mainValueUp;
	}

	public void setMainValueUp(String mainValueUp) {
		this.mainValueUp = mainValueUp;
	}

	public int getExtraGroup() {
		return extraGroup;
	}

	public void setExtraGroup(int extraGroup) {
		this.extraGroup = extraGroup;
	}

	public String getImprove() {
		return improve;
	}

	public void setImprove(String improve) {
		this.improve = improve;
	}

	public String getImproveCost() {
		return improveCost;
	}

	public void setImproveCost(String improveCost) {
		this.improveCost = improveCost;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public int getOverlayLimit() {
		return 1;
	}

	@Override
	public void check() {
		Assert.isTrue(basePropIndex.length == randomDown.length && basePropIndex.length == randomUp.length, "装备表 主属性类型，随机上限，随机下限 数量不一致！");
	}

	public int[] getBasePropIndex() {
		return basePropIndex;
	}

	public void setBasePropIndex(int[] basePropIndex) {
		this.basePropIndex = basePropIndex;
	}

	public float[] getRandomDown() {
		return randomDown;
	}

	public void setRandomDown(float[] randomDown) {
		this.randomDown = randomDown;
	}

	public float[] getRandomUp() {
		return randomUp;
	}

	public void setRandomUp(float[] randomUp) {
		this.randomUp = randomUp;
	}

	@Override
	public String getParameter1() {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public int getPrice() {
		return price;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
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
