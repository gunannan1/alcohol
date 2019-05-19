package com.app.alcohol.controller;

import com.app.alcohol.entity.Researcher;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import com.app.alcohol.service.ResearcherService;
import com.app.alcohol.vo.ResearcherVO;
import com.app.alcohol.vo.ResponseVO;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/researcher/")
@RestController
public class ResearcherController {

    @Autowired
    ResearcherService researcherService;

    /**
     * create researcher
     * @param  researcherVO
     * @return
     */
    @RequestMapping(value="create",method = RequestMethod.POST)
    public ResponseVO register(@RequestBody ResearcherVO researcherVO){
        if(researcherVO.getUsername() == null || researcherVO.getUsername().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Username);
        }
        if(researcherVO.getEmail() == null || researcherVO.getEmail().trim().length()==0){
            throw new GlobalException(ResultEnum.Empty_Email);
        }

        //if username is repeated,return true
        boolean repeatedUserName=researcherService.repeatedUserName(researcherVO.getUsername());

        boolean isSuccess=true;

        //if username is not repeated,it can be registered
        if(!repeatedUserName){
            isSuccess = researcherService.create(researcherVO);
        }
        else {
            throw new GlobalException(ResultEnum.Repeated_Username);
        }

        //return the userVo entity to the mobile since they may need cache it locally
        if(isSuccess){
            return ResponseVO.success(researcherVO);
        }else{
            throw new GlobalException(ResultEnum.Register_Failed);

        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getList(ResearcherVO researcherVO,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<Researcher> page = researcherService.list(researcherVO, pageSize, pageNum);
        Map<String, Object> result = new HashMap<>();
        result.put("total", page.getTotal());
        result.put("list", page.getRecords());
        return ResponseVO.success(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("id") int id) {
        boolean isSuccess=researcherService.delete(id);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO get(@PathVariable int id) {
        ResearcherVO researcherVO=researcherService.get(id);
        return ResponseVO.success(researcherVO);

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable int id, @RequestBody ResearcherVO researcherVO) {
        boolean isSuccess = researcherService.update(id, researcherVO);
        if(isSuccess){
            return ResponseVO.success(ResultEnum.SUCCESS);
        }
        return ResponseVO.error(ResultEnum.Error);
    }




}
