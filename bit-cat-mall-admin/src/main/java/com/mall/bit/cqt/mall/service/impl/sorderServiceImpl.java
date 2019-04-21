package com.mall.bit.cqt.mall.service.impl;


import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.Orderdetail;
import com.mall.bit.cqt.mall.entity.Receiptinfo;
import com.mall.bit.cqt.mall.service.SorderService;
import org.springframework.stereotype.Service;

@Service
public class sorderServiceImpl implements SorderService {


	@Override
	public Receiptinfo addSorder(Receiptinfo receiptinfo, Item item) {
		boolean isHave = false;
		Orderdetail orderdetail = snackinfoToOrder(item);
		//判断当前购物项是否重复，如果重复则添加数量即可
		for (Orderdetail oldOrderdetail : receiptinfo.getOrderdetailSet()) {
			if(oldOrderdetail.getItem().getId().equals(orderdetail.getItem().getId())){
				oldOrderdetail.setoNum(oldOrderdetail.getoNum() + orderdetail.getoNum());
				oldOrderdetail.setoMoney(orderdetail.getoMoney());
				isHave = true;
				break;
			}
		}
		//说明当前购物项  在购物车中是不是存在
		if(!isHave){
			receiptinfo.getOrderdetailSet().add(orderdetail);
		}
		
		return receiptinfo;
	}


	@Override
	public Orderdetail snackinfoToOrder(Item item) {
		Orderdetail orderdetail = new Orderdetail();
		orderdetail.setsName(item.getTitle());
		orderdetail.setoNum(item.getNum());
		orderdetail.setoMoney(item.getPrice()*0.1);
		orderdetail.setItem(item);
		orderdetail.setsId(item.getId());
		return orderdetail;
	}


}
