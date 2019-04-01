package com.app.alcohol.dao;

import com.app.alcohol.entity.Researcher;
import com.app.alcohol.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ResearcherMapper extends BaseMapper<Researcher> {
}
