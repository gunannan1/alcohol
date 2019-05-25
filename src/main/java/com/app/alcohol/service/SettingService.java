package com.app.alcohol.service;

import com.app.alcohol.dao.SettingMapper;
import com.app.alcohol.entity.Setting;
import com.app.alcohol.vo.SettingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
    @Autowired
    SettingMapper settingMapper;

    public boolean update(SettingVO settingVO){
        Setting setting=new Setting();
        //Setting map manages to total setting of the app, it only has one record
        setting.setId(1);
        setting.setShowResult(settingVO.getShowResult());
        Integer insert = settingMapper.updateById(setting);
        return insert>0;
    }

    public SettingVO getStatus(){
        Setting setting=settingMapper.selectById(1);
        SettingVO settingVO=new SettingVO();
        settingVO.setShowResult(setting.getShowResult());
        return settingVO;

    }
}
