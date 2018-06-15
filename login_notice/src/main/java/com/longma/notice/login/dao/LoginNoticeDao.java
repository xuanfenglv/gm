package com.longma.notice.login.dao;

import com.longma.notice.login.model.SimpleLoginNotice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:51 2018/5/18
 * @Modified By:
 */
@Component
public interface LoginNoticeDao{

    @Select("select id,title,imgs,content from t_notice_login where id = #{id}")
    public SimpleLoginNotice selectSimpleById(@Param("id") int id);

}
