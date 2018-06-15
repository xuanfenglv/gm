package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.model.RedeemCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:30 2018/5/3
 * @Modified By:
 */
@Component
public interface RedeemCodeDao {
    @Select("select redeem_code from t_redeem_code where codeId = #{codeId}")
    public List<String> selectRedeemCodeByCodeId(@Param("codeId") long codeId);

    @Select("insert into t_redeem_code(redeem_code,codeId,lastTimes) values(#{redeem_code},#{codeId},#{lastTimes})")
    public void insert(RedeemCode redeemCode);

    @Delete("delete from t_redeem_code where codeId = #{codeId}")
    public void deleteRedeemCode(@Param("codeId") long codeId);
}
