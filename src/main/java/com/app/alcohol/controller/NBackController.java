package com.app.alcohol.controller;


import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.NBackService;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nback/")
public class NBackController {
    @Autowired
    NBackService nBackService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseVO save(@RequestBody NBackRecordVO nBackRecordVO){
        boolean isSuccess= nBackService.save(nBackRecordVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        else {
            throw new GlobalException(ResultEnum.Error);
        }
    }

    @RequestMapping(value = "request",method = RequestMethod.POST)
    public ResponseVO request(@RequestBody NbackRequestVO nbackRequestVO){
        NBackResponseVO nBackResponseVO=nBackService.requestNBackInfo(nbackRequestVO);
        return ResponseVO.success(nBackResponseVO);
    }

    @RequestMapping(value = "getHistoryInfo",method = RequestMethod.GET)
    public ResponseVO getHistoryInfo(@RequestParam(name = "username")String username,@RequestParam(name = "level")int level, @RequestParam(name = "bound")int bound){
        NBackInfoVO nBackInfoVO=nBackService.getNbackHistoryInfo(username, bound, level);
        return ResponseVO.success(nBackInfoVO);
    }



}
