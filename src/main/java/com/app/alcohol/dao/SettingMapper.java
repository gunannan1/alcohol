package com.app.alcohol.dao;

import com.app.alcohol.entity.Setting;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SettingMapper extends BaseMapper<Setting> {
}
