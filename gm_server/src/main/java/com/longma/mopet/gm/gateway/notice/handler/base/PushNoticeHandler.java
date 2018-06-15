package com.longma.mopet.gm.gateway.notice.handler.base;

import com.longma.mopet.gm.constant.NoticeEnum;
import com.longma.mopet.gm.dao.gm.notice.PushNoticeDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:10 2018/5/14
 * @Modified By:
 */
public abstract class PushNoticeHandler extends NoticeHandler {
    @Autowired
    protected PushNoticeDao dao;

    protected final String exchange = NoticeEnum.PUSH.getExchange();

}
