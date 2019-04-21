package com.mall.bit.cqt.mall.entity;

import lombok.Data;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/22 9:40
 * @Version 1.0.0
 */
@Data
public class RespOrderItem {
    private String orderId ;//订单id
    private Integer num      ;//商品购买数量
    private String title    ;//商品标题
    private Long price    ;//商品单价
    private Long totalFee;//商品总金额
    private String picPath ;//商品图片地址
    private String username;
    private String phone;
    private String address;
}