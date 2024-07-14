package com.example.common;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtTokenUtils {
    private static  UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign));

    }
    public static User getCurrentUser(){
        String token = null;
        try{
            HttpServletRequest requset = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = requset.getParameter("token");
            if(StrUtil.isBlank(token)){
                token = requset.getParameter("token");
            }
            if(StrUtil.isBlank(token)){
                log.error("Failed to get token, token: {}",token);
                return null;
            }
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.findById(userId);
        }catch (Exception e){
            log.error("Failed to get current user info, token: {}", token, e);
            return null;
        }
    }
}
