package com.app.alcohol.controller;


import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.DDTService;
import com.app.alcohol.vo.DDTRecordVO;
import com.app.alcohol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for ddt test
 */
@RestController
@RequestMapping("/ddt/")
public class DDTController {

    @Autowired
    DDTService ddtService;

    /**
     * save ddt result
     * @param ddtRecordVO
     * @return
     */
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


}
