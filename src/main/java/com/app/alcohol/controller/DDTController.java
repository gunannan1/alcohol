package com.app.alcohol.controller;


import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.DDTService;
import com.app.alcohol.vo.DDTRecordVO;
import com.app.alcohol.vo.ResponseVO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@RestController
@RequestMapping("/ddt/")
public class DDTController {

    @Autowired
    DDTService ddtService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseVO save(@RequestBody DDTRecordVO ddtRecordVO){


        boolean isSuccess= ddtService.save(ddtRecordVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        else {
            throw new GlobalException(ResultEnum.Error);
        }
    }

    @RequestMapping(value = "save2",method = RequestMethod.POST)
    public ResponseVO save2( DDTRecordVO ddtRecordVO){
        boolean isSuccess= ddtService.save(ddtRecordVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        else {
            throw new GlobalException(ResultEnum.Error);
        }
    }

}
