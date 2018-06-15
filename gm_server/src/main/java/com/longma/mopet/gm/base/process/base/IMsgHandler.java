package com.longma.mopet.gm.base.process.base;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.result.CheckParamResult;

public abstract class IMsgHandler {
	public S2BMessage process(JSONObject parms) {
		B2SMessage message = null;
		// B2SMessage 为空时表示只有消息号的消息，不需要反序列化和校验参数
		if (getMessageType() != null) {
			try {
				message = parms.toJavaObject(getMessageType());
			} catch (JSONException e) {
				e.printStackTrace();
				S2BMessage s2BMessage = new S2BMessage();
				s2BMessage.setErrorReturn(3, "参数格式错误");
				return s2BMessage;
			}

			// 先校验参数
			CheckParamResult checkParmResult = message.checkParams();
			if (!checkParmResult.isValid()) {
				S2BMessage s2BMessage = new S2BMessage();
				s2BMessage.setErrorReturn(3, checkParmResult.getErrmsg());
				return s2BMessage;
			}
		}

		S2BMessage result = handle(message);
		return result;
	}

	// 需要具体业务实现的
	protected abstract S2BMessage handle(B2SMessage message);

	protected abstract Class<? extends B2SMessage> getMessageType();
}
