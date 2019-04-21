package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.abstracts.impl.AbstractBaseCrudServiceImpl;
import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.mapper.ItemMapper;
import com.mall.bit.cqt.mall.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl extends AbstractBaseCrudServiceImpl<Item, ItemMapper> implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public void save(Item item){
        //添加
        if(item.nowInsert(item)){
            //将当前时间设置为id达到伪随机
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String strId = sdf.format(currentTime);
            Long id = Long.valueOf(strId);
            item.setId(id);
            mapper.add(item);
        }
        //修改
        else {
            mapper.update(item);
        }
    }

    @Override
    public List<Item> selectList() {

        List<Item> items = itemMapper.selectList();
        return items;

    }

    @Override
    public List<Item> itemJoins(Long id) {
        List<Item> select = itemMapper.select(id);
        return select;
    }
}
