package com.app.alcohol.controller;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.AdminService;
import com.app.alcohol.vo.AdminVO;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    AdminService adminService;


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

        AdminVO adminVO=adminService.login(username,password);
        if(adminVO==null){
            throw new GlobalException(ResultEnum.Login_Failed);
        }

        return ResponseVO.success(adminVO);
        
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getAdminInfo(Principal principal) {
        String  username = principal.getName();
        Map<String, Object> data = new HashMap<>();
        data.put("username", "admin");
        data.put("roles", new String[]{"TEST"});
        data.put("icon", "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg");
        return  ResponseVO.success(data);
    }
}
