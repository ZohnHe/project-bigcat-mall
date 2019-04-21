package com.mall.bit.cqt.mall.entity;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息类
 * @param <T>
 */
@Data
public class PageInfo<T extends AbstractBaseEntity> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}
