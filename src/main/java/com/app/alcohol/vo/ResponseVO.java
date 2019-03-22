package com.app.alcohol.vo;

import com.app.alcohol.enums.ResultEnum;
import lombok.Data;

/**
 * response
 *
 */
@Data
public class ResponseVO<M> {

    //response status
    private int status;

    // response message
    private String msg;

    // response data
    private M data;

    private ResponseVO(){}

    public static<M> ResponseVO success(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(ResultEnum.SUCCESS.getStatus());
        responseVO.setMsg(ResultEnum.SUCCESS.getMessage());
        responseVO.setData(m);
        return responseVO;
    }



    public static<M> ResponseVO success(ResultEnum resultEnum){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(resultEnum.getStatus());
        responseVO.setMsg(resultEnum.getMessage());

        return responseVO;
    }

    public static<M> ResponseVO error(ResultEnum resultEnum){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(resultEnum.getStatus());
        responseVO.setMsg(resultEnum.getMessage());

        return responseVO;
    }

    public static<M> ResponseVO error(int status, String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(status);
        responseVO.setMsg(msg);

        return responseVO;
    }



}
