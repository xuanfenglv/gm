package com.longma.mopet.gm.template;

import com.longma.mopet.gm.annotation.SingleRow;
import com.longma.mopet.gm.exception.ConfigException;
import com.longma.mopet.gm.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;

/**
 * 模板数据管理器，在服务器启动时加载所有excel模板数据
 * 
 * @author zhangwh
 * @since 2010-4-8
 */
public class TemplateService implements ITemplateService {

	/** 所有通过模板文件转换而成的模板对象的实例 */
	private Map<Class<?>, Map<Integer, TemplateObject>> templateObjects;

	private List<TemplateConfig> templateConfigs;
	private TemplateFileParser objectsParser;
	private String resourceFolder;
	private boolean isDebug;
	private List<TemplateObserver> observers = new LinkedList<TemplateObserver>();


	public TemplateService(boolean isDebug, boolean check) {
		this.resourceFolder = ConfigUtil.getConfigURL("scripts").getPath();
		this.isDebug = isDebug;
		URL url = ConfigUtil.getConfigURL("config/templates.xml");
		init(url, check);
	}

	public void registerObserver(TemplateObserver observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void removeObserver(TemplateObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void init(URL cfgPath, boolean check) {
		this.loadConfig(cfgPath);
		templateObjects = new HashMap<Class<?>, Map<Integer, TemplateObject>>();
		objectsParser = new TemplateFileParser();
		InputStream is = null;
		String fileName = null;
		for (TemplateConfig cfg : templateConfigs) {
			try {
				fileName = cfg.getFileName();
				if (fileName == null) {// 没有配置文件名可能是parser内部自己处理
					this.getTemplateParser(cfg).parseXlsFile(cfg.getClasses(), templateObjects, null);
					continue;
				}
				System.out.println(String.format("loading %s", fileName));
				String xlsPath = resourceFolder + File.separator + cfg.getFileName();
				System.out.println(xlsPath);
				if (isDebug) {
					is = new FileInputStream(xlsPath);
				} else {
					is = new XorDecryptedInputStream(xlsPath);
				}
				this.getTemplateParser(cfg).parseXlsFile(cfg.getClasses(), templateObjects, TemplateFileParser.getWorkBook(xlsPath, is));
				is.close();
			} catch (Exception e) {
				throw new ConfigException("Errors occurs while parsing xls file:" + fileName, e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (check) {
			try {
				patchUpAndCheck();
			} catch (Exception e) {
				// 此处throw的有问题。内容不应该和上面一样。应该将exception打出来
				throw new ConfigException("patchUpAndCheck error !!!", e);
			}
		}
	}

	/**
	 * 加载配置文件
	 * 
	 * @param cfgPath
	 */
	@SuppressWarnings("unchecked")
	private void loadConfig(URL cfgPath) {
		Element root = JdomUtils.getRootElemet(cfgPath);
		templateConfigs = new ArrayList<TemplateConfig>();
		// templateFiles = new TreeMap<String, Class<?>[]>();
		List<Element> fileElements = root.getChildren();
		for (Element fileElement : fileElements) {
			String fileName = fileElement.getAttributeValue("name");
			String parserClassName = fileElement.getAttributeValue("parser");
			List<Element> sheetElements = fileElement.getChildren();
			Class<?>[] fileSheetClasses = new Class<?>[sheetElements.size()];
			for (int i = 0; i < sheetElements.size(); i++) {
				Element sheet = sheetElements.get(i);
				String className = sheet.getAttributeValue("class");
				if (StringUtil.isEmpty(className)) {
					fileSheetClasses[i] = null;
					continue;
				}
				try {
					Class<?> clazz = Class.forName(className);
					fileSheetClasses[i] = clazz;
				} catch (ClassNotFoundException e) {
					throw new ConfigException(e);
				}
			}
			TemplateConfig templateConfig = new TemplateConfig(fileName, fileSheetClasses);
			if (parserClassName != null && (parserClassName = parserClassName.trim()).length() > 0) {
				templateConfig.setParserClassName(parserClassName);
			}
			templateConfigs.add(templateConfig);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TemplateObject> T get(int id, Class<T> clazz) {
		
			System.out.println("[#CORE.TemplateService.get] [clazz:" + clazz + "]");
		Map<Integer, TemplateObject> map = templateObjects.get(clazz);
		return (T) map.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TemplateObject> Map<Integer, T> getAll(Class<T> clazz) {
		return (Map<Integer, T>) templateObjects.get(clazz);
	}

	@SuppressWarnings("unchecked")
	public <T extends TemplateObject> List<T> getAllList(Class<T> clazz) {
		List<T> templateList = new ArrayList<T>();
		Map<Integer, T> thisTemp = (Map<Integer, T>) templateObjects.get(clazz);
		for (Integer key : thisTemp.keySet()) {
			T t = thisTemp.get(key);
			if (t != null) {
				templateList.add(t);
			}
		}
		return templateList;
	}

	/**
	 * 返回所有类别的template列表
	 * 
	 * @return
	 */
	public Map<Class<?>, Map<Integer, TemplateObject>> getAllClassTemplateMaps() {
		return Collections.unmodifiableMap(templateObjects);
	}

	@Override
	public <T extends TemplateObject> void add(T t) {
		Map<Integer, TemplateObject> objs = templateObjects.get(t.getClass());
		if (objs == null || objs.containsKey(t.getId())) {
			return;
		}
		objs.put(t.getId(), t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TemplateObject> Map<Integer, T> removeAll(Class<T> clazz) {
		return (Map<Integer, T>) templateObjects.remove(clazz);
	}

	@Override
	public <T extends TemplateObject> boolean isTemplateExist(int id, Class<T> clazz) {
		Map<Integer, TemplateObject> map = templateObjects.get(clazz);
		if (null != map) {
			return null == map.get(id) ? false : true;
		}
		return false;
	}

	/**
	 * 重载数据表
	 *   <ol>
	 *		<li>文件名为null，index=-1，重载全部文件</li>
	 *		<li>文件名不为null，index=-1，重载单个文件</li>
	 *		<li>文件名不为null，index不等于-1，重载单个文件单个表</li>
	 *   </ol>
	 * @param fileName 文件名
	 * @param index 工作表索引，如果为0，重载全部
	 * @return
	 * @throws Exception
	 */
	public boolean reload(String fileName, int index) throws Exception {
		InputStream is = null;
		boolean loaded = false, all = false, sinleFile = false, singleFileSheet = false;
		if (templateConfigs != null) {
			for (TemplateConfig cfg : templateConfigs) {
				String xls = null;
				if (fileName == null && index == -1) {// 全部
					all = true;
					xls = cfg.getFileName();
				} else if (fileName != null && !fileName.isEmpty() && index == -1) {// 单文件
					if (fileName.equals(cfg.getFileName().substring(0, cfg.getFileName().indexOf(".")))) {
						sinleFile = true;
						xls = cfg.getFileName();
					}
				} else if (fileName != null && !fileName.isEmpty() && index != -1) {// 单表
					if (fileName.equals(cfg.getFileName().substring(0, cfg.getFileName().indexOf(".")))) {
						singleFileSheet = true;
						xls = cfg.getFileName();
					}
				}
				if ((!all && !singleFileSheet && !sinleFile) || xls == null) {
					continue;
				}
				System.out.println(String.format("reloading %s", xls));
				String xlsPath = resourceFolder + File.separator + xls;
				if (isDebug) {
					is = new FileInputStream(xlsPath);
				} else {
					is = new XorDecryptedInputStream(xlsPath);
				}
				Workbook wb = TemplateFileParser.getWorkBook(xlsPath, is);
				if (all || sinleFile) {
					this.getTemplateParser(cfg).parseXlsFile(cfg.getClasses(), templateObjects, wb);
					loaded = true;
				} else if (singleFileSheet) {
					this.getTemplateParser(cfg).parseXlsFile(wb, index, cfg.getClasses()[index], templateObjects);
					loaded = true;
				}
			}
		}
		if (loaded) {
			patchUpAndCheck();
			fireReload();
			return true;
		}
		return false;
	}

	private void fireReload() {
		for (TemplateObserver observer : observers) {
			observer.templateChanged();
		}
	}

	/**
	 * 进行合法性校验，并构建模板间对象依赖关系
	 */
	private void patchUpAndCheck() throws Exception {
		Collection<Map<Integer, TemplateObject>> tplObjMaps = templateObjects.values();
		for (Map<Integer, TemplateObject> tplObjMap : tplObjMaps) {
			Collection<TemplateObject> templates = tplObjMap.values();
			for (TemplateObject templateObject : templates) {
				templateObject.patchUp();
			}
		}
		for (Map<Integer, TemplateObject> tplObjMap : tplObjMaps) {
			Collection<TemplateObject> templates = tplObjMap.values();
			Set<Object> checkSet = new HashSet<Object>();
			for (TemplateObject templateObject : templates) {
				templateObject.check();
				templateObject.checkUnique(checkSet);
			}
			// 检查完毕后销毁此对象
			checkSet = null;
		}

	}

	private boolean isTemplateLevelRange(Class<?> clazz) {
		for (Class<?> interf : clazz.getInterfaces()) {
			if (interf == TemplateLevelRange.class) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据配置取得解析器
	 * 
	 * @param cfg
	 * @return
	 */
	private TemplateFileParser getTemplateParser(TemplateConfig cfg) {
		if (cfg.getParserClassName() != null && cfg.getParserClassName().length() > 0) {
			// 使用指定的解析器
			try {
				Class<?> clazz = Class.forName(cfg.getParserClassName());
				Constructor<?> constructor = clazz.getConstructor();
				return (TemplateFileParser) constructor.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			// 默认的解析器
			return objectsParser;
		}
	}

	@Override
	public <T extends TemplateObject> T getSingle(Class<T> clazz) {
		Assert.isTrue(clazz.isAnnotationPresent(SingleRow.class), "使用getSingle获取的模板实例所属的类必须包含@SingleRow注解");
		return getAll(clazz).values().iterator().next();
	}

}
