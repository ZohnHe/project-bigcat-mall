package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.TbOrderItem;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 16:02
 * @Version 1.0.0
 */
public interface TbOrderItemService {
    List<TbOrderItem> selectByUserId(Long UserId);
    Integer insertOrder (TbOrderItem tbOrderItem);
    Integer updateOrder (TbOrderItem tbOrderItem);
    List<TbOrderItem> selectByOrderId(String orderId);
}
