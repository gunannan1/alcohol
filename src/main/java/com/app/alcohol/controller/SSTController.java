package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.SSTRecordService;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.SSTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sst/")
public class SSTController {

    @Autowired
    SSTRecordService sstRecordService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseVO save(@RequestBody SSTRecordVO sstRecordVO){
       boolean isSuccess= sstRecordService.save(sstRecordVO);
       if(isSuccess){
           return ResponseVO.success(ResultEnum.SUCCESS);
       }
       else {
           throw new GlobalException(ResultEnum.Error);
       }
    }

    @RequestMapping(value = "save2",method = RequestMethod.POST)
    public ResponseVO save2(SSTRecordVO sstRecordVO){
        boolean isSuccess= sstRecordService.save(sstRecordVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        else {
            throw new GlobalException(ResultEnum.Error);
        }
    }


}
