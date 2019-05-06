package com.app.alcohol.service;

import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.DDTRecordMapper;
import com.app.alcohol.entity.DDTRecord;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.vo.DDTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Time;
import java.util.*;

@Service
public class DDTService {
    @Autowired
    DDTRecordMapper ddtRecordMapper;

    @Autowired
    UserService userService;

    @Autowired
    FilePathConfig filePathConfig;

    @Autowired
    DropBoxService dropBoxService;

    /**
     * save ddt data
     * @param ddtRecordVO
     * @return
     */
    public boolean save(DDTRecordVO ddtRecordVO){
        DDTRecord ddtRecord=new DDTRecord();
        List<List<Integer>> input=ddtRecordVO.getAnswers();
        Queue<List<Integer>> record=new PriorityQueue<>((o1,o2)->o1.get(0)-o2.get(0));

        record.addAll(input);
        List<Integer> list=new ArrayList<>();

        // 27 questions
        // not use input.size, maybe it is less than 27
        for (int i=0;i<27;i++){
            List<Integer> peak=record.peek();
            if(peak.get(0)==i){
                list.add(record.poll().get(1));
            }
            else {
                list.add(0);
            }
        }

        Date date=new Date();
        String currentTime=DateUtil.convert(date);

        ddtRecord.setUsername(ddtRecordVO.getUsername());
        ddtRecord.setQuestion_1(list.get(0));
        ddtRecord.setQuestion_2(list.get(1));
        ddtRecord.setQuestion_3(list.get(2));
        ddtRecord.setQuestion_4(list.get(3));
        ddtRecord.setQuestion_5(list.get(4));
        ddtRecord.setQuestion_6(list.get(5));
        ddtRecord.setQuestion_7(list.get(6));
        ddtRecord.setQuestion_8(list.get(7));
        ddtRecord.setQuestion_9(list.get(8));
        ddtRecord.setQuestion_10(list.get(9));
        ddtRecord.setQuestion_11(list.get(10));
        ddtRecord.setQuestion_12(list.get(11));
        ddtRecord.setQuestion_13(list.get(12));
        ddtRecord.setQuestion_14(list.get(13));
        ddtRecord.setQuestion_15(list.get(14));
        ddtRecord.setQuestion_16(list.get(15));
        ddtRecord.setQuestion_17(list.get(16));
        ddtRecord.setQuestion_18(list.get(17));
        ddtRecord.setQuestion_19(list.get(18));
        ddtRecord.setQuestion_20(list.get(19));
        ddtRecord.setQuestion_21(list.get(20));
        ddtRecord.setQuestion_22(list.get(21));
        ddtRecord.setQuestion_23(list.get(22));
        ddtRecord.setQuestion_24(list.get(23));
        ddtRecord.setQuestion_25(list.get(24));
        ddtRecord.setQuestion_26(list.get(25));
        ddtRecord.setQuestion_27(list.get(26));
        ddtRecord.setCreateTime(date);
        Integer insert=ddtRecordMapper.insert(ddtRecord);

        //create local file and upload it to dropbox
        String researcherId = userService.getResearcherId(ddtRecordVO.getUsername());
        String path=createLocalFile(ddtRecordVO.getUsername(),researcherId,list,currentTime);
        if(researcherId!=null){
            dropBoxService.upload(path,researcherId);
        }
        return insert>0;

    }

    /**
     * create local file
     * @param username
     * @param researcherId
     * @param list
     * @param currentTime
     * @return
     */
    private String createLocalFile(String username, String researcherId,List<Integer> list,String currentTime){

        if(researcherId==null){
            researcherId="NoResearcher";
        }
        String path= researcherId + "/" + username + "/" + "ddt/" + currentTime+".txt";
        String localPath = filePathConfig.getLocalPrefix() + path;

        try {
            File file = new File(localPath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();

            }
            if (!file.exists()){
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write("username");
            for (int i=1;i<=27;i++){
                out.write(",Q"+i);
            }
            out.write("\r\n");
            out.write(username);
            for (int i=0;i<27;i++){
                out.write(","+list.get(i));
            }
            out.write("\r\n");
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return path;

    }


}
