package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.Orderdetail;
import com.mall.bit.cqt.mall.entity.Receiptinfo;
import com.mall.bit.cqt.mall.service.ItemService;
import com.mall.bit.cqt.mall.service.SorderService;
import com.mall.bit.cqt.mall.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping(value = "/shopping")
@Api(value = "/shopping", tags = "购物车接口")
public class ShoppingCarController {

	@Autowired
	private SorderService sorderService;

	@Autowired
	private ItemService itemService;



	@ResponseBody
	@PostMapping(value="add")
	@ApiOperation(value = "向购物车添加商品", notes = "/add POST 请求")
	public Receiptinfo addSorder(String id,String num, HttpSession session) {

		// 1:通过 product.id获取当前的商品数据
		Item item1 = itemService.getById(Long.valueOf(id));

		item1.setNum(Integer.valueOf(num));

		//2:判断当前session是否有购物车，如果没有则创建
		if (session.getAttribute("receiptinfo") == null) {
			//创建购物车，存到session中
			session.setAttribute("receiptinfo", new Receiptinfo(new HashSet<Orderdetail>()));
		}
		Receiptinfo receiptinfo = (Receiptinfo) session.getAttribute("receiptinfo");

		//3:把商品信息转化为sorder,并且添加到购物车中(判断购物车是否重复)
		receiptinfo = sorderService.addSorder(receiptinfo, item1);
		//商品总价
		receiptinfo.setoPhone(cluTotal(receiptinfo));
		return receiptinfo;
	}


	@GetMapping("gotocar")
	@ApiOperation(value = "跳转到购物车页面",notes = "/gotocar Get 请求")
	public String gotocar() {
		return "/udai_shopcart";
	}

	@GetMapping("getcar")
	@ApiOperation(value = "获取购物车商品",notes = "/getcar Get 请求")
	@ResponseBody
	public Receiptinfo getcar(HttpSession session) {

		Receiptinfo receiptinfo = (Receiptinfo) session.getAttribute("receiptinfo");


		return receiptinfo;
	}




	//计算总金额
    public String cluTotal(Receiptinfo receiptinfo) {

        double tal=0.0;
        for(Orderdetail temp : receiptinfo.getOrderdetailSet()){

            tal+=temp.getoMoney()*temp.getoNum();
        }

        return String.valueOf(tal);
    }


	@RequestMapping(value="/delete",method= RequestMethod.GET)
	@ApiOperation(value = "删除购物车商品",notes = "/delete GET 请求")
	public String deleteSorder(Item item, HttpSession session) {

		Receiptinfo receiptinfo = (Receiptinfo)session.getAttribute("receiptinfo");

		Set<Orderdetail> set = receiptinfo.getOrderdetailSet();


		 Iterator<Orderdetail> iterator = set.iterator();

		 while(iterator.hasNext()){
			 Orderdetail orderdetail = iterator.next();

			 if(StringUtils.equals(orderdetail.getItem().getId().toString(),item.getId().toString())){
				iterator.remove();
				receiptinfo.setoPhone(String.valueOf((Double.parseDouble(receiptinfo.getoPhone())-orderdetail.getoMoney()*orderdetail.getoNum())));
			}
		 }
		 if( set.size() <= 0){
			 session.removeAttribute("receiptinfo");
		 }
		return "redirect:gotocar";
	}
}
