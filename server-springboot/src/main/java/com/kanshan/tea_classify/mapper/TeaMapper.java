package com.kanshan.tea_classify.mapper;

import com.kanshan.tea_classify.entity.Tea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeaMapper {
    @Select("select * from tea where label=#{label}")
    Tea findByLabel(int label);
}
