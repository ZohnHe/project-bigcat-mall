package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.TbOrderItem;
import com.mall.bit.cqt.mall.mapper.TbOrderItemMapper;
import com.mall.bit.cqt.mall.service.TbOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 16:03
 * @Version 1.0.0
 */
@Service
public class TbOrderItemServiceImpl implements TbOrderItemService {

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public List<TbOrderItem> selectByUserId(Long UserId) {
        return tbOrderItemMapper.selectByUserId(UserId);
    }

    @Override
    public Integer insertOrder(TbOrderItem tbOrderItem) {
        return tbOrderItemMapper.insertOrder(tbOrderItem);
    }

    @Override
    public Integer updateOrder(TbOrderItem tbOrderItem) {
        return tbOrderItemMapper.updateOrder(tbOrderItem);
    }

    @Override
    public List<TbOrderItem> selectByOrderId(String orderId) {
        return tbOrderItemMapper.selectByOrderId(orderId);
    }
}
