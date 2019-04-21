package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.TbOrder;
import com.mall.bit.cqt.mall.mapper.TbOrderMapper;
import com.mall.bit.cqt.mall.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 14:52
 * @Version 1.0.0
 */
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public List<TbOrder> selectByUserId(Long UserId) {
        return tbOrderMapper.selectByUserId(UserId);
    }

    @Override
    public Integer insertOrder(TbOrder tbOrder) {
        return tbOrderMapper.insertOrder(tbOrder);
    }

    @Override
    public Integer updateOrder(TbOrder tbOrder) {
        return tbOrderMapper.updateOrder(tbOrder);
    }
}
