package com.longma.mopet.gm.config;

import com.longma.mopet.gm.util.SystemUtil;
import com.longma.mopet.gm.util.SystemUtil.SystemType;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

public class GmConstant {
	/**
	 * 前端发过来的参数key
	 */
	public static final String PARAM_KEY_MSG_ID = "msgId";
	/**
	 * 无效消息id
	 */
	public static final int INVALID_MSG_ID = -1;
	/**
	 * 服务器资源文件根路径
	 */
	public static final String CONFIG_ROOT_PATH;
	public static final Set<Short> QUERY_PAGE_SIZE_TYPE;
	/**
	 * 分页数据大小
	 */
	public static final int PAGE_SIZE = 20;

	static {
		QUERY_PAGE_SIZE_TYPE = new HashSet<Short>();
		QUERY_PAGE_SIZE_TYPE.add((short) 1);// 服务器状态

		String string = GmConstant.class.getClassLoader().getResource("config").toString();
		try {
			string = java.net.URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (SystemUtil.currentSystemType() == SystemType.window) {
			string = string.substring(5);
		} else if (SystemUtil.currentSystemType() == SystemType.linux) {
			string = string.substring(4);
		}
		CONFIG_ROOT_PATH = string;
	}

	public static boolean con(short type) {
		return QUERY_PAGE_SIZE_TYPE.contains(type);
	}

	public static enum ResponseState {
		OK(1, "正确"), LOGIN_EXPIRE(2, "未登录或登录过期"), PARAM_INVALID(3, "参数不正确"), NO_PERMISSION(4, "权限不足"), NEED_NOTE(5, "需要备注"), BAN(6,
				"已经被封号"), LOGIN_FAILD(7, "登录失败"), SERVER_ERR(8, "服务器故障"), OTHER_ERR(9, "未知错误");
		private ResponseState(int code, String tip) {
			this.stateCode = code;
			this.tip = tip;
		}

		public final int stateCode;
		public final String tip;
	}
}
