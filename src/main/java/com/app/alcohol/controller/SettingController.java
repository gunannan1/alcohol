package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.service.SettingService;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.SettingVO;
import com.app.alcohol.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Application setting controleer
 */
@RequestMapping("/setting")
@RestController
public class SettingController {
    @Autowired
    SettingService settingService;

    /**
     * change setting of application
     * @param settingVO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@RequestBody SettingVO settingVO) {
        boolean isSuccess = settingService.update(settingVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);
    }

    /**
     * get application's setting infomation
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getStatus() {
        SettingVO settingVO = settingService.getStatus();
        return ResponseVO.success(settingVO);
    }


}
