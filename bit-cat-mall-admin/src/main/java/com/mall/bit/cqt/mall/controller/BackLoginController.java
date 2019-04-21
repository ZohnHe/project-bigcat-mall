package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.ResponseEntity;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.service.BackLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(value = "登录",tags = "登录模块")
@RequestMapping(value = {"/backuser"})
public class BackLoginController {

    @Autowired
    private BackLoginService loginService;

    //跳转登录页
    @GetMapping(value = {"", "login"})
    public String login() {
        return "/backlogin";
    }

    /**
     * 登录
     *
     * @param loginId 用户名，邮箱，电话号码
     * @param loginPwd 密码
     * @param verify 验证码
     * @return
     */
    @ResponseBody
    @PostMapping(value = "userLogin")
    @ApiOperation(value = "管理员登录", notes = "POST请求登录")
    public ResponseEntity login(String loginId, String loginPwd, String verify, HttpServletRequest request) {
        ResponseEntity responseEntity = new ResponseEntity();
        //TODO 判断验证码是否正确
        if (StringUtils.isNotBlank(verify)) {
            User user = loginService.Login(loginId);
            if (user != null) {
                //TODO 判断密码是否正确
                if (StringUtils.equals(loginPwd, user.getPassword())) {
                    request.getSession().setAttribute("user",user);
                    responseEntity.setSuccess("1");
                    return responseEntity;
                }
            }
                //TODO 账号或密码错误
                responseEntity.setSuccess("2");
                return responseEntity;
            }
            else {
                //TODO 验证码错误
                responseEntity.setSuccess("0");
                return responseEntity;
            }
        }

    /**
     * 获取数据库中的盐值
     * @param loginId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "salt")
    @ApiOperation(value = "获取数据库中的盐值", notes = "GET 请求")
    public String getSalt(String loginId){
        User user = loginService.Login(loginId);
        if(user != null){
            return user.getSalt();
        }
        return null;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @GetMapping(value = "logout")
    @ApiOperation(value = "管理员注销", notes = "GET 请求")
    public String logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/admin/login";
    }
}
