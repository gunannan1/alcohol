package com.app.alcohol.utils;

import com.alibaba.fastjson.JSON;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new GlobalException(ResultEnum.Write_Error);
        }
    }
}
