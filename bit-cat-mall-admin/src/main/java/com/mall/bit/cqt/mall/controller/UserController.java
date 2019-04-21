package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseController;
import com.mall.bit.cqt.mall.entity.PageInfo;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/*
 * 用户管理
 * @Author wodef
 * @Date 2019-02-22
 * @description
 *
 * */
@Controller
@Api(value = "用户",tags = "用户模块")
@RequestMapping(value = "/userx")
public class UserController extends AbstractBaseController <User, UserService> {

    @Autowired
    private UserService userService;

    /**
     * 查询所有数据并跳转到列表页
     * @param
     * @return
     */
    @GetMapping(value = {"list"})
    @ApiOperation(value = "跳转用户信息页", notes = "GET请求")
    public String list() {
        return "/userx/list";
    }

    @GetMapping(value = {"insert"})
    @ApiOperation(value = "跳转添加用户页", notes = "GET请求")
    public String insert(){
        return "/userx/insert";
    }

    /**
     * 从session中获取信息，返回用户信息
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "getMsg")
    public User getMsg(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    /**
     * 修改
     * @param user
     * @param redirectAttributes
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = {"save"})
    @ApiOperation(value = "保存商品", notes = "POST请求")
    public String save(User user, RedirectAttributes redirectAttributes){

        userService.save(user);

        return "1";
    }

    @GetMapping(value = {"delete"})
    @ApiOperation(value = "删除商品", notes = "GET请求")
    public String delete(User user){
        userService.delete(user);
        return  "/userx/list" ;
    }

    /**
     * 展示分页信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "page")
    @ApiOperation(value = "分页展示商品", notes = "GET请求")
    public PageInfo <User> pageUser(User entity, HttpServletRequest request){
        //从客户端获取分页所需参数信息
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength =request.getParameter("length");
        //转换格式
        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 10 : Integer.parseInt(strLength);
        //创建分页信息实例化对象，接收分页信息
        PageInfo<User> pageInfo = userService.page(entity,start,length,draw);

        return pageInfo;
    }

}
