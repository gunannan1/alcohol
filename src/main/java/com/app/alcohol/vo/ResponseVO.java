package com.app.alcohol.vo;

import com.app.alcohol.enums.ResultEnum;
import lombok.Data;

/**
 * response message
 */
@Data
public class ResponseVO<M> {

    /**
     * response code
     */
    private Integer code;

    /**
     * response message
     */
    private String message;

    /**
     * response data
     */
    private M data;

    private ResponseVO(){}

    public static<M> ResponseVO success(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(ResultEnum.SUCCESS.getCode());
        responseVO.setMessage(ResultEnum.SUCCESS.getMessage());
        responseVO.setData(m);
        return responseVO;
    }



    public static<M> ResponseVO success(ResultEnum resultEnum){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(resultEnum.getCode());
        responseVO.setMessage(resultEnum.getMessage());

        return responseVO;
    }

    public static<M> ResponseVO error(ResultEnum resultEnum){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(resultEnum.getCode());
        responseVO.setMessage(resultEnum.getMessage());

        return responseVO;
    }

    public static<M> ResponseVO error(int code, String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(code);
        responseVO.setMessage(msg);

        return responseVO;
    }



}
