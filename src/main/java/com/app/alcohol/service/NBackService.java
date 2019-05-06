package com.app.alcohol.service;

import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.dao.NBackRecordMapper;
import com.app.alcohol.entity.NBackRecord;
import com.app.alcohol.utils.DateUtil;
import com.app.alcohol.vo.NBackRecordVO;
import com.app.alcohol.vo.SSTRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

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

        String path= researcherId + "/" + username + "/" + "nback/" + currentTime+".txt";
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
            out.write("username,"+"level,"+"percentage\r\n");
            out.write(username+","+nBackRecordVO.getLevel()+","+nBackRecordVO.getPercentage()+"\r\n");
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;

    }

}
