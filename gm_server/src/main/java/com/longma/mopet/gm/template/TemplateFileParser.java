package com.longma.mopet.gm.template;

import com.longma.mopet.gm.annotation.ExcelRowBinding;
import com.longma.mopet.gm.annotation.FixUpByCellRange;
import com.longma.mopet.gm.exception.ConfigException;
import com.longma.mopet.gm.util.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板文件解析器
 * 
 * @author Yvon
 * @author taohl
 * @since 2010-4-8
 */
public class TemplateFileParser {
	public static final int HEADER_LENGTH = 8;
	// excel各版本文件头，不能随意更改
	public static final String XLS_HEADER = "D0CF11E0A1B11AE1";
	public static final String XLSX_HEADER = "504B3414060";

	private TemplateObjectAssembler objectAssembler;

	public TemplateFileParser() {
		objectAssembler = new TemplateObjectAssembler();
	}

	/**
	 * 根据文件获得对应的工作簿对象
	 * 
	 * @param xlsPath
	 *            文件路径
	 * @param inputStream
	 *            读取文件的流，用来组装{@link Workbook}对象
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkBook(String xlsPath, InputStream inputStream) throws Exception {
		Workbook wb = null;
		FileInputStream fin = new FileInputStream(new File(xlsPath));
		try {
			if (xlsPath.endsWith("xls")) {
				wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
			} else if (xlsPath.endsWith("xlsx")) {
				wb = new XSSFWorkbook(inputStream);
			}
			// String header = readFileHeaderString(fin);
			// if (header.equals(XLS_HEADER)) {
			// wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
			// } else if (header.equals(XLSX_HEADER)) {
			// wb = new XSSFWorkbook(inputStream);
			// } else {
			// String headStr = null;
			// String file_format = null;
			// int indexOf = xlsPath.indexOf(".");
			// if (indexOf != -1) {
			// file_format = xlsPath.substring(indexOf + 1).toLowerCase();
			// headStr = file_format.equals("xls") ? XLS_HEADER :
			// file_format.equals("xlsx") ? XLSX_HEADER : null;
			// }
			// String errLog = null;
			// if (headStr == null) {
			// errLog = xlsPath + "不是一个excel文件！";
			// } else {
			// errLog = String.format("错误文件头：%s，无法识别为Excel文件,。正确%s文件头为：%s",
			// header, file_format, headStr);
			// }
			// throw new ConfigException(errLog);
			// }
		} finally {
			fin.close();
		}
		return wb;
	}

	/**
	 * 读取文件头
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private static String readFileHeaderString(InputStream is) throws IOException {
		BufferedInputStream bin = new BufferedInputStream(is);
		if (bin.markSupported()) {
			bin.mark(HEADER_LENGTH);
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < HEADER_LENGTH; x++) {
				sb.append(Integer.toHexString(bin.read()));
			}
			bin.reset();
			return sb.toString().toUpperCase();
		} else {
			throw new ConfigException("读取文件头错误!");
		}
	}

	/**
	 * 解析一个Excel文件，加载该文件表示的所有TemplateObject对象到templateObjects；
	 * 
	 * @param classes
	 * @param templateObjects
	 *            TODO
	 */
	public void parseXlsFile(Class<?>[] classes, Map<Class<?>, Map<Integer, TemplateObject>> templateObjects, Workbook wb) throws Exception {
		int i = 0;
		for (; i < classes.length; i++) {
			Sheet sheet = wb.getSheetAt(i);
			Class<?> curClazz = classes[i];
			if (curClazz == null)
				continue;
			Map<Integer, TemplateObject> curSheetObjects = parseXlsSheet(sheet, curClazz);
			Map<Integer, TemplateObject> existCurClazzMap = templateObjects.get(curClazz);
			if (existCurClazzMap != null) {
				// 如果当前类型的对象已存在了，则合并
				existCurClazzMap.putAll(curSheetObjects);
			} else {
				templateObjects.put(curClazz, curSheetObjects);
			}
		}
	}

	/**
	 * 解析Excel文件中的一个Sheet，返回以<id,数据对象>为键值对的Map
	 * 
	 * @param sheet
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	protected Map<Integer, TemplateObject> parseXlsSheet(Sheet sheet, Class<?> clazz) throws InstantiationException, IllegalAccessException {
		// int rowLength = sheet.getPhysicalNumberOfRows();
		Map<Integer, TemplateObject> map = new HashMap<Integer, TemplateObject>(200, 0.8f);
		// 第一行(原来的标题行)肯定有空,忽略不计
		for (int i = 1; i <= Short.MAX_VALUE; i++) {
			TemplateObject obj;
			obj = (TemplateObject) clazz.newInstance();
			Row row = sheet.getRow(i);
			if (PoiUtils.isBlankRow(row)) {
				// 遇到空行就结束
				break;
			}
			this.parseXlsRow(obj, row);
			map.put(obj.getId(), obj);
		}
		return map;
	}

	/**
	 * 根据Excel表格，自行组装对象
	 * 
	 * @param obj
	 * @param row
	 */
	public void parseXlsRow(Object obj, Row row) {
		Class<?> clazz = obj.getClass();
		if (!clazz.isAnnotationPresent(ExcelRowBinding.class)) {
			throw new ConfigException(clazz + " is not a excel row binding object");
		}
		try {
			objectAssembler.doAssemble(obj, row, clazz);
		} catch (Exception e1) {
			System.err.println("parseXlsRow exception class:" + clazz + "   error line number: " + row.getPhysicalNumberOfCells());
			throw new ConfigException(e1);
		}
		try {
			Method[] methods = null;
			if (TemplateObjectAssembler.classMethods.containsKey(clazz)) {
				methods = TemplateObjectAssembler.classMethods.get(clazz);
			} else {
				methods = clazz.getDeclaredMethods();
				Method.setAccessible(methods, true);
				TemplateObjectAssembler.classMethods.put(clazz, methods);
			}
			for (int i = 0; i < methods.length; i++) {
				if ((methods[i].getModifiers() & Modifier.STATIC) != 0) {
					continue;
				}
				if (methods[i].isAnnotationPresent(FixUpByCellRange.class)) {
					FixUpByCellRange fixupByCellRange = methods[i].getAnnotation(FixUpByCellRange.class);
					int startOff = fixupByCellRange.start();
					int len = fixupByCellRange.len();
					String[] params = new String[len];
					for (int k = 0; k < params.length; k++) {
						params[k] = PoiUtils.getStringValue(row.getCell(startOff + k));
					}
					methods[i].invoke(obj, new Object[] { params });
				}
			}
		} catch (Exception e) {
			System.err.println("parseXlsRow exception class:" + clazz);
			throw new ConfigException("Unknown exception", e);
		}
	}

	/**
	 * 解析一个Excel文件，加载该文件表示的所有TemplateObject对象到templateObjects；
	 * 
	 * @param index
	 * @param clazz
	 * @param templateObjects
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void parseXlsFile(Workbook wb, int index, Class<?> clazz, Map<Class<?>, Map<Integer, TemplateObject>> templateObjects)
			throws InstantiationException, IllegalAccessException {
		Sheet sheet = wb.getSheetAt(index);
		Map<Integer, TemplateObject> curSheetObjects = parseXlsSheet(sheet, clazz);
		Map<Integer, TemplateObject> existCurClazzMap = templateObjects.get(clazz);
		if (existCurClazzMap != null) {
			// 如果当前类型的对象已存在了，则合并
			existCurClazzMap.putAll(curSheetObjects);
		} else {
			templateObjects.put(clazz, curSheetObjects);
		}
	}
}
