package com.example.backend.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    // 컨트롤러에 접근하기 전에 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("[BEGIN]");
        //log.info("[REQUEST] {} : {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
        return true;
    }

    // 컨트롤러를 접근한 후, 화면으로 결과를 전달하기 전에 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //log.info("[END]");
    }
}
