package com.piles.web.config;

import com.alibaba.fastjson.JSON;
import com.piles.sdk.entity.CommonResponse;
import com.piles.web.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json; charset=utf-8");
//        CommonResponse commonResponse = null;
//        String loginUser = CookieUtil.getLoginUser();
//        if (StringUtils.isBlank(loginUser)) {
//            commonResponse = new CommonResponse<>("1", "用户未登陆");
//        }
//        if(commonResponse != null){
//            httpServletResponse.getWriter().append(JSON.toJSONString(commonResponse));
//            return false;
//        }else {
//            return true;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
