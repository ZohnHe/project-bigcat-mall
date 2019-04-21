package com.mall.bit.cqt.mall.service.impl;


import com.mall.bit.cqt.mall.common.Salt;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.mapper.UserMapper;
import com.mall.bit.cqt.mall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public UserMapper userMapper;

    /**
     * 登录
     * @param loginId
     * @param password
     * @return
     */
    @Override
    public User Login(String loginId, String password) {
        //三种登录方式
        User param = new User();
        param.setPhone(loginId);
        param.setUsername(loginId);
        param.setEmail(loginId);

        User user = userMapper.getByLoginId(param);
        if(password.length()==32){
            String cookieMd5= DigestUtils.md5DigestAsHex((password+ Salt.USER_SALT).getBytes());
            if(user.getPassword().equals(cookieMd5)){
                return user;
            }
        }
        //判断是否登录
        //登录
        if(user != null){
//
            String md5PasswordAddSalt = DigestUtils.md5DigestAsHex(password.getBytes());
            md5PasswordAddSalt = DigestUtils.md5DigestAsHex((md5PasswordAddSalt+ Salt.USER_SALT).getBytes());

            if(user.getPassword().equals(md5PasswordAddSalt)){
                return user;
            }

        }

        return null;
    }

    /**
     * 注册
     * @param
     * @return
     */
    @Override
    @Transactional
    public Integer add(String username,String password) {
        User isUser = userMapper.getByUsername(username);
        if(isUser != null){
            return 2;
        }
         else{
            User user = new User();
            String md5PasswordAddSalt = DigestUtils.md5DigestAsHex(password.getBytes());
            md5PasswordAddSalt = md5PasswordAddSalt+ Salt.USER_SALT;
            md5PasswordAddSalt = DigestUtils.md5DigestAsHex(md5PasswordAddSalt.getBytes());

            user.setUsername(username);
            user.setPassword(md5PasswordAddSalt);
            user.setCreated(new Date());
            user.setUpdated(new Date());
        Integer ret = userMapper.add(user);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
        }
    }


}
