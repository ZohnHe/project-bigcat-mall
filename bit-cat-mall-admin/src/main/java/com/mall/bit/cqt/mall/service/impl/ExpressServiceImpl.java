package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.Express;
import com.mall.bit.cqt.mall.mapper.ExpressMapper;
import com.mall.bit.cqt.mall.service.ExpressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Resource
    ExpressMapper expressMapper;

    @Override
    public Express getById(int uid) {

        return expressMapper.getById(uid);
    }
}
