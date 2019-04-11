package com.app.alcohol.service;

import com.app.alcohol.dao.NBackRecordMapper;
import com.app.alcohol.entity.NBackRecord;
import com.app.alcohol.vo.NBackRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NBackService {
    @Autowired
    NBackRecordMapper nBackRecordMapper;

    public boolean save(NBackRecordVO nBackRecordVO){
        NBackRecord nBackRecord=new NBackRecord();
        nBackRecord.setUsername(nBackRecordVO.getUsername());
        nBackRecord.setLevel(nBackRecordVO.getLevel());
        nBackRecord.setPercentage(nBackRecordVO.getPercentage());
        nBackRecord.setCreateTime(new Date());
        Integer insert=nBackRecordMapper.insert(nBackRecord);
        return insert>0;

    }

}
