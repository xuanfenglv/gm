package com.longma.notice.login.message;


/**
 * @Author:Lvxingqing
 * @Description: 服务器发往浏览器的消息
 * @Date:Create in 18:30 2018/4/18
 * @Modified By:
 */
public class S2BMessage {
	/**
	 * 1 = 正确
	 * 2 = 未登录或登录过期
	 * 3 = 参数不正确
	 * 4 = 权限不足
	 * 5 = 需要备注
	 * 6 = 已被封号
	 * 7 = 登录失败
	 * 8 = 服务器故障
	 * 9 = 其他错误
	 */
	private int ret = 1;
	private String errmsg;

	public void setErrorReturn(int ret, String errmsg) {
		this.ret = ret;
		this.errmsg = errmsg;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
