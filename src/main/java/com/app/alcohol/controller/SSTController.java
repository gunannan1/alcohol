package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.SSTRecordService;
import com.app.alcohol.vo.NBackInfoVO;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.SSTInfoVO;
import com.app.alcohol.vo.SSTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "getHistoryInfo",method = RequestMethod.GET)
    public ResponseVO getHistoryInfo(@RequestParam(name = "username")String username, @RequestParam(name = "bound")int bound){
        List<SSTInfoVO> list=sstRecordService.getSSTHistoryInfo(username, bound);
        return ResponseVO.success(list);
    }


}
