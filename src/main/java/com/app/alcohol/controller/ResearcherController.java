package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.ResearcherService;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.ResearcherVO;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/researcher/")
@RestController
public class ResearcherController {

    @Autowired
    ResearcherService researcherService;

    /**
     * create researcher
     * @param  researcherVO
     * @return
     */
    @RequestMapping(value="create",method = RequestMethod.POST)
    public ResponseVO register( ResearcherVO researcherVO){
        if(researcherVO.getUsername() == null || researcherVO.getUsername().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Username);
        }
        if(researcherVO.getEmail() == null || researcherVO.getEmail().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Email);
        }

        //if username is repeated,return true
        boolean repeatedUserName=researcherService.repeatedUserName(researcherVO.getUsername());

        boolean isSuccess=true;

        //if username is not repeated,it can be registered
        if(!repeatedUserName){
            isSuccess = researcherService.create(researcherVO);
        }
        else {
            throw new GlobalException(ResultEnum.Repeated_Username);
        }

        //return the userVo entity to the mobile since they may need cache it locally
        if(isSuccess){
            return ResponseVO.success(researcherVO);
        }else{
            throw new GlobalException(ResultEnum.Register_Failed);

        }
    }


}
