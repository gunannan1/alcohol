package com.app.alcohol.service;

import com.app.alcohol.dao.UserMapper;
import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.utils.MD5Util;
import com.app.alcohol.vo.LoginVO;
import com.app.alcohol.vo.SecretVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
     * check if the email is repeated
     * @param email
     * @return
     */
    public boolean repeatedEmail(String email) {

        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("email",email);
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
            if(user.getPassword().equals(MD5Util.encrypt(password))){
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
        if(user!=null){
            return user.getResearcherId();
        }
        return null;
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
        userVO.setFirstName(user.getFirstName());
        userVO.setLastName(user.getLastName());
        userVO.setEmail(user.getEmail());
        userVO.setGender(user.getGender());
        userVO.setAge(user.getAge());
        userVO.setResearcherId(user.getResearcherId());
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
        user.setPassword(MD5Util.encrypt(userVO.getPassword()));
        user.setFirstName(userVO.getFirstName());
        user.setLastName(userVO.getLastName());
        user.setEmail(userVO.getEmail());
        user.setGender(userVO.getGender());
        user.setAge(userVO.getAge());
        user.setResearcherId(userVO.getResearcherId());
        user.setCreateTime(new Date());
        return user;
    }

    public Page<User> list(UserVO userVO, Integer pageSize, Integer pageNum) {
        List<User> userList = new ArrayList<>();
        Page<User> page = new Page<>(pageNum,pageSize);
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        if(userVO.getUsername()!=null&&!userVO.getUsername().equals("")){
            entityWrapper.eq("username",userVO.getUsername());
        }
        if(userVO.getEmail()!=null&&!userVO.getEmail().equals("")){
            entityWrapper.eq("email",userVO.getEmail());

        }
        if(userVO.getFirstName()!=null&&!userVO.getFirstName().equals("")){
            entityWrapper.eq("first_name",userVO.getFirstName());

        }
        if(userVO.getLastName()!=null&&!userVO.getLastName().equals("")){
            entityWrapper.eq("last_name",userVO.getLastName());

        }
        if(userVO.getResearcherId()!=null&&!userVO.getResearcherId().equals("")){
            entityWrapper.eq("researcher_id",userVO.getResearcherId());
        }

        userList=userMapper.selectPage(page,entityWrapper);
        long counts = userMapper.selectCount(entityWrapper);

        Page<User> result = new Page<>();
        result.setRecords(userList);
        result.setSize(pageSize);
        result.setTotal(counts);
        return result;

    }

    public boolean delete(int id){
        int count=userMapper.deleteById(id);
        return count>0;
    }

    public UserVO get(int id){
        User user=userMapper.selectById(id);
        UserVO userVO=UserToUserVO(user);
        return userVO;
    }

    @Transactional
    public boolean update(int id,UserVO userVO) {
        User user=new User();
        user.setId(id);
        user.setPassword(MD5Util.encrypt(userVO.getPassword()));
        user.setGender(userVO.getGender());
        user.setAge(userVO.getAge());
        user.setEmail(userVO.getEmail());
        user.setFirstName(userVO.getFirstName());
        user.setLastName(userVO.getLastName());
        user.setResearcherId(userVO.getResearcherId());
        // insert to the database
        Integer insert = userMapper.updateById(user);
        return insert>0;
    }

    @Transactional
    public boolean updatePassword(String username, SecretVO secretVO) {
        User user=new User();
        user.setUsername(username);
        user=userMapper.selectOne(user);
        if(user==null){
            throw new GlobalException(ResultEnum.User_Not_Exist);
        }
        user.setPassword(MD5Util.encrypt(secretVO.getNewPassword()));
        // insert to the database
        Integer insert = userMapper.updateById(user);
        return insert>0;
    }


    public User getByEmail(String email){
        User user=new User();
        user.setEmail(email);
        user=userMapper.selectOne(user);
        if(user!=null){
            return user;
        }
        return null;
    }

    public User getByUsername(String username){
        User user=new User();
        user.setUsername(username);
        user=userMapper.selectOne(user);
        if(user!=null){
            return user;
        }
        return null;
    }











}
