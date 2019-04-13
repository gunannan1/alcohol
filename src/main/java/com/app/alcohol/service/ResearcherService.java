package com.app.alcohol.service;

import com.app.alcohol.dao.ResearcherMapper;
import com.app.alcohol.entity.Researcher;
import com.app.alcohol.entity.User;
import com.app.alcohol.vo.ResearcherVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResearcherService {
    @Autowired
    ResearcherMapper researcherMapper;

    /**
     * create researcher
     * @param researcherVO
     * @return
     */
    @Transactional
    public boolean create(ResearcherVO researcherVO) {

        Researcher researcher=new Researcher();
        researcher.setUsername(researcherVO.getUsername());
        researcher.setAccessToken(researcherVO.getAccessToken());
        researcher.setEmail(researcherVO.getEmail());
        researcher.setName(researcherVO.getName());
        researcher.setResearcherId(researcherVO.getResearcherId());

        // insert to the database
        Integer insert = researcherMapper.insert(researcher);

        return insert>0;
    }

    /**
     * check if the username is repeated
     * @param username
     * @return
     */
    public boolean repeatedUserName(String username) {

        EntityWrapper<Researcher> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("username",username);
        Integer result = researcherMapper.selectCount(entityWrapper);

        return result!=null && result>0;
    }

    public String getToken(String researcherId){
        Researcher researcher=new Researcher();
        researcher.setResearcherId(researcherId);
        researcher=researcherMapper.selectOne(researcher);

        return researcher.getAccessToken();

    }





}
