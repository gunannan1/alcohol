package com.app.alcohol.dao;

import com.app.alcohol.entity.NBackRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface NBackRecordMapper extends BaseMapper<NBackRecord> {
    List<NBackRecord> selectLatestNbackRecord(@Param("username") String username, @Param("level") int level,
                                         @Param("bound") int bound);

}
