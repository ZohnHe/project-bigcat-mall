package com.mall.bit.cqt.mall.entity;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import lombok.Data;

import java.util.Date;

/*
 * 订单实体类
 * @Author wodef
 * @Date 2019-02-20
 * @description
 *
 * */

@Data
public class Order extends AbstractBaseEntity {
    private Long id;               //订单id
    private String orderId;       //订单编号
    private Float payment;        //实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
    private int paymentType;      //支付类型，1、在线支付，2、货到付款
    private Float postFee;        //邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
    private int status;           //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
    private Date createTime;      //订单创建时间
    private Date updateTime;      //订单修改时间
    private Date paymentTime;     //付款时间
    private Date consignTime;     //发货时间
    private Date endTime;         //交易完成时间
    private Date closeTime;       //交易关闭时间
    private String shippingName;  //物流名称
    private String shippingCode;  //物流单号
    private Long userId;          //用户id
    private String buyerMessage;  //买家留言
    private String buyerNick;     //买家昵称
    private int buyerRate;        //买家是否已经评价
}
