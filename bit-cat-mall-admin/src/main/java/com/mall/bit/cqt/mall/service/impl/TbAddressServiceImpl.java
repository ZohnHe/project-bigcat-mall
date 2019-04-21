package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.TbAddress;
import com.mall.bit.cqt.mall.mapper.TbAddressMapper;
import com.mall.bit.cqt.mall.service.TbAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/21 10:19
 * @Version 1.0.0
 */
@Service
public class TbAddressServiceImpl implements TbAddressService {

    @Autowired
    private TbAddressMapper tbAddressMapper;

    @Override
    public List<TbAddress> selectByUserId(Long userId) {
        return tbAddressMapper.selectByUserId(userId);
    }

    @Override
    public Integer insertAddress(TbAddress tbAddress) {
        return tbAddressMapper.insertAddress(tbAddress);
    }

    @Override
    public List<TbAddress> selectByOrderId(String orderId) {
        return tbAddressMapper.selectByOrderId(orderId);
    }
}
