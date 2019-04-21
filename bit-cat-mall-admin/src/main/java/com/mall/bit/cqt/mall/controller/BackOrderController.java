package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseController;
import com.mall.bit.cqt.mall.entity.Order;
import com.mall.bit.cqt.mall.entity.PageInfo;
import com.mall.bit.cqt.mall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 *
 * @Author wodef
 * @Date 2019-02-20
 * @description
 *
 * */
@Controller
@Api(value = "订单",tags = "订单模块")
@RequestMapping(value = {"order"})
public class BackOrderController extends AbstractBaseController <Order, OrderService> {

    @Resource
    private OrderService orderService;
    /**
     * 查询所有数据并跳转到列表页
     * @param
     * @return
     */
    @GetMapping(value = {"list"})
    @ApiOperation(value = "跳转订单页", notes = "GET请求")
    public String list() {
        return "/order/list";
    }

    /**
     * 跳转到管理用户页面
     * 如果是编辑，则显示用户信息
     * @return
     */
    @GetMapping(value = {"update"})
    @ApiOperation(value = "跳转修改订单页", notes = "GET请求")
    public String update(String id,HttpServletRequest request) {
        Order order = orderService.getById(Long.valueOf(id));
        request.getSession().setAttribute("order",order);
        return "/order/update";
    }

    @GetMapping(value = {"insert"})
    @ApiOperation(value = "跳转添加订单页", notes = "GET请求")
    public String insert(){
        return "/order/insert";
    }

    /**
     * 从session中获取信息，返回订单信息
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "getMsg")
    public Order getMsg(HttpServletRequest request){
        Order order = (Order) request.getSession().getAttribute("order");
        return order;
    }

    /**
     * 修改和添加
     * @param order
     * @param redirectAttributes
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = {"save"})
    @ApiOperation(value = "保存商品", notes = "POST请求")
    public String save(Order order, RedirectAttributes redirectAttributes){

//        if (beanValidator(order, redirectAttributes)) {
//            service.save(order);
//             addMessage(redirectAttributes, "保存成功");
//        }
        orderService.save(order);

        return "1";
    }

    @GetMapping(value = {"delete"})
    @ApiOperation(value = "删除商品", notes = "GET请求")
    public String delete(Order order){
        orderService.delete(order);
        return  "/order/list" ;
    }

    /**
     * 展示分页信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "page")
    @ApiOperation(value = "分页展示商品", notes = "GET请求")
    public PageInfo<Order> pageUser(Order entity, HttpServletRequest request){
        //从客户端获取分页所需参数信息
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength =request.getParameter("length");
        //转换格式
        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 10 : Integer.parseInt(strLength);
        //创建分页信息实例化对象，接收分页信息
        PageInfo <Order> pageInfo = service.page(entity,start,length,draw);

        return pageInfo;
    }
}
