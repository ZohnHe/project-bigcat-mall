package com.mall.bit.cqt.mall.mapper;

import com.mall.bit.cqt.mall.entity.TbAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/21 10:11
 * @Version 1.0.0
 */
@Repository
public interface TbAddressMapper {
    List<TbAddress> selectByUserId(Long UserId);
    Integer insertAddress (TbAddress tbAddress);
    List<TbAddress> selectByOrderId(String orderId);
}
