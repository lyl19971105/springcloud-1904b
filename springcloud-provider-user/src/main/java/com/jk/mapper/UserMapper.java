package com.jk.mapper;

import com.jk.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    Long queryUserCount();
    List<User> queryUser(@Param("page") int page, @Param("rows") Integer rows);
    User adUser(User user);
    @Delete("delete from t_user where id in (${ids})")
    void delUser(String ids);
    @Update("update  t_user set name=#{name},price=#{price} where id=#{id}")
    User toUser(Integer id);
    @Select("select * from t_user where id=#{id}")
    User toupUser(Integer id);

    User queryUserByHql(User user);
}
