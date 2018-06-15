package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.model.OperationRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:28 2018/4/25
 * @Modified By:
 */
@Component
public interface OperationRecordDao extends RecordDao{
    @Select("insert into t_operation_record(msgId,name,operator,time,ret,request,response) values(#{msgId},#{name},#{operator},#{time},#{ret},#{request},#{response})")
    public void insert(OperationRecord record);

    @Select("select * from t_operation_record where 1=1 ${_condition}  ${_limit}")
    public List<OperationRecord> selectListByConditionAndLimit(@Param("_condition") String _condition, @Param("_limit") String _limit);

    @Select("select count(1) from t_operation_record where 1=1 ${_condition}")
    public int selectTotalByCondition(@Param("_condition") String _condition);
}
