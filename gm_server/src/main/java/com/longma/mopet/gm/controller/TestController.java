package com.longma.mopet.gm.controller;

import com.longma.mopet.gm.dao.log.MoneyLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 12:54 2018/5/9
 * @Modified By:
 */
@RestController
public class TestController {
    @Autowired
    private MoneyLogDao moneyLogDao;

    @RequestMapping("test")
    public void test() {
        boolean r1 = moneyLogDao.existTable(1,"2017_04_27");
        boolean r2 = moneyLogDao.existTable(1,"2018_04_27");
    }
//    @RequestMapping("test2")
//    public String test2(int age,int num,String name,String addr) {
//        System.out.println(age);
//        System.out.println(num);
//        System.out.println(name);
//        System.out.println(addr);
//        return ""+age;
//    }
}
