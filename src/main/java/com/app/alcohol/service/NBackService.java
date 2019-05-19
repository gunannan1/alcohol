package com.app.alcohol.service;

import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.NBackRecordMapper;
import com.app.alcohol.entity.NBackRecord;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.utils.NBackUtil;
import com.app.alcohol.vo.NBackInfoVO;
import com.app.alcohol.vo.NBackRecordVO;
import com.app.alcohol.vo.NBackResponseVO;
import com.app.alcohol.vo.NbackRequestVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * save n-back experiment record
     * @param nBackRecordVO
     * @return
     */
    public boolean save(NBackRecordVO nBackRecordVO){
        Date date=new Date();
        String currentTime= DateUtil.convert(date);

        NBackRecord nBackRecord=new NBackRecord();
        nBackRecord.setUsername(nBackRecordVO.getUsername());
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
     * create local file
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
                out.write("username,"+"level,"+"percentage,"+"updateTime"+"\r\n");
                out.write(username+","+nBackRecordVO.getLevel()+","+nBackRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }
            else {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file, true)));
                out.write(username+","+nBackRecordVO.getLevel()+","+nBackRecordVO.getPercentage()+","+currentTime+"\r\n");
                out.flush();
                out.close();
            }


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
     * @param bound
     * @param level
     * @return
     */
    public NBackInfoVO getNbackHistoryInfo(String username,int bound,int level){
        NBackInfoVO nBackInfoVO=new NBackInfoVO();
        List<Float> arrayList=new ArrayList<>();
        List<NBackRecord> nBackRecords=nBackRecordMapper.selectLatestNbackRecord(username,level,bound);
        for(NBackRecord nBackRecord:nBackRecords){
            arrayList.add(nBackRecord.getPercentage());
        }
        nBackInfoVO.setRecords(arrayList);
        return nBackInfoVO;
    }


}
