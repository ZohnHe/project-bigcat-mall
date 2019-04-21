package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.TbAddress;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/21 10:18
 * @Version 1.0.0
 */
public interface TbAddressService {
    List<TbAddress> selectByUserId(Long userId);
    Integer insertAddress (TbAddress tbAddress);
    List<TbAddress> selectByOrderId(String orderId);
}
