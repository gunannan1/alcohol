package com.app.alcohol.filter;



import com.app.alcohol.config.CurrentUser;
import com.app.alcohol.config.JwtProperties;
import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.utils.JwtTokenUtil;
import com.app.alcohol.utils.RenderUtil;
import com.app.alcohol.vo.ResponseVO;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *filter requests without token
 */
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");


        //The management system need token to use
        if(request.getServletPath().startsWith("/management")){
            //login or logout function do not need token
            if (request.getServletPath().startsWith("/management/admin")) {
                chain.doFilter(request, response);
                return;
            }


            final String requestHeader = request.getHeader(jwtProperties.getTokenHeader());
            String authToken = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                authToken = requestHeader.substring(7);

                System.out.println(requestHeader);

                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                if(username == null){
                    return;
                } else {
                    CurrentUser.saveUserId(username);
                    System.out.println(username);
                }


                //check if token is expired or is invalid
                try {
                    boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                    if (flag) {
                        RenderUtil.renderJson(response, ResponseVO.error(ResultEnum.TOKEN_EXPIRED.getCode(), ResultEnum.TOKEN_EXPIRED.getMessage()));
                        return;
                    }
                } catch (JwtException e) {
                    RenderUtil.renderJson(response, ResponseVO.error(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getMessage()));
                    return;
                }
            } else {
                RenderUtil.renderJson(response, ResponseVO.error(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getMessage()));
                return;
            }
            chain.doFilter(request, response);
        }

    }


}