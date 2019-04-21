package com.mall.bit.cqt.mall.entity;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;

/**
 * 商品
 */
@Data
public class Item extends AbstractBaseEntity {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("卖点")
    private String sellPoint;
    @ApiModelProperty("价格")
    private Long price;
    @ApiModelProperty("库存")
    private Integer num;
    @ApiModelProperty("二维码")
    private String barcode;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("商品详情关联id")
    private Long cid;
    @ApiModelProperty("商品状态")
    private Integer status;

    @Transient
    private String cname;

    @Transient
    private String itemDesc;

}
