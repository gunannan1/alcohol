package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.ResearcherService;
import com.app.alcohol.service.UserService;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResearcherService researcherService;


    /**
     *
     * @param userVO the parameter for user register
     * @return
     */
    @RequestMapping(value="register",method = RequestMethod.POST)
    public ResponseVO register(@RequestBody UserVO userVO){
        if(userVO.getUsername() == null || userVO.getUsername().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Username);
        }
        if(userVO.getPassword() == null || userVO.getPassword().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Password);
        }
        if(userVO.getEmail() == null || userVO.getEmail().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Email);
        }

        //if username is repeated,return true
        boolean repeatedUserName=userService.repeatedUserName(userVO.getUsername());
        boolean repeatedEmail=userService.repeatedEmail(userVO.getEmail());

        boolean isSuccess=true;

        //if username and email is not repeated,it can be registered
        if(repeatedUserName){
            throw new GlobalException(ResultEnum.Repeated_Username);

        }
        else if(repeatedEmail) {
            throw new GlobalException(ResultEnum.Repeated_Email);
        }
        else {
            isSuccess = userService.register(userVO);
        }


        //return the userVo entity to the mobile since they may need cache it locally
        if(isSuccess){
            return ResponseVO.success(userVO);
        }else{
            throw new GlobalException(ResultEnum.Register_Failed);

        }
    }


    /**
     * login
     * @param loginVO
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public ResponseVO login(@RequestBody LoginVO loginVO){
        String username=loginVO.getUsername();
        String password=loginVO.getPassword();

        if(username==null||username.trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Username);
        }

        if(password==null||password.trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Password);
        }

        UserVO userVO=userService.login(loginVO);
        if(userVO==null){
            throw new GlobalException(ResultEnum.Login_Failed);
        }

        return ResponseVO.success(userVO);

    }

    /**
     * get researcher list for application end to do register
     * @return
     */
    @RequestMapping(value = "getResearcherList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getResearcherList() {
        List<ResearcherChooseVO> list=researcherService.getAllResearcherList();
        return ResponseVO.success(list);

    }



}
