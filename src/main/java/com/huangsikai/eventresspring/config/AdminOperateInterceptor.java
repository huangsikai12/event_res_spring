package com.huangsikai.eventresspring.config;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
public class AdminOperateInterceptor implements HandlerInterceptor {

    @Autowired
    JwtConfig jwtConfig;
    Gson gson = new Gson();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token))
        {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token))
        {
            //token为空
            sendError(response,"token为空");
            return false;
        }
       try {
           Claims tokenClaim = jwtConfig.getTokenClaim(token);
           User user = gson.fromJson(tokenClaim.getSubject(), User.class);
           if (user.getRoleId()!=1)
           {
               sendError(response,"操作越权");
               return false;
           }

       }catch (Exception e)
       {
          // throw  new RuntimeException(e.getMessage());
           sendError(response,"token过期");
           return false;
       }
       return true;
    }
    private void sendError(HttpServletResponse response, String msg) throws IOException {
       Result<String> result = new Result<>();
       result.setCode(401);
       result.setMsg(msg);
       result.setData(null);
       response.setStatus(401);
       response.setContentType("application/json;charset=utf-8");
       response.setCharacterEncoding("utf-8");
       response.getWriter().write(gson.toJson(result));
    }
}
