package com.longma.mopet.gm.dao.gm.notice;

import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.model.notice.LoginNotice;
import com.longma.mopet.gm.model.notice.SimpleLoginNotice;
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
public interface LoginNoticeDao extends NoticeDao{
    @Insert("insert into t_notice_login(beginTime,endTime,servers,channels,title,imgs,content,pause) values(#{beginTime},#{endTime},#{servers},#{channels},#{title},#{imgs},#{content},#{pause})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(LoginNotice _notice);

    @Select("select count(1) from t_notice_login where 1=1 ${_condition}")
    public int selectTotalByCondition(@Param("_condition") String _condition);

    @Select("select * from t_notice_login where 1=1 ${_condition} ${_limit}")
    public List<LoginNotice> selectListByConditionAndLimit(@Param("_condition") String _condition, @Param("_limit") String _limit);

    @Override
    @Select("select * from t_notice_login where id = #{id}")
    public LoginNotice selectById(@Param("id") int id);

    @Select("select id,title,imgs,content from t_notice_login where id = #{id}")
    public SimpleLoginNotice selectSimpleById(@Param("id") int id);

    @Update("update t_notice_login set beginTime=#{beginTime},endTime=#{endTime},servers=#{servers},channels=#{channels},title=#{title},imgs=#{imgs},content=#{content},pause=#{pause} where id=#{id}")
    public int update(LoginNotice _notice);

    @Update("update t_notice_login set pause=1 where id=#{_id}")
    public int pause(@Param("_id") int id);

    @Update("update t_notice_login set pause=0 where id=#{_id}")
    public int start(@Param("_id") int id);

    @Delete("delete from t_notice_login where  id=#{_id}")
    public int del(@Param("_id") int id);
}
