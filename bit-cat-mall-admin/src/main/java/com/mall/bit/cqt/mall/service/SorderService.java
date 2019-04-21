package com.mall.bit.cqt.mall.service;


import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.Orderdetail;
import com.mall.bit.cqt.mall.entity.Receiptinfo;

public interface SorderService {

	//计算购物总价格
	public Receiptinfo addSorder(Receiptinfo receiptinfo, Item item);

	//商品转化成订单项
	public Orderdetail snackinfoToOrder(Item item);

}
