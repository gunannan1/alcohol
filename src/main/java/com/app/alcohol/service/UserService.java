package com.app.alcohol.service;

import com.app.alcohol.dao.UserMapper;
import com.app.alcohol.entity.User;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * register
     * @param userVO
     * @return
     */
    public boolean register(UserVO userVO) {

        // convert UserVO to User
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setName(userVO.getName());
        user.setEmail(userVO.getEmail());
        user.setSex(userVO.getSex());
        user.setAge(userVO.getAge());

        // insert to the database
        Integer insert = userMapper.insert(user);

        return insert>0;
    }


    /**
     * check if the username is repeated
     * @param username
     * @return
     */
    public boolean repeatedUserName(String username) {

        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("username",username);
        Integer result = userMapper.selectCount(entityWrapper);

        return result!=null && result>0;
    }





}
