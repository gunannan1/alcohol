package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class SSTRecordListVO {
    /**
     * used in save all in sst
     */
    List<SSTRecordVO> records;
}
