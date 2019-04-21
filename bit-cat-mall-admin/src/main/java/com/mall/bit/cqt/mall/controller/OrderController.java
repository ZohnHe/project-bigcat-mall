package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.*;
import com.mall.bit.cqt.mall.service.*;
import com.mall.bit.cqt.mall.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sorder")
@Api(value = "/sorder", tags = "订单接口")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private TbOrderService tbOrderService;

    @Autowired
    private TbOrderItemService tbOrderItemService;

    @Autowired
    private TbAddressService tbAddressService;

    //去确认订单页
    @GetMapping(value = "confirm/order")
    @ApiOperation(value = "去确认订单页",notes = "confirm/order GET 请求")
    @ResponseBody
    public String confirmOrder( HttpSession session){
        Receiptinfo receiptinfo = (Receiptinfo) session.getAttribute("receiptinfo");
        if(receiptinfo != null){
            return "1";
        }
        return "0";
    }

    //去添加订单页
    @GetMapping(value = "go/set/order")
    @ApiOperation(value = "去添加订单页",notes = "go/set/order GET 请求")
    public String goSetOrder(){
        return "/udai_shopcart_pay";
    }

    //获取地址信息
    @GetMapping(value = "get/address")
    @ApiOperation(value = "获取地址信息",notes = "get/address GET 请求")
    @ResponseBody
    public Receiptinfo getAddress( HttpSession session){
        User  user = (User) session.getAttribute("user");

        Receiptinfo receiptinfo = (Receiptinfo) session.getAttribute("receiptinfo");
        //返回地址
        User user1 = userService.getByLoginId(user);

        List<TbAddress> tbAddresses = tbAddressService.selectByUserId(user1.getId());
        //数据库里有默认地址
        if(tbAddresses.size() > 0 && receiptinfo != null){
            TbAddress tbAddress = tbAddresses.get(0);
            receiptinfo.setoId(user1.getId().toString());
            receiptinfo.setoPhone(user1.getPhone());
            receiptinfo.setoName(user1.getUsername());
            receiptinfo.setoAddress(tbAddress.getOAddress());
        }
        //数据库里没默认地址，需要用户手动添加地址
        else if(receiptinfo != null){
            TbAddress tbAddress = new TbAddress();
            receiptinfo.setoId(user1.getId().toString());
            receiptinfo.setoPhone(user1.getPhone());
            receiptinfo.setoName(user1.getUsername());
        }

        return receiptinfo;
    }


    //添加订单
    @GetMapping("set/order")
    @ApiOperation(value = "添加订单",notes = "/set/order GET 请求")
    @ResponseBody
    public String setOrder(TbAddress tbAddress ,HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Receiptinfo receiptinfo = (Receiptinfo) session.getAttribute("receiptinfo");

        User user1 = userService.getByLoginId(user);

        if(user1 != null && receiptinfo != null && tbAddress != null){
            TbOrder tbOrder = new TbOrder();
            TbOrderItem tbOrderItem = new TbOrderItem();

            tbOrder.setBuyerNick(user1.getUsername());
            tbOrder.setUserId(user1.getId());
            tbOrder.setOrderId(UUID.randomUUID().toString());
            tbOrder.setCreateTime(new Date());
            Double totalPrice = 0.0;

            //添加一个地址信息
            tbAddress.setOId(tbOrder.getOrderId());
            tbAddress.setUserId(user1.getId());

            Integer insertAddress = tbAddressService.insertAddress(tbAddress);


            Set<Orderdetail> orderdetailSet = receiptinfo.getOrderdetailSet();
            for (Orderdetail orderdetail : orderdetailSet) {
                totalPrice += orderdetail.getoNum()* orderdetail.getoMoney();
                //TODO 添加多个订单详情表,设置图片地址
                tbOrderItem.setItemId( orderdetail.getItem().getId().intValue());
                tbOrderItem.setOrderId(tbOrder.getOrderId());
                tbOrderItem.setNum(orderdetail.getoNum());
                tbOrderItem.setTitle(orderdetail.getItem().getTitle());
                tbOrderItem.setPrice(orderdetail.getoMoney().longValue());
                tbOrderItem.setTotalFee(totalPrice.longValue());
                tbOrderItem.setUserId(user1.getId().intValue());
                tbOrderItem.setPicPath(orderdetail.getItem().getImage());
                tbOrderItemService.insertOrder(tbOrderItem);
            }
            tbOrder.setPayment(totalPrice);
            tbOrder.setStatus(2);
            tbOrder.setPaymentTime(new Date());

            Integer result = tbOrderService.insertOrder(tbOrder);
            //添加成功
            if(result != 0){
                //TODO 清空购物车
                //session.removeAttribute("receiptinfo");
                return "1";
            }
        }

        return "0";
    }


    @GetMapping(value = "to/order")
    @ApiOperation(value = "去订单页面",notes = "/to/order GET 请求")
    public String toOrder(){
        return "/udai_order";
    }

    @GetMapping(value = "get/all/order")
    @ApiOperation(value = "获取所有的订单",notes = "get/all/order GET 请求")
    @ResponseBody
    public List<RespOrder> getAllOrder(HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        User user1 = userService.getByLoginId(user);

        List<TbOrder> tbOrders = tbOrderService.selectByUserId(user1.getId());

        List<RespOrder> list = new ArrayList<RespOrder>();

        //设置所有的订单
        for (TbOrder tbOrder : tbOrders) {
            RespOrder respOrder = new RespOrder();
            respOrder.setUsername(user1.getUsername());
            respOrder.setOrderId(tbOrder.getOrderId());
            respOrder.setPayMent(tbOrder.getPayment());
            list.add(respOrder);
        }
        return list;
    }

    @GetMapping(value = "to/order/item")
    @ApiOperation(value = "去订单详情页面",notes = "/to/order/item GET 请求")
    public String toOrderItem(String orderId,HttpSession session){
        session.setAttribute("orderId",orderId);
        return "/udai_order_item";
    }

    @GetMapping(value = "get/order/item")
    @ApiOperation(value = "获取订单详情",notes = "get/order/item GET请求")
    @ResponseBody
    public List<RespOrderItem> getOrderItem(HttpSession session){

        String orderId = (String) session.getAttribute("orderId");
        List<TbOrderItem> tbOrderItems = tbOrderItemService.selectByOrderId(orderId);
        List<TbAddress> tbAddresses = tbAddressService.selectByOrderId(orderId);

        List<RespOrderItem> list = new ArrayList<RespOrderItem>();

        if(tbOrderItems.size() > 0 && tbAddresses.size() > 0){
            //添加订单详情
            for (TbOrderItem tbOrderItem : tbOrderItems) {
                //地址
                RespOrderItem respOrderItem = new RespOrderItem();

                TbAddress tbAddress = tbAddresses.get(0);
                respOrderItem.setOrderId(tbOrderItem.getOrderId());
                respOrderItem.setAddress(tbAddress.getOAddress());
                respOrderItem.setNum(tbOrderItem.getNum());
                respOrderItem.setPhone(tbAddress.getOPhone());
                respOrderItem.setPicPath(tbOrderItem.getPicPath());
                respOrderItem.setPrice(tbOrderItem.getPrice());
                respOrderItem.setTitle(tbOrderItem.getTitle());
                respOrderItem.setTotalFee(tbOrderItem.getTotalFee());
                respOrderItem.setUsername(tbAddress.getOUsername());

                list.add(respOrderItem);
            }

            //TODO 清空购物车
            session.removeAttribute("receiptinfo");
        }

        return list;
    }

}
