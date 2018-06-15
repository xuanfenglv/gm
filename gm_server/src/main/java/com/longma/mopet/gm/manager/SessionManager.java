package com.longma.mopet.gm.manager;

import com.longma.mopet.gm.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 19:03 2018/4/18
 * @Modified By:
 */
public class SessionManager {
    private static final Map<String,User> SESSION_MAP = new ConcurrentHashMap<String,User>();

    public static boolean existUser(String username) {
        if(SESSION_MAP.containsKey(username)) {
            return true;
        }
        return false;
    }

    public static User getUser(String username) {
        return SESSION_MAP.get(username);
    }
    public static void put(String username, User user) {
        SESSION_MAP.put(username, user);
    }
    public static void remove(String username) {
        SESSION_MAP.remove(username);
    }
}
