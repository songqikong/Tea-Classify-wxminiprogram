package com.kanshan.tea_classify.mapper;

import com.kanshan.tea_classify.entity.FeedBack;
import com.kanshan.tea_classify.entity.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedBackMapper {
    @Insert("insert into feedback(openid,img_src,label,permission) values (#{openid},#{img_src},#{label},#{permission})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(FeedBack feedBack);

    @Select("select * from feedback where openid=#{openid} and permission=0")
    List<FeedBack> findByOpenId(String openid);

    @Update("update feedback set permission=#{permission} where id=#{id}")
    void updatePermission(int id, int permission);
}
