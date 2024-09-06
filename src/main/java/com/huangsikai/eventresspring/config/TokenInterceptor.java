package com.huangsikai.eventresspring.config;

import ch.qos.logback.core.util.StringUtil;
import com.alibaba.druid.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    JwtConfig jwtConfig;
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
           jwtConfig.getTokenClaim(token);

       }catch (Exception e)
       {
          // throw  new RuntimeException(e.getMessage());
           sendError(response,"token过期");
           return false;
       }
       return true;
    }
    private void sendError(HttpServletResponse response, String msg) throws IOException {
       response.setStatus(401);
       response.setContentType("application/json;charset=utf-8");
       response.setCharacterEncoding("utf-8");
       response.getWriter().write(msg);
    }
}
