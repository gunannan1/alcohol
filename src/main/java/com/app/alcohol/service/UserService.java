package com.app.alcohol.service;

import com.app.alcohol.dao.UserMapper;
import com.app.alcohol.entity.User;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * register
     * @param userVO
     * @return
     */
    @Transactional
    public boolean register(UserVO userVO) {

        User user = UserVOToUser(userVO);

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


    /**
     * login
     * @param loginVO
     * @return
     */
    public UserVO login(LoginVO loginVO){
        String username=loginVO.getUsername();
        String password=loginVO.getPassword();

        User loginUser=new User();
        loginUser.setUsername(username);

        User user=userMapper.selectOne(loginUser);


        if(user!=null && user.getId()>0){
            if(user.getPassword().equals(password)){
                return UserToUserVO(user);
            }else {
                return null;
            }
        }
        else {
            return null;
        }


    }

    public String getResearcherId(String username){
        User user=new User();
        user.setUsername(username);

        user=userMapper.selectOne(user);

        return user.getResearcherId();
    }

    /**
     * convert User to UserVO
     * @param user
     * @return
     */
    private UserVO UserToUserVO(User user){
        UserVO userVO=new UserVO();
        userVO.setUsername(user.getUsername());
        userVO.setPassword(user.getPassword());
        userVO.setName(user.getName());
        userVO.setEmail(user.getEmail());
        userVO.setSex(user.getSex());
        userVO.setAge(user.getAge());
        return userVO;
    }

    /**
     * convert UserVO to User
     * @param userVO
     * @return
     */
    private User UserVOToUser(UserVO userVO){
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setName(userVO.getName());
        user.setEmail(userVO.getEmail());
        user.setSex(userVO.getSex());
        user.setAge(userVO.getAge());
        return user;
    }










}
