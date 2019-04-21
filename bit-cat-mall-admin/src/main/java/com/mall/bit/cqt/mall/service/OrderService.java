package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.abstracts.BaseCrudService;
import com.mall.bit.cqt.mall.entity.Order;

public interface OrderService extends BaseCrudService<Order> {


    /**
     * 查询总记录数
     * @param order
     * @return
     */
    int count(Order order);

}
