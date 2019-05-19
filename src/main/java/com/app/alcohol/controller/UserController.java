package com.app.alcohol.controller;

import com.app.alcohol.entity.Researcher;
import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.UserService;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.ResearcherVO;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        boolean isSuccess=true;

        //if username is not repeated,it can be registered
        if(!repeatedUserName){
            isSuccess = userService.register(userVO);
        }
        else {
            throw new GlobalException(ResultEnum.Repeated_Username);
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getList(UserVO userVO,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<User> page = userService.list(userVO, pageSize, pageNum);
        Map<String, Object> result = new HashMap<>();
        result.put("total", page.getTotal());
        result.put("list", page.getRecords());
        return ResponseVO.success(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("id") int id) {
        boolean isSuccess=userService.delete(id);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO get(@PathVariable int id) {
        UserVO userVO=userService.get(id);
        return ResponseVO.success(userVO);

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable int id, @RequestBody UserVO userVO) {
        boolean isSuccess = userService.update(id, userVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);
    }






}
