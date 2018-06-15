package com.longma.mopet.gm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.OperationRecord;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.config.GmConstant;
import com.longma.mopet.gm.config.GmConstant.ResponseState;
import com.longma.mopet.gm.config.OperationConfig;
import com.longma.mopet.gm.config.bean.Operation;
import com.longma.mopet.gm.dao.gm.OperationRecordDao;
import com.longma.mopet.gm.gateway.MainHandler;
import com.longma.mopet.gm.manager.HandlerManager;
import com.longma.mopet.gm.manager.SessionManager;
import com.longma.mopet.gm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:06 2018/4/18
 * @Modified By:
 */
@RestController
public class GateWayController {
	@Autowired
	private MainHandler mainHandler;
	@Autowired
	private HandlerManager handlerManager;
	@Autowired
	private OperationRecordDao operationRecordDao;
	/**
	 * @Description: 业务的入口，对请求进行各种校验（消息有效性，权限），在客户端正常的情况下帮助检查服务器配置是否正确
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("service")
	public String execute(HttpServletRequest request, HttpSession session, String msg) {
		long beginTime = System.currentTimeMillis();
//
		JSONObject jsonObject = JSON.parseObject(msg);
		S2BMessage result = new S2BMessage();

		int msgId = CommonUtil.parseInt(jsonObject.getInteger("msgId"), GmConstant.INVALID_MSG_ID);
		if (msgId == GmConstant.INVALID_MSG_ID) {
			result.setErrorReturn(ResponseState.PARAM_INVALID.stateCode,"没有消息号");
			return JSON.toJSONString(result);
		}

		// 权限校验
		Operation operation = OperationConfig.getOperation(msgId);
		if (operation == null) {
			result.setErrorReturn(ResponseState.SERVER_ERR.stateCode,"无此消息号对应的权限配置");
			return JSON.toJSONString(result);
		}
		int needPower = operation.getPower();
		String operator = getOperatorName(session);
		int userPower = getUserPower(operator);
		if (userPower < needPower) {
			result.setResponseState(ResponseState.NO_PERMISSION);
			return JSON.toJSONString(result);
		}
		if (handlerManager.getHttpHandler(msgId) == null) {
			result.setErrorReturn(ResponseState.SERVER_ERR.stateCode,"无此消息号对应的处理器");
			return JSON.toJSONString(result);
		}

		jsonObject.put("operator", operator);
		System.out.println("收到请求：" + msg);
		S2BMessage processResult = mainHandler.process(jsonObject);
		String response = JSON.toJSONString(processResult);

		// 存日志
		if (operation.isNeedLog()) {
			// 存日志
			operationRecordDao.insert(new OperationRecord(msgId,operation.getName(),operator,beginTime,processResult.getRet(),msg,response));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("发出响应：" + response+",用时："+(endTime-beginTime)+"ms");
		return response;
	}

	public String getOperatorName(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getName();
	}

	public int getUserPower(String name) {
		User user = SessionManager.getUser(name);
		int userPower = user.getPower();
		return userPower;
	}
}
