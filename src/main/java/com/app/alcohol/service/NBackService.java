package com.app.alcohol.service;

import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.NBackRecordMapper;
import com.app.alcohol.entity.NBackRecord;
import com.app.alcohol.entity.User;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.utils.NBackUtil;
import com.app.alcohol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Nback service
 */
@Service
public class NBackService {
    @Autowired
    NBackRecordMapper nBackRecordMapper;


    @Autowired
    UserService userService;

    @Autowired
    FilePathConfig filePathConfig;

    @Autowired
    DropBoxService dropBoxService;

    /**
     * save all four blocks records one time, we use this now
     * @param nBackRecordListVO
     * @return
     */
    public boolean save(NBackRecordListVO nBackRecordListVO){
        List<NBackRecordVO> list=nBackRecordListVO.getRecords();
        String username=list.get(0).getUsername();
        User user=userService.getByUsername(username);
        if(user==null){
            throw new GlobalException(ResultEnum.User_Not_Exist);
        }


        Date date=new Date();
        String currentTime= DateUtil.convert(date);


        try{
            for (int i=0;i<list.size();i++){
                NBackRecord nBackRecord=new NBackRecord();
                nBackRecord.setUsername(username);
                nBackRecord.setBlock(list.get(i).getBlock());
                nBackRecord.setIncorrect(list.get(i).getIncorrect());
                nBackRecord.setMissed(list.get(i).getMissed());
                nBackRecord.setTrials(list.get(i).getTrials());
                nBackRecord.setLevel(list.get(i).getLevel());
                nBackRecord.setPercentage(list.get(i).getPercentage());
                nBackRecord.setCreateTime(date);
                nBackRecordMapper.insert(nBackRecord);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        //generate and upload record file
        String researcherId=userService.getResearcherId(username);
        String path=createLocalFile(researcherId,list,currentTime);
        if(researcherId!=null){
            dropBoxService.upload(path,researcherId);
        }

        return true;
    }

    /**
     * create local file
     * @param researcherId
     * @param list
     * @param currentTime
     * @return
     */
    private String createLocalFile(String researcherId, List<NBackRecordVO> list, String currentTime){

        if(researcherId==null){
            researcherId="NoResearcher";
        }

        String username=list.get(0).getUsername();

        String path= researcherId + "/" + username + "/" + "nback.txt";
        String localPath = filePathConfig.getLocalPrefix() + path;

        //generate file
        try {
            File file = new File(localPath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();

            }
            if (!file.exists()){
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("username,"+"block,"+"trials,"+"level,"+"missed,"+"incorrect,"+"percentage,"+"updateTime"+"\r\n");
                out.flush();
                out.close();
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            for(int i=0;i<list.size();i++){
                out.write(username+","+list.get(i).getBlock()+","+list.get(i).getTrials()+","+list.get(i).getLevel()
                        +","+list.get(i).getMissed()+","+list.get(i).getIncorrect()+","
                        +list.get(i).getPercentage()+","+currentTime+"\r\n");
            }
            out.flush();
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return path;

    }



    /**
     * request for nback info
     * @param nbackRequestVO
     * @return
     */
    public NBackResponseVO requestNBackInfo(NbackRequestVO nbackRequestVO){
        int level = nbackRequestVO.getLevel();
        NBackResponseVO nBackResponseVO=new NBackResponseVO();
        try {
            nBackResponseVO= NBackUtil.generateNBack(level,7,20);
        }catch (Exception e){
            e.printStackTrace();
        }

        return nBackResponseVO;

    }

    /**
     * get the history record of a user to do rank
     * @param username
     * @param bound max num of records you want to return
     * @param level
     * @return
     */
    public NBackInfoVO getNbackHistoryInfo(String username,int bound,int level){
        NBackInfoVO nBackInfoVO=new NBackInfoVO();
        List<Double> arrayList=new ArrayList<>();
        List<NBackRecord> nBackRecords;
        if(level!=0){
            nBackRecords=nBackRecordMapper.selectLatestNbackRecord(username,level,bound);
        }
        else {
            nBackRecords=nBackRecordMapper.selectAllLatestNbackRecord(username,bound);
        }

        for(NBackRecord nBackRecord:nBackRecords){
            arrayList.add(nBackRecord.getPercentage());
        }
        nBackInfoVO.setRecords(arrayList);
        return nBackInfoVO;
    }

    /**
     * get correctness rank information
     * @param username
     * @param level
     * @return
     */
    public SortVO getCorrectnessRank(String username,int level){
        SortVO sortVO=new SortVO();
        List<SortElementVO> sortElementVOS=nBackRecordMapper.getCorrectnessRank(level);
        ;
        List<Double> res=new ArrayList<>();


        //fill 0 if some gaps have no information
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
        MyRankVO myRankVO=nBackRecordMapper.getMyCorrectnessRank(username,level);
        if(myRankVO==null){
            return sortVO;
        }
        //find the gap position for my score
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



    /**
     * save n-back experiment record every block,,this is not used now ,but maybe you need
     * @param nBackRecordVO
     * @return
     */
    public boolean save(NBackRecordVO nBackRecordVO){
        String username=nBackRecordVO.getUsername();
        User user=userService.getByUsername(username);
        if(user==null){
            throw new GlobalException(ResultEnum.User_Not_Exist);
        }

        Date date=new Date();
        String currentTime= DateUtil.convert(date);

        NBackRecord nBackRecord=new NBackRecord();
        nBackRecord.setUsername(nBackRecordVO.getUsername());
        nBackRecord.setBlock(nBackRecordVO.getBlock());
        nBackRecord.setIncorrect(nBackRecordVO.getIncorrect());
        nBackRecord.setMissed(nBackRecordVO.getMissed());
        nBackRecord.setTrials(nBackRecordVO.getTrials());
        nBackRecord.setLevel(nBackRecordVO.getLevel());
        nBackRecord.setPercentage(nBackRecordVO.getPercentage());
        nBackRecord.setCreateTime(date);
        Integer insert=nBackRecordMapper.insert(nBackRecord);

        String researcherId=userService.getResearcherId(nBackRecordVO.getUsername());
        String path=createLocalFile(researcherId,nBackRecordVO,currentTime);
        if(researcherId!=null){
            dropBoxService.upload(path,researcherId);
        }


        return insert>0;
    }

    /**
     * create local file for every block,this is not used now
     * @param researcherId
     * @param nBackRecordVO
     * @param currentTime
     * @return
     */
    private String createLocalFile(String researcherId, NBackRecordVO nBackRecordVO, String currentTime){

        if(researcherId==null){
            researcherId="NoResearcher";
        }

        String username=nBackRecordVO.getUsername();
        String fileName=currentTime.substring(0,10);

        String path= researcherId + "/" + username + "/" + "nback/" + fileName+".txt";
        String localPath = filePathConfig.getLocalPrefix() + path;

        try {
            File file = new File(localPath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();

            }
            if (!file.exists()){
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("username,"+"block,"+"trials,"+"level,"+"missed,"+"incorrect,"+"percentage,"+"updateTime"+"\r\n");
                out.write(username+","+nBackRecordVO.getBlock()+","+nBackRecordVO.getTrials()+","
                        +nBackRecordVO.getLevel()+","+nBackRecordVO.getMissed()+","+nBackRecordVO.getIncorrect()
                        +","+nBackRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }
            else {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file, true)));
                out.write(username+","+nBackRecordVO.getBlock()+","+nBackRecordVO.getTrials()+","
                        +nBackRecordVO.getLevel()+","+nBackRecordVO.getMissed()+","+nBackRecordVO.getIncorrect()
                        +","+nBackRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return path;

    }





}
