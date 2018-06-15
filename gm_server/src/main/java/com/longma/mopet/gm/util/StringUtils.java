package com.longma.mopet.gm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StringUtils {
	public static String trim(String str) {
		if (str == null) {
			str = "";
		} else {
			str = str.trim();
		}
		if (str.length() == 0) {
			return str;
		}

		if (str.charAt(0) == '"') {
			str = str.substring(1);
		}

		if (str.charAt(str.length() - 1) == '"') {
			str = str.substring(0, str.length() - 1);
		}

		return str;
	}

	public static String[] getStringList(String str) {
		str = trim(str);
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		String sep = ",";
		if (str.indexOf(':') >= 0) {
			sep = ":";
		}
		return str.split(sep);
	}

	public static String[] getStringList(String str, String sep) {
		str = trim(str);
		return str.split(sep);
	}

	public static int[] getIntArray(String str, String sep) {
		String[] prop = getStringList(str, sep);
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < prop.length; i++) {
			try {
				int r = Integer.parseInt(prop[i]);
				tmp.add(r);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		int[] ints = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			ints[i] = tmp.get(i);
		}
		return ints;
	}

	/**
	 * 将字符串通过逗号分割成int数组。支持中英文逗号混合(主要是策划填表容易写错)
	 * @param str
	 * @return
	 */
	public static int[] getIntArrayByComma(String str) {
		String[] cell = getStringList(str, ",");
		List<Integer> temp = new ArrayList<Integer>();
		for (String s : cell) {
			try {
				int r = Integer.parseInt(s);
				temp.add(r);
			} catch (Exception e) {
				String[] tempCell = getStringList(s, "，");
				for (String s2 : tempCell) {
					try {
						int r = Integer.parseInt(s2);
						temp.add(r);
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			}
		}
		int[] ints = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			ints[i] = temp.get(i);
		}
		return ints;
	}

	public static float[] getFloatArray(String str, String sep) {
		String[] prop = getStringList(str, sep);
		List<Float> tmp = new ArrayList<Float>();
		for (int i = 0; i < prop.length; i++) {
			try {
				float r = Float.parseFloat(prop[i]);
				tmp.add(r);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		float[] ints = new float[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			ints[i] = tmp.get(i);
		}
		return ints;
	}

	public static List<Integer> getIntList(String str, String sep) {
		List<Integer> tmp = new ArrayList<Integer>();
		if (str == null || str.trim().equals("")) {
			return tmp;
		}
		String[] prop = getStringList(str, sep);
		for (int i = 0; i < prop.length; i++) {
			try {
				int r = Integer.parseInt(prop[i]);
				tmp.add(r);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tmp;
	}

	public static String join(Object[] strs, String sep) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			buffer.append(sep).append(strs[i]);
		}
		return buffer.toString();
	}

	public static double[] getDoubleList(String str) {
		String[] prop = getStringList(str);
		double[] ds = new double[prop.length];
		for (int i = 0; i < ds.length; i++) {
			ds[i] = Double.parseDouble(prop[i]);
		}
		return ds;
	}

	public static List<String> getListBySplit(String str, String split) {
		List<String> list = new ArrayList<String>();
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
		String[] strs = str.split(split);
		for (String temp : strs) {
			if (temp != null && !temp.trim().equalsIgnoreCase("")) {
				list.add(temp.trim());
			}
		}
		return list;
	}

	public static int[] getIntList(String str) {
		String[] prop = getStringList(str);
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < prop.length; i++) {
			try {
				String sInt = prop[i].trim();
				if (sInt.length() < 20) {
					int r = Integer.parseInt(prop[i].trim());
					tmp.add(r);
				}
			} catch (Exception e) {
			}
		}
		int[] ints = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			ints[i] = tmp.get(i);
		}
		return ints;

	}

	public static String toWrapString(Object obj, String content) {
		if (obj == null) {
			return "null";
		} else {
			return obj.getClass().getName() + "@" + obj.hashCode() + "[\r\n" + content + "\r\n]";
		}
	}

	// 将1,2,3和{1,2,3}格式的字符串转化为JDK的bitset
	// 考虑了两边是否有{}，数字两边是否有空格，是否合法数字
	public static BitSet bitSetFromString(String str) {
		if (str == null) {
			return new BitSet();
		}
		if (str.startsWith("{")) {
			str = str.substring(1);
		}
		if (str.endsWith("}")) {
			str = str.substring(0, str.length() - 1);
		}
		int[] ints = getIntList(str);
		BitSet bs = new BitSet();
		for (int i : ints) {
			bs.set(i);
		}
		return bs;
	}

	public static boolean hasExcludeChar(String str) {
		if (str != null) {
			char[] chs = str.toCharArray();
			for (int i = 0; i < chs.length; i++) {

				if (Character.getType(chs[i]) == Character.PRIVATE_USE) {

					return true;
				}

			}
		}
		return false;
	}

	public static String replaceSql(String str) {
		if (str != null) {
			return str.replaceAll("'", "’").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
		}
		return "";
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param s1
	 * @param s2
	 * @return true,字符串相等;false,字符串不相等
	 */
	public static boolean isEquals(String s1, String s2) {
		if (s1 != null) {
			return s1.equals(s2);
		}
		if (s2 != null) {
			return false;
		}
		// 两个字符串都是null
		return true;
	}

	/**
	 * 将obj转变为String表示
	 * 
	 * @param obj
	 * @param excludes
	 * @return
	 */
	public static String obj2String(Object obj, Map<String, Boolean> excludes) {
		BaseReflectionToStringBuilder _builder = new BaseReflectionToStringBuilder(obj, ToStringStyle.SHORT_PREFIX_STYLE, excludes);
		return _builder.toString();
	}

	/**
	 * 重载ReflectionToStringBuilder,用于将BaseMessage用字符串表示,但是不处理buf字段
	 * 
	 * @author <a href="mailto:dongyong.wang@opi-corp.com">wang dong yong<a>
	 * 
	 */
	private static class BaseReflectionToStringBuilder extends ReflectionToStringBuilder {
		private final Map<String, Boolean> excludes;

		public BaseReflectionToStringBuilder(Object object, ToStringStyle style, Map<String, Boolean> excludes) {
			super(object, style);
			this.excludes = excludes;
		}

		@Override
		protected boolean accept(Field field) {
			boolean _accepted = true;
			if (this.excludes != null) {
				_accepted = this.excludes.get(field.getName()) == null;
			}
			return super.accept(field) && _accepted;
		}
	}

	/**
	 * 判断字符串是否时数字
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isDigit(String text) {
		String reg = "[-]*[\\d]+[\\.\\d+]*";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(text);
		return mat.matches();
	}

	/**
	 * 判断一句话是否是汉语
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isChiness(String text) {
		String reg = "[\\w]*[\\u4e00-\\u9fa5]+[\\w]*";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(text);
		boolean result = mat.matches();
		return result;
	}

	/**
	 * 判断单个字符是否是汉语
	 * 
	 * @param cha
	 * @return
	 */
	public static boolean isChineseChar(char cha) {
		String reg = "[\\u4e00-\\u9fa5]";
		Pattern pat = Pattern.compile(reg);
		String text = Character.toString(cha);
		Matcher mat = pat.matcher(text);
		boolean result = mat.matches();
		return result;
	}

	/**
	 * 判断字符是否是字母(包括大小写)或者数字
	 * 
	 * @param cha
	 * @return
	 */
	public static boolean isLetterAndDigit(String cha) {
		String reg = "[\\w]+";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(cha);
		boolean result = mat.matches();
		return result;
	}

	/**
	 * 返回字符串中汉字的数量
	 * 
	 * @param test
	 * @return
	 */
	public static int getChineseCount(String test) {
		int count = 0;
		boolean tempResult = false;
		for (int i = 0; i < test.length(); i++) {
			char cha = test.charAt(i);
			tempResult = isChineseChar(cha);
			if (tempResult) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 返回字符串中字母和数字的个数，其中字母包括大小写
	 * 
	 * @param text
	 * @return
	 */
	public static int getLetterAndDigitCount(String text) {
		int count = 0;
		boolean tempResult = false;
		for (int i = 0; i < text.length(); i++) {
			tempResult = isLetterAndDigit(text);
			if (tempResult) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return true,字符串是空的;false,字符串不是空的
	 */
	public static boolean isEmpty(String str) {
		if (str == null || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 将字符串首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String upperCaseFirstCharOnly(String s) {
		if (s == null || s.length() < 1) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	/**
	 * 将字符串按小括号()拆分成数组
	 * 
	 * @param src
	 * @return
	 */
	public static String[] bracketToArray(String src) {
		if (StringUtils.isEmpty(src)) {
			throw new IllegalArgumentException("source string is null or empty");
		}
		List<String> strList = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(\\()(.*?)(\\))");
		Matcher matcher = pattern.matcher(src);
		while (matcher.find()) {
			strList.add(matcher.group().replaceAll("\\(|\\)", ""));
		}
		if (strList.size() == 0) {
			throw new IllegalArgumentException("source string's format is not suitable");
		}
		return strList.toArray(new String[strList.size()]);
	}

	/**
	 * 合并两个数组
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static String[] mergeArray(String[] arr1, String[] arr2) {
		int len = arr1.length + arr2.length;
		String[] arr = new String[len];
		int i = 0;
		for (String str : arr1) {
			arr[i] = str;
			i++;
		}

		for (String str : arr2) {
			arr[i] = str;
			i++;
		}

		return arr;
	}

	/**
	 * 分割字符串。不同于{@link String#split(String)}，本方法不用正则匹配。
	 * 
	 * @param str 要分割的字符串
	 * @param delimiter 分隔符
	 * @return 分割后的字符串数组
	 */
	public static String[] split(String str, String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}
		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(str.substring(i, i + 1));
			}
		} else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return result.toArray(new String[result.size()]);
	}

	private static final String DEFAULT_COMPRESS_CHARSET = "UTF-8";

	/**
	 * 压缩字符串
	 * @param str 要压缩的字符串
	 * @param charSet 编码
	 * @return
	 */
	public static byte[] compressStr(String str, String charSet) {
		if (null == str || str.isEmpty()) {
			return null;
		}
		GZIPOutputStream gzip = null;
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(charSet));
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				gzip.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 使用{@link #DEFAULT_COMPRESS_CHARSET}压缩字符串
	 * @param str
	 * @return
	 */
	public static byte[] compressStr(String str) {
		return compressStr(str, DEFAULT_COMPRESS_CHARSET);
	}

	/**
	 * 解压缩字符串
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] unCompress(byte[] data, String charSet) {
		if (null == data || data.length < 1) {
			return data;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n = 0;
			while ((n = gzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用{@link #DEFAULT_COMPRESS_CHARSET}解压缩字符串
	 * @param str
	 * @return
	 */
	public static byte[] unCompress(byte[] data) {
		return unCompress(data, DEFAULT_COMPRESS_CHARSET);
	}

	public static String parseString(byte[] data) throws UnsupportedEncodingException {
		return new String(unCompress(data), DEFAULT_COMPRESS_CHARSET);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "1，2，3，4，5,5,34，23，5，6，7,8,9，0,3,3,5，6，7，2,2，4，5,6,7,7,6,7,8，0，9";
		int[] a1 = StringUtils.getIntArray(str, ",");
		int[] a2 = StringUtils.getIntArrayByComma(str);
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
	}

	/**
	 * 将字符串转换为数字，如果出错则返回默认值
	 * @param str
	 * @param defaultValue 转换出错默认值
	 * @return
	 */
	public static int parseInt(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

}
