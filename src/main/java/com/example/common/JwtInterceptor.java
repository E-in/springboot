package com.example.common;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.entity.User;
import cn.hutool.core.util.StrUtil;
import com.example.exception.CustomException;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Jwt拦截器
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }
        if(StrUtil.isBlank(token)){
            throw new CustomException("No token, Please log in again");
        }
        String userId;
        User user;
        try{
            userId = JWT.decode(token).getAudience().get(0);
            user = userService.findById(userId);
        }catch (Exception e){
            String errMsg = "Token failed, Please log in again";
            log.error(errMsg + ",token=" + token, e);
            throw new CustomException(errMsg);
        }
        if(user ==null){
            throw new CustomException("User not exist, Please log in again");
        }
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new CustomException("Token failed, Please log in again");
        }
        return true;
    }
}
