package com.app.alcohol.service;

import com.app.alcohol.dao.SettingMapper;
import com.app.alcohol.entity.Setting;
import com.app.alcohol.vo.SettingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * setting service
 */
@Service
public class SettingService {
    @Autowired
    SettingMapper settingMapper;

    /**
     * update setting
     * @param settingVO
     * @return
     */
    public boolean update(SettingVO settingVO){
        Setting setting=new Setting();
        //Setting map manages all settings of the app, it only has one record
        setting.setId(1);
        setting.setShowResult(settingVO.getShowResult());
        Integer insert = settingMapper.updateById(setting);
        return insert>0;
    }

    /**
     * get current setting
     * @return
     */
    public SettingVO getStatus(){
        Setting setting=settingMapper.selectById(1);
        SettingVO settingVO=new SettingVO();
        settingVO.setShowResult(setting.getShowResult());
        return settingVO;

    }
}
