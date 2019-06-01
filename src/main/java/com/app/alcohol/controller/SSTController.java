package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.SSTRecordService;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sst/")
public class SSTController {

    @Autowired
    SSTRecordService sstRecordService;

    /**
     * save after finish one block
     * @param sstRecordVO
     * @return
     */
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

    /**
     * save after finish all blocks
     * @param sstRecordListVO
     * @return
     */
    @RequestMapping(value = "saveall",method = RequestMethod.POST)
    public ResponseVO saveAll(@RequestBody SSTRecordListVO sstRecordListVO){
        boolean isSuccess= sstRecordService.save(sstRecordListVO);
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

    @RequestMapping(value = "getReactionTimeRank",method = RequestMethod.GET)
    public ResponseVO getReactionTimeRank(@RequestParam(name = "username")String username){
        SortVO sortVO=sstRecordService.getReactionTimeRank(username);

        return ResponseVO.success(sortVO);
    }

    @RequestMapping(value = "getStopSignalRank",method = RequestMethod.GET)
    public ResponseVO getStopSignalRank(@RequestParam(name = "username")String username){
        SortVO sortVO=sstRecordService.getStopSignalRank(username);

        return ResponseVO.success(sortVO);
    }

    @RequestMapping(value = "getGoStimuliRank",method = RequestMethod.GET)
    public ResponseVO getGoStimuliRank(@RequestParam(name = "username")String username){
        SortVO sortVO=sstRecordService.getGoStimuliRank(username);

        return ResponseVO.success(sortVO);
    }



}
