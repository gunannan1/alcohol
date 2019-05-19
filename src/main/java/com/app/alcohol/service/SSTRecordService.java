package com.app.alcohol.service;


import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.SSTRecordMapper;
import com.app.alcohol.entity.SSTRecord;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.vo.SSTRecordVO;
import com.app.alcohol.vo.SSTInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SSTRecordService {

    @Autowired
    SSTRecordMapper sstRecordMapper;

    @Autowired
    UserService userService;

    @Autowired
    FilePathConfig filePathConfig;

    @Autowired
    DropBoxService dropBoxService;


    /**
     * save sst data
     * @param sstRecordVO
     * @return
     */
    public boolean save(SSTRecordVO sstRecordVO){
        Date date=new Date();
        String currentTime= DateUtil.convert(date);

        SSTRecord sstRecord=new SSTRecord();
        sstRecord.setUsername(sstRecordVO.getUsername());
        sstRecord.setBlock(sstRecordVO.getBlock());
        sstRecord.setTrials(sstRecordVO.getTrials());
        sstRecord.setIncorrect(sstRecordVO.getIncorrect());
        sstRecord.setMissed(sstRecordVO.getMissed());
        sstRecord.setReactionTime(sstRecordVO.getReactionTime());
        sstRecord.setPercentage(sstRecordVO.getPercentage());
        sstRecord.setCreateTime(date);

        Integer insert=sstRecordMapper.insert(sstRecord);

        String researcherId=userService.getResearcherId(sstRecord.getUsername());
        String path=createLocalFile(researcherId,sstRecordVO,currentTime);
        if(researcherId!=null){
            dropBoxService.upload(path,researcherId);
        }

        return insert>0;

    }


    /**
     * create local file
     * @param sstRecordVO
     * @return
     */
    private String createLocalFile(String researcherId,SSTRecordVO sstRecordVO,String currentTime){

        if(researcherId==null){
            researcherId="NoResearcher";
        }

        String username=sstRecordVO.getUsername();
        String fileName=currentTime.substring(0,10);

        String path= researcherId + "/" + username + "/" + "sst/" + fileName+".txt";
        String localPath = filePathConfig.getLocalPrefix() + path;

        try {
            File file = new File(localPath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();

            }
            if (!file.exists()){
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("username,"+"block,"+"trials,"+"incorrect,"+"missed,"+"reaction_time,"+"percentage,"+"updateTime"+"\r\n");
                out.write(username+","+sstRecordVO.getBlock()+","+sstRecordVO.getTrials()+","+sstRecordVO.getIncorrect()
                        +","+sstRecordVO.getMissed()+","+sstRecordVO.getReactionTime()+","
                        +sstRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }
            else {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file, true)));
                out.write(username+","+sstRecordVO.getBlock()+","+sstRecordVO.getTrials()+","+sstRecordVO.getIncorrect()
                        +","+sstRecordVO.getMissed()+","+sstRecordVO.getReactionTime()+","
                        +sstRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return path;

    }

    public  List<SSTInfoVO> getSSTHistoryInfo(String username, int bound){
        List<SSTInfoVO> list=sstRecordMapper.selectLatestSSTRecord(username,bound);
        return list;

    }


}
