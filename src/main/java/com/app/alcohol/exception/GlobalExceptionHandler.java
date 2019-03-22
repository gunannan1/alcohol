package com.app.alcohol.exception;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseVO handle(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage());
        if(e instanceof GlobalException){
            GlobalException ex= (GlobalException)e;
            return ResponseVO.error(ex.getStatus(),ex.getMessage());
        }
        else {
            return ResponseVO.error(ResultEnum.Error);
        }

    }




}
