package com.app.alcohol.service;

import com.app.alcohol.dao.DDTRecordMapper;
import com.app.alcohol.entity.DDTRecord;
import com.app.alcohol.vo.DDTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class DDTService {
    @Autowired
    DDTRecordMapper ddtRecordMapper;

    public boolean save(DDTRecordVO ddtRecordVO){
        DDTRecord ddtRecord=new DDTRecord();
        List<List<Integer>> input=ddtRecordVO.getAnswers();
        Queue<List<Integer>> record=new PriorityQueue<>((o1,o2)->o1.get(0)-o2.get(0));

        record.addAll(input);

        ddtRecord.setUsername(ddtRecordVO.getUsername());
        ddtRecord.setQuestion_1(record.poll().get(1));
        ddtRecord.setQuestion_2(record.poll().get(1));
        ddtRecord.setQuestion_3(record.poll().get(1));
        ddtRecord.setQuestion_4(record.poll().get(1));
        ddtRecord.setQuestion_5(record.poll().get(1));
        ddtRecord.setQuestion_6(record.poll().get(1));
        ddtRecord.setQuestion_7(record.poll().get(1));
        ddtRecord.setQuestion_8(record.poll().get(1));
        ddtRecord.setQuestion_9(record.poll().get(1));
        ddtRecord.setQuestion_10(record.poll().get(1));
        ddtRecord.setQuestion_11(record.poll().get(1));
        ddtRecord.setQuestion_12(record.poll().get(1));
        ddtRecord.setQuestion_13(record.poll().get(1));
        ddtRecord.setQuestion_14(record.poll().get(1));
        ddtRecord.setQuestion_15(record.poll().get(1));
        ddtRecord.setQuestion_16(record.poll().get(1));
        ddtRecord.setQuestion_17(record.poll().get(1));
        ddtRecord.setQuestion_18(record.poll().get(1));
        ddtRecord.setQuestion_19(record.poll().get(1));
        ddtRecord.setQuestion_20(record.poll().get(1));
        ddtRecord.setQuestion_21(record.poll().get(1));
        ddtRecord.setQuestion_22(record.poll().get(1));
        ddtRecord.setQuestion_23(record.poll().get(1));
        ddtRecord.setQuestion_24(record.poll().get(1));
        ddtRecord.setQuestion_25(record.poll().get(1));
        ddtRecord.setQuestion_26(record.poll().get(1));
        ddtRecord.setQuestion_27(record.poll().get(1));


        Integer insert=ddtRecordMapper.insert(ddtRecord);

        return insert>0;


    }
}
