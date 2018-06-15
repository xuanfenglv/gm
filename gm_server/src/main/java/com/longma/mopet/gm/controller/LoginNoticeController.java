package com.longma.mopet.gm.controller;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.dao.gm.notice.LoginNoticeDao;
import com.longma.mopet.gm.model.notice.SimpleLoginNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:58 2018/5/21
 * @Modified By:
 */
@RestController
public class LoginNoticeController {
    @Autowired
    private LoginNoticeDao dao;
    @RequestMapping("loginNotice")
    public String get(String ids) {
        System.out.println(ids);
        RecordSearchS2B send = new RecordSearchS2B();
        List<Integer> noticeIds = null;
        try {
            noticeIds = JSON.parseArray(ids,Integer.class);
        } catch (Exception e) {
            send.setErrorReturn(3,"ids 不是json数组");
            return JSON.toJSONString(send);
        }

        if (noticeIds == null) {
            send.setErrorReturn(3,"ids 为空");
            return JSON.toJSONString(send);
        }
        List list = new ArrayList();
        for (int id : noticeIds) {
            SimpleLoginNotice notice = dao.selectSimpleById(id);
            if (notice != null) {
                list.add(notice);
            }
        }
        send.setRecordList(list);
        System.out.println(JSON.toJSONString(send));
        return JSON.toJSONString(send);
    }
}
