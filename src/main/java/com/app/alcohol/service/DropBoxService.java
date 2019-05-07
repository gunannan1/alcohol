package com.app.alcohol.service;

import com.app.alcohol.config.FilePathConfig;
import com.app.alcohol.exception.GlobalException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.app.alcohol.enums.ResultEnum.NO_UPLOAD_TOKEN;

@Service
public class DropBoxService {
    @Autowired
    ResearcherService researcherService;

    @Autowired
    FilePathConfig filePathConfig;

    /**
     * Async upload the file to dropbox
     * @param path
     * @param researcherId
     */
    @Async("asyncExecutor")
    public void upload(String path,String researcherId){
        String token=researcherService.getToken(researcherId);
        if(token==null){
            throw new GlobalException(NO_UPLOAD_TOKEN);
        }

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/upload").build();
        DbxClientV2 client = new DbxClientV2(config, token);

        try{
            client.files().deleteV2(filePathConfig.getDropboxPrefix()+path);
        }catch (Exception e){
            //do nothing here

        }

        try (InputStream in = new FileInputStream(filePathConfig.getLocalPrefix()+path)) {

            FileMetadata metadata = client.files().uploadBuilder(filePathConfig.getDropboxPrefix()+path)
                    .uploadAndFinish(in);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


//    public static void main(String[] args) {
//        String token="aEmzSeB1A9AAAAAAAAAAI3gGJH4Z5kmVIOr0F52x9WjwoIe_NgBbDJtvGSO1kyDJ";
//        if(token==null){
//            throw new GlobalException(NO_UPLOAD_TOKEN);
//        }
//
//        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/upload").build();
//        DbxClientV2 client = new DbxClientV2(config, token);
//
//        try{
//            client.files().deleteV2("/test/b.txt");
//        }catch (Exception e){
//
//        }
//
//        try (InputStream in = new FileInputStream("/Users/gunannan/Downloads/ffff.txt")) {
//            FileMetadata metadata = client.files().uploadBuilder("/test/a.txt")
//                    .uploadAndFinish(in);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
