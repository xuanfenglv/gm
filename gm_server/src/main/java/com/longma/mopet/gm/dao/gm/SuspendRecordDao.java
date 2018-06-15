package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.model.SuspendRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:01 2018/4/19
 * @Modified By:
 */
@Component
public interface SuspendRecordDao {
    // 查询某人最近的封号记录
    @Select("Select * from t_suspend_record where name=#{name} order by fengtime desc limit 1")
    public SuspendRecord getLatestRecordByName(String name);

    // 查询某人最近的封号原因
    @Select("Select reason from t_suspend_record where name=#{name} order by fengtime desc limit 1")
    public String getLatestReasonByName(String name);

}
