package com.mall.bit.cqt.mall.mapper;


import com.mall.bit.cqt.mall.entity.TbOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/20 14:37
 * @Version 1.0.0
 */
@Repository
public interface TbOrderMapper {
    List<TbOrder> selectByUserId(Long userId);
    Integer insertOrder (TbOrder tbOrder);
    Integer updateOrder (TbOrder tbOrder);
}
