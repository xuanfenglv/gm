package com.longma.mopet.gm.controller;

import com.longma.mopet.gm.rabbitmq.component.NoticeProperties;
import com.longma.mopet.gm.rabbitmq.init.RmqInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:52 2018/5/14
 * @Modified By:
 */
@RestController
@RequestMapping("init")
public class InitController {

    @Autowired
    private NoticeProperties noticeProperties;
    @Autowired
    private RmqInit rmqInit;

    @RequestMapping("notice")
    public boolean notice() {
        boolean result = true;
        try {
            rmqInit.notice();
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

}
