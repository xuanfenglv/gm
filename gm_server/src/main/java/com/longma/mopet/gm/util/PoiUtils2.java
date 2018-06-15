package com.longma.mopet.gm.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * 使用Poi实现的Excel解析工具类
 * 
 * @author <a href="mailto:dongyong.wang@opi-corp.com">wang dong yong<a>
 * 
 */
public class PoiUtils2 {

	private static final NumberFormat FMT_NUMBER = new DecimalFormat("0.#########");

	/**
	 * 按照整形int读取cell中的值
	 * 
	 * @param cell
	 * @return 0,当cell为空时;否则返回其内容所表示的值
	 * @exception NumberFormatException
	 */
	public static int getIntValue(Cell cell) {
		if (cell == null || cell.toString().trim().length() == 0) {
			return 0;
		}
		return (int) Double.parseDouble(cell.toString());
	}

	/**
	 * 按照整形short读取cell中的值
	 * 
	 * @param cell
	 * @return 0,当cell为空时;否则返回其内容所表示的值
	 * @exception NumberFormatException
	 */
	public static short getShortValue(Cell cell) {
		if (cell == null || cell.toString().length() == 0) {
			return 0;
		}
		return (short) Double.parseDouble(cell.toString());
	}

	/**
	 * @param cell
	 * @return
	 */
	public static byte getByteValue(Cell cell) {
		if (cell == null || cell.toString().length() == 0) {
			return 0;
		}
		return (byte) Double.parseDouble(cell.toString());
	}

	/**
	 * @param cell
	 * @return
	 */
	public static long getLongValue(Cell cell) {
		if (cell == null || cell.toString().length() == 0) {
			return 0;
		}
		return Long.parseLong(cell.toString());
	}

	/**
	 * 按照浮点型double读取cell中的值
	 * 
	 * @param cell
	 * @return 0.0,当cell为空时;否则返回其内容所表示的值
	 * @exception NumberFormatException
	 */
	public static double getDoubleValue(Cell cell) {
		if (cell == null || cell.toString().length() == 0) {
			return 0.0;
		}
		return Double.parseDouble(cell.toString());
	}

	/**
	 * 按照日期型读取cell中的值
	 * 
	 * @param cell
	 * @param pattern
	 * @return null,当cell为空时;否则返回其内容所表示的值
	 */
	public static Date getDateValue(Cell cell, String pattern) {
		if (cell != null && cell.toString().length() > 0) {
			return cell.getDateCellValue();
		}
		return null;

	}

	public static Calendar getCalendarValue(Cell cell) {
		if (cell != null && cell.toString().length() > 0) {
			double numDate = getDoubleValue(cell);
			Date date = DateUtil.getJavaDate(numDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
		return null;
	}

	public static String getStringValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: {
			return cell.toString();
		}
		case Cell.CELL_TYPE_NUMERIC: {
			String str = FMT_NUMBER.format(cell.getNumericCellValue());
			if (str.endsWith(".0")) {
				return str.substring(0, str.length() - 2);
			} else {
				return str;
			}
		}
		default: {
			return cell.toString();
		}
		}
	}

	public static float getFloatValue(Cell cell) {
		if (cell == null || cell.toString().length() == 0) {
			return 0;
		}
		try {
			return Float.parseFloat(cell.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(cell.toString());
			throw e;
		}
	}

	public static String getIntString(Cell cell) {
		return "" + getIntValue(cell);
	}

	/**
	 * 判断是否为空白行
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isBlankRow(Row row) {
		// 检测此行的第一个单元格是否为空
		if (row == null) {
			return true;
		}
		Cell cell0 = row.getCell(0);
		String value = PoiUtils2.getStringValue(cell0);
		if (value == null || StringUtil.isEmpty(value)) {
			return true;
		} else {
			return false;
		}
	}
}
