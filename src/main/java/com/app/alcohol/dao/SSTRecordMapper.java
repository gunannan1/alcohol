package com.app.alcohol.dao;

import com.app.alcohol.entity.SSTRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SSTRecordMapper extends BaseMapper<SSTRecord> {

}
