package com.app.alcohol.service;

import com.app.alcohol.config.JwtProperties;
import com.app.alcohol.dao.AdminMapper;
import com.app.alcohol.dao.ResearcherMapper;
import com.app.alcohol.entity.Admin;
import com.app.alcohol.entity.Researcher;
import com.app.alcohol.entity.User;
import com.app.alcohol.utils.JwtTokenUtil;
import com.app.alcohol.vo.AdminVO;
import com.app.alcohol.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Here only let admin user login, if needed, it can be changed
     * @param username
     * @param password
     * @return
     */
    public AdminVO login(String username,String password ){
        Admin admin=new Admin();
        admin.setUsername(username);
        admin=adminMapper.selectOne(admin);

        //generate json web token
        if(admin!=null && admin.getId()>0){
            if(admin.getPassword().equals(password)){
                AdminVO adminVO=new AdminVO();
                adminVO.setUsername(username);
                adminVO.setTokenHead(jwtProperties.getTokenHead());
                String randomKey=jwtTokenUtil.getRandomKey();
                String token = jwtTokenUtil.generateToken(""+username, randomKey);
                adminVO.setToken(token);
                return adminVO;

            }else {
                return null;
            }
        }
        else {
            return null;
        }


    }


}
