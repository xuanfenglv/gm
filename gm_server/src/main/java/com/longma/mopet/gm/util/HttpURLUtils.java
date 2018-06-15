package com.longma.mopet.gm.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public final class HttpURLUtils {
	/**
	 * @Author: Lvxianqing
	 * @Description: 发送字符串接收响应的post请求
	 * @Date: 2018-05-23 13:36:10
	 * @param reqUrl 请求地址
	 * @param content 请求内容，可以是xml、json、参数（例：parm1=xxx&parm2=xxx）
	 */
	public static String doPost(String reqUrl, String content) {

		HttpURLConnection urlConn = null;
		try {
			urlConn = sendPost(reqUrl, content);
			String responseContent = getResponseBody(urlConn);
			return responseContent.trim();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
	}
	/**
	 * @Author: Lvxianqing
	 * @Description: 发送参数接收响应的post请求
	 * @Date: 2018-05-23 13:36:10
	 * @param reqUrl 请求地址
	 * @param parameters 参数（把map转化为parm1=xxx&parm2=xxx 发送请求）
	 */
	public static String doPost(String reqUrl, Map<String, String> parameters) {
		String content = generatorParamString(parameters);
		return doPost(reqUrl, content);
	}
	/**
	 * @Author: Lvxianqing
	 * @Description: 获取响应体
	 * @Date: 2018-05-23 13:47:07
	 * @param urlConn
	 */
	private static String getResponseBody(HttpURLConnection urlConn) {
		try {
			String responseContent = null;
			InputStream in = urlConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
			return responseContent;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	/**
	 * @Author: Lvxianqing
	 * @Description: 发送字符串的post请求
	 * @Date: 2018-05-23 13:38:57
	 * @param reqUrl
	 * @param content
	 */
	private static HttpURLConnection sendPost(String reqUrl,String content) {
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(50000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(50000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);
			byte[] b = content.getBytes();
			urlConn.getOutputStream().write(b);;
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}

	/**
	 * @Author: Lvxianqing
	 * @Description: 将parameters中数据转换成用"&"链接的http请求参数形式
	 * @Date: 2018-05-23 13:41:56
	 * @param parameters
	 */
	public static String generatorParamString(Map<String, String> parameters) {
		StringBuffer params = new StringBuffer();
		if (parameters != null) {
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String value = parameters.get(name);
				params.append(name + "=");
				try {
					// 决定是否对http的get请求参数编码
//					params.append(URLEncoder.encode(value, "UTF-8"));
					params.append(value);
				} 
//				catch (UnsupportedEncodingException e) {
//					throw new RuntimeException(e.getMessage(), e);
//				}
				catch (Exception e) {
					String message = String.format("'%s'='%s'", name, value);
					throw new RuntimeException(message, e);
				}
				if (iter.hasNext())
					params.append("&");
			}
		}
		return params.toString();
	}
	/**
	 * @Author: Lvxianqing
	 * @Description: 发送指定响应编码格式的get请求（发送前请自行把参数拼接在url后）
	 * @Date: 2018-05-23 13:42:40
	 * @param link
	 * @param charset
	 */
	public static String doGet(String link, String charset) {

		try {
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			// HttpURLConnection 获取输入输出流时会自动打开连接
			BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;) {
				out.write(buf, 0, i);
			}
			out.flush();
			String s = new String(out.toByteArray(), charset);
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * @Author: Lvxianqing
	 * @Description: 发送UTF-8响应格式的get请求（发送前请自行把参数拼接在url后）
	 * @Date: 2018-05-23 13:44:09
	 * @param link
	 */
	public static String doGet(String link) {
		return doGet(link, "UTF-8");
	}

	/**
	 * @Author: Lvxianqing
	 * @Description: 用于生成升序排列的请求参数字符串（支付回调常用的方法）
	 * @Date: 2018-05-23 13:45:42
	 * @param params
	 * @param sign
	 */
	public static String genParmString(Map<String, String[]> params,String sign) {

		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if (sign.equals(key))
            {
                continue;
            }
            String value = (String) params.get(key)[0];
            if (value != null)
            {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            }
            else
            {
                content.append((i == 0 ? "" : "&") + key + "=");
            }

        }
        return content.toString();
	}

}
