package com.mall.bit.cqt.mall.entity;

import lombok.Data;

/**
 *  订单返回实体
 * @Author: 滴滴最可爱
 * @Date: 2019/2/21 20:47
 * @Version 1.0.0
 */
@Data
public class RespOrder {
    private String orderId;
    private String username;
    private Double payMent;
}