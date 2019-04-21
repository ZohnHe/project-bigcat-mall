package com.mall.bit.cqt.mall.entity;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import lombok.Data;

@Data
public class Express extends AbstractBaseEntity {

    private int uid;
    private String expKind;
    private String expNum;
    private String msg;

}
