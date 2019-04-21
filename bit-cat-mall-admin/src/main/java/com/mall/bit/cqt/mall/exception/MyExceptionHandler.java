package com.mall.bit.cqt.mall.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"errMsg\":\"系统繁忙，请稍后再试！\"}");
        } catch (IOException e) {

        }
        return new ModelAndView();
    }

}

