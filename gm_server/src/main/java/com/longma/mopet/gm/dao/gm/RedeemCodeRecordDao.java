package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.model.RedeemCodeRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:00 2018/5/3
 * @Modified By:
 */
@Component
public interface RedeemCodeRecordDao extends RecordDao {
    @Select("insert t_redeem_code_record(codeId,num,`name`,`desc`,beginTime,endTime,servers,channels,times,content,pause) value(#{codeId},#{num},#{name},#{desc},#{beginTime},#{endTime},#{servers},#{channels},#{times},#{content},#{pause})")
    public void insert(RedeemCodeRecord record);

    @Select("select * from t_redeem_code_record where 1=1 ${_condition} ${_limit}")
    public List<RedeemCodeRecord> selectListByConditionAndLimit(@Param("_condition") String _condition, @Param("_limit") String _limit);

    @Select("select count(1) from t_redeem_code_record where 1=1 ${_condition}")
    public int selectTotalByCondition(@Param("_condition") String _condition);

    @Update("update t_redeem_code_record set `name`=#{name},`desc`=#{desc},beginTime=#{beginTime},endTime=#{endTime},servers=#{servers},channels=#{channels},times=#{times},content=#{content},pause=#{pause} where codeId = #{codeId}")
    public int update(RedeemCodeRecord record);

    @Update("update t_redeem_code_record set pause = 1 where codeId = #{codeId}")
    public int pause(@Param("codeId") long codeId);

    @Update("update t_redeem_code_record set pause = 0 where codeId = #{codeId}")
    public int start(@Param("codeId") long codeId);

    @Delete("delete from t_redeem_code_record where codeId = #{codeId}")
    public int delete(@Param("codeId") long codeId);

}
