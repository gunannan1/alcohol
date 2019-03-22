package com.app.alcohol.service;


import com.app.alcohol.dao.SSTRecordMapper;
import com.app.alcohol.entity.SSTRecord;
import com.app.alcohol.vo.SSTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSTRecordService {

    @Autowired
    SSTRecordMapper sstRecordMapper;


    public boolean save(SSTRecordVO sstRecordVO){
        SSTRecord sstRecord=new SSTRecord();
        sstRecord.setUsername(sstRecordVO.getUsername());
        sstRecord.setBlock(sstRecordVO.getBlock());
        sstRecord.setTrials(sstRecordVO.getTrials());
        sstRecord.setIncorrect(sstRecordVO.getIncorrect());
        sstRecord.setMissed(sstRecordVO.getMissed());
        sstRecord.setReactionTime(sstRecordVO.getReactionTime());
        sstRecord.setPercentage(sstRecordVO.getPercentage());

        Integer insert=sstRecordMapper.insert(sstRecord);

        return insert>0;


    }
}
