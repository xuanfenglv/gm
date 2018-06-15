package com.longma.mopet.gm.util;

import com.longma.mopet.gm.manager.SessionManager;
import com.longma.mopet.gm.model.User;

import javax.servlet.http.HttpSession;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:56 2018/5/17
 * @Modified By:
 */
public class SessionUtil {
    public static String getOperatorName(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user.getName();
    }

    public static int getUserPower(String name) {
        User user = SessionManager.getUser(name);
        int userPower = user.getPower();
        return userPower;
    }
}
