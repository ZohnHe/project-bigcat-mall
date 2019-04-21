package com.mall.bit.cqt.mall.controller;


import com.mall.bit.cqt.mall.common.BaseResponse;
import com.mall.bit.cqt.mall.entity.*;
import com.mall.bit.cqt.mall.service.BackLoginService;
import com.mall.bit.cqt.mall.service.LoginService;
import com.mall.bit.cqt.mall.utils.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(value = "登录",tags = "登录模块")
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //跳转登录页
    @GetMapping(value = {"", "/login"})
    public String login() {

        return "/login";
    }
    /**
     * 登录
     *登录id与密码校验
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "POST请求登录")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public BaseResponse<ResponseEntity> userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountEntity accountEntity){
              ResponseEntity responseEntity = new ResponseEntity();
              responseEntity.setUrl("/");
              //根据解包json信息查找用户信息
              User user = loginService.Login(accountEntity.getLoginId(),accountEntity.getLoginPwd());

            if (user != null) {
                    //TODO 判断书否勾选记住我并写入cookie
                    if(accountEntity.getRemember() == true){
                        String md5=accountEntity.getLoginPwd();
                        if (accountEntity.getLoginPwd().length()<=16) {
                            md5 = DigestUtils.md5DigestAsHex(accountEntity.getLoginPwd().getBytes());
                        }
                        CookieUtils.setCookie(request,response,"loginId",accountEntity.getLoginId(),7*24*60*60);
                        CookieUtils.setCookie(request,response,"loginPwd",md5,7*24*60*60);
                        CookieUtils.setCookie(request,response,"rememberme","true",7*24*60*60);
                    }
                    if (accountEntity.getRemember() == false){
                        CookieUtils.deleteCookie(request,response,"loginId");
                        CookieUtils.deleteCookie(request,response,"loginPwd");
                        CookieUtils.deleteCookie(request,response,"rememberme");
                    }
                //TODO 判断是否有对象，没有则密码账号错误
                   responseEntity.setSuccess("1");
                   responseEntity.setMsg("登陆成功");
                   responseEntity.setSessionId(request.getSession().getId());
                   responseEntity.setUrl("/admin/main");
                   request.getSession().setAttribute("user",user);

            }else {
                //TODO 账号或密码错误
                responseEntity.setMsg("账号或密码错误");
                responseEntity.setSuccess("2");

            }
                return new BaseResponse(responseEntity);
        }

    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "POST请求注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public BaseResponse<RegisterResponseEntity> register(HttpServletRequest request, @RequestBody RegisterAccountEntity registerAccountEntity){
        RegisterResponseEntity registerResponseEntity = new RegisterResponseEntity();
        registerResponseEntity.setUrl("/");
        //根据解包json信息查找用户信息
        Integer ret = loginService.add(registerAccountEntity.getRegister_username(), registerAccountEntity.getRegister_password());
        if (ret == 2){
            //TODO 用户名已存在
            registerResponseEntity.setMsg("用户名已存在");
            registerResponseEntity.setSuccess("2");
        }else if(ret == 1){
            registerResponseEntity.setMsg("注册成功！");
            registerResponseEntity.setSuccess("1");
            registerResponseEntity.setUrl("/user/login");
        }else{
            registerResponseEntity.setMsg("失败");
            registerResponseEntity.setSuccess("0");
        }

        return new BaseResponse(registerResponseEntity);
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
