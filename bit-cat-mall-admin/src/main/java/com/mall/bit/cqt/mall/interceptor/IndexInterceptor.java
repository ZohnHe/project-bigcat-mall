package com.mall.bit.cqt.mall.interceptor;

import com.mall.bit.cqt.mall.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //根据Session中有无User对象来判断是否登录
        boolean flag = true;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        //为空则拦截跳转到登录页
        if (user == null){
            httpServletResponse.sendRedirect("/user/login");
            flag = false;
        }
        //不为空则放行
        else {
            flag = true;
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
