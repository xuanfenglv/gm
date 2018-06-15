package com.longma.mopet.gm.dao.gm.notice;

import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.model.notice.TimingNotice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:31 2018/5/10
 * @Modified By:
 */
@Component
public interface TimingNoticeDao extends NoticeDao {

    @Insert("insert into t_notice_timing(beginTime,endTime,servers,channels,content,repeatInterval,pause) values(#{beginTime},#{endTime},#{servers},#{channels},#{content},#{repeatInterval},#{pause})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(TimingNotice _notice);

    @Select("select count(1) from t_notice_timing where 1=1 ${_condition}")
    public int selectTotalByCondition(@Param("_condition") String _condition);

    @Select("select * from t_notice_timing where 1=1 ${_condition} ${_limit}")
    public List<TimingNotice> selectListByConditionAndLimit(@Param("_condition") String _condition, @Param("_limit") String _limit);

    @Override
    @Select("select * from t_notice_timing where id = #{id}")
    public TimingNotice selectById(@Param("id") int id);

    @Update("update t_notice_timing set beginTime=#{beginTime},endTime=#{endTime},servers=#{servers},channels=#{channels},content=#{content},repeatInterval=#{repeatInterval},pause=#{pause} where id=#{id}")
    public int update(TimingNotice _notice);

    @Update("update t_notice_timing set pause=1 where id=#{_id}")
    public int pause(@Param("_id") int id);

    @Update("update t_notice_timing set pause=0 where id=#{_id}")
    public int start(@Param("_id") int id);

    @Delete("delete from t_notice_timing where  id=#{_id}")
    public int del(@Param("_id") int id);
}
