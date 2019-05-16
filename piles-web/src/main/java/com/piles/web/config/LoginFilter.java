//package com.piles.web.config;
//
//import com.piles.web.util.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class LoginFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String path = httpServletRequest.getServletPath();
//        if ("/login".equals(path) || "/error".equals(path)
//        ) {
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        } else {
//            Cookie userCookie = CookieUtil.getCookieByName(httpServletRequest, "user");
//            if (userCookie != null && userCookie.getValue() != null && userCookie.getValue().length() > 0) {
//                String user = userCookie.getValue();
//                Object object = httpServletRequest.getSession().getAttribute(user);
//                if (object != null) {
//                    filterChain.doFilter(httpServletRequest, httpServletResponse);
//                    return;
//                }
//            }
//            httpServletResponse.setStatus(401);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
