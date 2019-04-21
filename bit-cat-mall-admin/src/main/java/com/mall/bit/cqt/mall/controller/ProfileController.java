package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.service.ProfileService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "个人信息修改模块")
@RequestMapping(value = {"profile"})
public class ProfileController {

    @Resource
    private ProfileService profileService;

    //跳转个人信息界面
    @GetMapping(value = {"info"})
    public String info() {
        return "/profile/info";
    }


    //修改信息
    @ResponseBody
    @PostMapping(value = {"save"})
    public String update(User user, HttpServletRequest request){
        profileService.update(user);
        return "1";
    }

}
