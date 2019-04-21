package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.abstracts.impl.AbstractBaseCrudServiceImpl;
import com.mall.bit.cqt.mall.entity.Order;
import com.mall.bit.cqt.mall.mapper.OrderMapper;
import com.mall.bit.cqt.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderServiceImpl extends AbstractBaseCrudServiceImpl<Order, OrderMapper> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public void save(Order order){
        //添加
        if(order.nowInsert(order)){
            //将当前时间设置为id达到伪随机
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String strId = sdf.format(currentTime);
            Long id = Long.valueOf(strId);
            order.setId(id);
            mapper.add(order);
        }
        //修改
        else {
            mapper.update(order);
        }
    }

}
