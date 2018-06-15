package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.model.GMReward;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:39 2018/5/7
 * @Modified By:
 */
@Component
public interface GMRewardDao {
    @Insert("insert into t_gm_reward(rewardId,`name`,account,`reason`,`desc`,deadline,content,status,operator) values(#{rewardId},#{name},#{account},#{reason},#{desc},#{deadline},#{content},#{status},#{operator})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(GMReward gmReward);

    @Select("select * from t_gm_reward where rewardId = #{rewardId}")
    public GMReward selectRecordByRewardId(@Param("rewardId") long rewardId);

    @Select("select * from t_gm_reward where 1=1 ${_condition}")
    public List<GMReward> selectByCondition(@Param("_condition") String _condition);

    @Select("select count(1) from t_gm_reward where 1=1 ${_condition}")
    public int selectTotal(@Param("_condition") String _condition);
}
