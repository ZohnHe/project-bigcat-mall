package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.mapper.UserMapper;
import com.mall.bit.cqt.mall.service.ProfileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Resource
    UserMapper userMapper;
    @Override
    public void update(User user) {
        Date currentData = new Date();
        user.setUpdated(currentData);
        userMapper.update(user);
    }
}