package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.User;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 主页面跳转
 */

@Controller
@Api(value = "主页", tags = "主页模板")
@RequestMapping(value = "/backadmin")
public class BackMainController {
    @GetMapping(value = {"main"})
    public String main(){
        return "/main";
    }

    /**
     * 从session中获取信息，返回登录id名
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "getMsg")
    public User getMsg(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }
}
