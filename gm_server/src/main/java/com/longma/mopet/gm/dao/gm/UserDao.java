package com.longma.mopet.gm.dao.gm;

import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.SelectUserListB2S;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:07 2018/4/19
 * @Modified By:
 */
@Component
public interface UserDao {

    @Select("select count(1) from t_user where name=#{name}")
    public int existName(String name);

    @Select("select * from t_user where name=#{name}")
    public User selectByName(String name);

    @Select("select name,power,deadline from t_user where name like '%${name}%' and power between #{minPower} and #{maxPower}")
    public List<User> selectUserList(SelectUserListB2S receive);

    @Insert("insert into t_user(name,pwd,power,deadline) values(#{name},#{pwd},#{power},#{deadline})")
    public int createUser(User user);

    @Delete("delete from t_user where name=#{name}")
    public int deleteUser(String name);

    @Update("update t_user set pwd=#{pwd} where name=#{name}")
    public int updatePwd(@Param("name") String name, @Param("pwd") String pwd);

    @Update("update t_user set power=#{power} where name=#{name}")
    public int updatePower(@Param("name") String name, @Param("power") int power);

    @Update("update t_user set deadline=#{deadline} where name=#{name}")
    public int updateDeadline(@Param("name") String name, @Param("deadline")long deadline);

}
