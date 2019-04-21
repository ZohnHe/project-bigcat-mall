package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.abstracts.impl.AbstractBaseCrudServiceImpl;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.mapper.UserMapper;
import com.mall.bit.cqt.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 滴滴最可爱
 * @Date: 2019/2/19 14:42
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl extends AbstractBaseCrudServiceImpl<User, UserMapper> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByLoginId(User user) {
        return userMapper.getByLoginId(user);
    }

    @Override
    public Integer add(User user) {
        return userMapper.add(user);
    }

    @Override
    public void update(User user) {
        Date currentData = new Date();
        user.setUpdated(currentData);
        userMapper.update(user);
    }

    @Override
    public void save(User user){
        //添加
        if(user.nowInsert(user)){
            //将当前时间设置为id达到伪随机
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String strId = sdf.format(currentTime);
            Long id = Long.valueOf(strId);
            user.setId(id);
            mapper.add(user);
        }
        //修改
        else {
            mapper.update(user);
        }
    }
}
