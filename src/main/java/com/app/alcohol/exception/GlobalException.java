package com.app.alcohol.exception;

import com.app.alcohol.enums.ResultEnum;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {
    private Integer status;
    private String msg;


    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.status = resultEnum.getStatus();
        this.msg=resultEnum.getMessage();
    }

    public GlobalException(Integer status, String message) {
        super(message);
        this.status = status ;
        this.msg=message;
    }


}
