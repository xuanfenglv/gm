package com.longma.mopet.gm.test;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.gateway.notice.b2s.AddTimingNoticeB2S;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:40 2018/4/24
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        String jstr ="{\"msgId\":401,\"repeatInterval\":\"20\",\"servers\":[1,2,3,4,5],\"channels\":[1,2,3,4,5],\"content\":\"test\",\"beginTime\":1525622400000,\"endTime\":1525881600000}";
        AddTimingNoticeB2S b2S = JSON.parseObject(jstr, AddTimingNoticeB2S.class);


    }
}
