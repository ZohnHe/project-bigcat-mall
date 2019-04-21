package com.mall.bit.cqt.mall.entity;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;

@Data
public class Record extends AbstractBaseEntity {
    private String message;
    private int senderId;
    private int receiverId;
    private Date created;

    /**
     * 发送方 名字
     */
    @Transient
    private String senderName;
    /**
     * 接收方 名字
     */
    @Transient
    private String receiverName;

    @Transient
    private String senderImage;
    /**
     * 接收方 名字
     */
    @Transient
    private String receiverImage;
}
