package com.app.alcohol.service;


import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.SSTRecordMapper;
import com.app.alcohol.entity.SSTRecord;
import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String username=sstRecordVO.getUsername();
        User user=userService.getByUsername(username);
        if(user==null){
            throw new GlobalException(ResultEnum.User_Not_Exist);
        }

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



    @Transactional
    public boolean save(SSTRecordListVO sstRecordListVO){

        List<SSTRecordVO> list=sstRecordListVO.getRecords();
        String username=list.get(0).getUsername();
        User user=userService.getByUsername(username);
        if(user==null){
            throw new GlobalException(ResultEnum.User_Not_Exist);
        }
        Date date=new Date();
        String currentTime= DateUtil.convert(date);

        try {
            for (int i=0;i<list.size();i++){
                SSTRecord sstRecord=new SSTRecord();
                sstRecord.setUsername(username);
                sstRecord.setBlock(list.get(i).getBlock());
                sstRecord.setTrials(list.get(i).getTrials());
                sstRecord.setIncorrect(list.get(i).getIncorrect());
                sstRecord.setMissed(list.get(i).getMissed());
                sstRecord.setReactionTime(list.get(i).getReactionTime());
                sstRecord.setPercentage(list.get(i).getPercentage());
                sstRecord.setCreateTime(date);
                sstRecordMapper.insert(sstRecord);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        String researcherId=userService.getResearcherId(username);
        String path=createLocalFile(researcherId,list,currentTime);
        if(researcherId!=null){
            dropBoxService.upload(path,researcherId);
        }

        return true;

    }
    private String createLocalFile(String researcherId,List<SSTRecordVO> sstRecordList,String currentTime){

        if(researcherId==null){
            researcherId="NoResearcher";
        }

        String username=sstRecordList.get(0).getUsername();

        String path= researcherId + "/" + username + "/" + "sst.txt";
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
                out.flush();
                out.close();
            }
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            for(int i=0;i<sstRecordList.size();i++){
                out.write(username+","+sstRecordList.get(i).getBlock()+","+sstRecordList.get(i).getTrials()+","+sstRecordList.get(i).getIncorrect()
                        +","+sstRecordList.get(i).getMissed()+","+sstRecordList.get(i).getReactionTime()+","
                        +sstRecordList.get(i).getPercentage()+","+currentTime+"\r\n");
            }
            out.flush();
            out.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return path;

    }

    public SortVO getReactionTimeRank(String username){
        SortVO sortVO=new SortVO();
        List<SortElementVO> sortElementVOS;
        List<Double> res=new ArrayList<>();
        sortElementVOS=sstRecordMapper.getReactionTimeRankInfo();

        if(sortElementVOS.size()!=10){
            int j=0;
            for(int i=1;i<=10;i++){
                if(j<sortElementVOS.size()&&sortElementVOS.get(j).getGapId()==i){
                    res.add(sortElementVOS.get(j).getPercentage());
                    j++;
                }
                else {
                    res.add(0D);
                }
            }
        }
        else {
            for(SortElementVO sortElementVO:sortElementVOS){
                res.add(sortElementVO.getPercentage());
            }
        }
        sortVO.setList(res);
        MyRankVO myRankVO=sstRecordMapper.getMyReactionTimeRank(username);
        if(myRankVO==null){
            return sortVO;
        }
        for(int i=0;i<10;i++){
            if(myRankVO.getMyScore()==1000){
                sortVO.setMyGapPosition(9);
                break;
            }
            else if(myRankVO.getMyScore()<(i+1)*100&&myRankVO.getMyScore()>=i*100){
                sortVO.setMyGapPosition(i);
                break;
            }
        }

        sortVO.setMyPercentage(myRankVO.getMyPercentage());
        sortVO.setMyScore(myRankVO.getMyScore());


        return sortVO;

    }

    public SortVO getStopSignalRank(String username){
        SortVO sortVO=new SortVO();
        List<SortElementVO> sortElementVOS;
        List<Double> res=new ArrayList<>();
        sortElementVOS=sstRecordMapper.getStopSignalRankInfo();

        if(sortElementVOS.size()!=10){
            int j=0;
            for(int i=1;i<=10;i++){
                if(j<sortElementVOS.size()&&sortElementVOS.get(j).getGapId()==i){
                    res.add(sortElementVOS.get(j).getPercentage());
                    j++;
                }
                else {
                    res.add(0D);
                }
            }
        }
        else {
            for(SortElementVO sortElementVO:sortElementVOS){
                res.add(sortElementVO.getPercentage());
            }
        }
        sortVO.setList(res);
        MyRankVO myRankVO=sstRecordMapper.getMyStopSignalRank(username);
        if(myRankVO==null){
            return sortVO;
        }
        for(int i=0;i<10;i++){
            if(myRankVO.getMyScore()==100){
                sortVO.setMyGapPosition(9);
                break;
            }
            else if(myRankVO.getMyScore()<(i+1)*10&&myRankVO.getMyScore()>=i*10){
                sortVO.setMyGapPosition(i);
                break;
            }
        }

        sortVO.setMyPercentage(myRankVO.getMyPercentage());
        sortVO.setMyScore(myRankVO.getMyScore());

        return sortVO;

    }

    public SortVO getGoStimuliRank(String username){
        SortVO sortVO=new SortVO();
        List<SortElementVO> sortElementVOS;
        List<Double> res=new ArrayList<>();
        sortElementVOS=sstRecordMapper.getGoStimuliRankInfo();

        if(sortElementVOS.size()!=10){
            int j=0;
            for(int i=1;i<=10;i++){
                if(j<sortElementVOS.size()&&sortElementVOS.get(j).getGapId()==i){
                    res.add(sortElementVOS.get(j).getPercentage());
                    j++;
                }
                else {
                    res.add(0D);
                }
            }
        }
        else {
            for(SortElementVO sortElementVO:sortElementVOS){
                res.add(sortElementVO.getPercentage());
            }
        }
        sortVO.setList(res);
        MyRankVO myRankVO=sstRecordMapper.getMyGoStimuliRank(username);
        if(myRankVO==null){
            return sortVO;
        }
        for(int i=0;i<10;i++){
            if(myRankVO.getMyScore()==100){
                sortVO.setMyGapPosition(9);
                break;
            }
            else if(myRankVO.getMyScore()<(i+1)*10&&myRankVO.getMyScore()>=i*10){
                sortVO.setMyGapPosition(i);
                break;
            }
        }
        sortVO.setMyPercentage(myRankVO.getMyPercentage());
        sortVO.setMyScore(myRankVO.getMyScore());

        return sortVO;

    }








    }
