package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.AdminService;
import com.app.alcohol.vo.AdminVO;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * Controller for management system
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    /**
     * Login
     * @param loginVO
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseVO login(@RequestBody LoginVO loginVO){
        String username=loginVO.getUsername();
        String password=loginVO.getPassword();

        if(username==null||username.trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Username);
        }

        if(password==null||password.trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Password);
        }

        AdminVO adminVO=adminService.login(username,password);
        if(adminVO==null){
            throw new GlobalException(ResultEnum.Login_Failed);
        }

        return ResponseVO.success(adminVO);
        
    }

    /**
     * Logout
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout() {
        return ResponseVO.success(ResultEnum.SUCCESS);
    }



}
