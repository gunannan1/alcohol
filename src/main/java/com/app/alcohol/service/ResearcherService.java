package com.app.alcohol.service;

import com.app.alcohol.dao.ResearcherMapper;
import com.app.alcohol.entity.Researcher;
import com.app.alcohol.entity.User;
import com.app.alcohol.utils.MD5Util;
import com.app.alcohol.vo.ResearcherChooseVO;
import com.app.alcohol.vo.ResearcherVO;
import com.app.alcohol.vo.UserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        researcher.setPassword(MD5Util.encrypt(researcherVO.getPassword()));
        researcher.setAccessToken(researcherVO.getAccessToken());
        researcher.setEmail(researcherVO.getEmail());
        researcher.setFirstName(researcherVO.getFirstName());
        researcher.setLastName(researcherVO.getLastName());
        researcher.setResearcherId(researcherVO.getResearcherId());
        researcher.setCreateTime(new Date());

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

    /**
     * create researcher
     * @param researcherVO
     * @return
     */
    @Transactional
    public boolean update(int id,ResearcherVO researcherVO) {
        Researcher researcher=new Researcher();
        researcher.setId(id);
        researcher.setPassword(MD5Util.encrypt(researcherVO.getPassword()));
        researcher.setAccessToken(researcherVO.getAccessToken());
        researcher.setEmail(researcherVO.getEmail());
        researcher.setFirstName(researcherVO.getFirstName());
        researcher.setLastName(researcherVO.getLastName());
        researcher.setResearcherId(researcherVO.getResearcherId());

        // insert to the database
        Integer insert = researcherMapper.updateById(researcher);

        return insert>0;
    }

    /**
     * this is for show researcher info in management system,
     * @param researcherVO
     * @param pageSize
     * @param pageNum
     * @return
     */
    public Page<Researcher> list(ResearcherVO researcherVO, Integer pageSize, Integer pageNum) {
        List<Researcher> researcherList = new ArrayList<>();
        Page<Researcher> page = new Page<>(pageNum,pageSize);
        EntityWrapper<Researcher> entityWrapper = new EntityWrapper<>();
        if(researcherVO.getUsername()!=null&&!researcherVO.getUsername().equals("")){
            entityWrapper.eq("username",researcherVO.getUsername());
        }
        if(researcherVO.getEmail()!=null&&!researcherVO.getEmail().equals("")){
            entityWrapper.eq("email",researcherVO.getEmail());

        }
        if(researcherVO.getFirstName()!=null&&!researcherVO.getFirstName().equals("")){
            entityWrapper.eq("first_name",researcherVO.getFirstName());

        }
        if(researcherVO.getLastName()!=null&&!researcherVO.getLastName().equals("")){
            entityWrapper.eq("last_name",researcherVO.getLastName());

        }
        if(researcherVO.getResearcherId()!=null&&!researcherVO.getResearcherId().equals("")){
            entityWrapper.eq("researcher_id",researcherVO.getResearcherId());
        }
        researcherList=researcherMapper.selectPage(page,entityWrapper);
        long counts = researcherMapper.selectCount(entityWrapper);

        Page<Researcher> result = new Page<>();
        result.setRecords(researcherList);
        result.setSize(pageSize);
        result.setTotal(counts);

        return result;

    }

    public boolean delete(int id){
        int count=researcherMapper.deleteById(id);
        return count>0;
    }

    public ResearcherVO get(int id){
        Researcher researcher=researcherMapper.selectById(id);
        ResearcherVO researcherVO=new ResearcherVO();
        researcherVO.setAccessToken(researcher.getAccessToken());
        researcherVO.setEmail(researcher.getEmail());
        researcherVO.setFirstName(researcher.getFirstName());
        researcherVO.setLastName(researcher.getLastName());
        researcherVO.setPassword(researcher.getPassword());
        researcherVO.setUsername(researcher.getUsername());
        researcherVO.setResearcherId(researcher.getResearcherId());

        return researcherVO;
    }

    /**
     * this is for user to choose list
     * @return
     */
    public List<ResearcherChooseVO> getAllResearcherList(){
        List<Researcher> list=researcherMapper.selectList(new EntityWrapper<Researcher>());
        List<ResearcherChooseVO> res=new ArrayList<>();
        for(Researcher researcher:list){
            ResearcherChooseVO rc=new ResearcherChooseVO();
            rc.setFirstName(researcher.getFirstName());
            rc.setLastName(researcher.getLastName());
            rc.setResearcherId(researcher.getResearcherId());
            rc.setUsername(researcher.getUsername());
            res.add(rc);
        }
        return res;

    }





}
