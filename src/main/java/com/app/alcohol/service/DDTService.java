package com.app.alcohol.service;

import com.app.alcohol.dao.DDTRecordMapper;
import com.app.alcohol.entity.DDTRecord;
import com.app.alcohol.vo.DDTRecordVO;
import com.app.alcohol.vo.SSTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DDTService {
    @Autowired
    DDTRecordMapper ddtRecordMapper;

    public boolean save(DDTRecordVO ddtRecordVO){
        DDTRecord ddtRecord=new DDTRecord();
        ddtRecord.setQuestion_1(ddtRecordVO.getQuestions().get(0));
        ddtRecord.setQuestion_2(ddtRecordVO.getQuestions().get(1));
        ddtRecord.setQuestion_3(ddtRecordVO.getQuestions().get(2));
        ddtRecord.setQuestion_4(ddtRecordVO.getQuestions().get(3));
        ddtRecord.setQuestion_5(ddtRecordVO.getQuestions().get(4));
        ddtRecord.setQuestion_6(ddtRecordVO.getQuestions().get(5));
        ddtRecord.setQuestion_7(ddtRecordVO.getQuestions().get(6));
        ddtRecord.setQuestion_8(ddtRecordVO.getQuestions().get(7));
        ddtRecord.setQuestion_9(ddtRecordVO.getQuestions().get(8));
        ddtRecord.setQuestion_10(ddtRecordVO.getQuestions().get(9));
        ddtRecord.setQuestion_11(ddtRecordVO.getQuestions().get(10));
        ddtRecord.setQuestion_12(ddtRecordVO.getQuestions().get(11));
        ddtRecord.setQuestion_13(ddtRecordVO.getQuestions().get(12));
        ddtRecord.setQuestion_14(ddtRecordVO.getQuestions().get(13));
        ddtRecord.setQuestion_15(ddtRecordVO.getQuestions().get(14));
        ddtRecord.setQuestion_16(ddtRecordVO.getQuestions().get(15));
        ddtRecord.setQuestion_17(ddtRecordVO.getQuestions().get(16));
        ddtRecord.setQuestion_18(ddtRecordVO.getQuestions().get(17));
        ddtRecord.setQuestion_19(ddtRecordVO.getQuestions().get(18));
        ddtRecord.setQuestion_20(ddtRecordVO.getQuestions().get(19));
        ddtRecord.setQuestion_21(ddtRecordVO.getQuestions().get(20));
        ddtRecord.setQuestion_22(ddtRecordVO.getQuestions().get(21));
        ddtRecord.setQuestion_23(ddtRecordVO.getQuestions().get(22));
        ddtRecord.setQuestion_24(ddtRecordVO.getQuestions().get(23));
        ddtRecord.setQuestion_25(ddtRecordVO.getQuestions().get(24));
        ddtRecord.setQuestion_26(ddtRecordVO.getQuestions().get(25));
        ddtRecord.setQuestion_27(ddtRecordVO.getQuestions().get(26));

        Integer insert=ddtRecordMapper.insert(ddtRecord);

        return insert>0;


    }
}
