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

    @RequestMapping(value = "saveall",method = RequestMethod.POST)
    public ResponseVO saveall(@RequestBody NBackRecordListVO nBackRecordListVO){
        boolean isSuccess= nBackService.save(nBackRecordListVO);
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
    public ResponseVO getHistoryInfo(@RequestParam(name = "username")String username,@RequestParam(name = "level",required = false)Integer level, @RequestParam(name = "bound")int bound){
        NBackInfoVO nBackInfoVO;
        if(level!=null){
            nBackInfoVO=nBackService.getNbackHistoryInfo(username, bound, level);
        }
        else {
            nBackInfoVO=nBackService.getNbackHistoryInfo(username,bound,0);
        }
        return ResponseVO.success(nBackInfoVO);
    }

    @RequestMapping(value = "getCorrectnessRank",method = RequestMethod.GET)
    public ResponseVO getCorrectnessRank(@RequestParam(name = "username")String username,@RequestParam(name = "level")Integer level){
        SortVO sortVO=nBackService.getCorrectnessRank(username, level);

        return ResponseVO.success(sortVO);
    }





}
