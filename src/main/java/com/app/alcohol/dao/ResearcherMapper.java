package com.app.alcohol.dao;

import com.app.alcohol.entity.Researcher;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ResearcherMapper extends BaseMapper<Researcher> {
}
