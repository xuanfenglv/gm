package com.longma.mopet.gm.controller;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.dao.gm.SuspendRecordDao;
import com.longma.mopet.gm.dao.gm.UserDao;
import com.longma.mopet.gm.gateway.user.b2s.LoginB2S;
import com.longma.mopet.gm.manager.SessionManager;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:07 2018/4/18
 * @Modified By:
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SuspendRecordDao suspendRecordDao;

	@RequestMapping("login")
	public String login(HttpSession session, LoginB2S login, String callback) {
		CheckParamResult checkParmResult = login.checkParams();
		S2BMessage result = new S2BMessage();
		if (!checkParmResult.isValid()) {
			result.setErrorReturn(3, checkParmResult.getErrmsg());
			return JSON.toJSONString(result);
		}
		// 真正的登录检查
		User user = userDao.selectByName(login.getName());
		login.setPwd(MD5.md5Digest(login.getPwd()));
		if (user == null) {
			result.setErrorReturn(7, "此用户不存在");
			return JSON.toJSONString(result);
		} else if (!login.getPwd().equals(user.getPwd())) {
			result.setErrorReturn(7, "用户名或密码错误");
			return JSON.toJSONString(result);
		} else if (user.getDeadline() > System.currentTimeMillis()) {
			// 从数据库查询封号原因
			String fengReason = suspendRecordDao.getLatestReasonByName(login.getName());
			result.setErrorReturn(6, fengReason);
			return JSON.toJSONString(result);
		}
		user.setPwd("xxx");
		SessionManager.put(user.getName(), user);
		session.setAttribute("user", user);
		System.out.println(login.getName() + "登录成功,sessionId:" + session.getId());
		return JSON.toJSONString(result);
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, String name, String callback) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			SessionManager.remove("name");
			session.removeAttribute("user");
		}
		S2BMessage result = new S2BMessage();
		return JSON.toJSONString(result);
	}

}
