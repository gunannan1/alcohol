package com.app.alcohol.controller;


import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.NBackService;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
