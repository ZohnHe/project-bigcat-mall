package com.mall.bit.cqt.mall.mapper;

import com.mall.bit.cqt.mall.entity.TbOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 15:34
 ersion 1.0.0
 */
@Repository
public interface TbOrderItemMapper {
    List<TbOrderItem> selectByUserId(Long userId);
    Integer insertOrder (TbOrderItem tbOrderItem);
    Integer updateOrder (TbOrderItem tbOrderItem);
    List<TbOrderItem> selectByOrderId(String orderId);
}
