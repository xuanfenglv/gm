package com.longma.mopet.gm.dao.gm.notice;

import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.model.notice.PushNotice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:37 2018/5/18
 * @Modified By:
 */
@Component
public interface PushNoticeDao extends NoticeDao {
    @Insert("insert into t_notice_push(beginTime,servers,channels,content,pause) values(#{beginTime},#{servers},#{channels},#{content},#{pause})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(PushNotice _notice);

    @Select("select count(1) from t_notice_push where 1=1 ${_condition}")
    public int selectTotalByCondition(@Param("_condition") String _condition);

    @Select("select * from t_notice_push where 1=1 ${_condition} ${_limit}")
    public List<PushNotice> selectListByConditionAndLimit(@Param("_condition") String _condition, @Param("_limit") String _limit);

    @Override
    @Select("select * from t_notice_push where id = #{id}")
    public PushNotice selectById(@Param("id") int id);

    @Update("update t_notice_push set beginTime=#{beginTime},servers=#{servers},channels=#{channels},content=#{content},pause=#{pause} where id=#{id}")
    public int update(PushNotice _notice);

    @Update("update t_notice_push set pause=1 where id=#{_id}")
    public int pause(@Param("_id") int id);

    @Update("update t_notice_push set pause=0 where id=#{_id}")
    public int start(@Param("_id") int id);

    @Delete("delete from t_notice_push where  id=#{_id}")
    public int del(@Param("_id") int id);
}
