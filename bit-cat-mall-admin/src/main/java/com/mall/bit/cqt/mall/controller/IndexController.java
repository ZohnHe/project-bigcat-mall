package com.mall.bit.cqt.mall.controller;


import com.mall.bit.cqt.mall.entity.Comment;
import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.service.CommentService;
import com.mall.bit.cqt.mall.service.ItemService;
import com.mall.bit.cqt.mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
    @Api(value = "首页",tags = "首页模块")
    @RequestMapping(value = {"/user"})
   public class IndexController {

        @Autowired
        ItemService itemService;

        @Autowired
        UserService userService;

        @Autowired
        CommentService commentService;

        //跳转登录页
        @ApiOperation(value = "跳转首页")
        @RequestMapping(value = {"/index"},method = RequestMethod.GET)
        public String indexTest() {

            return "/index";
        }

    @ApiOperation(value = "跳转个人中心")
    @RequestMapping(value = "/personal",method = RequestMethod.GET)
    public String SettingTest() {

        return "/personal";
    }

    @ApiOperation(value = "跳转详情")
    @RequestMapping(value = "/item_show",method = RequestMethod.GET)
    public String itemshowTest() {

        return "/item_show";
    }
    @ResponseBody
    @PostMapping(value = "getMsg")
    public User getMsg(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    @ResponseBody
    @PostMapping(value = "save")
    public String save(User user,HttpServletRequest request){
        User user1 = (User) request.getSession().getAttribute("user");
        user.setId(user1.getId());
        userService.update(user);
        return "1";
    }


    @ApiOperation(value = "首页跳转后台传id")
    @RequestMapping(value = "/item_user",method = RequestMethod.GET)
    public String userTest(HttpServletRequest request,String id) {

        request.getSession().setAttribute("id",id);

        return "/item_show";
    }



    @ApiOperation(value = "商品详情", notes = "自定义请求头sessionId，sessionId的值是登陆接口返回的")
    @RequestMapping(value = "/details",method = RequestMethod.POST)
    @ResponseBody
    //ResponseBody封包,@RequestBody解包，自定义协议、二进制字符串
    public Item detailsTest(HttpServletRequest request) {
        int id1 = Integer.parseInt((String) request.getSession().getAttribute("id"));
        List<Item> items = itemService.itemJoins((long) id1);
        if (items.size()>0 && items !=null){
            return items.get(0);
        }

         return null;


    }

        @ResponseBody
        @RequestMapping(value = "/show",method = RequestMethod.POST)
        @ApiOperation(value = "首页展示",notes = "POST请求登录")
        public List<Item> index(HttpServletRequest request) {

            List<Item> select = itemService.selectList();

            return  select;
        }

    @ApiOperation(value = "用户评论", notes = "自定义请求头sessionId，sessionId的值是登陆接口返回的")
    @RequestMapping(value = "/cent",method = RequestMethod.POST)
    @ResponseBody
    //ResponseBody封包,@RequestBody解包，自定义协议、二进制字符串
    public List<Comment> commentTest(HttpServletRequest request, @RequestBody Comment comment){
        List<Comment> comments = commentService.selectAll();
        String user1 = null;
        if (comments.size()>0 && comments !=null){
            user1 =  comments.get(comments.size()-1).getName();
        }

        User user = (User) request.getSession().getAttribute("user");

        String user2 = user.getUsername();

        comment.setUsers(user2);
        comment.setUsername(user2+" 回复 "+user1);

        Integer integer = commentService.addUser(comment.getUsers(), comment.getUsername(), comment.getPassword());
        System.out.println(integer+"ooookkkk");


        return comments;
    }

    @ApiOperation(value = "用户评论查询", notes = "自定义请求头sessionId，sessionId的值是登陆接口返回的")
    @RequestMapping(value = "/centselect",method = RequestMethod.POST)
    @ResponseBody
    //ResponseBody封包,@RequestBody解包，自定义协议、二进制字符串
    public List<Comment> commentselectTest(HttpServletRequest request){

        List<Comment> comments = commentService.selectAll();
        System.out.println(comments +"iiiiii");

        return comments;
    }


}
