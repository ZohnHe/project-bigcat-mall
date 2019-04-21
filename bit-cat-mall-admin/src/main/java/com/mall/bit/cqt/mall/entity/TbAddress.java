package com.mall.bit.cqt.mall.entity;

import lombok.Data;

/**
 * 收货地址表
 * @Author: 滴滴最可爱
 * @Date: 2019/2/21 10:09
 * @Version 1.0.0
 */
@Data
public class TbAddress {
    private Integer id;
    //用户id
    private Long userId;
    //订单ID
    private String oId;
    //收货人姓名
    private String oUsername;
    //收货人电话
    private String oPhone;
    //收货地址
    private String oAddress;
}
