package com.mall.bit.cqt.mall.mapper;


import com.mall.bit.cqt.mall.abstracts.BaseCrudMapper;
import com.mall.bit.cqt.mall.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper extends BaseCrudMapper<Item> {
    List<Item> select(Long id);

    List<Item> selectList();
}
