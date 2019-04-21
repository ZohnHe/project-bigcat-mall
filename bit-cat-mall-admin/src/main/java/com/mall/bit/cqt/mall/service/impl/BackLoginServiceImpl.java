package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.mapper.UserMapper;
import com.mall.bit.cqt.mall.service.BackLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BackLoginServiceImpl implements BackLoginService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public User Login(String loginId) {
        //三种登录方式
        User param = new User();
        param.setPhone(loginId);
        param.setUsername(loginId);
        param.setEmail(loginId);

        User user = userMapper.getByLoginId(param);
        //判断是否登录
        //登录
        if(user != null){
           return user;
        }

        return null;
    }

    @Override
    @Transactional
    public Integer add(User user) {
        Integer ret = userMapper.add(user);
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
