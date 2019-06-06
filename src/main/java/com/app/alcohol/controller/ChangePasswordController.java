package com.app.alcohol.controller;

import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.EmailService;
import com.app.alcohol.service.UserService;
import com.app.alcohol.utils.JwtTokenUtil;
import com.app.alcohol.vo.ResponseVO;
import com.app.alcohol.vo.PasswordVO;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for resetting password
 */
@Controller
@RequestMapping("/resetpassword/")
public class ChangePasswordController {
    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * Go to success page
     * @return
     */
    @RequestMapping(value="success",method = RequestMethod.GET)
    public String success(){
        return "success";
    }

    /**
     * Go to expiration page
     * @return
     */
    @RequestMapping(value="expire",method = RequestMethod.GET)
    public String expire(){
        return "expire";
    }


    /**
     * Do resetting password
     * @param
     * @return
     */
    @RequestMapping(value="do_reset/{jwt}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO resetSecret(PasswordVO passwordVO, @PathVariable String jwt){

        try {
            //check if jwt is expired
            boolean flag = jwtTokenUtil.isTokenExpired(jwt);
            if (flag) {
                throw new GlobalException(ResultEnum.TOKEN_EXPIRED);
            }
        } catch (JwtException e) {
            throw new GlobalException(ResultEnum.TOKEN_ERROR);
        }

        String newPassword= passwordVO.getNewPassword();
        String repeatPassword= passwordVO.getConfirmPassword();
        if(!newPassword.equals(repeatPassword)){
            throw new GlobalException(ResultEnum.Passworrd_Not_Match);
        }
        String username=jwtTokenUtil.getUsernameFromToken(jwt);

        boolean success=userService.updatePassword(username, passwordVO);
        if(success){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }

        return ResponseVO.success(ResultEnum.Error);

    }

    /**
     * Go to the resetting password page
     * @param
     * @return
     */
    @RequestMapping(value="{jwt}")
    public String changeSecret(@PathVariable String jwt){

        try {
            boolean flag = jwtTokenUtil.isTokenExpired(jwt);
            if (flag) {
                return "expire";
            }
        } catch (JwtException e) {
            throw new GlobalException(ResultEnum.TOKEN_ERROR);
        }

        String username=jwtTokenUtil.getUsernameFromToken(jwt);
        if(username!=null){
            User user= userService.getByUsername(username);
            if(user!=null){
                return "reset";
            }
            else {
                throw new GlobalException(ResultEnum.User_Not_Exist);
            }
        }
        else {
            throw new GlobalException(ResultEnum.TOKEN_ERROR);
        }

    }

    /**
     * Send resetting password email
     * @param
     * @return
     */
    @RequestMapping(value="request",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO resetRequest(HttpServletRequest request,@RequestParam(name = "email")String email){
        User user= userService.getByEmail(email);
        String username;
        try {
            username=user.getUsername();
        }catch (NullPointerException e ){
            throw new GlobalException(ResultEnum.Wrong_Email);
        }

        String subject="Rest your password for Alcohol App.";
        Map<String, Object> params = new HashMap<>();
        String randomKey=jwtTokenUtil.getRandomKey();
        String jwt = jwtTokenUtil.generateToken(""+username, randomKey,1800*1000);
        //Generate resetting password link
        String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/resetpassword/"+jwt;
        params.put("path",path);

        try {
            emailService.sendEmail(email,subject,"forgetPassword",params);

        }catch (Exception e){
            throw new GlobalException(ResultEnum.Send_Email_Error);
        }
        return ResponseVO.success(ResultEnum.SUCCESS);

    }

}
