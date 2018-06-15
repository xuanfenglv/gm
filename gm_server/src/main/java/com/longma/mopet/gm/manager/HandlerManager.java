package com.longma.mopet.gm.manager;

import com.longma.mopet.gm.base.process.base.IMsgHandler;

import java.util.Map;

/**
 * @Author:Lvxingqing
 * @Description: 主处理器
 * @Date:Create in 10:34 2018/4/19
 * @Modified By:
 */

public class HandlerManager {
    // 消息列表
    private Map<Integer, IMsgHandler> httpHandlerMapping;

    public IMsgHandler getHttpHandler(int msgId) {
        return httpHandlerMapping.get(msgId);
    }

    public void setHttpHandlerMapping(Map<Integer, IMsgHandler> httpHandlerMapping) {
        this.httpHandlerMapping = httpHandlerMapping;
    }
}
