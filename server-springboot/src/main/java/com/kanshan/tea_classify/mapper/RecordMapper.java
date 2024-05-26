package com.kanshan.tea_classify.mapper;

import com.kanshan.tea_classify.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    //   插入记录
    @Insert("insert into record(labels,openid,img_src) values (#{labels},#{openid},#{img_src})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Record record);

    @Select("select * from record where openid=#{openid}")
    List<Record> findByOpenId(String openid);
}
