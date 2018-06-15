package com.longma.mopet.gm.gateway.user.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:26 2018/4/24
 * @Modified By:
 */
public class SelectUserListS2B extends S2BMessage{
    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
