package com.longma.mopet.gm.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.config.GmConstant;
import com.longma.mopet.gm.manager.HandlerManager;
import com.longma.mopet.gm.util.CommonUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:36 2018/4/19
 * @Modified By:
 */
@Component
public class MainHandler {
	@Autowired
	private HandlerManager handlerManager;

	public S2BMessage process(JSONObject jsonObject) {
		S2BMessage s2BMessage = null;
		// gateway处已经校验过了，可以放心使用（不必担心空指针）
		int msgId = CommonUtil.parseInt(jsonObject.getString(GmConstant.PARAM_KEY_MSG_ID), -1);
		// 加载对应的处理器
		IMsgHandler msgHandler = handlerManager.getHttpHandler(msgId);
		// 处理
		s2BMessage = msgHandler.process(jsonObject);
		return s2BMessage;
	}

}
