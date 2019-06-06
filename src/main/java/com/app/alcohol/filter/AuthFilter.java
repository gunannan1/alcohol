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
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

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

        //Cross-domain config
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        String allowHeaders = "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization";
        response.addHeader("Access-Control-Allow-Headers", allowHeaders);




        //The management system，researcher service and updating setting need jwt token to do authorization
        if(request.getServletPath().startsWith("/management")||request.getServletPath().startsWith("/researcher")
        ||request.getServletPath().startsWith("/setting/update")){

            //but login or logout function do not need token
            if (request.getServletPath().startsWith("/management/admin")) {
                chain.doFilter(request, response);
                return;
            }


            //get jwt content
            final String requestHeader = request.getHeader(jwtProperties.getTokenHeader());
            String authToken = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                authToken = requestHeader.substring(7);
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                //if username exists, save it to current user
                if(username == null){
                    return;
                } else {
                    CurrentUser.saveUserId(username);
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
        }
        //pass when some requests do not need authorization
        chain.doFilter(request, response);

    }


}