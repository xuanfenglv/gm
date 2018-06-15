package com.longma.mopet.gm.template.resource;

import com.longma.mopet.gm.annotation.ExcelCellBinding;
import com.longma.mopet.gm.annotation.ExcelCollectionMapping;
import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.template.resource.base.ItemObjectTemplate;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:09 2018/5/2
 * @Modified By:
 */
@ExcelRowBinding
public class ItemTemplate  extends ItemObjectTemplate {
    /** 道具的名称 */
    @ExcelCellBinding(offset = 1)
    protected String name;
    /** 说明 */
    @ExcelCellBinding(offset = 2)
    private String des;
    /** 图标 */
    @ExcelCellBinding(offset = 3)
    private String icon;
    /** 品质 */
    @ExcelCellBinding(offset = 4)
    private int quality;
    /** 类型 */
    @ExcelCellBinding(offset = 5)
    private int type;
    /** 道具的效果参数 1 */
    @ExcelCellBinding(offset = 6)
    protected String parameter1;
    /** 道具的效果参数 2 */
    @ExcelCellBinding(offset = 7)
    protected String parameter2;
    /** 道具的效果参数 3 */
    @ExcelCellBinding(offset = 8)
    protected String parameter3;
    /** 道具的效果参数 4 */
    @ExcelCellBinding(offset = 9)
    protected String parameter4;
    /** 道具的效果参数 5 */
    @ExcelCellBinding(offset = 10)
    protected String parameter5;
    /** 冷却时间 */
    @ExcelCellBinding(offset = 11)
    private int cd;
    /** 公共冷却时间组 */
    @ExcelCellBinding(offset = 12)
    private int cdGroup;
    /** 公共cd */
    @ExcelCellBinding(offset = 13)
    private int ccd;
    /** 存储类型 */
    @ExcelCellBinding(offset = 14)
    private int save;
    /** 叠加上线 */
    @ExcelCellBinding(offset = 15)
    private int max;
    /** 排序优先级 */
    @ExcelCellBinding(offset = 16)
    private int priority;
    /** 是否任务物品 */
    @ExcelCellBinding(offset = 17)
    private int mission;
    /** 可否快捷栏 */
    @ExcelCellBinding(offset = 18)
    private long shortCut;
    /** 有效时间 */
    @ExcelCellBinding(offset = 19)
    private long suStain;
    /** 需要等级 */
    @ExcelCellBinding(offset = 20)
    private int minLv;
    /** 使用等级上线 */
    @ExcelCellBinding(offset = 21)
    private int maxLv;
    /** 操作功能 */
    @ExcelCollectionMapping(clazz = String.class, collectionNumber = "22")
    private String[] function;
    /** 卖出途径 */
    @ExcelCellBinding(offset = 23)
    private int sale;
    /** 价格 */
    @ExcelCellBinding(offset = 24)
    private int price;
    /** 分解类型 */
    @ExcelCellBinding(offset = 25)
    private int brokeType;
    /** 分解参数 */
    @ExcelCellBinding(offset = 26)
    private int brokeParameter;
    /** 快速获取途径 */
    @ExcelCellBinding(offset = 27)
    private String getType;
    /** 是否记录日志 */
    @ExcelCellBinding(offset = 28)
    private int isLog;
    /** 类型说明 */
    @ExcelCellBinding(offset = 29)
    private String typeDes;
    /** 功能说明 */
    @ExcelCellBinding(offset = 30)
    private String functionDes;

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdGroup() {
        return cdGroup;
    }

    public void setCdGroup(int cdGroup) {
        this.cdGroup = cdGroup;
    }

    public int getCcd() {
        return ccd;
    }

    public void setCcd(int ccd) {
        this.ccd = ccd;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMission() {
        return mission;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    public long getShortCut() {
        return shortCut;
    }

    public void setShortCut(long shortCut) {
        this.shortCut = shortCut;
    }

    public long getSuStain() {
        return suStain;
    }

    public void setSuStain(long suStain) {
        this.suStain = suStain;
    }

    public int getMinLv() {
        return minLv;
    }

    public void setMinLv(int minLv) {
        this.minLv = minLv;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(int maxLv) {
        this.maxLv = maxLv;
    }

    public String[] getFunction() {
        return function;
    }

    public void setFunction(String[] function) {
        this.function = function;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBrokeType() {
        return brokeType;
    }

    public void setBrokeType(int brokeType) {
        this.brokeType = brokeType;
    }

    public int getBrokeParameter() {
        return brokeParameter;
    }

    public void setBrokeParameter(int brokeParameter) {
        this.brokeParameter = brokeParameter;
    }

    public String getGetType() {
        return getType;
    }

    public void setGetType(String getType) {
        this.getType = getType;
    }

    public int getIsLog() {
        return isLog;
    }

    public void setIsLog(int isLog) {
        this.isLog = isLog;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public String getFunctionDes() {
        return functionDes;
    }

    public void setFunctionDes(String functionDes) {
        this.functionDes = functionDes;
    }

    @Override
    public int getOverlayLimit() {
        return max;
    }

    @Override
    public String getDesc() {
        return "";
    }

    @Override
    public void check() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLevel() {
        return minLv;
    }

    public long getExpiredTime() {
        return 0;
    }

    @Override
    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    @Override
    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    @Override
    public String getParameter3() {
        return parameter3;
    }

    public void setParameter3(String parameter3) {
        this.parameter3 = parameter3;
    }

    @Override
    public String getParameter4() {
        return parameter4;
    }

    public void setParameter4(String parameter4) {
        this.parameter4 = parameter4;
    }

    @Override
    public String getParameter5() {
        return parameter5;
    }

    public void setParameter5(String parameter5) {
        this.parameter5 = parameter5;
    }

    @Override
    public int getGroupId() {
        return 0;
    }

}
