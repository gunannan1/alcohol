package com.app.alcohol.service;

import com.app.alcohol.config.JwtProperties;
import com.app.alcohol.dao.ResearcherMapper;
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
    ResearcherMapper researcherMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private final String admin="admin";

    /**
     * Here only let admin user login, if needed, it can be changed
     * @param username
     * @param password
     * @return
     */
    public AdminVO login(String username,String password ){

        if(!username.equals(admin)){
            return null;
        }
        Researcher researcher=new Researcher();
        researcher.setUsername(username);
        Researcher loginResearcher=researcherMapper.selectOne(researcher);

        //generate json web token
        if(loginResearcher!=null && loginResearcher.getId()>0){
            if(loginResearcher.getPassword().equals(password)){
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
