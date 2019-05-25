package com.app.alcohol.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * Setting VO
 */

@Data
public class SettingVO {
    /**
     * if the result can be shown to users 0 show,1 not
     */
    private Integer showResult;

}
