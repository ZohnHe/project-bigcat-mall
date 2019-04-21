package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.abstracts.BaseCrudService;
import com.mall.bit.cqt.mall.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemService extends BaseCrudService<Item> {


    /**
     * 查询总记录数
     * @param item
     * @return
     */
    int count(Item item);

    public List<Item> selectList();

    public List<Item> itemJoins(Long id);
}
