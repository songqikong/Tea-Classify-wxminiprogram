package com.kanshan.tea_classify.mapper;

import com.kanshan.tea_classify.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
//    新建用户
    @Insert("insert into user(openid,ava_src,name) values (#{openid},#{ava_src},#{name})")
    void insert(User user);
    @Select("select * from user where name=#{openid} and password=#{openid}")
    User select(User user);
//    通过id寻找用户
    @Select("select * from user where openid=#{arg0}")
    User findById(String openid);

}
