package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.TbOrder;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 14:51
 * @Version 1.0.0
 */
public interface TbOrderService {
    List<TbOrder> selectByUserId(Long UserId);
    Integer insertOrder (TbOrder tbOrder);
    Integer updateOrder (TbOrder tbOrder);
}
