package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.service.UserService;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     *
     * @param userVO the parameter for user register
     * @return
     */
    @RequestMapping(value="register",method = RequestMethod.POST)
    public ResponseVO register(@RequestBody UserVO userVO){
        if(userVO.getUsername() == null || userVO.getUsername().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Username);
        }
        if(userVO.getPassword() == null || userVO.getPassword().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Password);
        }
        if(userVO.getEmail() == null || userVO.getEmail().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Email);
        }

        //if username is repeated,return true
        boolean repeatedUserName=userService.repeatedUserName(userVO.getUsername());

        boolean isSuccess=true;

        //if username is not repeated,it can be registered
        if(!repeatedUserName){
            isSuccess = userService.register(userVO);
        }
        else {
            return ResponseVO.error(ResultEnum.Repeated_Username);
        }


        //return the userVo entity to the mobile since they may need cache it locally
        if(isSuccess){
            return ResponseVO.success(userVO);
        }else{
            return ResponseVO.error(ResultEnum.Register_Failed);
        }
    }


    /**
     *test if we need add @Response
     * @param userVO the parameter for user register
     * @return
     */
    @RequestMapping(value="register2",method = RequestMethod.POST)
    public ResponseVO register2(UserVO userVO){
        if(userVO.getUsername() == null || userVO.getUsername().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Username);
        }
        if(userVO.getPassword() == null || userVO.getPassword().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Password);
        }
        if(userVO.getEmail() == null || userVO.getEmail().trim().length()==0){
            return ResponseVO.error(ResultEnum.Empty_Email);
        }

        //if username is repeated,return true
        boolean repeatedUserName=userService.repeatedUserName(userVO.getUsername());

        boolean isSuccess=true;

        //if username is not repeated,it can be registered
        if(!repeatedUserName){
            isSuccess = userService.register(userVO);
        }
        else {
            return ResponseVO.error(ResultEnum.Repeated_Username);
        }


        //return the userVo entity to the mobile since they may need cache it locally
        if(isSuccess){
            return ResponseVO.success(userVO);
        }else{
            return ResponseVO.error(ResultEnum.Register_Failed);
        }
    }




}
