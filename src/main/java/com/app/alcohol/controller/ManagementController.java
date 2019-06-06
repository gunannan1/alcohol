package com.app.alcohol.controller;

import com.app.alcohol.config.CurrentUser;
import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.AdminService;
import com.app.alcohol.service.UserService;
import com.app.alcohol.vo.AdminVO;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Controller for management system
 */
@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;


    /**
     * Login
     * @param loginVO
     * @return
     */
    @RequestMapping(value="/admin/login",method = RequestMethod.POST)
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
    @RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout() {
        return ResponseVO.success(ResultEnum.SUCCESS);
    }

    /**
     * Show user list
     * @param userVO
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getList(UserVO userVO,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        if(CurrentUser.getCurrentUser()!=null){
            Page<User> page = userService.list(userVO, pageSize, pageNum);
            Map<String, Object> result = new HashMap<>();
            result.put("total", page.getTotal());
            result.put("list", page.getRecords());
            return ResponseVO.success(result);
        }
        else {
            return ResponseVO.error(ResultEnum.Not_Login);
        }

    }

    /**
     * delete user
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(@RequestParam("id") int id) {
        boolean isSuccess=userService.delete(id);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);

    }

    /**
     * get one user's information
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO get(@PathVariable int id) {
        UserVO userVO=userService.get(id);
        return ResponseVO.success(userVO);
    }

    /**
     * update one user's information
     * @param id
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@PathVariable int id, @RequestBody UserVO userVO) {
        boolean isSuccess = userService.update(id, userVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);
    }

}
