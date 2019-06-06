package com.app.alcohol.vo;


import lombok.Data;

import java.util.List;

@Data
public class NBackRecordListVO {
    /**
     * used in save all nback records
     */
    List<NBackRecordVO> records;
}
